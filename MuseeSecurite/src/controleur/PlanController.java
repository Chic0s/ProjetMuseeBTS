package controleur;

import java.io.File;

import dao.Connexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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
    private Button addDetectorButton;
    
    @FXML
    private Button supprimerButton;
        
    @FXML
    private ComboBox<ElementDeSecurite> ListElem;

    
    //PLAN ETAGE
    
    @FXML
    private Button removeEtage;

    @FXML
    private TabPane etage;
    
   
    
    //SAUVEGARDE ETAGE
    
    @FXML
    private MenuItem sauvegarderPlanButton;

    @FXML
    private MenuItem ouvrirPlanButton;
    
        
    @FXML
    public void initialize() {
   
    	Connexion.getInstance();
    	
    	this.initializeZoneTableView(ElementDeControle.CAPTEUR);
    	this.initializeZoneTableView(ElementDeControle.CAMERA);
    	this.initializeZoneTableView(ElementDeControle.TELEPHONE);
        
        ComboBoxEdit.getInstance().ElementDeSecuriteComboBox(ListElem);
        
        etage.setOnDragOver(event -> handleDragOver(event));
        etage.setOnDragDropped(event -> handleDragDropped(event));
        addDetectorButton.setOnAction(e -> {PlanItems.getInstance().addItems("Detecteur",etage,Color.BLUE);});
        supprimerButton.setOnAction(event -> PlanItems.getInstance().toggleModeSupprimer()); 
        
        InfotelephoneController.getInstance().updateTextFromFile("demarcheasuivre.json", demarcheText);
        
        // Enleve un nouvel onglet lors du clic sur le bouton
        removeEtage.setOnAction(e -> {
        		PlanItems.getInstance().RemoveTabWithImage(etage);
            });
        
        tableViewCamera.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
            	ElementDeSecurite selectedItem = tableViewCamera.getSelectionModel().getSelectedItem();
            	try {
           	        FXMLLoader loader = new FXMLLoader(getClass().getResource(ListIhm.IHMCAMERAMODIFIER.getUrl()));
        	        Parent root = loader.load();
        	        Stage stage = new Stage();
        	        stage.initModality(Modality.APPLICATION_MODAL);
        	        stage.setTitle("Modifier Camera");
        	        stage.setScene(new Scene(root));
        	        ((ModifierCameraController) loader.getController()).openWindow(selectedItem);
        	        stage.showAndWait();
        	     	this.initializeZoneTableView(ElementDeControle.CAMERA);
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
                          
            }
        });
        
        tableViewCapteur.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
            	ElementDeSecurite selectedItem = tableViewCapteur.getSelectionModel().getSelectedItem();
            	try {
           	        FXMLLoader loader = new FXMLLoader(getClass().getResource(ListIhm.IHMCAPTEURMODIFIER.getUrl()));
        	        Parent root = loader.load();
        	        Stage stage = new Stage();
        	        stage.initModality(Modality.APPLICATION_MODAL);
        	        stage.setTitle("Modifier Capteur");
        	        stage.setScene(new Scene(root));
         	        ((ModifierCapteurController) loader.getController()).openWindow(selectedItem);
        	        stage.showAndWait();
        	     	this.initializeZoneTableView(ElementDeControle.CAPTEUR);
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
                          
            }
        });

        tableViewTelephone.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
            	InfoTelephone selectedItem = tableViewTelephone.getSelectionModel().getSelectedItem();
            	try {
           	        FXMLLoader loader = new FXMLLoader(getClass().getResource(ListIhm.IHMTELEPHONEMODIFIER.getUrl()));
        	        Parent root = loader.load();
        	        Stage stage = new Stage();
        	        stage.initModality(Modality.APPLICATION_MODAL);
        	        stage.setTitle("Modifier Telephone");
        	        stage.setScene(new Scene(root));
        	        ((ModifierTelephoneController) loader.getController()).openWindow(selectedItem);
        	        stage.showAndWait();
        	     	this.initializeZoneTableView(ElementDeControle.TELEPHONE);
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
                          
            }
        });
               
        
        // Écouteur pour la sélection d'un objet dans la ComboBox
        ListElem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof ElementDeSecurite) {
            	ElementDeSecurite elem = (ElementDeSecurite) newValue;
                String modele = elem.getModele();
                String nom = elem.getNom();
                if (modele.startsWith(ElementDeControle.CAMERA.getType())) {
                	
                    PlanItems.getInstance().addItems(nom,etage,Color.RED);
                } 
                if (modele.startsWith(ElementDeControle.CAPTEUR.getType())) {
                	
                	PlanItems.getInstance().addItems(nom,etage,Color.GREEN);
                }
            } 
        });

        //Fichier JSON 
        
        sauvegarderPlanButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner un emplacement pour la sauvegarde");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier JSON (*.json)", "*.json"));

            // Ouvrir le menu de sauvegarde et attendre que l'utilisateur effectue une sélection
            File selectedFile = fileChooser.showSaveDialog(etage.getScene().getWindow());

            if (selectedFile != null) {
                String filePath = selectedFile.getParent(); // Récupérer le chemin du dossier
                String fileName = selectedFile.getName(); // Récupérer le nom du fichier

                PlanItems.getInstance().savePlanData(filePath, fileName, etage);
            }
        });
        ouvrirPlanButton.setOnAction(event -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrir le plan");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Fichiers JSON", "*.json"));

            // Afficher la boîte de dialogue d'ouverture de fichier
            File selectedFile = fileChooser.showOpenDialog(etage.getScene().getWindow());


            if (selectedFile != null) {

            	PlanItems.getInstance().ouvrirPlan(selectedFile, etage);
            }
        });
        
    }
       
    public void initializeZoneTableView(ElementDeControle type) {
        switch (type) {
        case CAPTEUR:
            TableViewController.getInstance().initializeZoneTableViewElementDeSecurite(ElementDeControle.CAPTEUR.getType(),tableViewCapteur,idColumnCapteur,nomColumnCapteur,
            		modeleColumnCapteur,emplacementColumnCapteur,serveurColumnCapteur,etatColumnCapteur);
            break;
        case CAMERA:
            TableViewController.getInstance().initializeZoneTableViewElementDeSecurite(ElementDeControle.CAMERA.getType(),tableViewCamera,idColumnCamera,nomColumnCamera,
            		modeleColumnCamera,emplacementColumnCamera,serveurColumnCamera,etatColumnCamera);
            break;
        case TELEPHONE:
            TableViewController.getInstance().initializeZoneTableViewTelephone(tableViewTelephone, nomColumnTelephone, numeroColumnTelephone);
            break;
        default:
            break;
    }
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
                PlanItems.getInstance().addTabWithImage(imagePath, etage);
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
    
    //BOUTON AJOUTER 
    
    @FXML
    private void handleOpenAjouterCamera(ActionEvent event) {
    	 HandleActionController.getInstance().AjouterElemTableau(ElementDeControle.CAMERA.getType());
     	this.initializeZoneTableView(ElementDeControle.CAMERA);

    }
    
    @FXML
    private void handleOpenAjouterCapteur(ActionEvent event) {
	   	 HandleActionController.getInstance().AjouterElemTableau(ElementDeControle.CAPTEUR.getType());
	     this.initializeZoneTableView(ElementDeControle.CAPTEUR);
    }
    
    @FXML
    private void handleOpenAjouterTelephone(ActionEvent event) {
	   	 HandleActionController.getInstance().AjouterElemTableau(ElementDeControle.TELEPHONE.getType());
	     this.initializeZoneTableView(ElementDeControle.TELEPHONE);
    }

    //BOUTON MODIFIER 
    @FXML
    private void handleModifierCamera(ActionEvent event) {
    	HandleActionController.getInstance().ModifierElemDeSecurite(tableViewCamera,ElementDeControle.CAMERA.getType());
     	this.initializeZoneTableView(ElementDeControle.CAMERA);

    }
    
    @FXML
    private void handleModifierCapteur(ActionEvent event) {
    	HandleActionController.getInstance().ModifierElemDeSecurite(tableViewCapteur, ElementDeControle.CAPTEUR.getType());
     	this.initializeZoneTableView(ElementDeControle.CAPTEUR);
    }
    
    
    @FXML
    private void handleModifierTelephone(ActionEvent event) {
    	HandleActionController.getInstance().ModifierTelephone(tableViewTelephone);
     	this.initializeZoneTableView(ElementDeControle.TELEPHONE);
    }
    
    //SUPPRIMER LES ELEMENTS DU TABLEAU
    
    @FXML
    private void handleDeleteActionCamera(ActionEvent event) {
    	HandleActionController.getInstance().DeleteElem(tableViewCamera);
    }
    
    @FXML
    private void handleDeleteActionCapteur(ActionEvent event) {
    	HandleActionController.getInstance().DeleteElem(tableViewCapteur);
    }
    
    @FXML
    private void handleDeleteActionInfoTelephone(ActionEvent event) {
    	HandleActionController.getInstance().DeleteElemInfoTelephone(tableViewTelephone);
    }
   
    

    
}