CREATE TABLE Categories (
    CategoryId SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE Products (
    ProductId SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Stock_Quantity INT NOT NULL,
    CategoryId INT,
    FOREIGN KEY (CategoryId) REFERENCES Categories(CategoryId)
);

CREATE TABLE Customers (
    CustomerId SERIAL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Phone VARCHAR(15),
    Email VARCHAR(100)
);

CREATE TABLE Sales (
    SaleId SERIAL PRIMARY KEY,
    Sale_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CustomerId INT,
    Total_Value DECIMAL(10, 2),
    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId)
);

CREATE TABLE Sale_Items (
    SaleItemId SERIAL PRIMARY KEY,
    SaleId INT,
    ProductId INT,
    Quantity INT NOT NULL,
    Unit_Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (SaleId) REFERENCES Sales(SaleId),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId)
);
