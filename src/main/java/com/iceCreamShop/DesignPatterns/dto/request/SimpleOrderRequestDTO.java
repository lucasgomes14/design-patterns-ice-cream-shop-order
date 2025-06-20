package com.iceCreamShop.DesignPatterns.dto.request;

import com.iceCreamShop.DesignPatterns.dto.IceCreamSimpleDTO;
import com.iceCreamShop.DesignPatterns.model.DiscountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SimpleOrderRequestDTO(@NotNull @NotEmpty (message = "'CustomeName' cannot be empty") String customerName,
                                    @NotNull List<IceCreamSimpleDTO> iceCreams,
                                    @NotNull DiscountType discountType) {
}
