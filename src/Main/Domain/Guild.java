package Main.Domain;

import java.util.Objects;

/**
 * The Guild class represents a guild in a game with an ID, name, type, leader, and image.
 */
public class Guild {
    private int guildId;
    private String guildName;
    private String guildType;
    private int leaderId;
    private String guildImage;

    /**
     * Constructs a new guild with the specified ID, name, type, leader ID, and image.
     *
     * @param guildId    the unique identifier for the guild
     * @param guildName  the name of the guild
     * @param guildType  the type of the guild (e.g., adventure, trade)
     * @param leaderId   the ID of the guild leader
     * @param guildImage the image representing the guild
     */
    public Guild(int guildId, String guildName, String guildType, int leaderId, String guildImage) {
        this.guildId = guildId;
        this.guildName = guildName;
        this.guildType = guildType;
        this.leaderId = leaderId;
        this.guildImage = guildImage;
    }

    /**
     * Gets the unique identifier of the guild.
     *
     * @return the unique identifier of the guild
     */
    public int getGuildId() {
        return guildId;
    }

    /**
     * Sets the unique identifier of the guild.
     *
     * @param guildId the new unique identifier of the guild
     */
    public void setGuildId(int guildId) {
        this.guildId = guildId;
    }

    /**
     * Gets the name of the guild.
     *
     * @return the name of the guild
     */
    public String getGuildName() {
        return guildName;
    }

    /**
     * Sets the name of the guild.
     *
     * @param guildName the new name of the guild
     */
    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    /**
     * Gets the type of the guild.
     *
     * @return the type of the guild
     */
    public String getGuildType() {
        return guildType;
    }

    /**
     * Sets the type of the guild.
     *
     * @param guildType the new type of the guild
     */
    public void setGuildType(String guildType) {
        this.guildType = guildType;
    }

    /**
     * Gets the ID of the guild leader.
     *
     * @return the ID of the guild leader
     */
    public int getLeaderId() {
        return leaderId;
    }

    /**
     * Sets the ID of the guild leader.
     *
     * @param leaderId the new ID of the guild leader
     */
    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    /**
     * Gets the image representing the guild.
     *
     * @return the image representing the guild
     */
    public String getGuildImage() {
        return guildImage;
    }

    /**
     * Sets the image representing the guild.
     *
     * @param guildImage the new image representing the guild
     */
    public void setGuildImage(String guildImage) {
        this.guildImage = guildImage;
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
        Guild guild = (Guild) o;
        return guildId == guild.guildId &&
                leaderId == guild.leaderId &&
                Objects.equals(guildName, guild.guildName) &&
                Objects.equals(guildType, guild.guildType);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(guildId, guildName, guildType, leaderId);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Guild{" +
                "guildId=" + guildId +
                ", guildName='" + guildName + '\'' +
                ", guildType='" + guildType + '\'' +
                ", leaderId=" + leaderId +
                '}';
    }
}
