package fr.cpe.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameSession {
    private static GameSession instance;
    private final Player player;
    private Set<Clicker> clickers;
    private Map<Machine, Integer> machines;

    private GameSession(Player player) {
        this.player = player;
        this.clickers = new HashSet<>();
        this.machines = new HashMap<>();
    }

    public static GameSession getInstance(Player player) {
        if (instance == null) {
            instance = new GameSession(player);
        }
        return instance;
    }
}
