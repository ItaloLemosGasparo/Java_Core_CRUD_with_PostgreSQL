package Classes;

import Globals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category {

    // Função para adicionar uma nova categoria
    public static void addCategory(Scanner scanner) {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();

        // Criação dos parâmetros para a procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));

        // Executa a procedure no banco de dados
        Queries.executeProcedure("add_category", params, 30);

        if (Queries.getClassReturnMessage().equals("OK")) {
            System.out.println("Category added successfully!");
        } else {
            System.out.println("Error adding category: " + Queries.getClassReturnMessage());
        }
    }

    // Função para exibir todas as categorias
    public static void viewCategories() {
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

    // Função para atualizar uma categoria
    public static void updateCategory(Scanner scanner) {
        System.out.print("Enter category ID to update: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new category name: ");
        String name = scanner.nextLine();

        // Criação dos parâmetros para a procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_category_id", SqlDbType.INTEGER, categoryId));
        params.add(new SqlParameter("p_name", SqlDbType.VARCHAR, name));

        // Executa a procedure no banco de dados
        Queries.executeProcedure("update_category", params, 30);

        if (Queries.getClassReturnMessage().equals("OK")) {
            System.out.println("Category updated successfully!");
        } else {
            System.out.println("Error updating category: " + Queries.getClassReturnMessage());
        }
    }

    // Função para deletar uma categoria
    public static void deleteCategory(Scanner scanner) {
        System.out.print("Enter category ID to delete: ");
        int categoryId = scanner.nextInt();

        // Criação dos parâmetros para a procedure
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlParameter("p_category_id", SqlDbType.INTEGER, categoryId));

        // Executa a procedure no banco de dados
        Queries.executeProcedure("delete_category", params, 30);

        if (Queries.getClassReturnMessage().equals("OK")) {
            System.out.println("Category deleted successfully!");
        } else {
            System.out.println("Error deleting category: " + Queries.getClassReturnMessage());
        }
    }
}
