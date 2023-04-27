package controleur;

import dao.ElementDeSecuriteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import securite.ElementDeSecurite;


public class ModifierCameraController {
	


	@FXML 
	private TextField nomCamera;
	
	@FXML
	private ComboBox<String> modeleCamera;
	
	@FXML
	private ComboBox<String> emplacementCamera;
	
	@FXML
	private ComboBox<String> serveurCamera;
		
	@FXML
	private CheckBox etatCamera;
	
	@FXML
	private Button enregistrerCamera;
	
	
	@FXML
	public void initialize() {
		ComboBoxEdit.getInstance().modeleCameraComboBox(modeleCamera);
		ComboBoxEdit.getInstance().populateServeurComboBox(serveurCamera);
		ComboBoxEdit.getInstance().populateSalleComboBox(emplacementCamera);
	}
	
	public void openWindow(ElementDeSecurite selectedItem) {
        
	        //Utilise les informations des Guetters et Setters pour les objets de type ElementDeSecurite
	        //Pour pré-remplir les champs
		
		
	    	int id = selectedItem.getId();
	        String nom = selectedItem.getNom();
	        String modele = selectedItem.getModele();
	        String emplacement = selectedItem.getEmplacement();
	        String serveur = selectedItem.getServeur();
	        Boolean etat = selectedItem.getEtat();
	        	        
	        nomCamera.setText(nom);
	        modeleCamera.setValue(modele);
	        emplacementCamera.setValue(emplacement);
	        serveurCamera.setValue(serveur);
	        etatCamera.setSelected(etat);
	    }

	@FXML
	private void handleUpdateCameraAction(ActionEvent event) {
	    String nom = nomCamera.getText();
	    String emplacement = emplacementCamera.getValue();
	    String modele = modeleCamera.getValue();
	    String serveur = serveurCamera.getValue();
	    Boolean etat = etatCamera.isSelected();
	    
	    if (nom.isEmpty() || emplacement == null || modele == null || serveur == null || etat == null) {
	        // Afficher une alerte si les champs ne sont pas remplis correctement
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Informations manquantes");
	        alert.setHeaderText("Tous les champs doivent être remplis");
	        alert.setContentText("Veuillez renseigner le nom, l'emplacement, le modele et l'état de la camera.");
	        alert.showAndWait();
	    } else {
	    	
	    	

	        ElementDeSecuriteDAO.getInstance().update(newElement);
	        Stage stage = (Stage) enregistrerCamera.getScene().getWindow();
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
