package inputmanager;

public class Click {

    private int i;
    private int j;
    private boolean rightClick;

    public Click(int i, int j, boolean rightClick) {
        this.i = i-28;
        this.j = j-7;
        this.rightClick = rightClick;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isRightClick() {
        return rightClick;
    }

    public void setRightClick(boolean rightClick) {
        this.rightClick = rightClick;
    }
}
