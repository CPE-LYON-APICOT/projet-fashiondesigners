package fr.cpe.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Clicker {
    private ImageView imageView;
    private Ressource ressource;
    private int gain;

    public void setStrategy(ClickStrategy strategy) {
        this.strategy = strategy;
    }

    private ClickStrategy strategy;

    public Clicker(Ressource ressource, int gain, Image image) {
        this.ressource = ressource;
        this.gain = gain;
        this.imageView = new ImageView(image);
    }

    public ImageView getImageView() { return imageView; }
    public Ressource getRessource() { return ressource; }
    public int getGain() {
        if(strategy.canClick())
            return gain;
        return 0;

    }

    public boolean canClick(){
        return strategy!=null && strategy.canClick();
    }
}
