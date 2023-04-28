package controleur;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



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
		HandleActionController.getInstance().AddTelephoneAction(nomTelephone, numeroTelephone, enregistrerTelephone);
	}
	

}
