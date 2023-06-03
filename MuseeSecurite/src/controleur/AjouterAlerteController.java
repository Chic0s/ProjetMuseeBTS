package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import securite.Action;
import securite.ElementDeSecurite;
import securite.TypeCapteur;


public class AjouterAlerteController {
	
	@FXML 
	private TextField nomAlerte;

	@FXML 
	private TextArea descriptionAlerte;
	
	@FXML
	private ComboBox<ElementDeSecurite> comboBoxCapteur;
	
	@FXML
	private Button buttonConfigurerCapteur;
		
	@FXML
	private ComboBox<Action> comboBoxAction;
	
	@FXML
	private Button enregistrerAlerte;
	
	@FXML
	private Label labelAlerteId;
	
	
	@FXML
	public void initialize() {
		ComboBoxEdit.getInstance().ElementDeSecuriteCapteurAlertesComboBox(comboBoxCapteur);
		ComboBoxEdit.getInstance().ElementDeSecuriteActionComboBox(comboBoxAction);
	}

	
	@FXML
	private void handleAddCameraAction(ActionEvent event) {
	  HandleActionController.getInstance().handleAddElementDeSecuriteAction(nomCapteur, salleComboBox, nomCapteur, serveurComboBox, etatCapteur, enregistrerCapteur);
	}
	

}
