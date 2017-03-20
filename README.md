# cart-challenge

This is a sample Git repo that I am using for the cart challenge.

The following dependencies are necessary:

    Java 8
    Node 0.12 or higher
    bower
    maven 3

Development was done using STS using AngularJS and SpringMVC frameworks. Spring Boot and built-in server is used for unit testing and development.

After cloning the repository, the following command installs the Javascript dependencies:

bower install

After the server starts, the application is accessible at the following URL:
http://localhost:8080/

To authenticate with an existing user, login with the following credentials:

username: test123 / password: Password0

Frontend Overview

The sample project is a web application with an AngularJs-based frontend.


Backend Overview

The backend is based on Java 8, Spring MVC, MongoDB. The Spring configuration is based on Java. The main Spring modules used where Spring MVC. Spring Security will be used in the future to secure REST APIs. The backend was built using the DDD approach, which includes a domain model, services, repositories and DTOs for frontend/backend data transfer. We ran into few limitations of Spring Boot (annotations?) and were forced to place controllers, services and repositories into the same Java package, namely: com.vb.cart.controllers. More investigation is needed to sort out those issues and place them into separate Java packages.

The REST web services are based on Spring MVC and JSON. The unit tests are made with spring test and the REST API functional tests where made using Spring test MVC.


Users REST API
--------------
/users          |  GET  | Get a list of registered users
/users/:userId  |  GET  | Get user that matches {userId} path parameter
/users/login    | POST  | Authenticate the user

Phones REST API
---------------
/phones/list          |  GET  | Get a list of all phones in the database
/phones/list/:phoneId |  GET  | Get phone that matches {phoneId} path parameter
/phones/order         | POST  | Submit user order that contains one or more phones


Frontend validation is for user convenience only, more robust validation logic was also added to the backend. The use of Angular gives good protection against common problems like cross-site scripting or HTML injection. 

Test Automation
Front end tests cover mostly controller logic and use Karma + Jasmine
Back end test drive SpringMVC controllers via SpringBootTest framework
