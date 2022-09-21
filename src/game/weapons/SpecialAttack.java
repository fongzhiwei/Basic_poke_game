package game.weapons;

public enum SpecialAttack {
    EMBER("sparks"),
    BUBBLE("burbles"),
    VINEWHIP("whips");

    private final String label;

    SpecialAttack(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
