package com.iceCreamShop.DesignPatterns.dto;

import com.iceCreamShop.DesignPatterns.factory.TypeIceCream;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record IceCreamComplexDTO(@NotNull TypeIceCream iceCreamType,
                                 @NotNull String flavor,
                                 @NotNull Boolean syrup,
                                 @NotNull Boolean whipped,
                                 @Min(1) int quantity) {
}
