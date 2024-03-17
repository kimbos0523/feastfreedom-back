package com.feastfreedom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feastfreedom.enums.FoodType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Column(name = "menu_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "menu_type")
    private FoodType type;

    @Column(name = "menu_price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id", nullable = false)
    @JsonIgnore
    private Kitchen kitchen;

}
