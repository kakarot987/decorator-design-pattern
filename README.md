# ☕ Decorator Design Pattern — Spring Boot Demo

> A clean, portfolio-ready implementation of the **Decorator Design Pattern** using **Java 17 + Spring Boot 3**.

---

## Pattern Overview

The **Decorator Pattern** attaches additional responsibilities to an object *dynamically*.  
It provides a flexible alternative to subclassing for extending functionality.

```
Client → [VanillaDecorator → [MilkDecorator → [Espresso]]]
```

Each decorator wraps an inner component and delegates calls while adding its own behaviour — the client never knows the difference.

---

## Project Structure

```
src/main/java/com/decorator/
├── DecoratorDemoApplication.java
│
├── service/
│   ├── Coffee.java              ← «interface» Component
│   ├── Espresso.java            ← Concrete Component
│   ├── SimpleCoffee.java        ← Concrete Component
│   └── CoffeeBuilderService.java← Orchestrates pattern application
│
├── decorator/
│   ├── CoffeeDecorator.java     ← «abstract» Base Decorator
│   ├── MilkDecorator.java       ← Concrete Decorator
│   ├── SugarDecorator.java      ← Concrete Decorator
│   ├── WhipCreamDecorator.java  ← Concrete Decorator
│   └── VanillaDecorator.java    ← Concrete Decorator
│
├── model/
│   ├── CoffeeOrderRequest.java
│   └── CoffeeOrderResponse.java ← Includes decorator chain trace
│
└── controller/
    └── CoffeeController.java    ← REST endpoints

frontend/
└── index.html                   ← Vanilla JS UI (zero dependencies)
```

---

## API Endpoints

### `POST /api/coffee/order`
Build a coffee dynamically with decorators.

**Request:**
```json
{
  "baseType": "espresso",
  "addons": ["milk", "vanilla", "whip"]
}
```

**Response:**
```json
{
  "baseType": "espresso",
  "addons": ["milk", "vanilla", "whip"],
  "description": "Espresso, Milk, Vanilla, Whip Cream",
  "totalCost": 2.60,
  "decoratorChain": [
    { "layer": "Base: Espresso",       "descriptionSoFar": "Espresso",                         "costSoFar": 1.50 },
    { "layer": "+MilkDecorator",       "descriptionSoFar": "Espresso, Milk",                   "costSoFar": 1.75 },
    { "layer": "+VanillaDecorator",    "descriptionSoFar": "Espresso, Milk, Vanilla",           "costSoFar": 2.10 },
    { "layer": "+WhipCreamDecorator",  "descriptionSoFar": "Espresso, Milk, Vanilla, Whip Cream", "costSoFar": 2.60 }
  ]
}
```

### `GET /api/coffee/options`
Returns all available bases and add-ons with their prices.

---

## How to Run

### Backend
```bash
cd decorator-demo
./mvnw spring-boot:run
# API available at http://localhost:8080
```

### Frontend
Open `frontend/index.html` directly in a browser — no build step required.

---

## Tech Stack

| Layer     | Technology           |
|-----------|----------------------|
| Language  | Java 17              |
| Framework | Spring Boot 3.2      |
| Build     | Maven                |
| Frontend  | Vanilla HTML/CSS/JS  |
| Pattern   | Decorator (GoF)      |

---

## Key Design Decisions

- **Interface-based** — `Coffee` interface ensures both components and decorators are interchangeable
- **Open/Closed Principle** — Add new decorators without modifying existing code
- **Chain tracing** — Response includes the full wrapping chain for visualization/debugging
- **Zero frontend deps** — Single `index.html` with no frameworks or build tools

---

*GoF Decorator Pattern · Backend Demo · Java 17 · Spring Boot 3*
![img.png](img.png)