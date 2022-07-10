package com.app.purchases.dto;

import com.app.purchases.entity.enums.PurchaseItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@JsonRootName("purchase")
public class UserPurchaseDto {
    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "lastname")
    private String lastname;

    @JacksonXmlProperty(localName = "age")
    private int age;

    private PurchaseItem purchaseItem;

    @JacksonXmlProperty(localName = "count")
    private int count;

    private BigDecimal amount;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate purchaseDate;

    @JacksonXmlProperty(localName = "purchase_item")
    public void setPurchaseItem(String purchaseItem){
        this.purchaseItem = PurchaseItem.of(purchaseItem);
    }

    @JacksonXmlProperty(localName = "amount")
    public void setAmount(String amount){
        this.amount = new BigDecimal(amount);
    }

    @JacksonXmlProperty(localName = "purchase_date")
    public void setPurchaseDate(String date){
        this.purchaseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
