import Classes.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Mercadinho System ---");
            System.out.println("1. Categories");
            System.out.println("2. Products");
            System.out.println("3. Customers");
            System.out.println("4. Sales");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    categoryMenu(scanner);
                    break;
                case 2:
                    productMenu(scanner);
                    break;
                case 3:
                    customerMenu(scanner);
                    break;
                case 4:
                    salesMenu(scanner);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Menu for Categories
    private static void categoryMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Categories Menu ---");
            System.out.println("1. Add Category");
            System.out.println("2. View Categories");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Category.addCategory(scanner);
                    break;
                case 2:
                    Category.viewCategories();
                    break;
                case 3:
                    Category.updateCategory(scanner);
                    break;
                case 4:
                    Category.deleteCategory(scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Menu for Products
    private static void productMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Products Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Product.addProduct(scanner);
                    break;
                case 2:
                    Product.viewProducts();
                    break;
                case 3:
                    Product.updateProduct(scanner);
                    break;
                case 4:
                    Product.deleteProduct(scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Menu for Customers
    private static void customerMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Customer.addCustomer(scanner);
                    break;
                case 2:
                    Customer.viewCustomers();
                    break;
                case 3:
                    Customer.updateCustomer(scanner);
                    break;
                case 4:
                    Customer.deleteCustomer(scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Menu for Sales
    private static void salesMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Sales Menu ---");
            System.out.println("1. Add Sale");
            System.out.println("2. View Sales");
            System.out.println("3. View Sale Items");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Sales.addSale(scanner);
                    break;
                case 2:
                    Sales.viewSales();
                    break;
                case 3:
                    Sales.viewSaleItems(scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
