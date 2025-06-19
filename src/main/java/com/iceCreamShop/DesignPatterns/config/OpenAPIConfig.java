package com.iceCreamShop.DesignPatterns.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API IceCream Shop")
                        .version("1.0")
                        .description("API tem a responsabilidade de gerenciar a sorveteria.\n\n" +
                                "Endpoints:\n" +
                                "- `Cancel`: cancela pedido a partir do ID;\n" +
                                "- `Simple`: cria pedidos simples de sorvete. Requer:\n" +
                                "    • Nome do cliente,\n" +
                                "    • Lista com tipo de sorvete (`MILKSHAKE`, `POPSICLE`, `SCOOP_ICE_CREAM`),\n" +
                                "    • String sabor,\n" +
                                "    • Inteiro com quantidade,\n" +
                                "    • Tipo de desconto (`NONE`, `FREQUENT`, `SEASONAL`).\n" +
                                "- `Complex`: cria pedidos complexos. Requer os mesmos dados do Simple, mas também:\n" +
                                "    • Boolean para adicional de cobertura,\n" +
                                "    • Boolean para adicional de chantilly.\n" +
                                "- `Advanced status`: avança o status do pedido no início da fila;\n" +
                                "- `Orders`: retorna a lista de todos os pedidos feitos;\n" +
                                "- `Order`: retorna o pedido específico a partir do ID;\n" +
                                "- `Queue`: retorna a fila de pedidos;\n" +
                                "- `Customer`: retorna a lista de clientes que fizeram pedidos."));
    }
}