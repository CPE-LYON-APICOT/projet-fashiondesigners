package fr.cpe.service;

import com.google.inject.Inject;
import fr.cpe.model.ICraftAble;
import fr.cpe.model.MaterialRecipes;
import fr.cpe.model.Ressource;
import java.util.Map;

public class RecipeService {
    private final InventoryService inventoryService;

    @Inject
    public RecipeService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


}
