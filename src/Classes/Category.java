package Classes;

import Globals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category {

    // Adds a new category to the database
    public static void addCategory(Scanner scanner) {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();

        // Create parameters for the procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));

        // Execute the procedure in the database
        Queries.executeProcedure("add_category", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Category added successfully!" : "Error adding category: " + Queries.getClassReturnMessage());
    }

    // Displays all categories
    public static void viewCategories() {
        // Fetch categories using procedure
        List<List<Object>> categories = Queries.fetchProcedureResults("view_categories", new ArrayList<>(), 30);

        if (categories.isEmpty()) {
            System.out.println("No categories found.");
        } else {
            System.out.println("\nCategories:");
            System.out.println("ID\tName");
            for (List<Object> row : categories) {
                System.out.println(row.get(0) + "\t" + row.get(1));
            }
        }
    }

    // Updates an existing category
    public static void updateCategory(Scanner scanner) {
        System.out.print("Enter category ID to update: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new category name: ");
        String name = scanner.nextLine();

        // Create parameters for the procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_category_id", SqlDbType.INTEGER, categoryId));
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));

        // Execute the procedure in the database
        Queries.executeProcedure("update_category", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Category updated successfully!" : "Error updating category: " + Queries.getClassReturnMessage());
    }

    // Deletes a category from the database
    public static void deleteCategory(Scanner scanner) {
        System.out.print("Enter category ID to delete: ");
        int categoryId = scanner.nextInt();

        // Create parameters for the procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_category_id", SqlDbType.INTEGER, categoryId));

        // Execute the procedure in the database
        Queries.executeProcedure("delete_category", params, 30);
        System.out.println(Queries.getClassReturnMessage().equals("OK") ? "Category deleted successfully!" : "Error deleting category: " + Queries.getClassReturnMessage());
    }
}
