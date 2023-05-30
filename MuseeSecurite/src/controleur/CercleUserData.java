package controleur;

public class CercleUserData {
    private double startX;
    private double startY;
    private String name;

    public CercleUserData(double startX, double startY, String name) {
        this.startX = startX;
        this.startY = startY;
        this.name = name;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
