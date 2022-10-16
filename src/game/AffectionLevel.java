package game;

/**
 * An enumeration that store the affection level.
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * @version 1.0
 */
public enum AffectionLevel {
    DISLIKE(-50),   // Pokemon dislikes player/trainer, and the relationship cannot be fixed. It can't be captured anymore
    NEUTRAL(0),     // Neutral
    LIKE(50),       // Pokemon likes player/trainer and is willing to be captured with Pokeball
    FOLLOW(75),     // It follows the player/trainer around
    MAX(100);       // The maximum affection level

    /**
     * The affection points for affection level
     */
    private final int affectionPoints;

    /**
     * Constructor.
     *
     * @param points Affection points
     */
    AffectionLevel(int points) {
        this.affectionPoints = points;
    }

    /**
     * Get the affection points of pokemon.
     *
     * @return the affection points as an integer
     */
    public int getPoints() {
        return this.affectionPoints;
    }

    /**
     *
     * @return the affection points as a string
     */
    public String toString() {
        return String.valueOf(affectionPoints);
    }
}
