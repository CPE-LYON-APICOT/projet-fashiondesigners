package fr.cpe.service;

import com.google.inject.Inject;
import fr.cpe.model.Machine;
import fr.cpe.model.MachineFactory;

import java.util.HashMap;
import java.util.Map;

public class MachineService {
    private Map<Machine, Integer> machines;
    private final InventoryService inventoryService;

    @Inject
    public MachineService(Map<Machine, Integer> machines, InventoryService inventoryService, MachineFactory factory) {
        this.machines = new HashMap<>();

//        for (int i = 0; i < 3; i++) {
//            Machine machine = factory.createMachine();
//            machines.put(machine, 0);
//        }
        this.inventoryService = inventoryService;
    }
}
