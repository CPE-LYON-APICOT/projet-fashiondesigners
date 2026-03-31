package fr.cpe.service;

import fr.cpe.model.Player;
import fr.cpe.model.Ressource;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class InventoryService {
    private Map<Ressource, Integer> inventory;

    public InventoryService() {
        this.inventory = new HashMap<>();
    }

    public Map<Ressource, Integer> getInventory() {
        return inventory;
    }
    /**
     * Permet d'ajouter des ressources dans l'inventaire
     * @param ressource la ressource
     * @param amount le nombre de ressources présent dans l'inventaire
     */
    public void addRessource(Ressource ressource, Integer amount){
        if(inventory.containsKey(ressource)){
            inventory.put(ressource, inventory.get(ressource) + amount);
        }
        else{
            inventory.put(ressource, amount);
        }
    }
}
