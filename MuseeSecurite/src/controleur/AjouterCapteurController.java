package controleur;

import java.util.List;

import dao.ElementDeSecuriteDAO;
import dao.SalleDAO;
import dao.ServeurDAO;
import dao.TypeCapteurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import securite.ElementDeSecurite;
import securite.Salle;
import securite.Serveur;
import securite.TypeCapteur;


public class AjouterCapteurController {
	
	@FXML
	private ListView<TypeCapteur> typeCapteurListView;
	
	@FXML
	private ComboBox<String> serveurComboBox;
	
	@FXML
	private ComboBox<String> salleComboBox;
	
	@FXML
	private Label typeCapteur;
	
	@FXML
	private Label formatDeSortie;
	
	@FXML
	private Label description;
	
	@FXML 
	private Button enregistrerCapteur;
	
	@FXML 
	private TextField nomCapteur;
	
	@FXML 
	private CheckBox etatCapteur;
	
	@FXML
	public void initialize() {
	    this.populateTypeCameraListView();
	    this.populateServeurComboBox();
	    this.populateSalleComboBox();
	    
	    
	    //Ecouteur d'évènement 
	    typeCapteurListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, NewSelection)-> {
	    	if(NewSelection != null) {
	    		TypeCapteur selectedCapteur = typeCapteurListView.getSelectionModel().getSelectedItem();
	    		formatDeSortie.setText(selectedCapteur.getFormatdesortie());
	    		typeCapteur.setText(selectedCapteur.getType());
	    		description.setText(selectedCapteur.getDescription());
	    		
	    	}
	    });
	}

	
	private void populateSalleComboBox() {
	    List<Salle> sallelist = SalleDAO.getInstance().readAll();
	    ObservableList<String> salleNoms = FXCollections.observableArrayList();
	    for (Salle salle : sallelist) {
	        salleNoms.add(salle.getNom());
	    }
	    salleComboBox.setItems(salleNoms);
	}


	private void populateTypeCameraListView() {
	    List<TypeCapteur> typeCapteurList = TypeCapteurDAO.getInstance().readAll();
	    ObservableList<TypeCapteur> capteurType = FXCollections.observableArrayList();
	    for (TypeCapteur capteur : typeCapteurList) {
	    	capteurType.add(capteur);
	    }
	    typeCapteurListView.setItems(capteurType);
	}
	
	private void populateServeurComboBox() {
	    List<Serveur> serveurList = ServeurDAO.getInstance().readAll();
	    ObservableList<String> serveurNoms = FXCollections.observableArrayList();
	    for (Serveur serveur : serveurList) {
	        serveurNoms.add(serveur.getNom());
	    }
	    serveurComboBox.setItems(serveurNoms);
	}
	
	@FXML
	private void handleAddCameraAction(ActionEvent event) {
	    String nom = nomCapteur.getText();
	    String emplacement = salleComboBox.getValue();
	    String modele = typeCapteur.getText();
	    String serveur = serveurComboBox.getValue();
	    Boolean etat = etatCapteur.isSelected();
	    
	    if (nom.isEmpty() || emplacement == null || modele.isEmpty() || serveur == null || etat == null) {
	        // Afficher une alerte si les champs ne sont pas remplis correctement
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Informations manquantes");
	        alert.setHeaderText("Tous les champs doivent être remplis");
	        alert.setContentText("Veuillez renseigner le nom, l'emplacement et le modèle du capteur.");
	        alert.showAndWait();
	    } else {
	        ElementDeSecurite newElement = new ElementDeSecurite(0, nom, modele, emplacement, serveur, etat);
	        ElementDeSecuriteDAO.getInstance().create(newElement);
	        Stage stage = (Stage) enregistrerCapteur.getScene().getWindow();
	        stage.close();
	        /*/
	        if (ElementDeSecuriteDAO.getInstance().create(newElement)) {
	            tableViewCamera.getItems().add(newElement);
	            nameInput.clear();
	            emplacementInput.clear();
	            modeleComboBox.getSelectionModel().clearSelection();
	        } else {
	            // Afficher une alerte en cas d'échec de l'ajout
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Erreur lors de l'ajout");
	            alert.setContentText("Une erreur est survenue lors de l'ajout de l'élément. Veuillez réessayer.");
	            alert.showAndWait();
	        }
	        /*/
	    }
	}
	

}
