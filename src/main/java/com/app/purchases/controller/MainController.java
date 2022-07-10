package com.app.purchases.controller;

import com.app.purchases.service.PurchaseStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class MainController {
    private final PurchaseStatsService purchaseStatsService;

    @GetMapping
    public String getHomePage(){
        return "main";
    }

    @GetMapping("/last_week_purchases")
    public String getLastWeekPurchases(Model model){
        model.addAttribute("purchases", purchaseStatsService.getLastWeekPurchases());
        return "last_week_purchases";
    }

    @GetMapping("/most_purchased_good")
    public String getMostPurchasedGood(Model model){
        model.addAttribute("good", purchaseStatsService.getMostPurchasedGood());
        return "most_purchased_good";
    }

    @GetMapping("/user_with_most_purchases")
    public String getUserWithMostPurchases(Model model){
        String fullName = purchaseStatsService.getUserWithMostPurchases();
        String[] nameParts = fullName.split(" ");
        model.addAttribute("name", nameParts[0]);
        model.addAttribute("lastname", nameParts[1]);
        return "user_with_most_purchases";
    }

    @GetMapping("/most_purchased_good_among_18yo")
    public String getMostPurchasedGoodAmongEighteenYearsOldUsers(Model model){
        model.addAttribute("good", purchaseStatsService.getMostPurchasedGoodAmongEighteenYearsOldUsers());
        return "most_purchased_good_among_18yo";
    }
}
