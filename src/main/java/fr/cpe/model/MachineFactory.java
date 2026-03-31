package fr.cpe.model;

import com.google.inject.Singleton;

@Singleton
public  class MachineFactory {
    public Machine createMachine(Ressource ressource) {
        Machine machine = new Machine();
        if(ressource == Ressource.BLE) {
            machine.setStrategy(new blabla);
        }

        switch (ressource){
            case BLE -> machine.setProductionMinute(5);
            case FER -> machine.setProductionMinute(3);
            case FRENE -> machine.setProductionMinute(2);
            case ORTIE -> machine.setProductionMinute(1);
            case GOUJON -> machine.setProductionMinute(0.5);
        }

        if(ressource == Ressource.BLE) {
            machine.canBeRemoved = false;
        }
        machine.init();
        return machine;

    }
}
