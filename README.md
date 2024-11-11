# Java Core CRUD with PostgreSQL

This project is a simple **Java CRUD application** that interacts with a **PostgreSQL** database. The application allows
you to perform basic operations (Create, Read, Update, and Delete) on four entities: **Categories**, **Products**, **Customers**, and **Sales**.

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

<img src="https://i.ibb.co/1v0SHh0/Diagrama-sem-nome-drawio.png" alt="Database Structure">

## System Architecture

This system follows a simple architecture:

- **Java** for the application logic.
- **PostgreSQL** as the relational database for storing data.
- **ElephantSQL** for hosting the PostgreSQL database online, ensuring easy access and management of the database.

## Custom Database Interaction

In this project, we’ve opted to manually manage database interactions rather than using an ORM framework like Hibernate.
This is done through custom classes:

- **`SqlDbType.java`**: Defines the various SQL data types used in the procedures.
- **`SqlParameter.java`**: Encapsulates the parameters used in SQL queries, allowing for a more structured approach to
  handling inputs.
- **`Queries.java`**: Handles the execution of SQL queries and procedures, providing an abstraction for common database
  operations.

This approach allows for fine-grained control over the interaction with the PostgreSQL database while maintaining code
readability and reusability.

## Main Menu

The application starts with a main menu allowing users to choose between managing categories, products, customers, or
sales.

<img src="https://i.ibb.co/1Qn4pCf/imagem-2024-11-10-192955527.png" alt="Main Manu">

---

<img src="https://i.ibb.co/b3SCs09/imagem-2024-11-10-193916081.png" alt="Costumer Menu">

---

<img src="https://i.ibb.co/tpPtX65/imagem-2024-11-10-194113985.png" alt="Costumer Menu">

## Usage

- The application provides an interactive terminal menu that allows users to perform CRUD operations on the data.
- Each feature prompts the user for necessary input (e.g., category names, product prices, customer details).

## Technologies Used

- **Java**: The primary programming language used for the application logic.
- **PostgreSQL**: The relational database used for storing application data.
- **ElephantSQL**: A cloud-based PostgreSQL database service used for hosting the database.

## Acknowledgments

- **ElephantSQL**: For providing an easy-to-use and free cloud-hosted PostgreSQL service.  
  **Important Announcement**: ElephantSQL will discontinue its services. The product will reach its End of Life on *
  *January 27, 2025**.


