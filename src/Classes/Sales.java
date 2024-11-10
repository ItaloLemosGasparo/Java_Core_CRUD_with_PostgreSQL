package Classes;

import Globals.SqlParameter;
import Globals.SqlDbType;
import Globals.Queries;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sales {

    // Adds a new sale to the database
    public static void addSale(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Enter total sale value: ");
        double totalValue = scanner.nextDouble();

        // Parameters for the add sale procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_customer_id", SqlDbType.INTEGER, customerId));
        params.add(new SqlParameter("p_total_value", SqlDbType.DECIMAL, totalValue));

        // Execute the procedure in the database
        Queries.executeProcedure("add_sale", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Sale added successfully!" : "Error adding sale: " + Queries.getClassReturnMessage());
    }

    // Adds an item to an existing sale in the database
    public static void addSaleItem(Scanner scanner) {
        System.out.print("Enter sale ID: ");
        int saleId = scanner.nextInt();

        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();

        // Parameters for the add sale item procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_sale_id", SqlDbType.INTEGER, saleId));
        params.add(new SqlParameter("p_product_id", SqlDbType.INTEGER, productId));
        params.add(new SqlParameter("p_quantity", SqlDbType.INTEGER, quantity));
        params.add(new SqlParameter("p_unit_price", SqlDbType.DECIMAL, unitPrice));

        // Execute the procedure in the database
        Queries.executeProcedure("add_sale_item", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Sale item added successfully!" : "Error adding sale item: " + Queries.getClassReturnMessage());
    }

    // Displays all sales records from the database
    public static void viewSales() {
        List<List<Object>> sales = Queries.fetchFunctionResults("view_sales", new ArrayList<>(), 30);

        if (sales.isEmpty()) {
            System.out.println("No sales found.");
        } else {
            System.out.println("\nSales:");
            System.out.println("ID\tDate\t\tCustomer ID\tTotal Value");
            for (List<Object> row : sales) {
                System.out.println(row.get(0) + "\t" + row.get(1) + "\t" + row.get(2) + "\t" + row.get(3));
            }
        }
    }

    // Displays items related to a specific sale
    public static void viewSaleItems(Scanner scanner) {
        System.out.print("Enter sale ID to view items: ");
        int saleId = scanner.nextInt();

        // Parameter for the function that retrieves sale items
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_sale_id", SqlDbType.INTEGER, saleId));

        List<List<Object>> saleItems = Queries.fetchFunctionResults("view_sale_items", params, 30);

        if (saleItems.isEmpty()) {
            System.out.println("No items found for this sale.");
        } else {
            System.out.println("\nSale Items:");
            System.out.println("Item ID\tProduct ID\tQuantity\tUnit Price");
            for (List<Object> row : saleItems) {
                System.out.println(row.get(0) + "\t" + row.get(1) + "\t" + row.get(2) + "\t" + row.get(3));
            }
        }
    }

    // Deletes a sale record from the database
    public static void deleteSale(Scanner scanner) {
        System.out.print("Enter sale ID to delete: ");
        int saleId = scanner.nextInt();

        // Parameter for the delete sale procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_sale_id", SqlDbType.INTEGER, saleId));

        // Execute the procedure in the database
        Queries.executeProcedure("delete_sale", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Sale deleted successfully!" : "Error deleting sale: " + Queries.getClassReturnMessage());
    }
}
