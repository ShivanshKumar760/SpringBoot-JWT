package com.ptwo.jwtpractice.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController

public class TestController {
    @GetMapping("/test")
    public String checkAuth() {
        return "Authenticated successfully!";
    }
        
}
