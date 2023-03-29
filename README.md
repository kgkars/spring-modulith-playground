# Spring Modulith Playground

This is a playground project based on the [Spring Modulith](https://docs.spring.io/spring-modulith/docs/0.4.0/reference/html/) project.

The project leverages the approach outlined in the **JChampions Conference** demonstration, [Spring Modulith - Spring for the Architecturally Curious Developer](https://www.youtube.com/watch?v=jWCuFPT0640), and follows the rough structure that can be found in the presenter's demo repo [here](https://github.com/odrotbohm/arch-evident-spring).

## Database
The Database is configured to use PostgreSQL from a local Docker container. You can start the container by running `docker-compose up -d` inside the project's root directory. A simple PostreSQL database and associated PG-ADMIN pod will be created for you.

> NOTE: The `jpa_publication_event` table does not get created with the correct column types from the `spring-modulith-starter-jpa` package. You may need to update the column types to `TEXT` to allow events to be registered correctly.

## Progress

The playground is built in a layered approach, leveraging feature branches for each layer.

### Step 1

Branch `step-1` covers the following:

 - Create `user` module.


### Step 2

Branch `step-2` covers the following:

 - Set up `auth` module and Spring Security.
 - Set up events for registering new users.

> NOTE: Given authentication does not have an entity of its own, events need to be published using the `ApplicationEventPublisher`. Native `DomainEvents` only trigger when a repository's `.save()` call is made.


### Step 3

Branch `step-3` covers the following:

 - Add Audit tracking to database.
 - Set up historical tables to track changes.

