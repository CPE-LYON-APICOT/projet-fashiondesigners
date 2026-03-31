package fr.cpe.model;


public class Player {

    private String pseudo;
    private int level;
    private int xp;
    private int money;
    private Inventory inventory;

    public Player(int level, int xp, int money, Inventory inventory) {
        this.level = level;
        this.xp = xp;
        this.money = money;
        this.inventory = inventory;
    }

    public Player(String pseudo){
        this.pseudo = pseudo;
    }

    public String getPseudo(){
        return pseudo;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getMoney() {
        return money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void gainXP(int amount ) {
        this.xp+=amount;
    }

    public void earnMoney(int amount ) {
        this.money+=amount;
    }
}
