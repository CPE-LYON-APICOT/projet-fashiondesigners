package fr.cpe.model;

public class SpamClickStrategy extends ClickStrategyBase {
    @Override
    public boolean canClick() {
        return true;
    }

    @Override
    public double getGainMultiplier() {
        return 1;
    }

    @Override
    public void click() {

    }
}
