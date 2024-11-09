-- Inserir Produto
CREATE OR REPLACE PROCEDURE insert_product(prod_name VARCHAR, prod_price DECIMAL, stock_qty INT, cat_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO Products (Name, Price, Stock_Quantity, CategoryId) VALUES (prod_name, prod_price, stock_qty, cat_id);
END;
$$;

-- Listar Produtos
CREATE OR REPLACE FUNCTION get_products()
RETURNS TABLE (ProductId INT, Name VARCHAR, Price DECIMAL, Stock_Quantity INT, CategoryId INT) LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT * FROM Products;
END;
$$;

-- Atualizar Produto
CREATE OR REPLACE PROCEDURE update_product(prod_id INT, new_name VARCHAR, new_price DECIMAL, new_qty INT, new_cat_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE Products SET Name = new_name, Price = new_price, Stock_Quantity = new_qty, CategoryId = new_cat_id WHERE ProductId = prod_id;
END;
$$;

-- Deletar Produto
CREATE OR REPLACE PROCEDURE delete_product(prod_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM Products WHERE ProductId = prod_id;
END;
$$;
