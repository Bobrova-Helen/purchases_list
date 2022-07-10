package com.app.purchases.service.mapper;

import com.app.purchases.dto.UserPurchaseDto;
import com.app.purchases.entity.Purchase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseMapper {

    public List<UserPurchaseDto> mapToListDtos(List<Purchase> purchases){
        return purchases
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Purchase mapToObject(UserPurchaseDto dto){
        Purchase purchase = new Purchase();
        purchase.setName(dto.getName());
        purchase.setLastname(dto.getLastname());
        purchase.setAge(dto.getAge());
        purchase.setPurchaseItem(dto.getPurchaseItem());
        purchase.setCount(dto.getCount());
        purchase.setAmount(dto.getAmount());
        purchase.setPurchaseDate(dto.getPurchaseDate());
        return purchase;
    }

    public UserPurchaseDto mapToDto(Purchase purchase){
        UserPurchaseDto dto = new UserPurchaseDto();
        dto.setName(purchase.getName());
        dto.setLastname(purchase.getLastname());
        dto.setAge(purchase.getAge());
        dto.setPurchaseItem(purchase.getPurchaseItem());
        dto.setCount(purchase.getCount());
        dto.setAmount(purchase.getAmount());
        dto.setPurchaseDate(purchase.getPurchaseDate());
        return dto;
    }

    public UserPurchaseDto fromString(String purchase){
        try {
            return new XmlMapper().readValue(purchase, UserPurchaseDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
