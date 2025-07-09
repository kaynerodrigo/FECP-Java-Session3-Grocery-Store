import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
        Main.clearInventory();
    }

    // Add Product Tests

    @Test
    void testAddProductValid() {
        Main.addProduct("Banana", 30);
        assertEquals(30, Main.getInventory().get("Banana"));
    }

    @Test
    void testProductWithZeroQuantity() {
        Main main = new Main();
        assertThrows(UnsupportedOperationException.class, () -> main.addProduct("Mango", 0));
    }

    @Test
    void testAddProductOverwriteExisting() {
        Main.addProduct("Milk", 10);
        assertEquals(10, Main.getInventory().get("Milk"));
        Main.addProduct("Milk", 50); // Overwrite
        assertEquals(50, Main.getInventory().get("Milk"));
    }

    // Check Product Test
    @Test
    void testCheckExistingProduct() {
        Main.addProduct("Milk", 20);
        String result = Main.checkProduct("Milk");
        assertEquals("Milk is in stock: 20", result);
    }

    @Test
    void testCheckNonExistingProduct() {
        String result = Main.checkProduct("Ice Cream");
        assertEquals("Ice Cream is not in the inventory", result);
    }

    // Update
    @Test
    void testUpdateExistingProduct() {
        Main.addProduct("Bread", 10);
        String result = Main.updateProduct("Bread", 25);
        assertEquals("Stock updated!", result);
        assertEquals(25, Main.getInventory().get("Bread"));
    }

    @Test
    void testUpdateNonExistingProduct() {
        String result = Main.updateProduct("Tofu", 15);
        assertEquals("Product not found in inventory.", result);
    }

    // Remove products
    @Test
    void testViewEmptyInventory() {
        String result = Main.viewInventory();
        assertEquals("The inventory is empty.",result);
    }

    @Test
    void testViewNonEmptyInventory() {
        Main.addProduct("Rice", 10);
        Main.addProduct("Eggs", 5);
        String result = Main.viewInventory();

        assertTrue(result.startsWith("Current Inventory: "));
        assertTrue(result.contains("Rice - 10 pcs"));
        assertTrue(result.contains("Eggs - 5 pcs"));
    }

    // sanity test
    void testGetInventoryReturnsCopy() {
        Main.addProduct("Oil", 5);
        Map<String, Integer> copy = Main.getInventory();
        copy.put("Water", 10); // should not affect original
        assertFalse(Main.getInventory().containsKey("Water"));
    }


}