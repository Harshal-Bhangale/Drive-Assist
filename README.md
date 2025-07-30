# Drive-Assist

# 🚗 Drive Assist – Smart Road Assistance

**Drive Assist** is an AI-assisted smart road assistance web application built with **Spring Boot and Java**. It helps users locate nearby **fuel stations**, **garages**, and **car rentals** during vehicle breakdowns or emergencies. An upcoming feature will also suggest **nearby hotels** for extended stays.

---

## 🌐 Live Demo

> *(Optional: Add your deployment link or video demo link here)*

---

## 🧰 Tech Stack

- **Backend:** Spring Boot, Java  
- **Frontend:** HTML, CSS, JavaScript *(Optional: Add React if used)*  
- **Security:** JWT-based Authentication, Role-Based Access Control  
- **Database:** MySQL, Upcoming – PostgreSQL  
- **APIs:** Google Maps API for GPS-enabled assistance  
- **Tools:** IntelliJ IDEA, VS Code, Postman, Git, GitHub

---

## 🚀 Key Features

- 🛢️ **Fuel Station Locator**  
  - Locate nearest **Petrol, Diesel, and CNG stations** based on current location.

- 🔧 **Nearest Garages**  
  - Find garages nearby for **quick vehicle repair** in case of breakdown.

- 🚙 **Car Rental Services**  
  - Suggest **rental services** nearby for temporary vehicle replacement.

- 🏨 **(Upcoming)** Hotel Service  
  - Get recommendations for **affordable hotels** if your vehicle needs long-term service.

- 🔐 **Secure Access**  
  - JWT-based authentication with **role-based access control** for sensitive operations.

---

## ⚙️ Backend Overview

- Developed a **Spring Boot backend** with **RESTful APIs** to manage location-based data for services.
- Implemented **JWT-based authentication** and **role-based access control**, ensuring secure access to sensitive endpoints.
- Applied **OOP principles** to build **modular, maintainable, and scalable** service and controller layers.

---

## 📦 Project Structure

drive-assist/
├── src/
│ ├── main/
│ │ ├── java/com/driveassist/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── model/
│ │ │ └── repository/
│ │ └── resources/
│ │ ├── static/
│ │ ├── templates/
│ │ └── application.properties


---

## 📍 How It Works

1. User selects a service type (Fuel, Garage, Car Rental)
2. App fetches **current location** using Google Maps API
3. Backend returns nearby services sorted by distance
4. Results are displayed with **location info and directions**

---

## 🛠️ Setup & Installation

### ✅ Prerequisites

- Java 11+  
- Maven  
- Google Maps API Key  
- Git

### 📥 Steps

```bash
git clone https://github.com/your-username/drive-assist.git
cd drive-assist


1. Add your API key in application.properties:
    google.maps.api.key=YOUR_API_KEY

2. Run the application:
    ./mvnw spring-boot:run

Open in browser at:
http://localhost:8080/
    
