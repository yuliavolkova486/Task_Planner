package Task_Planner;

public enum Type {
    WORK(0),
    PRIVATE(1);

    public final int value;

    Type(final int value) {
        this.value = value;
    }
}
