package fr.cpe.model;

import java.util.Map;

public enum MaterialRecipes implements ICraftAble {

    PLANK("plank", Map.of(Ressource.FRENE, 4), Materials.PLANK),
    ALUMNITE("alumnite", Map.of(Ressource.FER,10), Materials.ALUMNITE),
    ELEXIR("elexir", Map.of(Ressource.ORTIE,6),Materials.ELEXIR),
    BREAD("bread",Map.of(Ressource.BLE, 3), Materials.BREAD),
    SOUP("soup",Map.of(Ressource.GOUJON, 5), Materials.SOUP);

    private String name;
    private Map<Ressource, Integer> ingredients;
    private Materials output;
    private int requiredLevel;

    private MaterialRecipes(String name, Map<Ressource, Integer> ingredients, Materials output) {
        this.name = name;
        this.ingredients = ingredients;
        this.output = output;
    }

    public Map<Ressource, Integer> getIngredients() {
        return ingredients;
    }

    public Materials getOutput() {
        return output;
    }
}
