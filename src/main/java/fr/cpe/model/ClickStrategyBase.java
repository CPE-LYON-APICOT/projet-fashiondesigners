package fr.cpe.model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public abstract class ClickStrategyBase implements IClickStrategy {
    private final ArrayList<Consumer<IClickStrategy>> observers = new ArrayList<>();
    private final Timer timer;

    @Override
    public void addReadinessObserver(Consumer<IClickStrategy> consumer) {
        observers.add(consumer);

    }

    protected ClickStrategyBase(){
        timer = new Timer();
        var that = this;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               // if(canClick()){
                    for (var observer : observers) {
                        observer.accept(that);
                    }
               // }
            }
        }, 0, 500);


    }
}
