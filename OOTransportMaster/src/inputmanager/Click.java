package inputmanager;

public class Click {

    int i;
    int j;
    boolean rightClick;

    public Click(int i, int j, boolean rightClick) {
        this.i = i;
        this.j = j;
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
