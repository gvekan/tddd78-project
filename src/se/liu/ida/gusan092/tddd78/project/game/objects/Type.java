package se.liu.ida.gusan092.tddd78.project.game.objects;

public enum Type
{
    PLAYER(),
    ROADBLOCK(),
    TRAFFIC_BARRIER(),
    POWERUP(),
    BULLET(),
    TRAIL(),
    ANIMAL(),
    ROAD();

    private final int index;

    private Type() {
        this.index = ordinal();
    }

    public int getIndex(){
        return index;
    }

    public static void main(String[] args) {
        Type test = Type.PLAYER;
	int v = test.getIndex();
        if (test == Type.values()[v]) System.out.println("sant");
        else System.out.println("falskt");

    }
}
