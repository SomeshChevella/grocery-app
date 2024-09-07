# Grocery Management App

## Overview
The **Grocery Management App** is a microservices-based application designed to manage grocery-related operations. Currently, the project is under development and the first microservice, **Product Service**, has been implemented. This microservice handles all product-related functionalities such as adding, updating, and retrieving product information.

### Key Features (Implemented)
- **Product Microservice**
    - Create, update, and manage products.
    - Retrieve product details.

### Future Enhancements
The following microservices and features will be added in the upcoming days:
- **User Microservice**: To manage customer and vendor accounts.
- **Order Microservice**: To handle order creation, tracking, and history.
- **Inventory Microservice**: To manage stock levels and product availability.
- **Payment Microservice**: To integrate with payment systems for processing transactions.

### Technologies
- **Java** with **Spring Boot** for backend development.
- **PostgreSQL (RDS)** as the database.
- **Docker** for containerization.
- **React** using TypeScript as part of Front End Development (planned).
- **AWS S3** for storage (planned).
- **AWS Lambda** for serverless functions (planned).
- **ECS** for Elastic Container Service (planned).

### Getting Started

#### Prerequisites
- Java 17 or later
- Docker
- PostgreSQL

#### Creating Docker Container that acts as MySQL Database

```bash
   docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=dummyuser --env MYSQL_PASSWORD=dummygrocery --env MYSQL_DATABASE=grocery --name mysql_grocery --publish 3306:3306 mysql:8-oracle
```

#### Running the Product Microservice
1. Clone the repository:
   ```bash
    git clone https://github.com/SomeshChevella/grocery-app.git
   ```
