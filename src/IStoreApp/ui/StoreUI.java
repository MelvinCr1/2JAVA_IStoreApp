package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreUI extends JFrame {
    public StoreUI() {
        setTitle("Store Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Création d'un panneau pour contenir les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 lignes, 1 colonne, espacement de 10 pixels

        // Bouton pour créer un nouveau magasin
        JButton createButton = new JButton("Créer un nouveau magasin");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Appeler la méthode pour créer un magasin
                createStore();
            }
        });
        panel.add(createButton);

        // Bouton pour afficher les détails d'un magasin
        JButton displayButton = new JButton("Afficher les détails d'un magasin");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Appeler la méthode pour afficher les détails d'un magasin
                displayStoreDetails();
            }
        });
        panel.add(displayButton);

        // Bouton pour mettre à jour un magasin
        JButton updateButton = new JButton("Mettre à jour un magasin");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Appeler la méthode pour mettre à jour un magasin
                updateStore();
            }
        });
        panel.add(updateButton);

        // Bouton pour supprimer un magasin
        JButton deleteButton = new JButton("Supprimer un magasin");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Appeler la méthode pour supprimer un magasin
                deleteStore();
            }
        });
        panel.add(deleteButton);

        // Ajout du panneau au cadre principal
        add(panel);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    // Méthodes pour gérer les actions du magasin
    private void createStore() {
        JOptionPane.showMessageDialog(this, "Fonctionnalité non implémentée : Créer un magasin");
    }

    private void displayStoreDetails() {
        JOptionPane.showMessageDialog(this, "Fonctionnalité non implémentée : Afficher les détails d'un magasin");
    }

    private void updateStore() {
        JOptionPane.showMessageDialog(this, "Fonctionnalité non implémentée : Mettre à jour un magasin");
    }

    private void deleteStore() {
        JOptionPane.showMessageDialog(this, "Fonctionnalité non implémentée : Supprimer un magasin");
    }

    public static void main(/*String[] args*/) {
        // Création et affichage de l'interface utilisateur
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StoreUI storeUI = new StoreUI();
                storeUI.setVisible(true);
            }
        });
    }
}


/* VERSION CONSOLE



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

 */