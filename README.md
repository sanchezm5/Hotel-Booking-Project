# Hotel-Booking-Project
This project is a simple system that allows a system to display all available rooms in a hotel, and to update the occupant of a hotel room. This system consists of the following:

A service that sits in front of the library and provides CRUD access to the data.
An edge service that provides an API that shows a list of available rooms, and to update the occupant of a room, and that communicates with the CRUD service via a Feign client.
A Eureka Service Registry. The CRUD service must register with the Eureka service and the edge service must use the Eureka service to locate the CRUD service.


Requirements

CRUD Service
The CRUD microservice must provide create, read, update, and delete functionality for Room entries in the backing database. You must design and implement the REST API that provides these services.

Edge Service

The edge microservice must provide an endpoint that allows customers to see the available hotel rooms. These are the rooms that have no occupant. You must design and implement this endpoint.
The edge microservice must provide an endpoint that allows a user to update the occupant of a room. A room with an occupant can only be updated to have no occupant.


Architecture

The system must incorporate and use the Eureka service registry.
The edge service must use Feign to talk to the CRUD microservice.
We highly encourage you to use JPA for database interaction in the CRUD microservice.


TDD

Follow TDD when building this project.
This includes using MockMvc to test all of the endpoint of both microservices.
This includes JUnit and Mockito for service layer and DAO tests.
