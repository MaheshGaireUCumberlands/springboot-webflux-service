# SpringBoot WebFlux Service 🚀

[![Build](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/yourusername/springboot-webflux-service/actions)  
[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://openjdk.org/projects/jdk/17/)  
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.x-brightgreen.svg)](https://spring.io/projects/spring-boot)  
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A high-performance reactive REST API built with **Spring Boot 3**, **WebFlux**, and **Java 17**. This project features non-blocking I/O, asynchronous processing, real-time monitoring with **Spring Boot Admin**, and performance/load testing with **Gatling**.

---

## 🔧 Tech Stack

- ✅ **Spring Boot 3.2.x**
- 🌊 **Spring WebFlux (Reactive)**
- ☕️ **Java 17**
- 📘 **OpenAPI / Swagger UI**
- 🧪 **JUnit 5** & **Reactor Test**
- 🧭 **Spring Boot Admin**
- 📈 **Gatling (Scala-based load testing)**
- 📦 **Maven**

---

## ✨ Features

- ⚡️ Reactive, non-blocking API using Spring WebFlux  
- 🧠 In-memory datastore (no external DB required)  
- 🔍 API documentation via Swagger UI  
- 📊 Admin dashboard with real-time monitoring (Spring Boot Admin)  
- 🧪 Load testing via Gatling simulation  
- ✅ Unit and integration tests using JUnit 5 and Reactor Test

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/springboot-webflux-service.git
cd springboot-webflux-service
```

### 2. Build the project
```bash
mvn clean install
```

### 3. Run the application
```bash
mvn spring-boot:run
```

### 4. Explore the API
- 📘 Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
- 🌐 Base Endpoint: `http://localhost:8080/api/...`

---

## 📊 Admin Dashboard

The app includes both **Spring Boot Admin Server** and **Client** in the same application.

- Access Dashboard: [http://localhost:8080/admin](http://localhost:8080/admin)

If you see “No applications registered”, ensure that:
- `@EnableAdminServer` is present
- `spring.boot.admin.client.enabled=true` is set in `application.yml`

---

## 🧪 Testing

### ✅ Unit & Integration Tests
```bash
mvn test
```

- Frameworks: **JUnit 5**, **reactor-test**

### 🏃 Load Testing with Gatling

Simulates load on the `/api/tasks` endpoint.

#### Run the test:
```bash
mvn gatling:test
```

#### What it does:
- Simulates 50 users over 30 seconds
- Sends concurrent **POST** and **GET** requests to `/api/tasks`

#### View the report:
Open the generated HTML report from:
```
target/gatling/<simulation-folder>/index.html
```

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/mahesh/webflux/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   └── WebfluxApplication.java
│   └── resources/application.yml
├── test/java/... (JUnit 5 + reactor-test)
├── gatling/simulations/TaskApiSimulation.scala
```

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

To contribute:

1. Fork the repo
2. Create a feature branch
3. Commit and push your changes
4. Open a PR 🎉

---

## 📌 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 🙋‍♂️ Author

**Mahesh Gaire**  
📍 Chantilly, VA  
🔗 [LinkedIn](https://www.linkedin.com/in/maheshgaire)