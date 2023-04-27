package controleur;

import dao.InfoTelephoneDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import securite.InfoTelephone;



public class AjouterTelephoneController {
	
	@FXML
	private TextField nomTelephone;
	
	@FXML
	private TextField numeroTelephone;
	
	@FXML
	private Button enregistrerTelephone;
	
	@FXML
	public void initialize() {
	}
	
	@FXML
	private void handleAddTelephoneAction(ActionEvent event) {
	    String nom = nomTelephone.getText();
	    String numero = numeroTelephone.getText();
	    
	    if (nom.isEmpty() || numero.isEmpty()) {
	        // Afficher une alerte si les champs ne sont pas remplis correctement
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Informations manquantes");
	        alert.setHeaderText("Tous les champs doivent �tre remplis");
	        alert.setContentText("Veuillez renseigner le nom, le num�ro de t�l�phone.");
	        alert.showAndWait();
	    } else {
	        InfoTelephone newElement = new InfoTelephone(0, nom, numero);
	        InfoTelephoneDAO.getInstance().create(newElement);
	        Stage stage = (Stage) enregistrerTelephone.getScene().getWindow();
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
