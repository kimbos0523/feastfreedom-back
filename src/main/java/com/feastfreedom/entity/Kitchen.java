package com.feastfreedom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kitchen")
@Entity
public class Kitchen extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_id")
    private Long id;
    @ElementCollection
    private List<DayOfWeek> workingDays;
    @Column(name = "kitchen_start_time")
    private LocalTime startTime;
    @Column(name = "kitchen_end_time")
    private LocalTime endTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "kitchen", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Menu> menus;
    @OneToOne(mappedBy = "kitchen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UploadFile file;
}
