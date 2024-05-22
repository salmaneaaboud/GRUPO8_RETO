package Main.Domain;

/**
 * The Mission class represents a mission in a game with an ID, match ID, description, reward, and difficulty level.
 */
public class Mission {
    private int missionId;
    private int matchId;
    private String description;
    private String reward;
    private String difficulty;

    /**
     * Constructs a new mission with the specified ID, match ID, description, reward, and difficulty level.
     *
     * @param missionId   the unique identifier for the mission
     * @param matchId     the ID of the match associated with the mission
     * @param description a description of the mission
     * @param reward      the reward for completing the mission
     * @param difficulty  the difficulty level of the mission
     */
    public Mission(int missionId, int matchId, String description, String reward, String difficulty) {
        this.missionId = missionId;
        this.matchId = matchId;
        this.description = description;
        this.reward = reward;
        this.difficulty = difficulty;
    }

    /**
     * Gets the unique identifier of the mission.
     *
     * @return the unique identifier of the mission
     */
    public int getMissionId() {
        return missionId;
    }

    /**
     * Gets the ID of the match associated with the mission.
     *
     * @return the ID of the match associated with the mission
     */
    public int getMatchId() {
        return matchId;
    }

    /**
     * Gets the description of the mission.
     *
     * @return the description of the mission
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the reward for completing the mission.
     *
     * @return the reward for completing the mission
     */
    public String getReward() {
        return reward;
    }

    /**
     * Gets the difficulty level of the mission.
     *
     * @return the difficulty level of the mission
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
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
