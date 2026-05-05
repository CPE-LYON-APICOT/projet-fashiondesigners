package fr.cpe.model;

import fr.cpe.service.InventoryService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class MaterialFactory {

    private final InventoryService inventoryService;

    @Inject
    public MaterialFactory(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    /**
     * Permet de vérifier si le joueur a les ressources nécessaires pour crafter un Matériau
     * @param recipe la recette
     * @return True si il posséde les ressources nécessaires , False sinon
     */
    public boolean canCraftMaterial(MaterialRecipes recipe){
        Map<ICraftAble, Integer> inventaire = inventoryService.getInventory();
        Map<Ressource, Integer> ingredients = recipe.getIngredients();

        for (Map.Entry<Ressource, Integer> entry : ingredients.entrySet()) {
            ICraftAble ingredient = entry.getKey();
            Integer requiredAmount = entry.getValue();

            Integer available = inventaire.get(ingredient);
            if (available == null || available < requiredAmount) {
                return false;
            }
        }
        return true;
    }

    /**
     * Permet de créer un Matériau et le mettre dans l'inventaire
     * @param materialsToCreate le matériau à créer
     * @return le matériau crafté
     */
    public Materials create(Materials materialsToCreate){
        var recipe = Arrays.stream(MaterialRecipes.values())
                .filter(e->e.getOutput() == materialsToCreate)
                .findFirst()
                .orElseThrow();

        if (!canCraftMaterial(recipe)) {
            throw new IllegalStateException("Ressources insuffisantes pour crafter : " + materialsToCreate);
        }

        for (Map.Entry<Ressource, Integer> ressourceIntegerEntry : recipe.getIngredients().entrySet()) {
            inventoryService.removeRessource(ressourceIntegerEntry.getKey(), ressourceIntegerEntry.getValue());
        }

        Materials crafted = recipe.getOutput();
        inventoryService.addRessource(crafted, 1); // tu ajoutes direct dans l'inventaire
        return crafted;
    }

    /**
     * Liste des recettes possibles
     * @return cette liste
     */
    public Map<Materials, Boolean> availableRecipies() {
        return Arrays.stream(MaterialRecipes.values())
                .collect(Collectors.toMap(
                        MaterialRecipes::getOutput,
                        this::canCraftMaterial
                ));
    }
}
