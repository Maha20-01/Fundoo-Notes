package com.example.fundoonote.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String securedEndpoint() {
        return "You are authenticated ✅";
    }
}