package IStoreApp.model;

public class Store {
    private int id;
    private String name;

    public Store(String name, int StoreId) {
        this.id = StoreId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
