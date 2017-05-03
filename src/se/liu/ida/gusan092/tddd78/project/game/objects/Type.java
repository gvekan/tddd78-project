package se.liu.ida.gusan092.tddd78.project.game.objects;
/**
 * Representing each GameObject
 */
public enum Type
{
    /**
     * Representing Player
     */
    PLAYER,
    /**
     * Representing Roadblock
     */
    ROADBLOCK,
    /**
     * Representing container
     */
    CONTAINER,
    /**
     * Representing bullet
     */
    BULLET,
    /**
     * Representing trail
     */
    TRAIL,
    /**
     * Representing animal
     */
    ANIMAL,
    /**
     * Representing road
     */
    ROAD;

    private final int index;

    private Type() {
        this.index = ordinal();
    }

    public int getIndex(){
        return index;
    }
}
