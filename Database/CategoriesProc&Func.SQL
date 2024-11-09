CREATE OR REPLACE PROCEDURE add_category(
    p_name VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Categories (Name) VALUES (p_name);
END;
$$;

CREATE OR REPLACE FUNCTION view_categories()
RETURNS TABLE (CategoryId INT, Name VARCHAR)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT c.CategoryId, c.Name FROM Categories c;
END;
$$;


CREATE OR REPLACE PROCEDURE update_category(
    p_category_id INT,
    p_name VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE Categories
    SET Name = p_name
    WHERE CategoryId = p_category_id;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_category(
    p_category_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM Categories WHERE CategoryId = p_category_id;
END;
$$;