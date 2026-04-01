package fr.cpe.model;

import com.google.inject.Singleton;

@Singleton
public  class MachineFactory {
    public Machine createMachine(Ressource ressource) {
        Machine machine = new Machine();

        switch (ressource){
            case BLE -> machine.setProductionMinute(5);
            case FER -> machine.setProductionMinute(3);
            case FRENE -> machine.setProductionMinute(2);
            case ORTIE -> machine.setProductionMinute(1);
            case GOUJON -> machine.setProductionMinute(0.5);
        }

        switch (ressource){
            case BLE -> machine.setCouleur("Noir");
            case FER -> machine.setCouleur("Bleu");
            case FRENE -> machine.setCouleur("Rose");
            case ORTIE -> machine.setCouleur("Vert");
            case GOUJON -> machine.setCouleur("Jaune");
        }


        if(ressource == Ressource.BLE) {
            machine.setCanBeRemoved(false);
        }
        machine.init();
        return machine;

    }
}
