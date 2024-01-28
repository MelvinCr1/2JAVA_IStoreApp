package IStoreApp.ui;

import java.sql.SQLException;
import java.util.Scanner;
import IStoreApp.model.User;
import IStoreApp.service.UserManager;

public class UserUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createUser() throws SQLException {
        System.out.println("Création d'un nouvel utilisateur :");
        System.out.print("Entrez l'email de l'utilisateur : ");
        String email = scanner.nextLine();
        System.out.print("Entrez le pseudo de l'utilisateur : ");
        String pseudo = scanner.nextLine();
        System.out.print("Entrez le mot de passe de l'utilisateur : ");
        String password = scanner.nextLine();

        // Création d'un nouvel objet User avec les informations saisies
        User newUser = new User(email, pseudo, password);

        // Appel de la méthode de gestion pour la création de l'utilisateur
        UserManager.createUser(newUser);
    }

    public static void displayUserDetails() throws SQLException {
        System.out.println("Affichage des détails d'un utilisateur :");
        System.out.println("Entrez l'email de l'utilisateur");
        String email = scanner.nextLine();

        // Appel de la méthode de gestion pour récupérer l'utilisateur par email
        User user = UserManager.getUserByEmail(email);

        if (user != null){
            // Affichage des détails de l'utilisateur
            System.out.println("Détails de l'utilisateur :");
            System.out.println("Email : " + user.getEmail());
            System.out.println("Pseudo : " + user.getPseudo());
        } else{
            System.out.println("Aucun utilisateur trouvé avec cet email.");
        }
    }

    public static void updateUser() throws SQLException {
        System.out.println("Mise à jour des informations d'un utilisateur :");
        System.out.println("Entrez l'email de l'utilisateur à mettre à jour : ");
        String email = scanner.nextLine();

        User user = UserManager.getUserByEmail(email);

        if (user != null){
            System.out.print("Entrez le nouveau pseudo de l'utilisateur : ");
            String newPseudo = scanner.nextLine();
            System.out.print("Entrez le nouveau mot de passe de l'utilisateur : ");
            String newPassword = scanner.nextLine();

            // Mise à jour des infos utilisateur
            user.setPseudo(newPseudo);
            user.setPassword(newPassword);

            // Appel de la méthode de gestion pour la mise à jour de l'utilisateur
            UserManager.updateUser(user);
            System.out.println("Utilisateur mis à jour avec succès !");
        } else{
            System.out.println("Aucun utilisateur trouvé avec cet email");
        }
    }

    public static void deleteUser() throws SQLException {
        System.out.println("Suppression d'un utilisateur :");
        System.out.print("Entrez l'email de l'utilisateur à supprimer : ");
        String email = scanner.nextLine();

        // Appel de la méthode pour récupérer l'utilisateur par email
        User user = UserManager.getUserByEmail(email);

        if (user != null){
            UserManager.deleteUser(user);
            System.out.println("Utilisateur supprimé avec succès");
        }else{
            System.out.println("Aucun utilisateur trouvé avec cet email.");
        }
    }

    public static void mainMenu() throws SQLException {
        boolean exit = false;
        while(!exit){
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouvel utilisateur");
            System.out.println("2. Afficher les détails d'un utilisateur");
            System.out.println("3. Mettre à jour un utilisateur");
            System.out.println("4. Supprimer un utilisateur");
            System.out.println("5. Quitter");

            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    displayUserDetails();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
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