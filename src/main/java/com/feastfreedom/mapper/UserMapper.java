package com.feastfreedom.mapper;

import com.feastfreedom.dto.request.KitchenRequest;
import com.feastfreedom.dto.request.UserRequest;
import com.feastfreedom.entity.User;
import com.feastfreedom.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;


    public User fromUserRequest(UserRequest userRequest) {
        log.info(userRequest.toString());
        return User.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .confirmPassword(passwordEncoder.encode(userRequest.getConfirmPassword()))
                .name(userRequest.getFirstName() + " " + userRequest.getLastName())
                .role(Role.REGULAR)
                .build();
    }

    public User fromKitchenRequest(KitchenRequest kitchenRequest) {
        return User.builder()
                .email(kitchenRequest.getEmail())
                .password(passwordEncoder.encode(kitchenRequest.getPassword()))
                .confirmPassword(passwordEncoder.encode(kitchenRequest.getConfirmPassword()))
                .name(kitchenRequest.getServicedProviderName())
                .role(Role.PROVIDER)
                .build();
    }

}
