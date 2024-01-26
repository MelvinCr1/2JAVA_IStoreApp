package IStoreApp.service;

import IStoreApp.model.Item;
import IStoreApp.dataAccess.ItemAccess;

import java.util.List;

public class ItemManager {
    private static final ItemAccess itemAccess = new ItemAccess();

    public static void createItem(Item item) {
        // Vérifier si l'article existe déjà
        if (getItemById(item.getId()) != null) {
            System.out.println("Erreur : Un article avec cet ID existe déjà.");
            return;
        }
        // Appeler la méthode d'accès aux données pour créer l'article
        IStoreApp.dataAccess.ItemAccess.createItem(item);
        System.out.println("Article créé avec succès !");
    }

    public static Item getItemById(int itemId) {
        // Appeler la méthode d'accès aux données pour récupérer l'article par ID
        return itemAccess.getItemById(itemId);
    }

    public static void updateItem(Item item) {
        // Vérifier si l'article existe
        Item existingItem = getItemById(item.getId());
        if (existingItem == null) {
            System.out.println("Erreur : Cet article n'existe pas.");
            return;
        }
        // Appeler la méthode d'accès aux données pour mettre à jour l'article
        IStoreApp.dataAccess.ItemAccess.updateItem(item);
        System.out.println("Article mis à jour avec succès !");
    }

    public static void deleteItem(Item item) {
        // Vérifier si l'article existe
        Item existingItem = getItemById(item.getId());
        if (existingItem == null) {
            System.out.println("Erreur : Cet article n'existe pas.");
            return;
        }
        // Appeler la méthode d'accès aux données pour supprimer l'article
        IStoreApp.dataAccess.ItemAccess.deleteItem(item);
        System.out.println("Article supprimé avec succès !");
    }

    public static List<Item> getAllItems() {
        return null;
    }
}
