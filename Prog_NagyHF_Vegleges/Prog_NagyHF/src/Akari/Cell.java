package Akari;

public class Cell{

    private int lightLevel = 0;
    private String type = "unassigned";
    private final int r;
    private final int c;
    private final MyGButton button;
    
    public Cell(MyGButton b, int i, int j) {
        button = b;
        r = i;
        c = j;
    }

    public void setLightLevel(int l) {
    lightLevel = l;
    }

    public int getLightLevel() {
    return lightLevel;
    }

    public void setType(String t) {
        type = t;
    }

    public String getType() {
        return type;
    }

    public void addLight() {
        lightLevel++;
    }

    public void subLight() {
        lightLevel--;
    }
    
    public int getRow() {
        return r;
    }

    public int getCol() {
        return c;
    }

    public MyGButton getButton() {
        return button;
    }
}