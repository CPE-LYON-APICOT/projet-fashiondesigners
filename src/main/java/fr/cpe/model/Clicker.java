package fr.cpe.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.function.Consumer;

public class Clicker {
    private ImageView imageView;
    private Ressource ressource;
    private int gain;
    private IClickStrategy strategy;

    public void setStrategy(IClickStrategy strategy) {
        this.strategy = strategy;
    }

    public Clicker(Ressource ressource, int gain, Image image, IClickStrategy strategy) {
        this.ressource = ressource;
        this.gain = gain;
        this.imageView = new ImageView(image);
        this.strategy = strategy;
    }

    public ImageView getImageView() { return imageView; }
    public Ressource getRessource() { return ressource; }

    public boolean canClick(){
        return strategy!=null && strategy.canClick();
    }

    public int getGain() {
        if (this.canClick())
            return (int) (gain * strategy.getGainMultiplier());
        else
            return 0;
    }

    public void onChange(Consumer<IClickStrategy> b) {
        strategy.addReadinessObserver(b);
    }

    public IClickStrategy getStrategy() {
    return strategy;
    }
}
