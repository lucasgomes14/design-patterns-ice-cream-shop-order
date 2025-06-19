package com.iceCreamShop.DesignPatterns.controller;

import com.iceCreamShop.DesignPatterns.dto.request.ComplexOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.request.SimpleOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.response.CustomerResponseDTO;
import com.iceCreamShop.DesignPatterns.dto.response.OrderResponseDTO;
import com.iceCreamShop.DesignPatterns.exception.IceCreamTypeNotSupportedException;
import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderController {
    @Operation(
            summary = "Criar um pedido simples",
            description = "Recebe por parâmetro nome do cliente, tipo de sorvete(MILKSHAKE, POPSICLE, SCOOP_ICE_CREAM) e sabor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido anotado"),
            @ApiResponse(responseCode = "400", description = "Erro ao fazer pedido"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @PostMapping("/simple")
    ResponseEntity<Void> createSimpleOrder(@RequestBody @Valid SimpleOrderRequestDTO request) throws OrderStateException, IceCreamTypeNotSupportedException;

    @Operation(
            summary = "Criar um pedido complexo",
            description = "Recebe por parâmetro nome do cliente, tipo de sorvete(MILKSHAKE, POPSICLE, SCOOP_ICE_CREAM), sabor, calda, chantilly, se é frequente ou não"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido anotado"),
            @ApiResponse(responseCode = "400", description = "Erro ao fazer pedido"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @PostMapping("/complex")
    ResponseEntity<Void> createComplexOrder(@RequestBody @Valid ComplexOrderRequestDTO request) throws OrderStateException, IceCreamTypeNotSupportedException;

    @Operation(
            summary = "Avançar status de um pedido",
            description = "Modifica o status do pedido que está em primeiro na fila"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status avançou"),
            @ApiResponse(responseCode = "400", description = "Erro ao avançar status"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @PostMapping("/advance-status")
    ResponseEntity<Void> advanceOrderStatus() throws OrderStateException;

    @Operation(
            summary = "Cancelar um pedido",
            description = "Cancela pedido a partir do ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido cancelado"),
            @ApiResponse(responseCode = "400", description = "Erro ao cancelar pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @PostMapping("/{orderId}/cancel")
    ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) throws OrderStateException;

    @Operation(
            summary = "Listar todos os pedidos",
            description = "Faz uma listagem de todos os pedidos cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a listagem"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar pedido"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @GetMapping
    ResponseEntity<List<OrderResponseDTO>> getAllOrders();

    @Operation(
            summary = "Obter detalhes de um pedido específico",
            description = "A partir do ID tem a listagem desse pedido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna pedido"),
            @ApiResponse(responseCode = "400", description = "Erro ao encontra pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @GetMapping("/{orderId}")
    ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId);

    @Operation(
            summary = "Obter a fila de pedidos",
            description = "Fila de pedidos é retornado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna da fila de pedidos"),
            @ApiResponse(responseCode = "400", description = "Erro ao retorna fila de pedidos"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @GetMapping("/queue")
    ResponseEntity<List<OrderResponseDTO>> getOrdersInQueue();

    @Operation(
            summary = "Obter os clientes dos pedidos",
            description = "todos os clientes que fizeram pedidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os clientes"),
            @ApiResponse(responseCode = "400", description = "Erro ao retorna clientes"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @GetMapping("/customer")
    ResponseEntity<List<CustomerResponseDTO>> getConsumers();
}
