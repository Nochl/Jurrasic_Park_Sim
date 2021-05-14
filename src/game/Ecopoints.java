package game;

public class Ecopoints {
    private int points;

    public Ecopoints() {
        this.points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }

    public void removePoints(int points) {
        this.points -= points;
    }
}
