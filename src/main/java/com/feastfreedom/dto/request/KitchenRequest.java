package com.feastfreedom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KitchenRequest {

    private String servicedProviderName;
    private String email;
    private String password;
    private String confirmPassword;
    private List<DayOfWeek> workingDays;
    private String startTime;
    private String endTime;
    private String kitchenImageBase64;
    private List<MenuRequest> menus;

}
