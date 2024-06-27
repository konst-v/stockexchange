# Stock exchange store application
## Prerequisirtes
Application and auxiliary services are running inside Docker containers. To build and run application, Docker must be installed(for local environment - Docker desktop).

## Building the application
Open terminal and execute the following commands:
```
docker build -t stockexchange-img -f Dockerfile .
```
This command will build Docker image with application.
To run the application execute command
```
docker-compose up -d
```
This will start containers with Postgres, Kafka and application. Application will listen on port 8080. Exposed port number can be changed in docker-compose.yml via services.app.ports setting.

## API description
Below examples are applicable to local host deployment with standard port. Host and port can be different depending on installation.
All requests must have content-type:application/json in header

API provides following possibilities:
### Create item
method: ```POST```

url: ```127.0.0.1:8080/api/item```

body(all fields are mandatory):
```
{
    "name": "item1",
    "price": 14,
    "quantity": 1
}
```
response body:
```
{
    "id": 5,
    "name": "item1",
    "price": 14,
    "quantity": 1,
    "status": "AVAILABLE"
}
```

### Update item
method: ```PATCH```

url: ```127.0.0.1:8080/api/item```

body(id is mandatory):
```
{
    "id": 5,
    "name": "item44",
    "price": 22,
    "quantity": 3
}
```
response body:
```
{
    "id": 5,
    "name": "item44",
    "price": 22,
    "quantity": 3,
    "status": "AVAILABLE"
}
```

### Delete item
method: ```DELETE```

url: ```127.0.0.1:8080/api/item/{item id}```

### Sell item
This method will mark item as sold and notify consumers via Kafka about time, quantity and last price. Message will be received and logged to transaction table.

method: ```POST```

url: ```127.0.0.1:8080/api/item/sell/{item id}```
response body:
```
{
    "id": 5,
    "name": "item44",
    "price": 22.00,
    "quantity": 3,
    "status": "SOLD"
}
```
