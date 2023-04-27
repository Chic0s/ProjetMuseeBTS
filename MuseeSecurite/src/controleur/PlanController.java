package controleur;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import dao.Connexion;
import dao.ElementDeSecuriteDAO;
import dao.InfoTelephoneDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import securite.ElementDeSecurite;
import securite.InfoTelephone;


public class PlanController {

	
	// TABLEAU CAMERA
    @FXML
    private TableView<ElementDeSecurite> tableViewCamera;

    @FXML
    private TableColumn<ElementDeSecurite, String> idColumnCamera;

    @FXML
    private TableColumn<ElementDeSecurite, String> nomColumnCamera;

    @FXML
    private TableColumn<ElementDeSecurite, String> modeleColumnCamera;

    @FXML
    private TableColumn<ElementDeSecurite, String> emplacementColumnCamera;
    
    @FXML 
    private TableColumn<ElementDeSecurite, String> serveurColumnCamera;
        
    @FXML 
    private TableColumn<ElementDeSecurite, String> etatColumnCamera;
    
    
	// TABLEAU CAPTEUR
    @FXML
    private TableView<ElementDeSecurite> tableViewCapteur;

    @FXML
    private TableColumn<ElementDeSecurite, String> idColumnCapteur;

    @FXML
    private TableColumn<ElementDeSecurite, String> nomColumnCapteur;

    @FXML
    private TableColumn<ElementDeSecurite, String> modeleColumnCapteur;

    @FXML
    private TableColumn<ElementDeSecurite, String> emplacementColumnCapteur;
    
    @FXML 
    private TableColumn<ElementDeSecurite, String> serveurColumnCapteur;
        
    @FXML 
    private TableColumn<ElementDeSecurite, String> etatColumnCapteur;
    
    
	// TABLEAU TELEPHONE
    @FXML
    private TableView<InfoTelephone> tableViewTelephone;

    @FXML
    private TableColumn<InfoTelephone, String> nomColumnTelephone;

    @FXML
    private TableColumn<InfoTelephone, String> numeroColumnTelephone;
    
    // TEXTE TELEPHONE
    
    @FXML
    public Label demarcheText;

    
    //PLAN ELEMENT CAPTEUR / DETECTEUR / CAMERA
    
    @FXML
    private Button addRedCircleButton;
    
    @FXML
    private Button addCaptorButton;
    
    @FXML
    private Button addDetectorButton;
    
    @FXML
    private Button supprimerButton;
    
    @FXML
    private TextField circleNameInput;

    
    //PLAN ETAGE
    
    @FXML
    private Button removeEtage;

    @FXML
    private TabPane etage;
    
    private int tabCounter = 0;
    
    
    //SAUVEGARDE ETAGE
    
    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;
    
        
    @FXML
    public void initialize() {
   
    	Connexion.getInstance();
    	
        //Initalisation des différents tableaux 
        this.initializeZoneTableViewCamera();
        this.initializeZoneTableViewCapteur();
        this.initializeZoneTableViewTelephone();
        
        // Enleve un nouvel onglet lors du clic sur le bouton
        removeEtage.setOnAction(e -> {
            Tab selectedTab = etage.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                etage.getTabs().remove(selectedTab);
                tabCounter = tabCounter-1;
            }
        });
        
        tableViewCamera.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
            	ElementDeSecurite selectedItem = tableViewCamera.getSelectionModel().getSelectedItem();
            	try {
           	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/ModifierCamera.fxml"));
        	        Parent root = loader.load();
        	        Stage stage = new Stage();
        	        stage.initModality(Modality.APPLICATION_MODAL);
        	        stage.setTitle("Modifier Camera");
        	        stage.setScene(new Scene(root));
        	        ((ModifierCameraController) loader.getController()).openWindow(selectedItem);
        	        stage.showAndWait();
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
                          
            }
        });

        
        InfotelephoneController.getInstance().updateTextFromFile("demarcheasuivre.json", demarcheText);
        
        etage.setOnDragOver(event -> handleDragOver(event));
        etage.setOnDragDropped(event -> handleDragDropped(event));
        addRedCircleButton.setOnAction(e -> {PlanItems.getInstance().addItems(circleNameInput,etage,Color.RED);});
        addCaptorButton.setOnAction(e -> {PlanItems.getInstance().addItems(circleNameInput,etage,Color.GREEN);});
        addDetectorButton.setOnAction(e -> {PlanItems.getInstance().addItems(circleNameInput,etage,Color.BLUE);});
        supprimerButton.setOnAction(event -> PlanItems.getInstance().toggleModeSupprimer());
                        
        


    }
    
    
    // Fonction pour initialiser les données SQL vers un tableau 
    
    public void initializeZoneTableViewCamera() {
        List<ElementDeSecurite> allElements = ElementDeSecuriteDAO.getInstance().readAll();
        List<ElementDeSecurite> filteredElements = allElements.stream()
                .filter(element -> element.getModele().startsWith("Camera"))
                .collect(Collectors.toList());

        this.tableViewCamera.getItems().setAll(filteredElements);
        idColumnCamera.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()+""));
        nomColumnCamera.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()+""));
        modeleColumnCamera.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModele()+""));
        emplacementColumnCamera.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmplacement()+""));
        serveurColumnCamera.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServeur()+""));
        etatColumnCamera.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat()+""));
    }
    public void initializeZoneTableViewCapteur() {
        List<ElementDeSecurite> allElements = ElementDeSecuriteDAO.getInstance().readAll();
        List<ElementDeSecurite> filteredElements = allElements.stream()
                .filter(elem -> elem.getModele().startsWith("Capteur"))
                .collect(Collectors.toList());
        
        this.tableViewCapteur.getItems().setAll(filteredElements);
        idColumnCapteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId() + ""));
        nomColumnCapteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom() + ""));
        modeleColumnCapteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModele() + ""));
        emplacementColumnCapteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmplacement() + ""));
        serveurColumnCapteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServeur()+""));
        etatColumnCapteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat()+""));
    }
    
    public void initializeZoneTableViewTelephone() {
        this.tableViewTelephone.getItems().setAll(InfoTelephoneDAO.getInstance().readAll());
        nomColumnTelephone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()+""));
        numeroColumnTelephone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumerotelephone()+""));
    }
    
    //TO DO ... Tableau Alertes
    
    
    
    //Ajout d'une image via un drag and drop
          
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
    
    @FXML
    private void handleOpenAjouterCamera(ActionEvent event) {
        try {
            // Charge le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ihm/AjouterCamera.fxml"));
            Parent root = fxmlLoader.load();

            // Crée une nouvelle scène et une nouvelle fenêtre (stage)
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajouter un élément");

            // Affiche la nouvelle fenêtre
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleOpenAjouterCapteur(ActionEvent event) {
        try {
            // Charge le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ihm/AjouterCapteur.fxml"));
            Parent root = fxmlLoader.load();

            // Crée une nouvelle scène et une nouvelle fenêtre (stage)
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajouter un élément");

            // Affiche la nouvelle fenêtre
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleOpenAjouterTelephone(ActionEvent event) {
        try {
            // Charge le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ihm/AjouterTelephone.fxml"));
            Parent root = fxmlLoader.load();

            // Crée une nouvelle scène et une nouvelle fenêtre (stage)
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajouter un élément");

            // Affiche la nouvelle fenêtre
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    private void handleDeleteActionCamera(ActionEvent event) {
        ElementDeSecurite selectedItem = tableViewCamera.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            tableViewCamera.getItems().remove(selectedItem);
            ElementDeSecuriteDAO.getInstance().delete(selectedItem);
        } else {
            // Afficher une alerte si aucune ligne n'est sélectionnée
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun élément sélectionné");
            alert.setContentText("Veuillez sélectionner un élément dans le tableau.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteActionCapteur(ActionEvent event) {
        ElementDeSecurite selectedItem = tableViewCapteur.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            tableViewCapteur.getItems().remove(selectedItem);
            ElementDeSecuriteDAO.getInstance().delete(selectedItem);
        } else {
            // Afficher une alerte si aucune ligne n'est sélectionnée
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun élément sélectionné");
            alert.setContentText("Veuillez sélectionner un élément dans le tableau.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteActionInfoTelephone(ActionEvent event) {
        InfoTelephone selectedItem = tableViewTelephone.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
        	tableViewTelephone.getItems().remove(selectedItem);
            InfoTelephoneDAO.getInstance().delete(selectedItem);
        } else {
            // Afficher une alerte si aucune ligne n'est sélectionnée
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun élément sélectionné");
            alert.setContentText("Veuillez sélectionner un élément dans le tableau.");
            alert.showAndWait();
        }
    }
   
}


