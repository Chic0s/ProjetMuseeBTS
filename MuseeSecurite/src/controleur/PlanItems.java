package controleur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import securite.Cercle;

public class PlanItems {

    private int tabCounter = 0;
    
	private static PlanItems instance = null;
    
	private BooleanProperty modeSupprimer;
	
	
	private int ImageWitdh = 600;
	
	private int ImageHeight = 300;
	

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
    
   
    public void addItems(String nameInput, TabPane tabPane, Color color) {
        Cercle cercleItem = new Cercle(50,50,10.0,color,nameInput);
        Circle circle = new Circle(cercleItem.getRadius(), cercleItem.getColor());
        circle.setLayoutX(50);
        circle.setLayoutY(50);

        Text circleName = new Text(nameInput);
        circleName.setLayoutX(65);
        circleName.setLayoutY(50);

        circle.setOnMousePressed(event -> {
            if (modeSupprimer.get()) {
                // Supprime le cercle et le texte si le mode supprimer est activé
                Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
                if (selectedTab != null) {
                    AnchorPane anchorPane = (AnchorPane) selectedTab.getContent();
                    anchorPane.getChildren().removeAll(circle, circleName);
                }
            } else {
                circle.setUserData(new CercleUserData(event.getX(), event.getY(), nameInput));
            }
        });

        circle.setOnMouseDragged(event -> {
            if (!modeSupprimer.get()) {
                CercleUserData cercleUserData = (CercleUserData) circle.getUserData();
                double deltaX = event.getX() - cercleUserData.getStartX();
                double deltaY = event.getY() - cercleUserData.getStartY();
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);
                circleName.setLayoutX(circleName.getLayoutX() + deltaX);
                circleName.setLayoutY(circleName.getLayoutY() + deltaY);
            }
        });

        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
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
        double defaultWidth = ImageWitdh;
        double defaultHeight = ImageHeight;
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
	
	public void savePlanData(String filePath, String fileName, TabPane etage) {
	    JSONArray tabPaneArray = new JSONArray();

	    // Parcourir tous les TabPane
	    for (Tab tabPane : etage.getTabs()) {
	        JSONObject tabPaneObject = new JSONObject();

	        // Sauvegarder l'image
	        AnchorPane anchorPane = (AnchorPane) tabPane.getContent();
	        String imagePath = (String) tabPane.getUserData();
	        JSONObject imageObject = new JSONObject();
	        imageObject.put("imagePath", imagePath);
	        imageObject.put("fitWidth", ImageWitdh);
	        imageObject.put("fitHeight", ImageHeight);

	        tabPaneObject.put("image", imageObject);

	        // Sauvegarder les cercles
	        JSONArray circlesArray = new JSONArray();
	        for (Node node : anchorPane.getChildren()) {
	            if (node instanceof Circle) {
	                Circle circle = (Circle) node;
	                JSONObject circleObject = new JSONObject();
	                circleObject.put("layoutX", circle.getLayoutX());
	                circleObject.put("layoutY", circle.getLayoutY());
	                circleObject.put("radius", circle.getRadius());
	                circleObject.put("color", circle.getFill().toString());

	                // Vérifier si le cercle a une propriété "name" dans le UserData
	                Object userData = circle.getUserData();
	                if (userData instanceof CercleUserData) {
	                    CercleUserData cercleUserData = (CercleUserData) userData;
	                    circleObject.put("name", cercleUserData.getName());
	                } else if (userData instanceof String) {
	                    circleObject.put("name", (String) userData);
	                }

	                circlesArray.put(circleObject);
	            }
	        }

	        tabPaneObject.put("circles", circlesArray);
	        tabPaneArray.put(tabPaneObject);
	    }

	    JSONObject jsonPlan = new JSONObject();
	    jsonPlan.put("tabPanes", tabPaneArray);

	    try (FileWriter file = new FileWriter(filePath + "/" + fileName)) {
	        file.write(jsonPlan.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public void ouvrirPlan(File selectedFile, TabPane etage) {
	    try {
	        String jsonString = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);

	        JSONObject json = new JSONObject(jsonString);

	        // Charger les TabPanes depuis le fichier JSON
	        JSONArray tabPanesArray = json.getJSONArray("tabPanes");
	        for (int i = 0; i < tabPanesArray.length(); i++) {
	            JSONObject tabPaneObject = tabPanesArray.getJSONObject(i);

	            // Charger l'image pour le TabPane courant
	            JSONObject jsonImage = tabPaneObject.getJSONObject("image");
	            String imagePath = jsonImage.getString("imagePath");
	            double fitWidth = jsonImage.getDouble("fitWidth");
	            double fitHeight = jsonImage.getDouble("fitHeight");

	            Image image = new Image("file:" + imagePath);
	            ImageView imageView = new ImageView(image);
	            imageView.setFitWidth(fitWidth);
	            imageView.setFitHeight(fitHeight);
	            imageView.setPreserveRatio(true);
	            imageView.setSmooth(true);

	            AnchorPane anchorPane = new AnchorPane(imageView);
	            Tab newTab = new Tab("Etage " + tabCounter++, anchorPane);
	            newTab.setUserData(imagePath);
	            etage.getTabs().add(newTab);

	            // Charger les cercles pour le TabPane courant
	            JSONArray jsonCircles = tabPaneObject.getJSONArray("circles");
	            for (int j = 0; j < jsonCircles.length(); j++) {
	                JSONObject jsonCircle = jsonCircles.getJSONObject(j);
	                double layoutX = jsonCircle.getDouble("layoutX");
	                double layoutY = jsonCircle.getDouble("layoutY");
	                double radius = jsonCircle.getDouble("radius");

	                String circleNameText = "";
	                if (jsonCircle.has("name")) {
	                	circleNameText = jsonCircle.getString("name");
	                }
	                
	                String colorStr = jsonCircle.getString("color"); // Récupérer la couleur du cercle
	                Color color = Color.web(colorStr); // Convertir la couleur à partir de la représentation en chaîne

	                Cercle cercle = new Cercle(layoutX,layoutY,radius,color,circleNameText);
	                Circle circle = cercle.createCircle();
	                circle.setLayoutX(layoutX);
	                circle.setLayoutY(layoutY);

	                // Récupérer les informations de position et de nom du cercle
	                circle.setUserData(new CercleUserData(layoutX, layoutY, cercle.getName()));

	                Text circleName = new Text(cercle.getName());
	                circleName.setLayoutX(layoutX + 15);
	                circleName.setLayoutY(layoutY - 15);

	                
	                circle.setOnMousePressed(event -> {
	                    if (modeSupprimer.get()) {
	                        // Supprime le cercle et le texte si le mode supprimer est activé
	                        Tab selectedTab = etage.getSelectionModel().getSelectedItem();
	                        if (selectedTab != null) {
	                            anchorPane.getChildren().removeAll(circle, circleName);
	                        }
	                    } else {
	                    	circle.setUserData(new CercleUserData(layoutX, layoutY, cercle.getName()));
	                    }
	                });
	                

	                circle.setOnMouseDragged(event -> {
	                    if (!modeSupprimer.get()) {
	                        double deltaX = event.getX();
	                        double deltaY = event.getY();
	                        circle.setLayoutX(circle.getLayoutX() + deltaX);
	                        circle.setLayoutY(circle.getLayoutY() + deltaY);
	                        circleName.setLayoutX(circleName.getLayoutX() + deltaX);
	                        circleName.setLayoutY(circleName.getLayoutY() + deltaY);
	                    }
	                });

	                anchorPane.getChildren().addAll(circle, circleName);
	            }
	        }
	    } catch (IOException | JSONException e) {
	        e.printStackTrace();
	    }
	}

	public boolean fermerPlan() {
		boolean rep = false;
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Confirmation");
	    alert.setHeaderText("Fermer le plan actuel");
	    alert.setContentText("Avez-vous bien sauvegardé avant de fermer le plan actuel ?");

	    ButtonType buttonTypeOui = new ButtonType("Oui");
	    ButtonType buttonTypeNon = new ButtonType("Non");

	    alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.isPresent() && result.get() == buttonTypeOui) {
	    	rep = true;
	        //etage.getTabs().clear();
	    }
		return rep;
	}

	
}
