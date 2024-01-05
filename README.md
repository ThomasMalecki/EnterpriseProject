# Enterprise Development Experience Project

## Gekozen Thema

Dit project demonstreert een vereenvoudigde werking van een hotelboekingssysteem. Een boeking bezit een hotel, en een klant bezit ook een boeking met het aantal nachten dat een persoon verblijft.

## API Gateway
**Port:** 8083

### Authenticatie
Wanneer u niet geauthenticeerd bent, kunt u alleen toegang krijgen tot de lijst met alle hotels. De rest van de services vereist authenticatie. U kunt uzelf bv authentiseren door een token te genereren met Postman en dit te gebruiken als een Bearer-token.

## Hotel Service
**Port:** 8080

### API Endpoints
- **GET:** `localhost:8083/hotels`
- ![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/all-hotels.png?raw=true)
- **POST:** `localhost:8083/hotels`
- ![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/hotel-post.png?raw=true)
### Service Endpoints
- **GET:** `/api/hotel/all`
- **POST:** `/api/hotel`

## Booking Service
**Port:** 8081

### API Endpoints
- **GET:** `localhost:8083/bookings`
- ![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/all-bookings.png?raw=true)
- **POST:** `localhost:8083/bookings`
- ![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/booking-post.png?raw=true)
- **DELETE:** `localhost:8083/bookings/{bookingNbr}`
- ![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/booking-delete.png?raw=true)

### Service Endpoints
- **GET:** `/api/booking/all`
- **POST:** `/api/booking`
- **DELETE:** `/bookings/{bookingNbr}`

## Customer Service
**Port:** 8082

### API Endpoints
- **GET:** `localhost:8083/customers`
- ![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/all-customers.png?raw=true)
- **PUT:** `localhost:8083/customers/{customerId}`
- ![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/customer-update.png?raw=true)

### Service Endpoints
- **GET:** `/api/customer/all`
- **PUT:** `/api/customer/{customerId}`
## Schema
![alt text](https://github.com/ThomasMalecki/EnterpriseProject/blob/main/schema.png?raw=true)
## Github Actions
Ik maak gebruik van github actions, dit voert de unit tests, image push, build, ... uit. Deze controle gebeurd bij elke push.
