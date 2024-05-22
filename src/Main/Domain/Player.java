package Main.Domain;

import java.util.Objects;

public class Player {
    private int playerId;
    private String ranking;
    private int level;
    private String name;
    private String password;
    private String email;

    public Player(int playerId, String ranking, int level, String name, String password, String email) {
        this.playerId = playerId;
        this.ranking = ranking;
        this.level = level;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId) && Objects.equals(password, player.password) && Objects.equals(email, player.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, password, email);
    }

    @Override
    public String toString() {
        return "Player{" +
                "idPlayer='" + playerId + '\'' +
                ", ranking='" + ranking + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
