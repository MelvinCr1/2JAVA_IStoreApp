package IStoreApp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
    private Inventory inventory;
    private Item item1;
    private Item item2;

    @Before
    public void setUp() {
        inventory = new Inventory(1);
        item1 = new Item("Ordinateur", 999.99, 5, "mag1");
        item2 = new Item("Telephone", 699.99, 10, "mag2");
    }

    @Test
    public void testAddItem() {
        inventory.addItem(item1);
        List<Item> items = inventory.getItems();
        assertTrue(items.contains(item1));
    }

    @Test
    public void testRemoveItem() {
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.removeItem(item1);
        List<Item> items = inventory.getItems();
        assertFalse(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    @Test
    public void testGetStoreId() {
        assertEquals(1, inventory.getStoreId());
    }

    @Test
    public void testSetId() {
        inventory.setId(10);
        assertEquals(10, inventory.getId());
    }
}