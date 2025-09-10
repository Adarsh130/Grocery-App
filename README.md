# Grocery App API Documentation

This is a simple CRUD API built with **Spring Boot** and **MongoDB** to manage grocery items.

**Base URL:**  
`http://localhost:8080/api/grocery`

---

## GroceryItem Model
| Field     | Type    | Description |
|-----------|---------|-------------|
| id        | String  | Auto-generated unique ID |
| name      | String  | Grocery item name |
| price     | Double  | Price of the item |
| quantity  | Integer | Available stock |

---

## Endpoints

### 1. Get All Items
**Request:**
```
GET /api/grocery
```

**Response:**
```json
[
  {
    "id": "66dcdccff123",
    "name": "Rice",
    "price": 45.5,
    "quantity": 10
  }
]
```

### 2. Get Item by ID
**Request:**
```
GET /api/grocery/{id}
```

**Example:**
```
GET /api/grocery/66dcdccff123
```

**Response:**
```json
{
  "id": "66dcdccff123",
  "name": "Rice",
  "price": 45.5,
  "quantity": 10
}
```

### 3. Add a New Item
**Request:**
```
POST /api/grocery
```

**Body:**
```json
{
  "name": "Milk",
  "price": 30,
  "quantity": 20
}
```

**Response:**
```json
{
  "id": "66dcdccff456",
  "name": "Milk",
  "price": 30,
  "quantity": 20
}
```

### 4. Update an Item
**Request:**
```
PUT /api/grocery/{id}
```

**Body:**
```json
{
  "name": "Milk Updated",
  "price": 32,
  "quantity": 25
}
```

**Example:**
```
PUT /api/grocery/66dcdccff456
```

**Response:**
```json
{
  "id": "66dcdccff456",
  "name": "Milk Updated",
  "price": 32,
  "quantity": 25
}
```

### 5. Delete an Item
**Request:**
```
DELETE /api/grocery/{id}
```

**Example:**
```
DELETE /api/grocery/66dcdccff456
```

**Response:**
```
Item deleted successfully!
```

### 6. Filter Items
**Request:**
```
GET /api/grocery/filter
```

| Query Param | Example | Description |
|-------------|---------|-------------|
| name | ?name=rice | Filter by item name |
| minPrice | ?minPrice=20 | Minimum price filter |
| maxPrice | ?maxPrice=50 | Maximum price filter |
| minQuantity | ?minQuantity=10 | Minimum quantity filter |

**Example Request:**
```
GET /api/grocery/filter?name=Rice&minPrice=20&maxPrice=50
```

**Example Response:**
```json
[
  {
    "id": "66dcdccff123",
    "name": "Rice",
    "price": 45.5,
    "quantity": 10
  }
]
```

---

## CRUD Quick Reference for Postman

| Operation | Method | Endpoint | Body Example |
|-----------|--------|----------|--------------|
| Get All Items | GET | /api/grocery | None |
| Get Item by ID | GET | /api/grocery/{id} | None |
| Add Item | POST | /api/grocery | {"name":"Milk","price":30,"quantity":20} |
| Update Item | PUT | /api/grocery/{id} | {"name":"Milk Updated","price":32,"quantity":25} |
| Delete Item | DELETE | /api/grocery/{id} | None |

---

## MongoDB Setup

Make sure MongoDB is running locally:
```bash
mongosh
```

View databases:
```bash
show dbs
```

Use your database:
```bash
use grocerydb
```

View data in the collection:
```bash
db.groceryItem.find().pretty()
```

Delete a specific document manually:
```bash
db.groceryItem.deleteOne({ "_id": ObjectId("YOUR_ID_HERE") })
```

---

## Global Exception Handling

We have added a GlobalExceptionHandler using @ControllerAdvice to handle errors such as:

- Resource Not Found
- Invalid Data
- Generic Server Errors

**Example error response:**
```json
{
  "timestamp": "2025-09-10T10:00:00",
  "message": "Item not found with ID: 66dcdccff456",
  "details": "uri=/api/grocery/66dcdccff456"
}
```

---

## Project Structure

```
GroceryApp
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.adarsh.GroceryApp
│   │   │       ├── controller
│   │   │       │   └── GroceryController.java
│   │   │       ├── model
│   │   │       │   └── GroceryItem.java
│   │   │       ├── repository
│   │   │       │   └── GroceryRepository.java
│   │   │       ├── service
│   │   │       │   └── GroceryService.java
│   │   │       └── exception
│   │   │           └── GlobalExceptionHandler.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│
└── pom.xml
```

---

## Running the Project

1. **Start MongoDB:**
   ```bash
   mongosh
   ```

2. **Run the Spring Boot application:**
   ```bash
   mvn spring-boot:run
   ```

3. **Access API:**
   ```bash
   http://localhost:8080/api/grocery
   ```