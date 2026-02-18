# 🏪 Gestão Fácil - Product API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Status](https://img.shields.io/badge/status-in%20development-yellow)

## 🇧🇷 Sobre o Projeto

API REST desenvolvida para gerenciamento de produtos de uma loja.

O projeto foi construído utilizando **Java 21** e **Spring Boot**, seguindo arquitetura em camadas (Controller → Service → Repository), aplicando boas práticas de desenvolvimento backend.

Este projeto tem como objetivo consolidar conhecimentos em:

* Desenvolvimento de APIs REST
* Separação de responsabilidades
* Organização profissional de código
* Integração com banco de dados relacional

---

## 🇺🇸 About the Project

REST API developed for store product management.

Built with **Java 21** and **Spring Boot**, following a layered architecture pattern (Controller → Service → Repository), applying clean code and backend best practices.

This project focuses on:

* REST API development
* Clean architecture principles
* Professional backend structure
* PostgreSQL integration

---

## 🚀 Technologies

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok

---

## 🏗 Architecture

The project follows a layered architecture:

```
controller  → Handles HTTP requests  
service     → Business rules  
repository  → Database access  
model       → JPA entities  
dto         → Data transfer objects  
```

Application flow:

```
Controller → Service → Repository → Database
```

---

## 📌 Features

* Create product
* List all products
* Get product by ID
* Delete product
* DTO usage for request/response
* PostgreSQL integration

---

## 🔌 Endpoints

### 🔹 Get all products

```
GET /products
```

### 🔹 Get product by ID

```
GET /products/{id}
```

### 🔹 Create product

```
POST /products
```

Example body:

```json
{
  "name": "Example Product",
  "price": 49.90,
  "image": "image.jpg"
}
```

### 🔹 Delete product

```
DELETE /products/{id}
```

---

## ⚙️ Setup

1. Clone the repository
2. Create `application.properties` inside:

```
src/main/resources/
```

3. Configure your PostgreSQL connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestaofacil
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

4. Run the project:

```
mvn spring-boot:run
```

---

## 🗺 Roadmap

* [ ] Implement PUT (update product)
* [ ] Add validation with @Valid
* [ ] Global exception handling
* [ ] Unit tests
* [ ] Authentication with Spring Security
* [ ] Frontend integration (React)

---

## 📈 Project Status

🚧 In development – backend core structure completed.

---

## 👨‍💻 Author

Developed by Henrique João.

Backend developer in progress, focused on Java and Spring Boot ecosystem.

---
