package fr.cpe.model;

public class SpamClickStrategy implements ClickStrategy {
    @Override
    public boolean canClick() {
        return true;
    }

    @Override
    public double getGainMultiplier() {
        return 0;
    }
}
