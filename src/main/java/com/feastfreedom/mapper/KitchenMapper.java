package com.feastfreedom.mapper;

import com.feastfreedom.dto.request.KitchenRequest;
import com.feastfreedom.dto.response.KitchenResponse;
import com.feastfreedom.entity.Kitchen;
import com.feastfreedom.entity.User;
import com.feastfreedom.repository.MenuRepository;
import com.feastfreedom.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class KitchenMapper {

    private final MenuRepository menuRepository;
    private final FileService fileService;
    private final MenuMapper menuMapper;


    public Kitchen fromKitchenRequest(KitchenRequest kitchenRequest, User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return Kitchen.builder()
                .workingDays(kitchenRequest.getWorkingDays())
                .startTime(LocalTime.parse(kitchenRequest.getStartTime(), formatter))
                .endTime(LocalTime.parse(kitchenRequest.getEndTime(), formatter))
                .user(user)
                .build();
    }

    public KitchenResponse fromKitchen(Kitchen kitchen) throws IOException {
        return KitchenResponse.builder()
                .id(kitchen.getId())
                .name(kitchen.getUser().getName())
                .workingDays(kitchen.getWorkingDays())
                .startTime(kitchen.getStartTime())
                .endTime(kitchen.getEndTime())
                .menus(
                        menuRepository.findByKitchenId(kitchen.getId())
                                .stream()
                                .map(menuMapper::fromMenu)
                                .collect(Collectors.toList()))
                .kitchenImageBase64(fileService.getFileAsBase64(fileService.findByKitchenId(kitchen.getId()).getStoreFileName()))
                .build();
    }

}
