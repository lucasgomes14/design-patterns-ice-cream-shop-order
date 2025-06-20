# 📘 Projeto Web API

Este é um projeto de API RESTful desenvolvido com **Spring Boot**, documentado com **Swagger (OpenAPI)** e utilizando **Lombok** para reduzir a verbosidade do código Java.

## 🚀 Tecnologias Utilizadas

- Java 21+
- Spring Boot
- Spring Web
- Spring Data JPA
- Lombok
- Swagger/OpenAPI (via springdoc-openapi)
- Banco de dados: Postgres

---

## 📑 Documentação da API

A documentação interativa da API é gerada automaticamente com o **Swagger** e pode ser acessada após rodar a aplicação:

📍 Acesse: `http://localhost:8080/swagger-ui/index.html#/`

Essa interface permite testar todos os endpoints disponíveis, visualizar os parâmetros esperados e entender melhor o funcionamento da API.

---

## ⚠️ Requisitos para Rodar o Projeto

### 1. Java

É necessário ter o **Java 21 ou superior** instalado na sua máquina.

### 2. Lombok Plugin

Este projeto utiliza **Lombok**, uma biblioteca que gera automaticamente código repetitivo como getters, setters, construtores, etc., em tempo de compilação.

> 💡 Para que o Lombok funcione corretamente, é **obrigatório instalar o plugin do Lombok** no seu IDE.

#### Instruções para instalação do plugin:

- **IntelliJ IDEA**:
    - Vá em `File > Settings > Plugins`
    - Pesquise por `Lombok` e clique em **Install**
    - Reinicie a IDE
    - Verifique se a opção *Enable annotation processing* está ativada:  
      `File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Design Patterns > 'Obtain processors from project classpath'`

- **Eclipse**:
    - Baixe o jar do site oficial: https://projectlombok.org/download
    - Execute: `java -jar lombok.jar` e selecione o diretório de instalação do Eclipse
    - Reinicie o Eclipse

---

## 🧪 Rodando o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/lucasgomes14/design-patterns-ice-cream-shop-order.git
