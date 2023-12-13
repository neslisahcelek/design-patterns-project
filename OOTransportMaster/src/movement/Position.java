package movement;

public class Position {
    public Position(double i, double j) {
        this.i = i;
        this.j = j;
    }

    private double i;
    private double j;

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }
}