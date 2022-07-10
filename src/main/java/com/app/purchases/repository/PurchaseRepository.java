package com.app.purchases.repository;

import com.app.purchases.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByPurchaseDateAfter(LocalDate date);
}
