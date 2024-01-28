package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIManager extends JFrame {
    public UIManager() {
        setTitle("iStore Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Création d'un panneau pour contenir les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10)); // 6 lignes, 1 colonne, espacement de 10 pixels

        // Bouton pour la gestion des utilisateurs
        JButton userButton = new JButton("Gestion des utilisateurs");
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(UIManager.this, "Pas encore développé");
            }
        });
        panel.add(userButton);

        // Bouton pour la gestion des administrateurs
        JButton adminButton = new JButton("Gestion des administrateurs");
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminUI.main();
            }
        });
        panel.add(adminButton);

        // Bouton pour la gestion des magasins
        JButton storeButton = new JButton("Gestion des magasins");
        storeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StoreUI.main();
            }
        });
        panel.add(storeButton);

        // Bouton pour la gestion des inventaires
        JButton inventoryButton = new JButton("Gestion des inventaires");
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InventoryUI.main();
            }
        });
        panel.add(inventoryButton);

        // Bouton pour la gestion des articles
        JButton itemButton = new JButton("Gestion des articles");
        itemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemUI.main();
            }
        });
        panel.add(itemButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Au revoir !");
                System.exit(0);
            }
        });
        panel.add(exitButton);

        // Ajout du panneau au cadre principal
        add(panel);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Création et affichage de l'interface utilisateur
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager uiManager = new UIManager();
                uiManager.setVisible(true);
            }
        });
    }
}



/* VERSION CONSOLE



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
*/