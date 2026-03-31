package fr.cpe.service;

import com.google.inject.Inject;
import fr.cpe.model.Machine;

import java.util.HashMap;
import java.util.Map;

public class MachineService {
    private Map<Machine, Integer> machines;
    private final InventoryService inventoryService;

    @Inject
    public MachineService(Map<Machine, Integer> machines, InventoryService inventoryService) {
        this.machines = new HashMap<>();
        this.inventoryService = inventoryService;
    }
}
