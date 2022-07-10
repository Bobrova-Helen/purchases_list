package com.app.purchases.service;

import com.app.purchases.dto.UserPurchaseDto;
import com.app.purchases.entity.Purchase;
import com.app.purchases.exception.NoSuchPurchaseException;
import com.app.purchases.repository.PurchaseRepository;
import com.app.purchases.service.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseStatsService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    public List<UserPurchaseDto> getLastWeekPurchases(){
        LocalDate weekAgo = LocalDate.now().minusWeeks(1);
        List<Purchase> purchases = purchaseRepository.findAllByPurchaseDateAfter(weekAgo);
        return purchaseMapper.mapToListDtos(purchases);
    }

    public String getMostPurchasedGood(){
        return purchaseRepository.findAll()
                .stream()
                .filter(purchase -> purchase.getPurchaseDate()
                        .isAfter(LocalDate.now().minusMonths(1)))
                .collect(Collectors.groupingBy(
                        purchase -> purchase.getPurchaseItem().name(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchPurchaseException("Purchases list is empty"))
                .getKey();
    }

    public String getUserWithMostPurchases(){
        return purchaseRepository.findAll()
                .stream()
                .filter(purchase -> purchase.getPurchaseDate()
                        .isAfter(LocalDate.now().minusMonths(6)))
                .collect(Collectors.groupingBy(
                        purchase -> purchase.getName() + " " + purchase.getLastname(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchPurchaseException("Purchases list is empty"))
                .getKey();
    }

    public String getMostPurchasedGoodAmongEighteenYearsOldUsers(){
        return purchaseRepository.findAll()
                .stream()
                .filter(purchase -> purchase.getAge() == 18)
                .collect(Collectors.groupingBy(
                        purchase -> purchase.getPurchaseItem().name(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchPurchaseException("Purchases list is empty"))
                .getKey();
    }
}
