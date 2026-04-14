# Drive Assist – Smart Road Assistance Microservices Platform

**Drive Assist** is a **microservices-based road assistance platform** built with **Spring Boot 3, Spring Cloud, and MySQL**. It provides drivers with real-time services including **car rentals with booking, fuel stations, garage support, and map aggregation** — ensuring **smooth, safe, and stress-free journeys**.

This project demonstrates **enterprise-grade backend development** using **Java 17, Spring Cloud (Eureka, Gateway, OpenFeign), JWT authentication, Resilience4j circuit breakers, and OOP/SOLID design principles**.

---

## Architecture Overview

```
                          ┌──────────────────┐
                          │  Service Registry │
                          │  (Eureka – 8761)  │
                          └────────┬─────────┘
                                   │ registers/discovers
         ┌─────────────────────────┼─────────────────────────┐
         │                         │                         │
┌────────▼────────┐  ┌─────────────▼──────────────┐  ┌──────▼──────┐
│   API Gateway   │  │     Downstream Services     │  │  Frontend   │
│   (Port 8080)   │──│                              │  │  (Vite+React)│
│  JWT Filter     │  │  user-service       :8081   │  └─────────────┘
│  Route Defs     │  │  fuel-station-service:8082   │
│  CORS Config    │  │  garage-service      :8083   │
└─────────────────┘  │  rental-service      :8084   │
                     │  map-service          :8085   │
                     └──────────────────────────────┘
```

Each service has **its own MySQL database**, **independent deployment**, and communicates via **REST + Feign clients**.

---

## Key Features

1. **User Authentication & Authorization**
   - JWT-based login/register with **role-based access** (USER / ADMIN).
   - BCrypt password hashing, profile management, password change.

2. **Fuel Station Finder**
   - Full CRUD + **geo-radius search** using the **Haversine formula**.
   - Filter by **fuel type** (PETROL, DIESEL, CNG, ELECTRIC) and **24-hour availability**.

3. **Garage Assistance**
   - Full CRUD + **nearby search** with distance sorting.
   - Filter by **specialization** (GENERAL, ENGINE, ELECTRICAL, BODY_WORK, TYRE, AC) and **emergency service**.

4. **Car Rental & Booking System**
   - Full CRUD for rental cars + **advanced filtering** (car type, max price, availability).
   - **Booking lifecycle**: create → confirm → cancel, with **cost calculation** based on duration × hourly rate.
   - Paged booking history per user.

5. **Map Aggregation Service**
   - Single endpoint returns **nearby fuel stations, garages, and rental cars** via **Feign clients**.
   - **Resilience4j circuit breakers** with graceful fallbacks if a downstream service is unavailable.
   - **Route estimation** with distance (Haversine) and ETA calculation.

6. **Cross-Cutting Concerns**
   - **Global exception handling** via `@RestControllerAdvice` with consistent `ApiResponse<T>` wrapper.
   - **Pagination** support across all list endpoints.
   - **Swagger/OpenAPI** docs on each service (`/swagger-ui.html`).
   - **Spring Boot Actuator** health endpoints.

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.3.3 |
| **Cloud** | Spring Cloud 2023.0.3 (Eureka, Gateway, OpenFeign) |
| **Security** | JWT (jjwt 0.11.5), BCrypt |
| **Database** | MySQL 8 (one DB per service) |
| **Resilience** | Resilience4j Circuit Breaker |
| **API Docs** | SpringDoc OpenAPI 2.6.0 |
| **Build** | Maven Multi-Module |
| **Frontend** | React + TypeScript + Vite |

---

## Project Structure

```
Backend/
├── pom.xml                     # Parent POM (module aggregator)
├── common-library/             # Shared: BaseEntity, ApiResponse, exceptions, interfaces, GeoUtils
├── service-registry/           # Eureka Server (port 8761)
├── api-gateway/                # Spring Cloud Gateway + JWT filter (port 8080)
├── user-service/               # Auth, profiles, JWT generation (port 8081)
├── fuel-station-service/       # Fuel station CRUD + geo-search (port 8082)
├── garage-service/             # Garage CRUD + geo-search (port 8083)
├── rental-service/             # Car rentals + booking system (port 8084)
└── map-service/                # Feign aggregation + routing (port 8085)

frontend/
├── src/
│   ├── App.tsx
│   ├── components/
│   ├── pages/
│   └── lib/api.ts
└── package.json
```

---

## OOP & SOLID Design

### Abstraction & Inheritance
- **`BaseEntity`** (`@MappedSuperclass`) — abstract class with `id`, `createdAt`, `updatedAt` and lifecycle hooks (`@PrePersist`, `@PreUpdate`). All entities extend it.
- **Exception hierarchy** — `BaseException` (abstract) → `ResourceNotFoundException`, `DuplicateResourceException`, `UnauthorizedException`, `BadRequestException`, `ServiceUnavailableException`.

### Interface Segregation & Polymorphism
- **`CrudService<REQ, RES, ID>`** — generic interface for standard CRUD operations.
- **`LocationAwareService<REQ, RES, ID>`** — extends `CrudService`, adds `findNearby()` and `search()`.
- Each domain service implements these interfaces differently (polymorphism).

### Encapsulation
- Entities are never exposed directly — separate **Request DTOs** (input validation) and **Response DTOs** (output shaping).
- Private fields + getters/setters throughout.

### Enums Over Strings
- `Role` (USER, ADMIN), `FuelType`, `CarType`, `BookingStatus`, `GarageSpecialization` — type-safe domain values.

### Factory Pattern
- `ApiResponse.success()` / `ApiResponse.error()` — static factory methods for consistent response building.

---

## Database Schema

Each microservice owns its database:

| Service | Database | Tables |
|---------|----------|--------|
| user-service | `user_service_db` | `users` (id, username, email, password, role, phone, preferences) |
| fuel-station-service | `fuel_station_db` | `fuel_stations` (id, name, address, city, state, phone, lat, lon, fuel_type, price, rating, open_24_hours) |
| garage-service | `garage_db` | `garages` (id, name, address, city, state, phone, lat, lon, specialization, rating, emergency_service) |
| rental-service | `rental_db` | `car_rentals` (id, car_type, model, brand, price_per_hour, available, pickup_lat/lon, seats, fuel_type) + `bookings` (id, car_id, user_id, pickup_time, drop_time, total_cost, status, pickup/drop_location) |

---

## API Endpoints (all versioned `/api/v1/`)

### User Service — `/api/v1/users`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register a new user |
| POST | `/login` | Authenticate and receive JWT |
| GET | `/profile` | Get current user profile |
| GET | `/` | List all users (paginated) |
| PUT | `/profile` | Update profile |
| PUT | `/change-password` | Change password |
| PUT | `/preferences` | Update preferences |
| DELETE | `/{id}` | Delete user |

### Fuel Station Service — `/api/v1/fuel-stations`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create fuel station |
| GET | `/{id}` | Get by ID |
| GET | `/` | List all (paginated, sortable) |
| PUT | `/{id}` | Update fuel station |
| DELETE | `/{id}` | Delete fuel station |
| GET | `/nearby` | Nearby within radius (lat, lon, radiusKm, sortBy) |
| GET | `/search` | Keyword search |
| GET | `/type/{fuelType}` | Filter by fuel type |
| GET | `/open-24-hours` | 24-hour stations only |

### Garage Service — `/api/v1/garages`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create garage |
| GET | `/{id}` | Get by ID |
| GET | `/` | List all (paginated, sortable) |
| PUT | `/{id}` | Update garage |
| DELETE | `/{id}` | Delete garage |
| GET | `/nearby` | Nearby within radius |
| GET | `/search` | Keyword search |
| GET | `/specialization/{type}` | Filter by specialization |
| GET | `/emergency` | Emergency garages only |

### Rental Service — `/api/v1/rentals`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/cars` | Add a rental car |
| GET | `/cars/{id}` | Get car by ID |
| GET | `/cars` | List all cars (paginated) |
| PUT | `/cars/{id}` | Update car |
| DELETE | `/cars/{id}` | Delete car |
| GET | `/cars/available` | Available cars (filter by type, maxPrice) |
| GET | `/cars/nearby` | Nearby cars within radius |
| GET | `/cars/search` | Search by keyword |
| POST | `/bookings` | Create a booking |
| GET | `/bookings/{id}` | Get booking by ID |
| PUT | `/bookings/{id}/cancel` | Cancel a booking |
| GET | `/bookings/user/{userId}` | User's bookings |
| GET | `/bookings/user/{userId}/paged` | User's bookings (paginated) |

### Map Service — `/api/v1/map`
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/nearby` | Aggregated nearby markers (fuel + garage + rental) |
| GET | `/route` | Route distance & ETA between two coordinates |

---

## Example JSON Bodies

### Register User
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePass123",
  "phone": "+911234567890"
}
```

### Create Fuel Station
```json
{
  "name": "HP Petrol Pump",
  "address": "MG Road",
  "city": "Pune",
  "state": "Maharashtra",
  "phone": "+911234567890",
  "latitude": 18.5204,
  "longitude": 73.8567,
  "fuelType": "PETROL",
  "pricePerUnit": 105.50,
  "rating": 4.3,
  "open24Hours": true
}
```

### Create Garage
```json
{
  "name": "City Auto Works",
  "address": "FC Road, Pune",
  "city": "Pune",
  "state": "Maharashtra",
  "phone": "+911234567890",
  "latitude": 18.5204,
  "longitude": 73.8567,
  "specialization": "GENERAL",
  "rating": 4.5,
  "emergencyService": true
}
```

### Add Rental Car
```json
{
  "carType": "SEDAN",
  "model": "Honda City",
  "brand": "Honda",
  "pricePerHour": 250.0,
  "pickupLat": 18.5204,
  "pickupLon": 73.8567,
  "pickupAddress": "Pune Railway Station",
  "provider": "City Rentals",
  "rating": 4.6,
  "seats": 5,
  "fuelType": "PETROL",
  "automaticTransmission": false
}
```

### Create Booking
```json
{
  "carId": 1,
  "userId": 1,
  "pickupTime": "2026-04-16T09:00:00Z",
  "dropTime": "2026-04-16T18:00:00Z",
  "pickupLocation": "Pune Railway Station",
  "dropLocation": "Pune Airport"
}
```

---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8+

### Database Setup
Create the following databases in MySQL:
```sql
CREATE DATABASE user_service_db;
CREATE DATABASE fuel_station_db;
CREATE DATABASE garage_db;
CREATE DATABASE rental_db;
```

### Build & Run

```bash
# From the Backend/ directory
mvn clean install

# Start services in order:
# 1. Service Registry
cd service-registry && mvn spring-boot:run

# 2. API Gateway
cd api-gateway && mvn spring-boot:run

# 3. Domain services (any order)
cd user-service && mvn spring-boot:run
cd fuel-station-service && mvn spring-boot:run
cd garage-service && mvn spring-boot:run
cd rental-service && mvn spring-boot:run
cd map-service && mvn spring-boot:run
```

### Verify
- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **Swagger UI** (per service): http://localhost:{port}/swagger-ui.html

---

## Key Achievements

- Migrated from **monolithic to microservices** architecture with 8 independently deployable modules.
- Implemented **service discovery** (Eureka), **API gateway** (Spring Cloud Gateway), and **inter-service communication** (OpenFeign).
- Built **JWT-based authentication** with gateway-level token validation and header forwarding.
- Applied **circuit breaker pattern** (Resilience4j) with graceful fallbacks in the map aggregation service.
- Designed a **generic service interface hierarchy** (`CrudService` → `LocationAwareService`) demonstrating OOP polymorphism.
- Implemented **Haversine-based geo-proximity search** with multi-field sorting across all location-aware services.
- Built a complete **car rental booking system** with cost calculation, availability management, and booking lifecycle.
- Achieved **45+ REST API endpoints** with pagination, validation, and consistent error responses.
- Each service has **its own database** following the database-per-service microservices pattern.

