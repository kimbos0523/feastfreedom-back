package com.feastfreedom.controller;

import com.feastfreedom.dto.request.KitchenRequest;
import com.feastfreedom.dto.response.KitchenResponse;
import com.feastfreedom.entity.Kitchen;
import com.feastfreedom.service.FileService;
import com.feastfreedom.service.KitchenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/kitchens")
@CrossOrigin("http://localhost:4200")
@RestController
public class KitchenRestController {

    private final KitchenService kitchenService;
    private final FileService fileService;


    @PostMapping
    public void saveKitchen(@RequestBody KitchenRequest kitchenRequest) throws IOException {
        Kitchen kitchen = kitchenService.saveKitchenRequest(kitchenRequest);
        fileService.storeFile(kitchenRequest.getKitchenImageBase64(), kitchen);
    }

    @GetMapping
    public List<KitchenResponse> getKitchens() {
        return kitchenService.getKitchenResponses();
    }

}
