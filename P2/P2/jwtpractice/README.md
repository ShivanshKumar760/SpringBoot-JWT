# jwtpractice (P2) — Simple JWT Authentication (Spring Security)

This project is a minimal JWT-based authentication setup using Spring Boot + Spring Security + JJWT.

## Routes

### Public (no token required)

- `POST /api/v1/auth/signup`
  - Creates a new user in H2 DB (password is BCrypt-hashed).
- `POST /api/v1/auth/login`
  - Verifies username/password via Spring Security `AuthenticationManager`, then returns a JWT.
- `GET /api/v1/auth/test`
  - Public test endpoint (because it lives under `/api/v1/auth/**` which is permitted).
- `GET /h2-console/**`
  - H2 console (special-cased to bypass the JWT filter).

### Protected (token required)

- `GET /test`
  - Requires `Authorization: Bearer <token>`.

## Request processing (what file runs first?)

For **every request**:

1. Spring Security **filter chain** runs first (configured in `Config/SecurityConfig.java`).
2. Your `Filter/JwtAuthFilter.java` runs **before** Spring’s `UsernamePasswordAuthenticationFilter`.
3. If the request is allowed/authenticated, Spring MVC routes to the controller method.

## Endpoint-wise file flow (route → controller → service → repo → util)

### `POST /api/v1/auth/signup`

```
Client
  -> SecurityConfig.java  (filter chain)
     -> JwtAuthFilter.java  (passes through; no Bearer token required)
  -> UserController.java  (/api/v1/auth/signup)
     -> UserService.java (registerUser)
        -> UserRepository.java (findByUsername, save)
        -> toEntity.java (BCrypt-hash password, map DTO -> Entity)
        -> toResponse.java (Entity -> RegisterResponseDTO)
  -> Response: RegisterResponseDTO
```

Related files:

- Controller: `Controller/UserController.java`
- Service: `service/UserService.java`
- Repository: `Repo/UserRepository.java`
- Entity/DTO/Mapper: `Entity/User.java`, `dto/RegisterDTO.java`, `dto/RegisterResponseDTO.java`, `Mapper/toEntity.java`, `Mapper/toResponse.java`

---

### `POST /api/v1/auth/login`

```
Client (username/password JSON)
  -> SecurityConfig.java (filter chain)
     -> JwtAuthFilter.java (passes through; login itself is public)
  -> UserController.java (/api/v1/auth/login)
     -> AuthService.java (login)
        -> AuthenticationManager.authenticate(...)
           -> (Spring Security internal auth provider chain)
              -> UserService.java (loadUserByUsername)
                 -> UserRepository.java (findByUsername)
              -> PasswordEncoder (BCrypt) verifies password vs stored hash
     -> JwtUtil.java (generateToken)
     -> toResponse.java (token -> AuthResponseDTO)
  -> Response: AuthResponseDTO { token }
```

Related files:

- Controller: `Controller/UserController.java`
- Service: `service/AuthService.java`, `service/UserService.java`
- JWT util: `util/JwtUtil.java`
- DTO/Mapper: `dto/LoginRequestDTO.java`, `dto/AuthResponseDTO.java`, `Mapper/toResponse.java`

---

### `GET /test` (protected)

```
Client (Authorization: Bearer <jwt>)
  -> SecurityConfig.java (filter chain)
     -> JwtAuthFilter.java
        -> JwtUtil.java (validateToken, extractUsername)
        -> UserService.java (loadUserByUsername)
           -> UserRepository.java (findByUsername)
        -> SecurityContextHolder setAuthentication(...)
  -> TestController.java (/test)
  -> Response: "Authenticated successfully!"
```

Related files:

- Controller: `Controller/TestController.java`
- Filter: `Filter/JwtAuthFilter.java`
- Services/Repo: `service/UserService.java`, `Repo/UserRepository.java`
- JWT util: `util/JwtUtil.java`

---

### `GET /api/v1/auth/test` (public)

```
Client
  -> SecurityConfig.java (filter chain)
     -> JwtAuthFilter.java (passes through; endpoint is permitted)
  -> UserController.java (/api/v1/auth/test)
  -> Response: "Authenticated successfully!"
```

## ASCII authentication flow diagram (JWT)

### A) Login (token issuance)

```
POST /api/v1/auth/login
  |
  v
UserController.login()
  |
  v
AuthService.login()
  |
  v
AuthenticationManager.authenticate(UsernamePasswordAuthenticationToken)
  |
  +--> UserService.loadUserByUsername()
  |       |
  |       +--> UserRepository.findByUsername()
  |
  +--> PasswordEncoder (BCrypt) matches password
  |
  v
JwtUtil.generateToken(username)
  |
  v
Return token (AuthResponseDTO)
```

### B) Accessing a protected route (token validation)

```
GET /test  (Authorization: Bearer <token>)
  |
  v
JwtAuthFilter.doFilterInternal()
  |
  +--> JwtUtil.validateToken()
  +--> JwtUtil.extractUsername()
  +--> UserService.loadUserByUsername()
  |       |
  |       +--> UserRepository.findByUsername()
  |
  +--> SecurityContextHolder.setAuthentication(...)
  |
  v
Controller executes (TestController.checkAuth)
```

## Which files depend on which? (creation order guide)

If you’re building this project from scratch, a typical creation order is:

1. **`Entity/User.java`**
2. **`Repo/UserRepository.java`** (depends on `User`)
3. **DTOs**: `dto/RegisterDTO.java`, `dto/RegisterResponseDTO.java`, `dto/LoginRequestDTO.java`, `dto/AuthResponseDTO.java`
4. **Mappers**: `Mapper/toEntity.java`, `Mapper/toResponse.java` (depend on DTOs + Entity)
5. **`service/UserService.java`** (depends on Repository + DTOs + Entity; implements `UserDetailsService`)
6. **`util/JwtUtil.java`** (token generate/validate)
7. **`service/AuthService.java`** (depends on `AuthenticationManager` + login DTO)
8. **`Filter/JwtAuthFilter.java`** (depends on `UserService` + `JwtUtil`)
9. **`Config/SecurityConfig.java`** (depends on `JwtAuthFilter`, defines routes security + filter chain)
10. **Controllers**:
    - `Controller/UserController.java` (depends on `AuthService`, `UserService`, `JwtUtil`)
    - `Controller/TestController.java` (simple protected endpoint)
11. **`JwtpracticeApplication.java`** (bootstraps the app)

## Notes / current behavior

- `SecurityConfig.java` permits `/api/v1/auth/**` and `/h2-console/**`, and requires auth for all other routes.
- `JwtAuthFilter.java` only authenticates a request if it sees `Authorization: Bearer ...` and the token validates.
- Passwords are stored BCrypt-hashed (`Mapper/toEntity.java`), and login verification is done by Spring Security using the configured `PasswordEncoder`.

