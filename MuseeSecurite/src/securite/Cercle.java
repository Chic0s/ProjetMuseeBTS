package securite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Cercle {
    private double layoutX;
    private double layoutY;
    private double radius;
    private Color color;
    private String name;

    // Constructeur
    public Cercle(double layoutX, double layoutY, double radius, Color color, String name) {
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.radius = radius;
        this.color = color;
        this.name = name;
    }

    // Getters et setters

    public double getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
    }

    public double getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Circle createCircle() {
        Circle circle = new Circle(radius, color);
        circle.setLayoutX(layoutX);
        circle.setLayoutY(layoutY);
        return circle;
    }

}
