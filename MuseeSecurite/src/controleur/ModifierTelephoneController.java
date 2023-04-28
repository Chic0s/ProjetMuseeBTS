package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import securite.InfoTelephone;


public class ModifierTelephoneController {
	
	@FXML 
	private Label idTelephone;

	@FXML 
	private TextField nomTelephone;
	
	@FXML 
	private TextField numeroTelephone;
	
	@FXML
	private Button enregistrerTelephone;
	
	
	@FXML
	public void initialize() {
	}
	
	public void openWindow(InfoTelephone selectedItem) {
        
	        //Utilise les informations des Guetters et Setters pour les objets de type ElementDeSecurite
	        //Pour pré-remplir les champs
		
		
	    	String id = Integer.toString(selectedItem.getId());
	        String nom = selectedItem.getNom();
	        String numero = selectedItem.getNumerotelephone();
	        
	        idTelephone.setText(id);        
	        nomTelephone.setText(nom);
	        numeroTelephone.setText(numero);

	    }

	@FXML
	private void handleUpdateTelephoneAction(ActionEvent event) {
		HandleActionController.getInstance().UpdateTelephoneInfo(nomTelephone, numeroTelephone, idTelephone, enregistrerTelephone);
	}
	
	

}
