package Domain;

import java.util.Objects;

public class Character_NOTAVAILABLE {

    private String characterId;
    private String name;
    private int mana;
    private int health;
    private int speed;
    private int strength;
    private int level;
    private int experiencePoints;
    private int valuePoints;
    private String coins;
    private String type;

    public Character_NOTAVAILABLE(String characterId, String name, int mana, int health, int velocidad, int strength, int level, int experiencePoints, int valuePoints, String coins, String type) {
        this.characterId = characterId;
        this.name = name;
        this.mana = mana;
        this.health = health;
        this.speed = velocidad;
        this.strength = strength;
        this.level = level;
        this.experiencePoints = experiencePoints;
        this.valuePoints = valuePoints;
        this.coins = coins;
        this.type = type;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int velocidad) {
        this.speed = velocidad;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getValuePoints() {
        return valuePoints;
    }

    public void setValuePoints(int valuePoints) {
        this.valuePoints = valuePoints;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Character_NOTAVAILABLE))
            return false;
        Character_NOTAVAILABLE character = (Character_NOTAVAILABLE) o;
        return Objects.equals(getCharacterId(), character.getCharacterId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterId());
    }

    @Override
    public String toString() {
        return "Character{" +
                "characterId='" + characterId + '\'' +
                ", name='" + name + '\'' +
                ", mana=" + mana +
                ", health=" + health +
                ", speed=" + speed +
                ", strength=" + strength +
                ", level=" + level +
                ", experiencePoints=" + experiencePoints +
                ", puntosValor=" + valuePoints +
                ", coins='" + coins + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }
}
