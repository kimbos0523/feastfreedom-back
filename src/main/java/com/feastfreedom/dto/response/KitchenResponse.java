package com.feastfreedom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KitchenResponse {

    private Long id;
    private String name;
    private List<DayOfWeek> workingDays;
    private LocalTime startTime;
    private LocalTime endTime;
    private String kitchenImageBase64;
    private List<MenuResponse> menus;
}
