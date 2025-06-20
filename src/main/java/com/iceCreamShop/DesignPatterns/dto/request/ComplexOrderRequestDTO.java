package com.iceCreamShop.DesignPatterns.dto.request;

import com.iceCreamShop.DesignPatterns.dto.IceCreamComplexDTO;
import com.iceCreamShop.DesignPatterns.model.DiscountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ComplexOrderRequestDTO(@NotNull @NotEmpty (message = "'CustomeName' cannot be empty") String customerName,
                                     @NotNull List<IceCreamComplexDTO> iceCreams,
                                     @NotNull Boolean addSyrup,
                                     @NotNull Boolean addWhippedCream,
                                     @NotNull DiscountType discountType) {
}
