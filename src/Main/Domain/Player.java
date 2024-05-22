package Main.Domain;

import java.util.Objects;

/**
 * The Player class represents a player in a game with details such as ID, ranking, level, name, password, and email.
 */
public class Player {
    private int playerId;
    private String ranking;
    private int level;
    private String name;
    private String password;
    private String email;

    /**
     * Constructs a new player with the specified details.
     *
     * @param playerId the unique identifier for the player
     * @param ranking  the ranking of the player
     * @param level    the level of the player
     * @param name     the name of the player
     * @param password the password of the player
     * @param email    the email of the player
     */
    public Player(int playerId, String ranking, int level, String name, String password, String email) {
        this.playerId = playerId;
        this.ranking = ranking;
        this.level = level;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructs a new player with only the name and password.
     *
     * @param name     the name of the player
     * @param password the password of the player
     */
    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Gets the unique identifier of the player.
     *
     * @return the unique identifier of the player
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Sets the unique identifier of the player.
     *
     * @param playerId the new unique identifier of the player
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Gets the ranking of the player.
     *
     * @return the ranking of the player
     */
    public String getRanking() {
        return ranking;
    }

    /**
     * Sets the ranking of the player.
     *
     * @param ranking the new ranking of the player
     */
    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    /**
     * Gets the level of the player.
     *
     * @return the level of the player
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the player.
     *
     * @param level the new level of the player
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name the new name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the password of the player.
     *
     * @return the password of the player
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the player.
     *
     * @param password the new password of the player
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email of the player.
     *
     * @return the email of the player
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the player.
     *
     * @param email the new email of the player
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * The equals method implements an equivalence relation on non-null object references.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId == player.playerId &&
                Objects.equals(password, player.password) &&
                Objects.equals(email, player.email);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(playerId, password, email);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", ranking='" + ranking + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
