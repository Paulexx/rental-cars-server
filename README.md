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

`GET /car/cars/id` - return car by `id` in JSON format

`GET /car/cars` - return all cars in JSON format

`POST /order/add` - add order to database (accept data in JSON format)

`PUT /order/edit` - edit order in database (accept data in JSON format)

`DELETE /order/delete` - delete order from database (accept data in JSON format)

`GET /order/orders/id` - return order by `id` in JSON format

`GET /order/orders` - return all orders in JSON format

`POST /user/register` - add user to database (accept data in JSON format)

`PUT /user/edit` - edit user in database (accept data in JSON format)

`DELETE /user/delete` - delete user from database (accept data in JSON format)

`GET /user/users/id` - return user by `id` in JSON format

`GET /user/users` - return all users in JSON format