package com.iceCreamShop.DesignPatterns.dto;

import com.iceCreamShop.DesignPatterns.factory.TypeIceCream;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record IceCreamSimpleDTO(@NotNull TypeIceCream iceCreamType,
                                 @NotNull String flavor,
                                 @Min(1) int quantity) {
}
