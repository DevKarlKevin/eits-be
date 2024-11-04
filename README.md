# EitsBe

## Introduction
EITS-BE is the backend module of the E-ITS system, providing RESTful APIs for content management.
It is built using Spring Boot and Java.
Its main purpose is to act as a proxy for interacting with the [API](https://eits.ria.ee/swagger-ui/index.html)

## Technical description
EITS-BE facilitates data retrieval for the frontend (EITS-FE) through two main flows:

1. Catalog-Based Retrieval:
   * Fetches catalog data using the `catalog/{version}` endpoint, maps it, and returns it to the frontend.
   * Retrieves measures for a selected catalog group using the `article/{version}/{id}` endpoint.
2. Content Tree Retrieval:
   * Queries the entire content tree through the `content_tree` endpoint.
   * Parses and maps the data to provide hierarchical groups with associated measures for the frontend.


### Key Design Choices
The project utilizes Domain-Driven (DD) package groupings and DTOs, along with patterns like builders, records, and Lombok annotations.
The inclusion of these patterns serves as examples of different implementation styles, though typically a single approach would be chosen in a production environment for consistency.  

# Running the application
## Prerequisites 
* Gradle
* Java21
* Docker

Once you have them and you have cloned the project, run `./gradlew build`.

Then run the Docker container using `docker-compose up --build`

Then you can continue with setting up EITS-FE application