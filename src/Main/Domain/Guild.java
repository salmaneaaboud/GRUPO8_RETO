package Main.Domain;

import java.util.Objects;

public class Guild {
    private int guildId;
    private String guildName;
    private String guildType;
    private int leaderId;
    private String guildImage;

    public Guild(int guildId, String guildName, String guildType, int leaderId, String guildImage) {
        this.guildId = guildId;
        this.guildName = guildName;
        this.guildType = guildType;
        this.leaderId = leaderId;
        this.guildImage = guildImage;
    }

    public int getGuildId() {
        return guildId;
    }

    public void setGuildId(int guildId) {
        this.guildId = guildId;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getGuildType() {
        return guildType;
    }

    public void setGuildType(String guildType) {
        this.guildType = guildType;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guild guild = (Guild) o;
        return guildId == guild.guildId && leaderId == guild.leaderId && Objects.equals(guildName, guild.guildName) && Objects.equals(guildType, guild.guildType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guildId, guildName, guildType, leaderId);
    }

    @Override
    public String toString() {
        return "Guild{" +
                "guildId=" + guildId +
                ", guildName='" + guildName + '\'' +
                ", guildType='" + guildType + '\'' +
                ", leaderId=" + leaderId +
                '}';
    }

    public String getGuildImage() {
        return guildImage;
    }

    public void setGuildImage(String guildImage) {
        this.guildImage = guildImage;
    }
}
