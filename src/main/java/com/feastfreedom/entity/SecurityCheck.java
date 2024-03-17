package com.feastfreedom.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "security_check")
@Entity
public class SecurityCheck extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_id")
    private Long id;
    @Column(name = "security_question1")
    private String securityQuestion1;
    @Column(name = "security_answer1")
    private String securityAnswer1;
    @Column(name = "security_question2")
    private String securityQuestion2;
    @Column(name = "security_answer2")
    private String securityAnswer2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}