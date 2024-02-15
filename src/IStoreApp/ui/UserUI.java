// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import IStoreApp.model.User;
import IStoreApp.service.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserUI extends JFrame {
    private static final JTextField emailField = new JTextField(20);
    private static final JTextField pseudoField = new JTextField(20);
    private static final JPasswordField passwordField = new JPasswordField(20);
    private static final JTextField roleField = new JTextField((20));

    public UserUI() {
        setTitle("Gestion des utilisateurs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 lignes, 1 colonne, espacement de 10 pixels

        panel.add(new JLabel("Email de l'utilisateur :"));
        panel.add(emailField);

        panel.add(new JLabel("Pseudo de l'utilisateur :"));
        panel.add(pseudoField);

        panel.add(new JLabel("Mot de passe de l'utilisateur :"));
        panel.add(passwordField);

        panel.add(new JLabel("Rôle de l'utilisateur :"));
        panel.add(roleField);

        // Bouton pour créer un nouvel utilisateur
        JButton createUserButton = new JButton("Créer un nouvel utilisateur");
        createUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String pseudo = pseudoField.getText();
                String password = new String(passwordField.getPassword());
                String role = roleField.getText();

                // Vérifier si l'un des champs est vide
                if(email.isEmpty() || pseudo.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(UserUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    IStoreApp.service.UserManager.createUser(new User(email, pseudo, password, role));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(createUserButton);

        // Bouton pour afficher les détails d'un utilisateur
        JButton displayUserButton = new JButton("Afficher les détails d'un utilisateur");
        displayUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                try {
                    User user = UserManager.getUserByEmail(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(displayUserButton);

        // Bouton pour mettre à jour un utilisateur
        JButton updateAdminButton = new JButton("Mettre à jour un utilisateur");
        updateAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String newPseudo = pseudoField.getText();
                String newPassword = new String(passwordField.getPassword());
                // Admin admin = AdminManager.getAdminByEmail(email);
                // admin.setPseudo(newPseudo);
                // admin.setPassword(newPassword);
                // AdminManager.updateAdmin(admin);
                JOptionPane.showMessageDialog(UserUI.this, "Pas encore développé");
            }
        });
        panel.add(updateAdminButton);

        // Bouton pour supprimer un utilisateur
        JButton deleteUserButton = new JButton("Supprimer un utilisateur");
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                User user = null;
                try {
                    user = UserManager.getUserByEmail(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    UserManager.deleteUser(user);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(deleteUserButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        // Ajout du panneau au cadre principal
        panel.add(createUserButton);
        add(panel);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    public static void main(/*String[] args*/) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserUI userUI = new UserUI();
                userUI.setVisible(true);
            }
        });
    }
}





















// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------
/*
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
        System.out.print("Entrez le role de l'utilisateur : ");
        String role = scanner.nextLine();

        // Création d'un nouvel objet User avec les informations saisies
        User newUser = new User(email, pseudo, password, role);

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
            System.out.println("Role : " + user.getRole());
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
            System.out.print("Entrez le nouveau role de l'utilisateur : ");
            String newRole = scanner.nextLine();

            // Mise à jour des infos utilisateur
            user.setPseudo(newPseudo);
            user.setPassword(newPassword);
            user.setRole(newRole);

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
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }
}
*/