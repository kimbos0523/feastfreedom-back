package com.feastfreedom.controller;

import com.feastfreedom.dto.request.OrderRequest;
import com.feastfreedom.entity.User;
import com.feastfreedom.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("http://localhost:4200")
@RestController
public class OrderRestController {

    private final OrderService orderService;


    @PostMapping
    public void orderItems(@RequestBody OrderRequest orderRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info(user.toString());
        log.info(orderRequest.toString());
        orderService.saveOrder(orderRequest, user);
    }

}
