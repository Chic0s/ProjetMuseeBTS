package controleur;

import javafx.scene.control.CheckBox;

import dao.ElementDeSecuriteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import securite.ElementDeSecurite;
import securite.TypeCamera;


public class AjouterCameraController {
	
	@FXML
	private ListView<TypeCamera> typeCameraListView;
	
	@FXML
	private ComboBox<String> serveurComboBox;
	
	@FXML
	private ComboBox<String> salleComboBox;
	
	@FXML
	private Label fluxLabel;
	
	@FXML
	private Label angleDeVue;
	
	@FXML
	private Label typeDeCamera;
	
	@FXML
	private Label description;
	
	@FXML 
	private Button enregistrerCamera;
	
	@FXML 
	private TextField nomCamera;
	
	@FXML 
	private CheckBox etatCamera;
	
	@FXML
	public void initialize() {
	    ComboBoxEdit.getInstance().populateTypeCameraListView(typeCameraListView);
	    ComboBoxEdit.getInstance().populateServeurComboBox(serveurComboBox);
	    ComboBoxEdit.getInstance().populateSalleComboBox(salleComboBox);
	    
	    
	    //Ecouteur d'�v�nement 
	    typeCameraListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, NewSelection)-> {
	    	if(NewSelection != null) {
	    		TypeCamera selectedCamera = typeCameraListView.getSelectionModel().getSelectedItem();
	    		fluxLabel.setText(selectedCamera.getFlux());
	    		angleDeVue.setText(selectedCamera.getAngledevue());
	    		typeDeCamera.setText(selectedCamera.getTypedecamera());
	    		description.setText(selectedCamera.getDescription());
	    		
	    	}
	    });
	}

		
	@FXML
	private void handleAddCameraAction(ActionEvent event) {
	    String nom = nomCamera.getText();
	    String emplacement = salleComboBox.getValue();
	    String modele = typeDeCamera.getText();
	    String serveur = serveurComboBox.getValue();
	    Boolean etat = etatCamera.isSelected();
	    
	    if (nom.isEmpty() || emplacement == null || modele.isEmpty() || serveur == null || etat == null) {
	        // Afficher une alerte si les champs ne sont pas remplis correctement
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Informations manquantes");
	        alert.setHeaderText("Tous les champs doivent �tre remplis");
	        alert.setContentText("Veuillez renseigner le nom, l'emplacement et le mod�le de la cam�ra.");
	        alert.showAndWait();
	    } else {
	        ElementDeSecurite newElement = new ElementDeSecurite(0, nom, modele, emplacement, serveur, etat);
	        ElementDeSecuriteDAO.getInstance().create(newElement);
	        Stage stage = (Stage) enregistrerCamera.getScene().getWindow();
	        stage.close();
	        /*/
	        if (ElementDeSecuriteDAO.getInstance().create(newElement)) {
	            tableViewCamera.getItems().add(newElement);
	            nameInput.clear();
	            emplacementInput.clear();
	            modeleComboBox.getSelectionModel().clearSelection();
	        } else {
	            // Afficher une alerte en cas d'�chec de l'ajout
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Erreur lors de l'ajout");
	            alert.setContentText("Une erreur est survenue lors de l'ajout de l'�l�ment. Veuillez r�essayer.");
	            alert.showAndWait();
	        }
	        /*/
	    }
	}
	

}
