package com.feastfreedom.mapper;

import com.feastfreedom.dto.request.UserRequest;
import com.feastfreedom.entity.SecurityCheck;
import com.feastfreedom.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SecurityCheckMapper {

    public SecurityCheck fromUserRequest(UserRequest userRequest, User user) {
        return SecurityCheck.builder()
                .securityQuestion1(userRequest.getSecurityQuestion1())
                .securityAnswer1(userRequest.getSecurityAnswer1())
                .securityQuestion2(userRequest.getSecurityQuestion2())
                .securityAnswer2(userRequest.getSecurityAnswer2())
                .user(user)
                .build();
    }
}
