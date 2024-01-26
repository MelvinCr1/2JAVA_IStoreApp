package IStoreApp.service;

import IStoreApp.model.Store;
import IStoreApp.dataAccess.StoreAccess;

public class StoreManager {
    private static final StoreAccess storeAccess = new StoreAccess();

    // Méthode pour créer un nouveau magasin
    public static void createStore(Store store){
        // Vérification si le magasin existe déja
        if (getStoreById(store.getId()) != null) {
            System.out.println("Erreur : Un magasin avec cet ID existe déja.");
            return;
        }
        // Appel de la méthode d'accès aux données pour créer le magasin
        storeAccess.createStore(store);
        System.out.println("Magasin créé avec succès !");
    }

    // Méthode pour récupérer un magasin par son ID
    public static Store getStoreById(int storeId){
        return storeAccess.getStoreById(storeId);
    }

    // Méthode pour mettre à jour un magasin existant
    public static void updateStore(Store store){
        // Vérification si le magasin existe
        Store existingStore = getStoreById(store.getId());
        if (existingStore == null) {
            System.out.println("Erreur : Ce magasin n'existe pas.");
            return;
        }
        storeAccess.updateStore(store);
        System.out.println("Magasin mis à jour avec succès !");
    }

    // Méthode pour supprimer un magasin
    public static void deleteStore(Store store){
        // Vérification si le magasin existe
        Store existingStore = getStoreById(store.getId());
        if (existingStore == null) {
            System.out.println("Erreur : ce magasin n'existe pas.");
            return;
        }
        storeAccess.deleteStore(store);
        System.out.println("Magasin supprimé avec succès !");
    }
}
