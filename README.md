# rental-cars-server
A server-side application of the project.

## Tutorial how to install the project

Installing environment (You need Java 8 to install):

1. Download Tomcat 9 (Core) by [link](http://tomcat.apache.org/download-90.cgi) and unpack it.

2. Download Gradle ver. 3.5 by [link](https://gradle.org/install#manually).

3. Unpack and set path in the environment variable Path to folder */bin*.

## Building project

1. Download or clone project from this repository.

2. In the console, go to the folder with the project and run the command `gradle war`.

3. Copy file `rental-cars-server.war` from folder /build/libs/ to Tomcat's /webapps/.

## Server API

`POST /car/add` - add car to database (accept data in JSON format)

`PUT /car/edit` - edit car in database (accept data in JSON format)

`DELETE /car/delete` - delete car from database (accept data in JSON format)

`DELETE /car/delete/id` - delete car by `id` from database

`GET /car/cars/id` - return car by `id` in JSON format

`GET /car/cars` - return all cars in JSON format

`POST /car/filter` - return filtered cars JSON format (Filtered by @RequestBody "Car")

`POST /order/add` - add order to database (accept data in JSON format)

`PUT /order/edit` - edit order in database (accept data in JSON format)

`DELETE /order/delete` - delete order from database (accept data in JSON format)

`DELETE /order/delete/id` - delete order by `id` from database

`GET /order/orders/id` - return order by `id` in JSON format

`GET /order/orders` - return all orders in JSON format

`POST /user/register` - add user to database (accept data in JSON format)

`PUT /user/edit` - edit user in database (accept data in JSON format)

`DELETE /user/delete` - delete user from database (accept data in JSON format)

`DELETE /user/delete/id` - delete user by `id` from database

`GET /user/users/id` - return user by `id` in JSON format

`GET /user/users` - return all users in JSON format

`POST /auth/login` - user authentication (accept data in JSON format), return JWT-token for user