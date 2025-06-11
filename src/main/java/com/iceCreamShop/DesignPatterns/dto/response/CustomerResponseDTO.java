package com.iceCreamShop.DesignPatterns.dto.response;

import jakarta.validation.constraints.NotNull;

public record CustomerResponseDTO(@NotNull String name) {
}
