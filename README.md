
# Spring Boot E-Commerce Backend (Stripe Test Integration)

A backend project for an e-commerce system built with Spring Boot. It includes cart functionality, product management, user authentication, and Stripe payment integration in test mode.

## Features
- User registration & login
- Add/update/delete products
- Add to cart and checkout
- Stripe payment using test tokens
- Transaction logging
- Postman collection included

## Tech Stack
- Java 17, Spring Boot
- Spring Security, JWT
- MySQL, Spring Data JPA
- Stripe Java SDK
- Maven

## Setup Instructions

1. Clone the repo and open in IntelliJ or your IDE.
2. Create `application.properties` with the following:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword

stripe.api.key=sk_test_yourStripeSecretKey
```

3. Create the database:
```sql
CREATE DATABASE ecommerce;
```

4. Run the app:
```bash
mvn spring-boot:run
```

## Test Payment (Stripe)
Use the following card for test payments:
- Card: 4242 4242 4242 4242
- Token: tok_visa
- Expiry: any future date
- CVC: any 3 digits

## Author
Santhosh Reddy Komatireddy
