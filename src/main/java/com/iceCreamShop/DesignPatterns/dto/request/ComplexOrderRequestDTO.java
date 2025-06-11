package com.iceCreamShop.DesignPatterns.dto.request;

import com.iceCreamShop.DesignPatterns.dto.IceCreamDTO;
import com.iceCreamShop.DesignPatterns.model.DiscountType;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ComplexOrderRequestDTO(@NotNull String customerName,
                                     @NotNull List<IceCreamDTO> iceCreams,
                                     @NotNull Boolean addSyrup,
                                     @NotNull Boolean addWhippedCream,
                                     @NotNull DiscountType discountType) {
}
