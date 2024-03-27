Spring-Boot-app
Backend project E-Commerce App This is a sample e-commerce application developed using Spring Boot. The application provides functionality for managing products, orders, and user authentication.

Prerequisites Before running the application, make sure you have the following prerequisites installed:

Java Development Kit (JDK) 21, Maven, PostgreSQL database

Description and project specification: This is Java Spring Boot (Backend) , for storing data I used PostgreSQL (All the necessary SQL queries for creating and loading each table will be published in the data.sql file).

User Registration: Users can register on the system by providing their information and selecting their role as a Admin or Customer. The system also includes a pre-defined Administrator user. During registration, the user's password is encrypted and stored securely.

Login and Logout: Users can log in to the system using their username and password. The login form also includes a registration link for new users. Users can also logout from the system. User authentication is performed using credentials, and user authorization is implemented using a token-based mechanism.

Product List Management: User can view all the products they have listed. User have the option to update or delete individual products. Deleting a product removes it from the product list, while updating a product redirects the seller to a dedicated page where they can modify the product's attributes using a form.

Password and Personal Data Change: Users can change their password and update their personal information. After successfully changing the password, the user is redirected to their profile page.


User can search base on product or order
