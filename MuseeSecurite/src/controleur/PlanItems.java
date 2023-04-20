package controleur;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;



public class PlanItems {
	
	private static PlanItems instance=null;
	
	public static PlanItems getInstance(){
		if (instance==null){
			instance = new PlanItems();
		}
		return instance;
	}
		
    public void addItems(TextField nameimput, TabPane tabpane, Color color) {
        // Crée un cercle 
    	Circle circle = new Circle(10, color);  	
        circle.setLayoutX(50);
        circle.setLayoutY(50);

        // Ajoute un nom au-dessus du cercle
        String circleNameText = nameimput.getText().isEmpty() ? "" : nameimput.getText();
        Text circleName = new Text(circleNameText);
        circleName.setLayoutX(65);
        circleName.setLayoutY(50);

        // Gestion des événements pour déplacer le cercle et le texte
        circle.setOnMousePressed(event -> {
            circle.setUserData(new double[] {event.getX(), event.getY()});
        });

        circle.setOnMouseDragged(event -> {
            double[] startCoords = (double[]) circle.getUserData();
            double deltaX = event.getX() - startCoords[0];
            double deltaY = event.getY() - startCoords[1];
            circle.setLayoutX(circle.getLayoutX() + deltaX);
            circle.setLayoutY(circle.getLayoutY() + deltaY);
            circleName.setLayoutX(circleName.getLayoutX() + deltaX);
            circleName.setLayoutY(circleName.getLayoutY() + deltaY);
        });

        // Ajoute le cercle et le texte à l'AnchorPane de l'onglet sélectionné
        Tab selectedTab = tabpane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            AnchorPane anchorPane = (AnchorPane) selectedTab.getContent();
            anchorPane.getChildren().addAll(circle, circleName);
        }
	
};
}
