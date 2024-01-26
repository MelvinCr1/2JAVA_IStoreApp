package IStoreApp.ui;

import java.util.Scanner;
import IStoreApp.model.Admin;
import IStoreApp.service.AdminManager;

public class AdminUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createAdmin() {
        System.out.println("Création d'un nouvel administrateur :");
        System.out.print("Entrez l'email de l'administrateur : ");
        String email = scanner.nextLine();
        System.out.print("Entrez le pseudo de l'administrateur : ");
        String pseudo = scanner.nextLine();
        System.out.print("Entrez le mot de passe de l'administrateur : ");
        String password = scanner.nextLine();

        // Créer un nouvel objet Admin avec les informations saisies
        Admin newAdmin = new Admin(email, pseudo, password);

        // Appeler la méthode de gestion pour créer l'administrateur
        AdminManager.createAdmin(newAdmin);
    }

    public static void displayAdminDetails() {
        System.out.println("Affichage des détails d'un administrateur :");
        System.out.print("Entrez l'email de l'administrateur : ");
        String email = scanner.nextLine();

        // Appeler la méthode de gestion pour récupérer l'administrateur par email
        Admin admin = AdminManager.getAdminByEmail(email);

        if (admin != null) {
            // Afficher les détails de l'administrateur
            System.out.println("Détails de l'administrateur :");
            System.out.println("Email : " + admin.getEmail());
            System.out.println("Pseudo : " + admin.getPseudo());
            // Ne pas afficher le mot de passe pour des raisons de sécurité
        } else {
            System.out.println("Aucun administrateur trouvé avec cet email.");
        }
    }

    public static void updateAdmin() {
        System.out.println("Mise à jour des informations d'un administrateur :");
        System.out.print("Entrez l'email de l'administrateur à mettre à jour : ");
        String email = scanner.nextLine();

        // Appeler la méthode de gestion pour récupérer l'administrateur par email
        Admin admin = AdminManager.getAdminByEmail(email);

        if (admin != null) {
            // Demander les nouvelles informations à l'utilisateur
            System.out.print("Entrez le nouveau pseudo de l'administrateur : ");
            String newPseudo = scanner.nextLine();
            System.out.print("Entrez le nouveau mot de passe de l'administrateur : ");
            String newPassword = scanner.nextLine();

            // Mettre à jour les informations de l'administrateur
            admin.setPseudo(newPseudo);
            admin.setPassword(newPassword);

            // Appeler la méthode de gestion pour mettre à jour l'administrateur
            AdminManager.updateAdmin(admin);
            System.out.println("Administrateur mis à jour avec succès !");
        } else {
            System.out.println("Aucun administrateur trouvé avec cet email.");
        }
    }

    public static void deleteAdmin() {
        System.out.println("Suppression d'un administrateur :");
        System.out.print("Entrez l'email de l'administrateur à supprimer : ");
        String email = scanner.nextLine();

        // Appeler la méthode de gestion pour récupérer l'administrateur par email
        Admin admin = AdminManager.getAdminByEmail(email);

        if (admin != null) {
            // Appeler la méthode de gestion pour supprimer l'administrateur
            AdminManager.deleteAdmin(admin);
            System.out.println("Administrateur supprimé avec succès !");
        } else {
            System.out.println("Aucun administrateur trouvé avec cet email.");
        }
    }

    public static void mainMenuAdmin() {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menu Principal Administrateur ===");
            System.out.println("1. Créer un nouvel administrateur");
            System.out.println("2. Afficher les détails d'un administrateur");
            System.out.println("3. Mettre à jour un administrateur");
            System.out.println("4. Supprimer un administrateur");
            System.out.println("5. Quitter");

            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    createAdmin();
                    break;
                case 2:
                    displayAdminDetails();
                    break;
                case 3:
                    updateAdmin();
                    break;
                case 4:
                    deleteAdmin();
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