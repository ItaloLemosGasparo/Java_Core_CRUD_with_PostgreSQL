-- Inserir Cliente
CREATE OR REPLACE PROCEDURE insert_customer(cust_name VARCHAR, cust_phone VARCHAR, cust_email VARCHAR)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO Customers (Name, Phone, Email) VALUES (cust_name, cust_phone, cust_email);
END;
$$;

-- Listar Clientes
CREATE OR REPLACE FUNCTION get_customers()
RETURNS TABLE (CustomerId INT, Name VARCHAR, Phone VARCHAR, Email VARCHAR) LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT * FROM Customers;
END;
$$;

-- Atualizar Cliente
CREATE OR REPLACE PROCEDURE update_customer(cust_id INT, new_name VARCHAR, new_phone VARCHAR, new_email VARCHAR)
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE Customers SET Name = new_name, Phone = new_phone, Email = new_email WHERE CustomerId = cust_id;
END;
$$;

-- Deletar Cliente
CREATE OR REPLACE PROCEDURE delete_customer(cust_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM Customers WHERE CustomerId = cust_id;
END;
$$;
