package IStoreApp.ui;

import java.util.Scanner;
import java.util.List;
import IStoreApp.model.Inventory;
import IStoreApp.service.InventoryManager;
import IStoreApp.model.Store;
import IStoreApp.model.Item;

public class InventoryUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createInventory() {
        System.out.println("Création d'un nouvel inventaire :");
        System.out.print("Entrez l'ID du magasin associé à cet inventaire : ");
        int storeId = scanner.nextInt();
        scanner.nextLine();

        // Vérifier si le magasin existe
        Store store = IStoreApp.service.StoreManager.getStoreById(storeId);
        if (store == null) {
            System.out.println("Aucun magasin trouvé avec cet ID. Impossible de créer l'inventaire.");
            return;
        }

        // Création du nouvel inventaire associé au magasin spécifié
        Inventory newInventory = new Inventory(storeId);

        // Appel de la méthode de gestion pour créer l'inventaire
        InventoryManager.createInventory(newInventory);
    }

    public static void displayInventoryItems() {
        System.out.println("Affichage des articles dans un inventaire :");
        System.out.print("Entrez l'ID de l'inventaire : ");
        int inventoryId = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne

        // Appeler la méthode de gestion pour récupérer les articles de l'inventaire par son ID
        List<Item> items = InventoryManager.getInventoryItems(inventoryId);

        if (items != null && !items.isEmpty()) {
            // Afficher les détails de chaque article dans l'inventaire
            System.out.println("Articles dans l'inventaire :");
            for (Item item : items) {
                System.out.println("ID : " + item.getId() + ", Nom : " + item.getName() + ", Prix : " + item.getPrice());
            }
        } else {
            System.out.println("Aucun article trouvé dans cet inventaire.");
        }
    }

    public static void adjustInventoryItemQuantity() {
        System.out.println("Ajustement de la quantité d'un article dans un inventaire :");
        System.out.print("Entrez l'ID de l'article : ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne
        System.out.print("Entrez l'ajustement de quantité (+ pour augmenter, - pour diminuer) : ");
        int quantityAdjustment = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne

        // Appeler la méthode de gestion pour ajuster la quantité de l'article dans l'inventaire
        IStoreApp.service.InventoryManager.adjustInventoryItemQuantity(itemId, quantityAdjustment);
        System.out.println("Quantité d'article ajustée avec succès !");
    }

    public static void mainMenuInventory() {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menu Principal Inventaire ===");
            System.out.println("1. Créer un nouvel inventaire");
            System.out.println("2. Afficher les articles dans un inventaire");
            System.out.println("3. Ajuster la quantité d'un article dans un inventaire");
            System.out.println("4. Quitter");

            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    createInventory();
                    break;
                case 2:
                    displayInventoryItems();
                    break;
                case 3:
                    adjustInventoryItemQuantity();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }
}