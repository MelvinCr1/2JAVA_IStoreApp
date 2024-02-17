// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import IStoreApp.model.Store;
import IStoreApp.service.Authentication;
import IStoreApp.service.StoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StoreUI extends JFrame {
    private static final JTextField idField = new JTextField(20);
    private static final JTextField nameField = new JTextField(20);
    private String sessionId;

    public StoreUI(String sessionId) {
        this.sessionId = sessionId;

        setTitle("Gestion des utilisateurs");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 lignes, 1 colonne, espacement de 10 pixels

        panel.add(new JLabel("ID du magasin :"));
        panel.add(idField);

        panel.add(new JLabel("Nom du magasin :"));
        panel.add(nameField);

        if (Authentication.isCurrentUserAdmin(sessionId)){
            // Bouton pour créer un nouveau magasin
            JButton createStoreButton = new JButton("Créer un nouveau magasin");
            createStoreButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();

                    // Vérifier si l'un des champs est vide
                    if(name.isEmpty()) {
                        JOptionPane.showMessageDialog(StoreUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        IStoreApp.service.StoreManager.createStore(new Store(name, id));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            panel.add(createStoreButton);

            // Bouton pour mettre à jour un magasin
            JButton updateStoreButton = new JButton("Mettre à jour un magasin");
            updateStoreButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id = idField.getText();
                    String newName = nameField.getText();

                    // Vérification si les champs sont vides
                    if (id.isEmpty() || newName.isEmpty()) {
                        JOptionPane.showMessageDialog(StoreUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        // Récupérer le magasin à mettre à jour par son ID
                        Store store = StoreManager.getStoreById(Integer.parseInt(id));

                        // Vérifier si le magasin existe
                        if (store == null) {
                            JOptionPane.showMessageDialog(StoreUI.this, "Aucun magasin trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Mettre à jour le nom du magasin
                        store.setName(newName);

                        // Appeler la méthode de gestion pour mettre à jour le magasin
                        StoreManager.updateStore(store);

                        // Afficher un message de réussite
                        JOptionPane.showMessageDialog(StoreUI.this, "Magasin mis à jour avec succès !");
                    } catch (NumberFormatException ex) {
                        // Gérer l'exception si l'ID n'est pas un entier valide
                        JOptionPane.showMessageDialog(StoreUI.this, "Veuillez saisir un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        // Gérer l'exception liée à l'accès à la base de données
                        throw new RuntimeException(ex);
                    }
                }
            });
            panel.add(updateStoreButton);

            // Bouton pour supprimer un magasin
            JButton deleteStoreButton = new JButton("Supprimer un magasin");
            deleteStoreButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(idField.getText());

                    // Vérifier si les champs sont vides
                    if (id == 0) {
                        JOptionPane.showMessageDialog(StoreUI.this, "Veuillez saisir un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        // Récupérer le magasin à supprimer par son ID
                        Store store = StoreManager.getStoreById(id);

                        // Vérifier si le magasin existe
                        if (store == null) {
                            JOptionPane.showMessageDialog(StoreUI.this, "Aucun magasin trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Appeler la méthode de gestion pour supprimer le magasin
                        StoreManager.deleteStore(store);

                        // Afficher un message de réussite
                        JOptionPane.showMessageDialog(StoreUI.this, "Magasin supprimé avec succès !");
                    } catch (NumberFormatException ex) {
                        // Gérer l'exception si l'ID n'est pas un entier valide
                        JOptionPane.showMessageDialog(StoreUI.this, "Veuillez saisir un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        // Gérer l'exception liée à l'accès à la base de données
                        throw new RuntimeException(ex);
                    }
                }
            });
            panel.add(deleteStoreButton);
        }

        // Boutton pour afficher les détails d'un magasin
        JButton displayStoreButton = new JButton("Afficher les détails d'un magasin");
        displayStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'id du magasin depuis le champ de saisie
                String id = idField.getText();

                try {
                    // Récupérer le magasin depuis la base de données en utilisant son email
                    Store store = StoreManager.getStoreById(Integer.parseInt(id));

                    // Vérifier si le magasin existe
                    if (store != null) {
                        // Afficher les détails du magasin dans une boîte de dialogue
                        JOptionPane.showMessageDialog(null, "Détails du magasin :\n" +
                                "Id: " + store.getId() + "\n" +
                                "Nom: " + store.getName());
                    } else {
                        // Afficher un message si le magasin n'existe pas
                        JOptionPane.showMessageDialog(null, "Le magasin avec l'id " + id + " n'existe pas.");
                    }
                } catch (SQLException ex) {
                    // Gérer les exceptions liées à l'accès à la base de données
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(displayStoreButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        // Ajout du panneau au cadre principal
        add(panel);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    public static void main(String sessionId) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StoreUI storeUI = new StoreUI(sessionId);
                storeUI.setVisible(true);
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