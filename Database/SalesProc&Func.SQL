-- Inserir Venda
CREATE OR REPLACE PROCEDURE insert_sale(cust_id INT, total_val DECIMAL)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO Sales (CustomerId, Total_Value) VALUES (cust_id, total_val);
END;
$$;

-- Listar Vendas
CREATE OR REPLACE FUNCTION get_sales()
RETURNS TABLE (SaleId INT, Sale_Date TIMESTAMP, CustomerId INT, Total_Value DECIMAL) LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT * FROM Sales;
END;
$$;

-- Deletar Venda
CREATE OR REPLACE PROCEDURE delete_sale(sale_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM Sales WHERE SaleId = sale_id;
END;
$$;
