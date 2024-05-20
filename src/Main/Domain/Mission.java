package Main.Domain;

public class Mission {
    private int missionId;
    private int matchId;
    private String description;
    private String reward;
    private String difficulty;

    public Mission(int missionId, int matchId, String description, String reward, String difficulty) {
        this.missionId = missionId;
        this.matchId = matchId;
        this.description = description;
        this.reward = reward;
        this.difficulty = difficulty;
    }

    public int getMissionId() {
        return missionId;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getDescription() {
        return description;
    }

    public String getReward() {
        return reward;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "missionId=" + missionId +
                ", matchId=" + matchId +
                ", description='" + description + '\'' +
                ", reward='" + reward + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
