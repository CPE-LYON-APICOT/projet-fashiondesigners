package fr.cpe.model;

import java.util.function.Consumer;

public interface IClickStrategy {
    public boolean canClick();
    public double getGainMultiplier();
    public void click();
    void addReadinessObserver(Consumer<IClickStrategy> consumer);
}
