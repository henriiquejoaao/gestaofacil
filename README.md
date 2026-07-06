# 🏪 Gestão Fácil - Store Management API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Status](https://img.shields.io/badge/status-in%20development-yellow)

## 🇧🇷 Sobre o Projeto

API REST desenvolvida para gerenciamento de uma loja familiar.

O sistema tem como objetivo substituir controles manuais de produtos, fornecedores e estoque por uma solução organizada, escalável e de fácil manutenção.

A loja trabalha com diferentes tipos de produtos, como brinquedos, decoração, roupas, acessórios para celular e utilidades diversas.

O projeto foi construído utilizando **Java 21** e **Spring Boot**, seguindo arquitetura em camadas (Controller → Service → Repository), aplicando boas práticas de desenvolvimento backend.

Este projeto tem como objetivo consolidar conhecimentos em:

* Desenvolvimento de APIs REST
* Separação de responsabilidades
* Organização profissional de código
* Integração com banco de dados relacional
* Uso de DTOs para entrada e saída de dados
* Implementação de regras de negócio no backend
* Controle de estoque e movimentações

---

## 🇺🇸 About the Project

REST API developed for managing a family-owned store.

The system aims to replace manual controls for products, suppliers and inventory with an organized, scalable and maintainable solution.

The store sells different types of products, such as toys, decoration items, clothes, cellphone accessories and general utilities.

Built with **Java 21** and **Spring Boot**, following a layered architecture pattern (Controller → Service → Repository), applying clean code and backend best practices.

This project focuses on:

* REST API development
* Clean architecture principles
* Professional backend structure
* PostgreSQL integration
* DTO usage for request and response data
* Backend business rules
* Inventory control and stock movements

---

## 🚀 Technologies

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok

---

## 🏗 Architecture

The project follows a layered architecture:

```text
controller  → Handles HTTP requests  
service     → Business rules  
repository  → Database access  
model       → JPA entities  
dto         → Data transfer objects  
enums       → Application enums  
```

Application flow:

```text
Controller → Service → Repository → Database
```

---

## 📌 Features

### Products

* Create product
* List all products
* Get product by ID
* Update product
* Soft delete product
* Reactivate product
* Permanently delete product
* Search product by barcode
* List low stock products
* List out-of-stock products

### Suppliers

* Create supplier
* List all suppliers
* List active suppliers
* Get supplier by ID
* Update supplier
* Soft delete supplier
* Reactivate supplier
* Permanently delete supplier

### Stock Movements

* Register stock entries
* Register stock outputs
* Register manual stock adjustments
* List all stock movements
* List latest stock movements
* List today's stock movements
* Count today's stock movements
* List movements by product

---

## 🔌 Endpoints

### 🔹 Products

#### Get all products

```http
GET /products
```

#### Get product by ID

```http
GET /products/{id}
```

#### Get product by barcode

```http
GET /products/barcode/{barcode}
```

#### Get low stock products

```http
GET /products/low-stock
```

#### Get out-of-stock products

```http
GET /products/out-of-stock
```

#### Create product

```http
POST /products
```

Example body:

```json
{
  "name": "Example Product",
  "description": "Product description",
  "barcode": "7891234567890",
  "costPrice": 20.00,
  "salePrice": 49.90,
  "currentStock": 10,
  "minimumStock": 3
}
```

#### Update product

```http
PUT /products/{id}
```

Example body:

```json
{
  "name": "Updated Product",
  "description": "Updated description",
  "barcode": "7891234567890",
  "costPrice": 25.00,
  "salePrice": 59.90,
  "currentStock": 15,
  "minimumStock": 5
}
```

#### Soft delete product

```http
DELETE /products/{id}
```

#### Reactivate product

```http
PUT /products/{id}/activate
```

#### Permanently delete product

```http
DELETE /products/{id}/permanent
```

---

### 🔹 Suppliers

#### Get all suppliers

```http
GET /suppliers
```

#### Get active suppliers

```http
GET /suppliers/active
```

#### Get supplier by ID

```http
GET /suppliers/{id}
```

#### Create supplier

```http
POST /suppliers
```

Example body:

```json
{
  "name": "Example Supplier",
  "cnpj": "12345678000199",
  "phone": "(15) 99999-9999",
  "email": "supplier@example.com",
  "address": "Example address"
}
```

#### Update supplier

```http
PUT /suppliers/{id}
```

#### Soft delete supplier

```http
DELETE /suppliers/{id}
```

#### Reactivate supplier

```http
PUT /suppliers/{id}/activate
```

#### Permanently delete supplier

```http
DELETE /suppliers/{id}/permanent
```

---

### 🔹 Stock Movements

#### Get all stock movements

```http
GET /stock-movements
```

#### Get latest stock movements

```http
GET /stock-movements/latest
```

#### Get today's stock movements

```http
GET /stock-movements/today
```

#### Count today's stock movements

```http
GET /stock-movements/today/count
```

#### Get movements by product

```http
GET /stock-movements/product/{productId}
```

#### Register stock entry

```http
POST /stock-movements/in
```

Example body:

```json
{
  "productId": "product-uuid-here",
  "quantity": 10,
  "reason": "PURCHASE"
}
```

#### Register stock output

```http
POST /stock-movements/out
```

Example body:

```json
{
  "productId": "product-uuid-here",
  "quantity": 2,
  "reason": "SALE"
}
```

#### Register manual stock adjustment

```http
POST /stock-movements/adjust
```

Example body:

```json
{
  "productId": "product-uuid-here",
  "newStock": 15
}
```

---

## 📦 Stock Movement Reasons

The system currently supports the following stock movement reasons:

```text
PURCHASE
SALE
MANUAL_ADJUSTMENT
LOSS
RETURN
INITIAL_STOCK
```

---

## ⚙️ Setup

1. Clone the repository:

```bash
git clone https://github.com/henriiquejoaao/gestaofacil.git
```

2. Access the project folder:

```bash
cd gestaofacil
```

3. Create or configure `application.properties` inside:

```text
src/main/resources/
```

4. Configure your PostgreSQL connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestaofacil
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

5. Run the project:

```bash
mvn spring-boot:run
```

---

## 🗺 Roadmap

* [x] Product management
* [x] Supplier management
* [x] Stock movement management
* [x] Manual stock adjustment
* [ ] Customer management
* [ ] Purchase orders
* [ ] Purchases
* [ ] Sales
* [ ] Customer credit / fiado
* [ ] Financial control
* [ ] Dashboard
* [ ] Reports
* [ ] Global exception handling
* [ ] Unit tests
* [ ] Authentication with Spring Security
* [ ] Frontend integration with React

---

## 📈 Project Status

🚧 In development – backend core structure in progress.

Implemented modules:

* Products
* Suppliers
* Stock movements

Next planned module:

* Customers

---

## 👨‍💻 Author

Developed by João Henrique.

Backend developer in progress, focused on Java and Spring Boot ecosystem.

---