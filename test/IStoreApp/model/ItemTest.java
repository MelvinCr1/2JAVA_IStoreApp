package IStoreApp.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
    private Item item;

    @Before
    public void setUp() {
        item = new Item("Laptop", 999.99, 5, "Store1");
    }

    @Test
    public void testGetName() {
        assertEquals("Laptop", item.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(999.99, item.getPrice(), 0.01);
    }

    @Test
    public void testGetQuantity() {
        assertEquals(5, item.getQuantity());
    }

    @Test
    public void testGetStore() {
        assertEquals("Store1", item.getStore());
    }

    @Test
    public void testSetName() {
        item.setName("Smartphone");
        assertEquals("Smartphone", item.getName());
    }

    @Test
    public void testSetPrice() {
        item.setPrice(799.99);
        assertEquals(799.99, item.getPrice(), 0.01);
    }

    @Test
    public void testSetQuantity() {
        item.setQuantity(10);
        assertEquals(10, item.getQuantity());
    }
}