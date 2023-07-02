package com.evonniy.testapi.controller;

import com.evonniy.testapi.model.entity.User;
import com.evonniy.testapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Validated
@RestController
public class PingController {
    private final AuthenticationService authenticationService;

    public PingController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        System.out.println("/ping");
        Optional<User> isUser = authenticationService.getUserFromAuth();
        if (isUser.isEmpty()) {
            return ResponseEntity.ok("Hello from server!");
        } else {
            return ResponseEntity.ok("Hello, " + isUser.get().getUsername() + " from server!");
        }

    }

}
