package IStoreApp.ui;

import java.util.Scanner;
import IStoreApp.model.Store;
import IStoreApp.service.StoreManager;

public class StoreUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createStore() {
        System.out.println("Création d'un nouveau magasin :");
        System.out.print("Entrez le nom du magasin : ");
        String name = scanner.nextLine();

        // Création du nouvel objet Store avec le nom saisi
        Store newStore = new Store(name);

        // Appel de la méthode de gestion pour créer le magasin
        StoreManager.createStore(newStore);
    }

    public static void displayStoreDetails() {
        System.out.println("Affichage des détails d'un magasin :");
        System.out.print("Entrez l'ID du magasin : ");
        int storeId = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne

        // Appel de la méthode de gestion pour récupérer le magasin par son ID
        Store store = StoreManager.getStoreById(storeId);

        if (store != null) {
            System.out.println("Détails du magasin :");
            System.out.println("ID : " + store.getId());
            System.out.println("Nom : " + store.getName());
        } else {
            System.out.println("Aucun magasin trouvé avec cet ID.");
        }
    }

    public static void updateStore() {
        System.out.println("Mise à jour des informations d'un magasin :");
        System.out.print("Entrez l'ID du magasin à mettre à jour : ");
        int storeId = scanner.nextInt();
        scanner.nextLine();

        // Appel de la méthode de gestion pour récupérer le magasin par son ID
        Store store = StoreManager.getStoreById(storeId);

        if (store != null) {
            System.out.print("Entrez le nouveau nom du magasin : ");
            String newName = scanner.nextLine();

            // Mise à jour des infos du magasin
            store.setName(newName);

            // Appel de la méthode de gestion pour mettre à jour le magasin
            StoreManager.updateStore(store);
            System.out.println("Magasin mis à jour avec succès !");
        } else {
            System.out.println("Aucun magasin trouvé avec cet ID.");
        }
    }

    public static void deleteStore() {
        System.out.println("Suppression d'un magasin :");
        System.out.print("Entrez l'ID du magasin à supprimer : ");
        int storeId = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne

        // Appeler la méthode de gestion pour récupérer le magasin par son ID
        Store store = StoreManager.getStoreById(storeId);

        if (store != null) {
            // Appeler la méthode de gestion pour supprimer le magasin
            StoreManager.deleteStore(store);
            System.out.println("Magasin supprimé avec succès !");
        } else {
            System.out.println("Aucun magasin trouvé avec cet ID.");
        }
    }

    public static void mainMenuStore() {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menu Principal Magasin ===");
            System.out.println("1. Créer un nouveau magasin");
            System.out.println("2. Afficher les détails d'un magasin");
            System.out.println("3. Mettre à jour un magasin");
            System.out.println("4. Supprimer un magasin");
            System.out.println("5. Quitter");

            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    createStore();
                    break;
                case 2:
                    displayStoreDetails();
                    break;
                case 3:
                    updateStore();
                    break;
                case 4:
                    deleteStore();
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