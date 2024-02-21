package IStoreApp.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StoreTest {
    private Store store;

    @Before
    public void setUp() {
        store = new Store("Magasin A", 1);
    }

    @Test
    public void testGetName() {
        assertEquals("Magasin A", store.getName());
    }

    @Test
    public void testGetId() {
        assertEquals(1, store.getId());
    }

    @Test
    public void testSetName() {
        store.setName("Magasin B");
        assertEquals("Magasin B", store.getName());
    }
}

