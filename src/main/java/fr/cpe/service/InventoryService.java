package fr.cpe.service;

import fr.cpe.model.ICraftAble;
import fr.cpe.model.Player;
import fr.cpe.model.Ressource;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class InventoryService {
    private Map<ICraftAble, Integer> inventory;

    public InventoryService() {
        this.inventory = new HashMap<>();
    }

    public Map<ICraftAble, Integer> getInventory() {
        return inventory;
    }
    /**
     * Permet d'ajouter des ressources dans l'inventaire
     * @param item l'item
     * @param amount le nombre d'item à rajouter
     */
    public void addRessource(ICraftAble item, Integer amount){
        if(inventory.containsKey(item)){
            inventory.put(item, inventory.get(item) + amount);
        }
        else{
            inventory.put(item, amount);
        }
    }

    /**
     * Permet de retirer des ressources de l'invantaire
     * @param item l'item a rétiré
     * @param amount le nombre d'items à retirer
     */
    public void removeRessource(ICraftAble item, Integer amount) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) - amount);
        }
    }
}
