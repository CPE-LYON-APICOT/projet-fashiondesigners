package fr.cpe.service;

import com.google.inject.Inject;
import fr.cpe.model.Clicker;
import jakarta.inject.Singleton;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class ClickerService {
    private Set<Clicker> clickers;
    public final InventoryService inventoryService;

    @Inject
    public ClickerService(InventoryService inventoryService){
        this.inventoryService = inventoryService;
        this.clickers = new HashSet<>();
    }

    public void handleClick(Clicker clicker) {
        inventoryService.addRessource(clicker.getRessource(), clicker.getGain());
    }
}
