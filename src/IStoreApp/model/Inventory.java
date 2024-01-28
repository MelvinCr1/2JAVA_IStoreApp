package IStoreApp.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int storeId;
    private List<Item> items;
    private int id;

    public Inventory(int storeId) {
        this.storeId = storeId;
        this.items = new ArrayList<>();
    }

    public int getStoreId() {
        return storeId;
    }

    public List<Item> getItems(){
        return items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}