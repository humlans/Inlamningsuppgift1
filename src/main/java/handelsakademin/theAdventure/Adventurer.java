package handelsakademin.theAdventure;

public class Adventurer {
    private final String name;

    private int stamina;

    public Adventurer(String name) {
        this.name = name;
        stamina = 50;
    }

    public String getName() {
        return name;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
}
