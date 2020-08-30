public class Cell {
    private int x;
    private int y;
    private int w;
    private int h;
    private String value;

    public static final String X_VALUE = "X";
    public static final String O_VALUE = "O";

    public Cell() {
        this.setValue("");
    }

    public Cell(int x, int y, int w, int h, String value) {
        this();
        this.setX(x);
        this.setY(y);
        this.setW(w);
        this.setH(h);
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}