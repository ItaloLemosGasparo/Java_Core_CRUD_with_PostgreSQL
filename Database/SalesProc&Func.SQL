CREATE OR REPLACE PROCEDURE add_sale(
    p_customer_id INT,
    p_total_value DECIMAL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Sales (CustomerId, Total_Value)
    VALUES (p_customer_id, p_total_value);
END;
$$;

CREATE OR REPLACE PROCEDURE add_sale_item(
    p_sale_id INT,
    p_product_id INT,
    p_quantity INT,
    p_unit_price DECIMAL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Sale_Items (SaleId, ProductId, Quantity, Unit_Price)
    VALUES (p_sale_id, p_product_id, p_quantity, p_unit_price);
END;
$$;

CREATE OR REPLACE FUNCTION view_sales()
RETURNS TABLE (SaleId INT, Sale_Date TIMESTAMP, CustomerId INT, Total_Value DECIMAL)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT s.SaleId, s.Sale_Date, s.CustomerId, s.Total_Value FROM Sales s;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_sale(
    p_sale_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM Sale_Items WHERE SaleId = p_sale_id;
    DELETE FROM Sales WHERE SaleId = p_sale_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_sale(
    p_sale_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM Sale_Items WHERE SaleId = p_sale_id;
    DELETE FROM Sales WHERE SaleId = p_sale_id;
END;
$$;
