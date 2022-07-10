package com.app.purchases.controller;

import com.app.purchases.dto.UserPurchaseDto;
import com.app.purchases.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
@Tag(name = "Purchase API", description = "operations with user's purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "Get all the purchases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found purchases"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "No access rights for endpoint")})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<UserPurchaseDto> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @Operation(summary = "Get a purchase by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a purchase by ID"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "No access rights for endpoint"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public UserPurchaseDto getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    @Operation(summary = "Save a new purchase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved purchase"),
            @ApiResponse(responseCode = "400", description = "Wrong XML format"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "No access rights for endpoint")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void savePurchase(@RequestBody String body) {
        purchaseService.savePurchase(body);
    }

    @Operation(summary = "Update an existing purchase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated purchase"),
            @ApiResponse(responseCode = "400", description = "Wrong XML format"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "No access rights for endpoint"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void updatePurchaseById(@PathVariable Long id, @RequestBody String body) {
        purchaseService.updatePurchaseById(id, body);
    }

    @Operation(summary = "Delete an existing purchase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted purchase"),
            @ApiResponse(responseCode = "400", description = "Wrong XML format"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "No access rights for endpoint"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePurchaseById(@PathVariable Long id){
        purchaseService.deletePurchaseById(id);
    }
}