CREATE OR REPLACE PROCEDURE add_product(
    p_name VARCHAR,
    p_price DECIMAL,
    p_stock_quantity INT,
    p_category_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId)
    VALUES (p_name, p_price, p_stock_quantity, p_category_id);
END;
$$;

CREATE OR REPLACE FUNCTION view_products()
RETURNS TABLE (ProductId INT, Name VARCHAR, Price DECIMAL, Stock_Quantity INT, CategoryId INT)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT p.ProductId, p.Name, p.Price, p.Stock_Quantity, p.CategoryId FROM Products p;
END;
$$;

CREATE OR REPLACE PROCEDURE update_product(
    p_product_id INT,
    p_name VARCHAR,
    p_price DECIMAL,
    p_stock_quantity INT,
    p_category_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE Products
    SET Name = p_name,
        Price = p_price,
        Stock_Quantity = p_stock_quantity,
        CategoryId = p_category_id
    WHERE ProductId = p_product_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_product(
    p_product_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM Products WHERE ProductId = p_product_id;
END;
$$;