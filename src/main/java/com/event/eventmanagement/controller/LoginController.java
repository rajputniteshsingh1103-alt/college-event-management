package com.event.eventmanagement.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @PostMapping("/doLogin")
    public String doLogin(@RequestBody Map<String, String> body) {
        String email = body.get("userEmail");
        String password = body.get("password");

        if ("student@gmail.com".equals(email) && "student123".equals(password)) {
            return "student";
        } else if ("teacher@gmail.com".equals(email) && "teacher123".equals(password)) {
            return "teacher";
        } else if ("admin@gmail.com".equals(email) && "admin123".equals(password)) {
            return "admin";
        } else {
            return "invalid";
        }
    }
}
