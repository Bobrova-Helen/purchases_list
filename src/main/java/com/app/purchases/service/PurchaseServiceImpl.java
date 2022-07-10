package com.app.purchases.service;

import com.app.purchases.dto.UserPurchaseDto;
import com.app.purchases.entity.Purchase;
import com.app.purchases.exception.NoSuchPurchaseException;
import com.app.purchases.exception.XsdValidationException;
import com.app.purchases.repository.PurchaseRepository;
import com.app.purchases.service.mapper.PurchaseMapper;
import com.app.purchases.service.validator.XsdValidator;
import com.app.purchases.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final XsdValidator validator;

    @Override
    public List<UserPurchaseDto> getAllPurchases() {
        return purchaseMapper.mapToListDtos(purchaseRepository.findAll());
    }

    @Override
    public UserPurchaseDto getPurchaseById(Long id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchPurchaseException(
                        String.format("Purchase with id='%d' not found", id)));
        return purchaseMapper.mapToDto(purchase);
    }

    @Override
    public void savePurchase(String purchase) {
        File scheme = FileUtils.getXsdScheme();
        File input = FileUtils.writeAndGetXmlInputFile(changeDateFormat(purchase));
        Purchase entity = parsePurchaseDtoFromXml(purchase);
        purchaseRepository.save(entity);
    }
    @Override
    public void updatePurchaseById(Long id, String purchaseXml) {
        purchaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchPurchaseException(
                        String.format("Purchase with id='%d' not found", id)));

        Purchase purchase = parsePurchaseDtoFromXml(purchaseXml);
        purchase.setId(id);
        purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchaseById(Long id) {
        if(purchaseRepository.existsById(id)){
            purchaseRepository.deleteById(id);
        }
        throw new NoSuchPurchaseException(String.format("Purchase with id='%d' not found", id));
    }

    private Purchase parsePurchaseDtoFromXml(String purchase){
        File scheme = FileUtils.getXsdScheme();
        File input = FileUtils.writeAndGetXmlInputFile(changeDateFormat(purchase));
        if(validator.validate(input, scheme)){
            UserPurchaseDto dto = purchaseMapper.fromString(purchase);
            return purchaseMapper.mapToObject(dto);
        }
        throw new XsdValidationException("Incorrect XML input");
    }

    private String changeDateFormat(String purchase) {
        int dateBeginIndex = purchase.indexOf("<purchase_date>");
        int dateEndIndex = purchase.indexOf("</purchase_date>");
        if(dateBeginIndex == -1 || dateEndIndex == -1){
            return purchase;
        }
        LocalDate date = LocalDate.parse(
                purchase.substring(dateBeginIndex, dateEndIndex).replace("<purchase_date>", ""),
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String correctDate = date.toString();
        String inputDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return purchase.replace(inputDate, correctDate);
    }
}
