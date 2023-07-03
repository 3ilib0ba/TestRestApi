package com.evonniy.testapi.controller;

import com.evonniy.testapi.model.dto.RegisterOrganizatorDto;
import com.evonniy.testapi.model.dto.RegisterUserDto;
import com.evonniy.testapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping(value = "/register/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserDto request
    ) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping(value = "/register/organizator",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerOrganizator(
            @Valid @RequestBody RegisterOrganizatorDto request
    ) {
        return ResponseEntity.ok(authService.registerOrganizator(request));
    }

}
