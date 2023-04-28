package controleur;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class PlanItems {

    private int tabCounter = 0;
    
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
    
   
    public void addItems(String nameimput, TabPane tabpane, Color color) {
        Circle circle = new Circle(10, color);
        circle.setLayoutX(50);
        circle.setLayoutY(50);

        String circleNameText = nameimput.isEmpty() ? "" : nameimput;
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
    
    
    //Ajout d'une image via un drag and drop
          
	public void addTabWithImage(String imagePath, TabPane etage) {
        // Création d'une nouvelle image
        Image image;
        if (imagePath != null) {
            image = new Image("file:" + imagePath);
        } else {
            image = new Image("example_image.png");// A Modifier 
        }
        ImageView imageView = new ImageView(image);
        
        // Définition de la taille par défaut de l'image
        double defaultWidth = 300;
        double defaultHeight = 200;
        imageView.setFitWidth(defaultWidth);
        imageView.setFitHeight(defaultHeight);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        // Création d'un AnchorPane pour contenir l'image
        AnchorPane anchorPane = new AnchorPane(imageView);

        // Création d'un nouvel onglet et ajout de l'AnchorPane contenant l'image
        Tab newTab = new Tab("Etage " + tabCounter++, anchorPane);
        newTab.setUserData(imagePath); // Stocke le chemin de l'image en tant que donnée utilisateur
        etage.getTabs().add(newTab);
    }
	
	public void RemoveTabWithImage(TabPane etage) {
        Tab selectedTab = etage.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            etage.getTabs().remove(selectedTab);
            tabCounter = tabCounter-1;
        }
	}
}
