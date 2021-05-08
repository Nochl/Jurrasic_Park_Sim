package game;

public class Counter {
    private int max;
    private int value;

    public Counter(int max) {
        this.max = max;
        value = max;
    }

    public void dec() {
        if (value > 1) {
            value -= 1;
        }
    }

    public int getValue() {
        return value;
    }

}
