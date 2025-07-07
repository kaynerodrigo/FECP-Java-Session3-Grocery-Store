import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final HashMap<String, Integer> inventory = new HashMap<>();

    private static void addProduct(Scanner sc) {
        System.out.print("Enter product name: ");
        String productName = sc.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        inventory.put(productName, quantity);
        System.out.println("Product added!");
    }

    private static void checkProduct(Scanner sc) {
        System.out.print("Enter product name to check: ");
        String productName = sc.nextLine();

        if (inventory.containsKey(productName)) {
            System.out.println(productName + " is in stock: " + inventory.get(productName));
        } else {
            System.out.println(productName + " is not in the inventory.");
        }
    }

    private static void updateProduct(Scanner sc) {
        System.out.print("Enter product name to update: ");
        String productName = sc.nextLine();

        if (inventory.containsKey(productName)) {
            System.out.print("Enter new stock quantity: ");
            int newQuantity = sc.nextInt();
            sc.nextLine();

            inventory.put(productName, newQuantity);
            System.out.println("Stock updated!");
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    private static void removeProduct(Scanner sc) {
        System.out.print("Enter product name to remove: ");
        String productName = sc.nextLine();

        if (inventory.containsKey(productName)) {
            inventory.remove(productName);
            System.out.println("Product removed.");
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    private static void viewInventory() {
        System.out.println("Current Inventory:");
        if (inventory.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " pcs");
            }
        }
    }

    private static void showInterfaceMenu() {
        System.out.println("\n--- Grocery Inventory Menu ---");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Product");
        System.out.println("3. Check Product");
        System.out.println("4. Update Stock");
        System.out.println("5. Remove Product");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userChoice;

        do {
            showInterfaceMenu();
            userChoice = sc.nextInt();
            sc.nextLine();

            switch (userChoice) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    addProduct(sc);
                    break;
                case 3:
                    checkProduct(sc);
                    break;
                case 4:
                    updateProduct(sc);
                    break;
                case 5:
                    removeProduct(sc);
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        } while (userChoice != 6);

        sc.close();
    }
}