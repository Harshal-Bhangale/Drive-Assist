# Drive Assist – Smart Road Assistance System

**Drive Assist** is an **AI-powered road assistance platform** designed to provide drivers with real-time services including **car rentals, fuel stations, and garage support**, ensuring **smooth, safe, and stress-free journeys**.

This project demonstrates **end-to-end application development** using **Java, Spring Boot, MySQL, and OOP principles**, with a **scalable and maintainable architecture**.

---

## Key Features

1. **Real-Time Car Rental Locator**
   - Find the **nearest available cars** based on type and user location.
   - Displays **availability, price per day, and driver options**.

2. **Fuel Station Finder**
   - Locate **Petrol, Diesel, and CNG stations** nearby.
   - Shows **distance and directions** for quick navigation.

3. **Garage Assistance**
   - Identify **nearest garages** for quick repairs or emergency support.
   - Filters services like **Oil Change, Car Wash, General Repairs**, etc.

4. **Smart Nearest-Service Finder**
   - Uses **geolocation** to calculate distance using the **Haversine formula**.
   - Returns **the closest service based on user latitude and longitude**.

5. **CRUD Operations**
   - Add, view, update, and delete services: **CarRental, Garage, FuelStation**.
   - Fully integrated with **MySQL** database for persistent storage.

---

## Project Structure - 

src
├───main
│   ├───java
│   │   └───com
│   │       └───codewithharshal
│   │           └───driveassist
│   │               │   DriveassistApplication.java
│   │               │
│   │               ├───config
│   │               │       SecurityConfig.java
│   │               │
│   │               ├───controller
│   │               │       CarRentalController.java
│   │               │       FuelStationController.java
│   │               │       GarageController.java
│   │               │
│   │               ├───model
│   │               │   │   BaseService.java
│   │               │   │   CarRental.java
│   │               │   │   FuelStation.java
│   │               │   │   Garage.java
│   │               │   │
│   │               │   ├───dto
│   │               │   │       NearbyRequest.java
│   │               │   │       ServiceResponse.java
│   │               │   │
│   │               │   └───enums
│   │               │           FuelType.java
│   │               │           GarageServiceType.java
│   │               │           ServiceType.java
│   │               │
│   │               ├───repository
│   │               │       CarRentalRepository.java
│   │               │       FuelStationRepository.java
│   │               │       GarageRepository.java
│   │               │
│   │               ├───service
│   │               │   │   CarRentalService.java
│   │               │   │   FuelStationService.java
│   │               │   │   GarageService.java
│   │               │   │   NearbyService.java
│   │               │   │
│   │               │   └───impl
│   │               │           CarRentalServiceImpl.java
│   │               │           FuelStationServiceImpl.java
│   │               │           GarageServiceImpl.java
│   │               │
│   │               └───utils
│   │                       DistanceCalculator.java
│   │
│   └───resources
│       │   application.properties
│       │
│       ├───static
│       └───templates


## Tech Stack

- **Backend:** Java, Spring Boot, Spring Data JPA, Hibernate  
- **Database:** MySQL  
- **APIs:** RESTful endpoints for all services  
- **Validation:** Jakarta Bean Validation  
- **Build & Tools:** Maven, Postman for testing  

---

## Architecture & OOP Design

Drive Assist is designed with **object-oriented principles** and **SOLID architecture**, ensuring **modularity, reusability, and scalability**.

### 1. Inheritance
- `BaseService` is the **abstract parent** for all services (`Garage`, `CarRental`, `FuelStation`) with common fields like `name`, `latitude`, `longitude`, and `rating`.

### 2. Encapsulation
- All entity fields are **private**, accessed via **getters/setters**, ensuring **data integrity**.

### 3. Polymorphism & Abstraction
- Methods like `findNearest()` operate on **BaseService references**, allowing **generic handling** of different service types.
- `NearbyService<T>` interface abstracts **common CRUD & proximity operations**.

### 4. Composition
- `Garage` has a **set of services** (`EnumSet<GarageServiceType>`) representing multiple repair or support options.
- Relationship between `Garage` and its services is modeled via **join tables** in MySQL.

### 5. SOLID Principles
- **Single Responsibility:** Each service class handles one type of service.  
- **Open/Closed:** New service types can be added with minimal changes.  
- **Liskov Substitution:** All subclasses of `BaseService` can replace it wherever needed.  
- **Interface Segregation:** `NearbyService<T>` is focused and minimal.  
- **Dependency Inversion:** Controllers depend on interfaces, not implementations.

---

## Database Schema

- **services** (common table for all service types)
  - id, name, address, phone, website, latitude, longitude, rating
- **car_rentals**
  - availableCars, pricePerDay, driverAvailable, carType
- **garages**
  - emergencySupport
- **garage_services** (join table)
  - garage_id, service (Enum: OIL_CHANGE, CAR_WASH, GENERAL_REPAIR, etc.)
- **fuel_stations**
  - fuelType, pricePerUnit

> **Note:** Subclass tables (`garages`, `car_rentals`, `fuel_stations`) are **JOINED with the `services` table** for normalized relational mapping.

---

## API Endpoints

### Car Rentals
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/car-rentals` | List all car rentals |
| GET    | `/api/car-rentals/{id}` | Get car rental by ID |
| POST   | `/api/car-rentals` | Create a new car rental |
| DELETE | `/api/car-rentals/{id}` | Delete a car rental |
| GET    | `/api/car-rentals/nearest?carType={type}&latitude={lat}&longitude={lon}` | Get nearest available car |

### Garages
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/garages` | List all garages |
| GET    | `/api/garages/{id}` | Get garage by ID |
| POST   | `/api/garages` | Create a new garage |
| DELETE | `/api/garages/{id}` | Delete a garage |
| GET    | `/api/garages/nearest?lat={lat}&lon={lon}` | Get nearest garage |

### Fuel Stations
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/fuel-stations` | List all fuel stations |
| GET    | `/api/fuel-stations/{id}` | Get fuel station by ID |
| POST   | `/api/fuel-stations` | Create a new fuel station |
| DELETE | `/api/fuel-stations/{id}` | Delete a fuel station |
| GET    | `/api/fuel-stations/nearest?lat={lat}&lon={lon}&fuelType={type}` | Get nearest fuel station |

---

## Example JSON Body

### Create Garage
```json
{
  "name": "City Garage",
  "address": "Pune, India",
  "phone": "+911234567890",
  "website": "https://citygarage.com",
  "latitude": 18.5204,
  "longitude": 73.8567,
  "rating": 4.5,
  "emergencySupport": true,
  "servicesProvided": ["OIL_CHANGE", "CAR_WASH", "GENERAL_REPAIR"]
}
```

### Create Car Rental
```json
{
  "name": "City Rentals",
  "latitude": 18.5204,
  "longitude": 73.8567,
  "availableCars": 5,
  "carType": "Sedan",
  "pricePerDay": 1200.0,
  "driverAvailable": true,
  "address": "Pune, India",
  "phone": "+911234567890",
  "website": "https://cityrentals.com",
  "rating": 4.5
}
```

---

## Key Achievements
- Developed a **real-time, scalable system** to assist drivers in emergencies.  
- Applied **OOP and SOLID principles** to build a **clean, maintainable, and extensible architecture**.  
- Integrated **JOINED inheritance mapping** in MySQL for **normalized relational design**.  
- Implemented **distance-based nearest-service queries** using the **Haversine formula**.  
- Built a **complete CRUD API system** ready for frontend or mobile integration.

---

## Future Enhancements
- AI-driven **dynamic route optimization** for emergencies.  
- Integration with **Google Maps API** for live navigation.  
- Real-time **car rental booking and availability updates**.  
- Mobile app support with **push notifications** for nearby services.

---

## Why This Project is Interview-Worthy
- Demonstrates **full-stack backend development skills** with Spring Boot & MySQL.  
- Showcases **real-world problem-solving** using **object-oriented design and software engineering principles**.  
- Combines **database design, API design, and geolocation-based computations**.  
- Easily extendable to **AI, mobile apps, or cloud services**.

