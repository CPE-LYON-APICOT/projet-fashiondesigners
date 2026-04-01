package fr.cpe.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class LoadClickStrategy implements ClickStrategy {
    private final int secondsToWait;

    public LoadClickStrategy(int secondsToWait, int maxMultiplier, int secondsToMax) {
        this.secondsToWait = secondsToWait;
        this.maxMultiplier = maxMultiplier;
        this.secondsToMax = secondsToMax;
    }

    @Override
    public boolean canClick() {
        //Si ca fait plus d'une seconde depuis le dernier clic
        if (LocalDate.now().isAfter(lastClickDate.plus(secondsToWait, ChronoUnit.SECONDS ))) {
            lastClickDate = LocalDate.now();
            return true;
        }
        return false;
    }

    @Override
    public double getGainMultiplier() {
        long secondeEcoules = Duration.between(lastClickDate, LocalDate.now()).getSeconds();
        double multiplier = 1.0 + ((double) secondeEcoules / secondsToMax) * (maxMultiplier - 1.0);
        multiplier = Math.min(multiplier, maxMultiplier);
        lastClickDate = LocalDate.now();
        return multiplier;

    }

    private LocalDate lastClickDate;
    private final double maxMultiplier;
    private final int secondsToMax;

}
