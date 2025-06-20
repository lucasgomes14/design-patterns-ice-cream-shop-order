package com.iceCreamShop.DesignPatterns.dto;

import com.iceCreamShop.DesignPatterns.factory.TypeIceCream;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IceCreamComplexDTO(@NotNull TypeIceCream iceCreamType,
                                 @NotNull @NotEmpty String flavor,
                                 @NotNull Boolean syrup,
                                 @NotNull Boolean whipped,
                                 @Min(1) int quantity) {
}
