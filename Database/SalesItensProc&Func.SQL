-- Inserir Item de Venda
CREATE OR REPLACE PROCEDURE insert_sale_item(sale_id INT, prod_id INT, qty INT, unit_price DECIMAL)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO Sale_Items (SaleId, ProductId, Quantity, Unit_Price) VALUES (sale_id, prod_id, qty, unit_price);
END;
$$;

-- Listar Itens de Venda para uma Venda Específica
CREATE OR REPLACE FUNCTION get_sale_items(sale_id INT)
RETURNS TABLE (SaleItemId INT, SaleId INT, ProductId INT, Quantity INT, Unit_Price DECIMAL) LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT * FROM Sale_Items WHERE SaleId = sale_id;
END;
$$;

-- Deletar Item de Venda
CREATE OR REPLACE PROCEDURE delete_sale_item(sale_item_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM Sale_Items WHERE SaleItemId = sale_item_id;
END;
$$;
