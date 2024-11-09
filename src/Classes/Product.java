package Classes;

import Globals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {

    // Function to insert a new product
    public static void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter stock quantity: ");
        int stockQuantity = scanner.nextInt();

        System.out.print("Enter category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Create parameters list for procedure call
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));
        params.add(new SqlParameter("p_price", SqlDbType.DECIMAL, price));
        params.add(new SqlParameter("p_stock_quantity", SqlDbType.INTEGER, stockQuantity));
        params.add(new SqlParameter("p_category_id", SqlDbType.INTEGER, categoryId));

        // Execute the procedure to add the product
        Queries.executeProcedure("add_product", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Product added successfully!" : "Error adding Product: " + Queries.getClassReturnMessage());
    }

    // Function to display all products
    public static void viewProducts() {
        // Execute the function to fetch products
        List<List<Object>> products = Queries.fetchFunctionResults("view_products", new ArrayList<>(), 30);

        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("\nProducts:");
            System.out.println("ID\tName\tPrice\tStock\tCategory ID");
            for (List<Object> row : products) {
                System.out.println(row.get(0) + "\t" + row.get(1) + "\t" + row.get(2) + "\t" + row.get(3) + "\t" + row.get(4));
            }
        }
    }

    // Function to update an existing product
    public static void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter new stock quantity: ");
        int stockQuantity = scanner.nextInt();

        System.out.print("Enter new category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Create parameters list for procedure call
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_product_id", SqlDbType.INTEGER, productId));
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));
        params.add(new SqlParameter("p_price", SqlDbType.DECIMAL, price));
        params.add(new SqlParameter("p_stock_quantity", SqlDbType.INTEGER, stockQuantity));
        params.add(new SqlParameter("p_category_id", SqlDbType.INTEGER, categoryId));

        // Execute the procedure to update the product
        Queries.executeProcedure("update_product", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Product updated successfully!" : "Error updating Product: " + Queries.getClassReturnMessage());
    }

    // Function to delete a product
    public static void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();

        // Create parameters list for procedure call
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_product_id", SqlDbType.INTEGER, productId));

        // Execute the procedure to delete the product
        Queries.executeProcedure("delete_product", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Product deleted successfully!" : "Error deleting Product: " + Queries.getClassReturnMessage());
    }
}