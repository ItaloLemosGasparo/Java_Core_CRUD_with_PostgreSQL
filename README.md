# Java CRUD with PostgreSQL

This project is a simple **Java CRUD application** that interacts with a **PostgreSQL** database. The application allows
you to perform basic operations (Create, Read, Update, and Delete) on four entities: **Categories**, **Products**, *
*Customers**, and **Sales**.

The database was built using **PostgreSQL** and is hosted on **ElephantSQL**, a free online PostgreSQL service, to make
it easily accessible.

## Features

- **Category Management**: Add, view, update, and delete categories.
- **Product Management**: Add, view, update, and delete products associated with categories.
- **Customer Management**: Add, view, update, and delete customer records.
- **Sales Management**: Add sales records, view sales, and manage sale items.

## Database Design

The database includes five main tables:

- `Categories`: Stores category details.
- `Products`: Stores product details with a reference to the category.
- `Customers`: Stores customer information.
- `Sales`: Records details of each sale and references customers.
- `Sale_Items`: Stores the items in each sale, with references to both products and sales.

<img src="" alt="Database Structure">

## System Architecture

This system follows a simple architecture:

- **Java** for the application logic.
- **PostgreSQL** as the relational database for storing data.
- **ElephantSQL** for hosting the PostgreSQL database online, ensuring easy access and management of the database.

## Main Menu

The application starts with a main menu allowing users to choose between managing categories, products, customers, or
sales.

<img src="https://i.ibb.co/1Qn4pCf/imagem-2024-11-10-192955527.png" alt="Main Manu">

## Usage

- The application provides an interactive terminal menu that allows users to perform CRUD operations on the data.
- Each feature prompts the user for necessary input (e.g., category names, product prices, customer details).

## Technologies Used

- **Java**: The primary programming language used for the application logic.
- **PostgreSQL**: The relational database used for storing application data.
- **ElephantSQL**: A cloud-based PostgreSQL database service used for hosting the database.

## Acknowledgments

- **ElephantSQL**: For providing an easy-to-use and free cloud-hosted PostgreSQL service.

