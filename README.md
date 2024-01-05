# Enterprise Development Experience Project

## Gekozen Thema

Dit thema illustreert beknopt de functionaliteit van een hotelboekingssysteem. Een boeking omvat specifieke gegevens zoals het geselecteerde hotel en de betrokken klant. Daarnaast bevat elke boeking informatie over de duur van het verblijf, uitgedrukt in het aantal nachten dat de persoon zal doorbrengen. Hierdoor wordt op een heldere wijze inzicht geboden in het proces van het vastleggen van hotelreserveringen en de relatie tussen hotels, klanten en de verblijfsduur.

## API Gateway
**Port:** 8083

### Authenticatie
Wanneer u niet geauthenticeerd bent, kunt u alleen toegang krijgen tot de lijst met alle hotels. De rest van de services vereist authenticatie. 
In dit voorbeeld word er met auth 2.0 een token gegenereerd en word het id-token dan gebruikt als Bearer-token.

## Hotel Service
Binnen de hotel service heb je de mogelijkheid om een overzicht op te vragen van alle beschikbare hotels. Daarnaast is het ook mogelijk om nieuwe hotels toe te voegen aan de lijst. Deze functionaliteiten stellen gebruikers in staat om snel en eenvoudig informatie over bestaande hotels te verkrijgen en tegelijkertijd het aanbod uit te breiden door nieuwe hotels toe te voegen.

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
Binnen de booking service kun je eenvoudig een nieuwe boeking aanmaken. Elke boeking is voorzien van een uniek boekingsnummer en bevat relevante gegevens zoals het gekozen hotel, de betrokken klant en het aantal nachten van het verblijf. Mocht het nodig zijn, is er ook de mogelijkheid om geplaatste boekingen te verwijderen.

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
In deze service heb je de mogelijkheid om een lijst van alle klanten op te vragen. Indien de klant bestaat, kun je de informatie van deze klant aanpassen. Deze functies stellen gebruikers in staat om efficiënt en nauwkeurig met klantgegevens om te gaan.

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
