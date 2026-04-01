package fr.cpe.model;



public class GameSession {
    private static GameSession instance;
    private final Player player;

    private GameSession(Player player) {
        this.player = player;
    }

    public static GameSession getInstance(Player player) {
        if (instance == null) {
            instance = new GameSession(player);
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }
}
