import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final HashMap<String, Integer> inventory = new HashMap<>();

    static void addProduct(String name, int quantity) {
        if (quantity <= 0) {
            throw new UnsupportedOperationException("Quantity must be more than 0!");
        }
        inventory.put(name, quantity);
    }

    static String checkProduct(String name) {
        if (inventory.containsKey(name)) {
            return name + " is in stock: " + inventory.get(name);
        } else{
            return name + " is not in the inventory";
        }
    }

    static String updateProduct(String name, int newQuantity) {
        if (inventory.containsKey(name)) {
            inventory.put(name, newQuantity);
            return "Stock updated!";
        } else {
            return "Product not found in inventory.";
        }
    }

    private static String removeProduct(String name) {
        if (inventory.containsKey(name)) {
            inventory.remove(name);
            return "Stock updated!";
        } else {
            return "Product not found in inventory.";
        }
    }

    public static Map<String, Integer> getInventory() {
        return new HashMap<>(inventory);
    }

    public static void clearInventory() {
        inventory.clear();
    }

    public static String viewInventory() {
        if (inventory.isEmpty()) {
            return "The inventory is empty.";
        }

        StringBuilder sb = new StringBuilder("Current Inventory: ");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" pcs\n");
        }
        return sb.toString().trim(); // remove trailing newline
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
                    System.out.println(viewInventory());
                    break;
                case 2:
                    System.out.println("Enter product name: ");
                    String nameToAdd = sc.nextLine();
                    System.out.println("Enter quantity: ");
                    int qtyToAdd = sc.nextInt();
                    sc.nextLine();
                    try {
                        addProduct(nameToAdd, qtyToAdd);
                    } catch (UnsupportedOperationException e) {
                        System.out.println("Invalid quantity!");
                    }
                    break;
                case 3:
                    System.out.print("Enter product name to check: ");
                    String nameToCheck = sc.nextLine();
                    System.out.println(checkProduct(nameToCheck));
                    break;
                case 4:
                    System.out.print("Enter product name to update: ");
                    String nameToUpdate = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQty = sc.nextInt();
                    sc.nextLine();
                    System.out.println(updateProduct(nameToUpdate, newQty));
                    break;
                case 5:
                    System.out.print("Enter product name to remove: ");
                    String nameToRemove = sc.nextLine();
                    System.out.println(removeProduct(nameToRemove));
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