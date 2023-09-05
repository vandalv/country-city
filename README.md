## Description
This application empowers users to:
1. Browse Paginated City List with Logos:
2. Effortlessly navigate through a paginated list of cities, each accompanied by their respective logos.
Display Unique City Names:
3. Showcase a collection of unique city names.
4. Retrieve All Cities by Country Name:
5. Access all cities associated with a specific country name.
6. Search by City Name:
7. Utilize a search functionality to find cities based on their names.
8. Edit City Information (Name and Logos):
9. Allow authorized users with the "EDITOR" role to modify city details, including name and logos.

## Application Build And Run
Open root folder and run commands in terminal:
```sh
 ./gradlew build
```

```sh
docker-compose up
```

## Important
Navigate to swagger to see controller description - http://localhost:8737/swagger-ui/index.html#/
By default server.port = 8737 is used.

## Controllers
Shows page with cities where page - number of page and size - number of elements on page:
```sh
curl -X GET "http://localhost:8737/cities?page=0&size=2"
```
Displays unique city names:
```sh
curl -X GET "http://localhost:8737/cities/unique"
```
Get cities by country names:
```sh
curl -X GET "http://localhost:8737/cities/country?name=france"
```
Search city by name:
```sh
curl -X GET "http://localhost:8737/cities/search?name=paris"
```
Create User. Important!!! - Default role of created user - USER:
```sh
curl -X POST "http://localhost:8737/users/register" -H "Content-Type: application/json" -d '{
    "email": "1@example.com",
    "password": "12345"
}'
```
Generate token for user. Importamt that user has EDITOR role:
```sh
curl -X POST "http://localhost:8737/users/authenticate" -H "Content-Type: application/json" -d '{
    "email": "2@example.com",
    "password": "12345"
}'
```
Update city name and logo. Requires Bearer token generated in previous step:
```sh
curl -X PUT "http://localhost:8731/cities/1/edit?name=Paris&logo=qBw9snD/Szczecin-PL.png" -H "Authorization: Bearer TOKEN_HERE"
}'
```
