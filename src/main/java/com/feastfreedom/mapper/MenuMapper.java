package com.feastfreedom.mapper;

import com.feastfreedom.dto.request.MenuRequest;
import com.feastfreedom.dto.response.MenuResponse;
import com.feastfreedom.entity.Kitchen;
import com.feastfreedom.entity.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public MenuResponse fromMenu(Menu menu) {
        return MenuResponse.builder()
                .id(menu.getId())
                .name(menu.getName())
                .type(menu.getType())
                .price(menu.getPrice())
                .build();
    }

    public Menu fromMenuRequest(MenuRequest menuRequest, Kitchen kitchen) {
        return Menu.builder()
                .name(menuRequest.getName())
                .type(menuRequest.getType())
                .price(menuRequest.getPrice())
                .kitchen(kitchen)
                .build();
    }

}
