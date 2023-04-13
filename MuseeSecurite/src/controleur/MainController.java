package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class MainController {
    
    @FXML
    private Button removeEtage;

    @FXML
    private TabPane etage;
    
    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;
    
    @FXML
    private Button addRedCircleButton;

    private int tabCounter = 0;


    
    @FXML
    public void initialize() {
        // Ajoute un nouvel onglet lors du clic sur le bouton
        
        // Enleve un nouvel onglet lors du clic sur le bouton
        removeEtage.setOnAction(e -> {
            Tab selectedTab = etage.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                etage.getTabs().remove(selectedTab);
                tabCounter = tabCounter-1;
            }
        });
        
        etage.setOnDragOver(event -> handleDragOver(event));
        etage.setOnDragDropped(event -> handleDragDropped(event));
        addRedCircleButton.setOnAction(e -> {addRedCircleWithText();});

        
    }
    

    private void addRedCircleWithText() {
        // Crée un cercle rouge
        Circle circle = new Circle(25, Color.RED);
        circle.setLayoutX(50);
        circle.setLayoutY(50);

        // Ajoute un nom au-dessus du cercle
        Text circleName = new Text("Camera");
        circleName.setLayoutX(50);
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
		
	}


	private void addTabWithImage(String imagePath) {
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

    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != etage && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            try {
                String imagePath = db.getFiles().get(0).getAbsolutePath();
                addTabWithImage(imagePath);
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
}


