package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import securite.Action;
import securite.Alertes;
import securite.ElementDeSecurite;


public class ModifierAlerteController {
	
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
	
	public void openWindow(Alertes selectedItem) {
        
	        //Utilise les informations des Guetters et Setters pour les objets de type Alertes
	        //Pour pr�-remplir les champs
		
	        String nom = selectedItem.getNom();
	        String description = selectedItem.getDescription();
	        ElementDeSecurite capteur = selectedItem.getAction().getCapteur();
	        Action action = selectedItem.getAction();
	        int id = selectedItem.getId();
	        
	        nomAlerte.setText(nom);        
	        descriptionAlerte.setText(description);
	        comboBoxCapteur.setValue(capteur);
	        comboBoxAction.setValue(action);
	        labelAlerteId.setText(String.valueOf(id));
	    }

	@FXML
	private void handleUpdateAlerte(ActionEvent event) {
		HandleActionController.getInstance().UpdateAlerte(labelAlerteId, nomAlerte, descriptionAlerte, comboBoxCapteur, comboBoxAction, enregistrerAlerte);
	}
	
}
