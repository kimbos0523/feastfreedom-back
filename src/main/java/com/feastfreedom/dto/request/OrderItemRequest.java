package com.feastfreedom.dto.request;

import com.feastfreedom.enums.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Long id;
    private String name;
    private FoodType type;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal cost;

}
