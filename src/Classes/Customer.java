package Classes;

import Globals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {

    // Adds a new customer to the database
    public static void addCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        // Create parameters for the procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));
        params.add(new SqlParameter("p_phone", SqlDbType.VARCHAR, phone));
        params.add(new SqlParameter("p_email", SqlDbType.VARCHAR, email));

        // Execute the procedure in the database
        Queries.executeProcedure("add_customer", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Customer added successfully!" : "Error adding category: " + Queries.getClassReturnMessage());
    }

    // Displays all customers
    public static void viewCustomers() {
        // Fetch customers using function
        List<List<Object>> customers = Queries.fetchFunctionResults("view_customers", new ArrayList<>(), 30);

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("\nCustomers:");
            System.out.println("ID\tName\tPhone\tEmail");
            for (List<Object> row : customers) {
                System.out.println(row.get(0) + "\t" + row.get(1) + "\t" + row.get(2) + "\t" + row.get(3));
            }
        }
    }

    // Updates an existing customer
    public static void updateCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new customer phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter new customer email: ");
        String email = scanner.nextLine();

        // Create parameters for the procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_customer_id", SqlDbType.INTEGER, customerId));
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));
        params.add(new SqlParameter("p_phone", SqlDbType.VARCHAR, phone));
        params.add(new SqlParameter("p_email", SqlDbType.VARCHAR, email));

        // Execute the procedure in the database
        Queries.executeProcedure("update_customer", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Customer updated successfully!" : "Error updating category: " + Queries.getClassReturnMessage());
    }

    // Deletes a customer from the database
    public static void deleteCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();

        // Create parameters for the procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_customer_id", SqlDbType.INTEGER, customerId));

        // Execute the procedure in the database
        Queries.executeProcedure("delete_customer", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Customer deleted successfully!" : "Error deleting category: " + Queries.getClassReturnMessage());
    }
}
