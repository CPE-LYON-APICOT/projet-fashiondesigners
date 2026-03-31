package fr.cpe.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Clicker {
    private ImageView imageView;
    private Ressource ressource;
    private int gain;

    public Clicker(Ressource ressource, int gain, Image image) {
        this.ressource = ressource;
        this.gain = gain;
        this.imageView = new ImageView(image);
    }

    public ImageView getImageView() { return imageView; }
    public Ressource getRessource() { return ressource; }
    public int getGain() { return gain; }
}
