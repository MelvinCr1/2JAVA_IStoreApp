package IStoreApp.ui;

import java.util.Scanner;
import java.util.List;
import IStoreApp.model.Item;
import IStoreApp.service.ItemManager;

public class ItemUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createItem() {
        System.out.println("Création d'un nouvel article :");
        System.out.print("Entrez le nom de l'article : ");
        String name = scanner.nextLine();
        System.out.print("Entrez le prix de l'article : ");
        double price = scanner.nextDouble();
        System.out.print("Entrez la quantité en stock : ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        // Création d'un nouvel objet Item avec les informations saisies
        Item newItem = new Item(name, price, quantity);

        // Appel de la méthode de gestion pour créer l'article
        ItemManager.createItem(newItem);
    }

    public static void displayAllItems() {
        System.out.println("Affichage de tous les articles :");

        // Appeler la méthode de gestion pour récupérer tous les articles
        List<Item> items = ItemManager.getAllItems();

        if (items != null && !items.isEmpty()) {
            // Affichage des détails de chaque article
            for (Item item : items) {
                System.out.println("ID : " + item.getId() + ", Nom : " + item.getName() + ", Prix : " + item.getPrice() + ", Quantité : " + item.getQuantity());
            }
        } else {
            System.out.println("Aucun article trouvé.");
        }
    }

    public static void updateItem() {
        System.out.println("Mise à jour des informations d'un article :");
        System.out.print("Entrez l'ID de l'article à mettre à jour : ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne

        // Appel de la méthode de gestion pour récupérer l'article par son ID
        Item item = ItemManager.getItemById(itemId);

        if (item != null) {
            System.out.print("Entrez le nouveau nom de l'article : ");
            String newName = scanner.nextLine();
            System.out.print("Entrez le nouveau prix de l'article : ");
            double newPrice = scanner.nextDouble();
            System.out.print("Entrez la nouvelle quantité en stock : ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();

            // Mise à jour des informations de l'article
            item.setName(newName);
            item.setPrice(newPrice);
            item.setQuantity(newQuantity);

            // Appel de la méthode de gestion pour mettre à jour l'article
            ItemManager.updateItem(item);
            System.out.println("Article mis à jour avec succès !");
        } else {
            System.out.println("Aucun article trouvé avec cet ID.");
        }
    }

    public static void deleteItem() {
        System.out.println("Suppression d'un article :");
        System.out.print("Entrez l'ID de l'article à supprimer : ");
        int itemId = scanner.nextInt();
        scanner.nextLine();

        // Appel de la méthode de gestion pour récupérer l'article par son ID
        Item item = ItemManager.getItemById(itemId);

        if (item != null) {
            // Appel de la méthode de gestion pour supprimer l'article
            ItemManager.deleteItem(item);
            System.out.println("Article supprimé avec succès !");
        } else {
            System.out.println("Aucun article trouvé avec cet ID.");
        }
    }

    public static void mainMenuItem() {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menu Principal Articles ===");
            System.out.println("1. Créer un nouvel article");
            System.out.println("2. Afficher tous les articles");
            System.out.println("3. Mettre à jour un article");
            System.out.println("4. Supprimer un article");
            System.out.println("5. Quitter");

            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createItem();
                    break;
                case 2:
                    displayAllItems();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    deleteItem();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }
}