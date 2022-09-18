package pokemon;

public enum AffectionLevel {
    DISLIKE(-50),
    NEUTRAL(0),
    LIKE(50),
    FOLLOW(75),
    MAX(100);

    private final int affectionPoints;

    AffectionLevel(int points) {
        this.affectionPoints = points;
    }

    public String toString() {
        return String.valueOf(affectionPoints);
    }
}
