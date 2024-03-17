package com.feastfreedom.controller;

import com.feastfreedom.dto.request.AuthenticationRequest;
import com.feastfreedom.dto.request.UserRequest;
import com.feastfreedom.dto.response.AuthenticationResponse;
import com.feastfreedom.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:4200")
@RestController
public class UserRestController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequest userRequest) {
        userService.register(userRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(userService.login(authenticationRequest));
    }

}
