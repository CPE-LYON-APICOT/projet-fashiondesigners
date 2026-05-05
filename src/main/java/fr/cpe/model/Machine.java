package fr.cpe.model;

public class Machine {
    protected String name;
    private boolean canBeRemoved = true;
    private String couleur;
    private double productionMinute;

    public void init() {

    }

    public String getName() {
        return name;
    }

    public void setProductionMinute(double productionMinute) {
        this.productionMinute = productionMinute;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setCanBeRemoved(boolean canBeRemoved) {
        this.canBeRemoved = canBeRemoved;
    }

    public boolean isCanBeRemoved() {
        return canBeRemoved;
    }

    public String getCouleur() {
        return couleur;
    }

    public double getProductionMinute() {
        return productionMinute;
    }
}
