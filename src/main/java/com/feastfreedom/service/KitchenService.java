package com.feastfreedom.service;

import com.feastfreedom.dto.request.KitchenRequest;
import com.feastfreedom.dto.response.KitchenResponse;
import com.feastfreedom.entity.Kitchen;
import com.feastfreedom.entity.User;
import com.feastfreedom.mapper.KitchenMapper;
import com.feastfreedom.mapper.MenuMapper;
import com.feastfreedom.mapper.UserMapper;
import com.feastfreedom.repository.KitchenRepository;
import com.feastfreedom.repository.MenuRepository;
import com.feastfreedom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final KitchenMapper kitchenMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;


    public List<KitchenResponse> getKitchenResponses() {
        return kitchenRepository.findAll()
                .stream()
                .map(kitchen -> {
                    try {
                        return kitchenMapper.fromKitchen(kitchen);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Save the kitchen request.
     * In the kitchen request there are 3 information
     * 1. user info
     * 2. kitchen info
     * 3. menus (multiple menu) info
     *
     * @param kitchenRequest All the elements from the user to save the kitchen info
     */
    public Kitchen saveKitchenRequest(KitchenRequest kitchenRequest) {
        User user = userMapper.fromKitchenRequest(kitchenRequest);
        userRepository.save(user);
        Kitchen kitchen = kitchenMapper.fromKitchenRequest(kitchenRequest, user);
        Kitchen savedKitchen = kitchenRepository.save(kitchen);
        menuRepository.saveAll(kitchenRequest.getMenus()
                .stream()
                .map(menuRequest -> menuMapper.fromMenuRequest(menuRequest, kitchen))
                .collect(Collectors.toList()));
        return kitchenRepository.findByIdFromDatabase(savedKitchen.getId());
    }
}
