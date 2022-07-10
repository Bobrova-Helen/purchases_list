package com.app.purchases.entity;

import com.app.purchases.entity.enums.PurchaseItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private PurchaseItem purchaseItem;

    private int count;

    private BigDecimal amount;

    private LocalDate purchaseDate;
}
