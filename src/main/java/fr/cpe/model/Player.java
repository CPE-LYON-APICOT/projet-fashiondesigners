package fr.cpe.model;


import com.google.inject.Inject;
import fr.cpe.service.InventoryService;

import java.util.stream.Collectors;

public class Player {

    private String pseudo;
    private int level;
    private int xp;
    private int money;
    private InventoryService inventoryService;

    @Inject
    public Player(String pseudo,InventoryService inventoryService) {
        this.pseudo = pseudo;
        this.level = 1;
        this.xp = 0;
        this.money = 0;
        this.inventoryService = inventoryService;
    }

    public String getPseudo(){
        return pseudo;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getMoney() {
        return money;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public String showInventory(){
        return inventoryService.getInventory().entrySet().stream()
                .map(e -> "L'inventaire du joueur est composé de :" + e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}
