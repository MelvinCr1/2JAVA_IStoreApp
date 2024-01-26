package IStoreApp.ui;

import java.util.Scanner;

public class UIManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Bienvenue dans l'application iStore ===");

        // Boucle principale
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Gestion des utilisateurs");
            System.out.println("2. Gestion des administrateurs");
            System.out.println("3. Gestion des magasins");
            System.out.println("4. Gestion des inventaires");
            System.out.println("5. Gestion des articles");
            System.out.println("6. Quitter");

            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Pas encore dev");
                    break;
                case 2:
                    AdminUI.mainMenuAdmin();
                    break;
                case 3:
                    StoreUI.mainMenuStore();
                    break;
                case 4:
                    InventoryUI.mainMenuInventory();
                    break;
                case 5:
                    ItemUI.mainMenuItem();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
        scanner.close();
    }
}
