package com.app.purchases.service;

import com.app.purchases.dto.UserPurchaseDto;

import java.util.List;

public interface PurchaseService {
    List<UserPurchaseDto> getAllPurchases();
    void savePurchase(String purchase);

    UserPurchaseDto getPurchaseById(Long id);

    void updatePurchaseById(Long id, String purchase);

    void deletePurchaseById(Long id);
}
