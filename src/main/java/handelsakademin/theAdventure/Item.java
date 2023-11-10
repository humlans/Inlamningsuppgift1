package handelsakademin.theAdventure;

public class Item {
    // The name attribute has both a getter and a setter
    private String name;

    //The Item can only be one of these four types which makes the best suited variable an enum
    public enum ItemType {
        Axe,
        Hammer,
        Scissors,
        Potion,

    }
    public ItemType type;

    //The stat is decided in the constructor and doesn't change throughout the game, making it final
    private final int stat;

    // The constructor takes in a name and a type.
    // But the stat attribute is assigned a random variable between 0 and 10.
    public Item(String name, ItemType type){
        this.name = name;
        this.type = type;
        this.stat = (int)(Math.random()*10);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public int getStat() {
        return stat;
    }

}
