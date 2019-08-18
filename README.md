# **Cinema**
Cinema "GlowStories" 

A web application where a simple user can sign up,sing in, buy a ticket. An admin can add ticket, movie,rooms for watching movies. 

## Developer

IHOR KULPEKIN

##


## Technologies

* Java;
* Thymeleaf;
* MySQL;
* Spring Boot;
* Spring Security;
* Spring Data;
* Spring Mail;
* Hibernate;

## Frontend
To display site to users we use the Thymeleaf engine.It is the same HTML, but it has more functional than a simple HTML.
It has own attributes. To our pages were understanding for users we use CSS.

All views are located in the resources folder.

## Security

To set up the security our application we make class "SecurityConfiguration" in folder which is called configuration.
To limit the access to admin page or user page, we use this method"protected void configure(HttpSecurity http)...".

".antMatchers("/").permitAll()" means all users can see this page.

".antMatchers("/admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")" means admin only see this page

## Layers

Our application have three layers: repository, service and controller.

All of them layers have own interfaces and implementations.
