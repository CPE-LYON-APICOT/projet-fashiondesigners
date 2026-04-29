package fr.cpe.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LoadClickStrategy extends  ClickStrategyBase{
    private final int secondsToWait;

    public LoadClickStrategy(int secondsToWait, int maxMultiplier, int secondsToMax) {
        this.secondsToWait = secondsToWait;
        this.maxMultiplier = maxMultiplier;
        this.secondsToMax = secondsToMax;
    }

    @Override
    public boolean canClick() {
        //Si ca fait plus d'une seconde depuis le dernier clic
        if (LocalTime.now().isAfter(lastClickDate.plus(secondsToWait, ChronoUnit.SECONDS ))) {

            return true;
        }
        return false;
    }



    @Override
    public double getGainMultiplier() {

        long secondeEcoules = Duration.between(lastClickDate, LocalTime.now()).getSeconds();
        double multiplier = 1.0 + ((double) secondeEcoules / secondsToMax) * (maxMultiplier - 1.0);
        multiplier = Math.min(multiplier, maxMultiplier);
        return multiplier;

    }

    @Override
    public void click() {
        lastClickDate = LocalTime.now();
    }

    private LocalTime lastClickDate = LocalTime.now();
    private final double maxMultiplier;
    private final int secondsToMax;

}
