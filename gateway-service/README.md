# Product API Documentation

## Base URL

`http://your-domain.com/api/product`

---

## 1. Create a Product

**Endpoint:** `POST /api/product`

**Request Body:**

```json
{
  "name": "Laptop",
  "description": "A high-performance laptop for professionals.",
  "hsnCode": "123456",
  "price": 1500.99
}
```

**Response:**

```json
{
  "id": 1,
  "name": "Laptop",
  "description": "A high-performance laptop for professionals.",
  "hsnCode": "123456",
  "price": 1500.99
}
```

**Status Codes:**

- `201 Created`: Product successfully created
- `400 Bad Request`: Validation error

---

## 2. Get All Products

**Endpoint:** `GET /api/product`

**Response:**

```json
[
  {
    "id": 1,
    "name": "Laptop",
    "description": "A high-performance laptop for professionals.",
    "hsnCode": "123456",
    "price": 1500.99
  },
  {
    "id": 2,
    "name": "Mouse",
    "description": "Wireless mouse with ergonomic design.",
    "hsnCode": "654321",
    "price": 25.50
  }
]
```

**Status Codes:**

- `200 OK`: List of products retrieved successfully

---

## 3. Get Product by ID

**Endpoint:** `GET /api/product/{id}`

**Example Request:**
`GET /api/product/1`

**Response:**

```json
{
  "id": 1,
  "name": "Laptop",
  "description": "A high-performance laptop for professionals.",
  "hsnCode": "123456",
  "price": 1500.99
}
```

**Status Codes:**

- `200 OK`: Product found
- `404 Not Found`: Product does not exist

---

## 4. Get Products by Name

**Endpoint:** `GET /api/product/by-name?name={name}`

**Example Request:**
`GET /api/product/by-name?name=Laptop`

**Response:**

```json
[
  {
    "id": 1,
    "name": "Laptop",
    "description": "A high-performance laptop for professionals.",
    "hsnCode": "123456",
    "price": 1500.99
  }
]
```

**Status Codes:**

- `200 OK`: Products retrieved successfully
- `404 Not Found`: No product found with the given name

---

## 5. Delete Product

**Endpoint:** `DELETE /api/product/{id}`

**Example Request:**
`DELETE /api/product/1`

**Response:**
*No content*

**Status Codes:**

- `204 No Content`: Product successfully deleted
- `404 Not Found`: Product does not exist

---

## Common Error Responses

**400 Bad Request:**

```json
{
  "error": "Validation failed",
  "message": "Product name is required"
}
```

**404 Not Found:**

```json
{
  "error": "Not Found",
  "message": "Product not found with ID: 1"
}
```



