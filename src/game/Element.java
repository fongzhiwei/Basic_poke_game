package game;

/**
 * An enumeration that store the elements.
 */

public enum Element {
    WATER("Water"),
    FIRE("Fire"),
    GRASS("Grass");

    private final String label;

    Element(String label){
        this.label = label;
    }

    /**
     *
     * @return the label text
     */
    @Override
    public String toString() {
        return label;
    }
}
