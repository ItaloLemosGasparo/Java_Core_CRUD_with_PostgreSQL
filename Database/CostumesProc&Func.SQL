CREATE OR REPLACE PROCEDURE add_customer(
    p_name VARCHAR,
    p_phone VARCHAR,
    p_email VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Customers (Name, Phone, Email)
    VALUES (p_name, p_phone, p_email);
END;
$$;

CREATE OR REPLACE FUNCTION view_customers()
RETURNS TABLE (CustomerId INT, Name VARCHAR, Phone VARCHAR, Email VARCHAR)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT c.CustomerId, c.Name, c.Phone, c.Email FROM Customers c;
END;
$$;

CREATE OR REPLACE PROCEDURE update_customer(
    p_customer_id INT,
    p_name VARCHAR,
    p_phone VARCHAR,
    p_email VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE Customers
    SET Name = p_name,
        Phone = p_phone,
        Email = p_email
    WHERE CustomerId = p_customer_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_customer(
    p_customer_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM Customers WHERE CustomerId = p_customer_id;
END;
$$;
