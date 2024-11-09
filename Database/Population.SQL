-- Populando a tabela Categories
INSERT INTO Categories (Name) VALUES ('Bebidas');
INSERT INTO Categories (Name) VALUES ('Alimentos');
INSERT INTO Categories (Name) VALUES ('Higiene');
INSERT INTO Categories (Name) VALUES ('Limpeza');

-- Populando a tabela Products
INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId) VALUES ('Refrigerante', 5.00, 50, 1);
INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId) VALUES ('Suco', 7.50, 30, 1);
INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId) VALUES ('Arroz', 20.00, 100, 2);
INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId) VALUES ('Feijão', 12.00, 80, 2);
INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId) VALUES ('Sabonete', 3.00, 200, 3);
INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId) VALUES ('Detergente', 2.50, 150, 4);

-- Populando a tabela Customers
INSERT INTO Customers (Name, Phone, Email) VALUES ('João Silva', '11999999999', 'joao.silva@example.com');
INSERT INTO Customers (Name, Phone, Email) VALUES ('Maria Oliveira', '11888888888', 'maria.oliveira@example.com');
INSERT INTO Customers (Name, Phone, Email) VALUES ('Carlos Souza', '11777777777', 'carlos.souza@example.com');

-- Populando a tabela Sales
INSERT INTO Sales (CustomerId, Total_Value) VALUES (1, 25.00);
INSERT INTO Sales (CustomerId, Total_Value) VALUES (2, 45.50);
INSERT INTO Sales (CustomerId, Total_Value) VALUES (3, 10.00);

-- Populando a tabela Sale_Items
INSERT INTO Sale_Items (SaleId, ProductId, Quantity, Unit_Price) VALUES (1, 1, 2, 5.00); -- 2 Refrigerantes
INSERT INTO Sale_Items (SaleId, ProductId, Quantity, Unit_Price) VALUES (1, 3, 1, 20.00); -- 1 Arroz
INSERT INTO Sale_Items (SaleId, ProductId, Quantity, Unit_Price) VALUES (2, 2, 3, 7.50); -- 3 Sucos
INSERT INTO Sale_Items (SaleId, ProductId, Quantity, Unit_Price) VALUES (2, 4, 2, 12.00); -- 2 Feijões
INSERT INTO Sale_Items (SaleId, ProductId, Quantity, Unit_Price) VALUES (3, 5, 2, 3.00); -- 2 Sabonetes
