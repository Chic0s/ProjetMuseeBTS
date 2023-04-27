package controleur;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class PlanItems {


	private static PlanItems instance = null;
    private BooleanProperty modeSupprimer;

    public static PlanItems getInstance() {
        if (instance == null) {
            instance = new PlanItems();
        }
        return instance;
    }

    private PlanItems() {
        modeSupprimer = new SimpleBooleanProperty(false);
    }

    public void toggleModeSupprimer() {
        modeSupprimer.set(!modeSupprimer.get());
    }

    public void addItems(TextField nameimput, TabPane tabpane, Color color) {
        Circle circle = new Circle(10, color);
        circle.setLayoutX(50);
        circle.setLayoutY(50);

        String circleNameText = nameimput.getText().isEmpty() ? "" : nameimput.getText();
        Text circleName = new Text(circleNameText);
        circleName.setLayoutX(65);
        circleName.setLayoutY(50);

        circle.setOnMousePressed(event -> {
            if (modeSupprimer.get()) {
                // Supprime le cercle et le texte si le mode supprimer est activé
                Tab selectedTab = tabpane.getSelectionModel().getSelectedItem();
                if (selectedTab != null) {
                    AnchorPane anchorPane = (AnchorPane) selectedTab.getContent();
                    anchorPane.getChildren().removeAll(circle, circleName);
                }
            } else {
                circle.setUserData(new double[]{event.getX(), event.getY()});
            }
        });

        circle.setOnMouseDragged(event -> {
            if (!modeSupprimer.get()) {
                double[] startCoords = (double[]) circle.getUserData();
                double deltaX = event.getX() - startCoords[0];
                double deltaY = event.getY() - startCoords[1];
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);
                circleName.setLayoutX(circleName.getLayoutX() + deltaX);
                circleName.setLayoutY(circleName.getLayoutY() + deltaY);
            }
        });

        Tab selectedTab = tabpane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            AnchorPane anchorPane = (AnchorPane) selectedTab.getContent();
            anchorPane.getChildren().addAll(circle, circleName);
        }
    }
}
