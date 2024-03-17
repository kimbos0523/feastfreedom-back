package com.feastfreedom.service;

import com.feastfreedom.dto.request.AuthenticationRequest;
import com.feastfreedom.dto.request.UserRequest;
import com.feastfreedom.dto.response.AuthenticationResponse;
import com.feastfreedom.entity.SecurityCheck;
import com.feastfreedom.entity.User;
import com.feastfreedom.mapper.SecurityCheckMapper;
import com.feastfreedom.mapper.UserMapper;
import com.feastfreedom.repository.SecurityCheckRepository;
import com.feastfreedom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SecurityCheckRepository securityCheckRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final SecurityCheckMapper securityCheckMapper;


    public void register(UserRequest userRequest) {
        User user = userMapper.fromUserRequest(userRequest);
        User savedUser = userRepository.save(user);
        SecurityCheck securityCheck = securityCheckMapper.fromUserRequest(userRequest, savedUser);
        securityCheckRepository.save(securityCheck);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .name(user.getName())
                .build();
    }

}
