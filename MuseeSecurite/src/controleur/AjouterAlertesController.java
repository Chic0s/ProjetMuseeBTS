package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import securite.ElementDeSecurite;


public class AjouterAlertesController {
	
	@FXML
	private ListView<ElementDeSecurite> typeCapteurListView;
	
	@FXML
	private ComboBox<String> conditionComboBox;
				
	@FXML 
	private Button enregistrerAlertes;
	
	@FXML 
	private Label nomCapteur;
	
	@FXML 
	private TextField valeurAlertes;
	
	@FXML 
	private CheckBox etatAlertes;
	
	@FXML
	public void initialize() {
	    ComboBoxEdit.getInstance().ElementDeSecuriteListView(typeCapteurListView);
	    ComboBoxEdit.getInstance().ConditionAlertes(conditionComboBox);
	    
	    typeCapteurListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            String capteurNom = newValue.getNom();
	            nomCapteur.setText(capteurNom);
	        }
	    });
	}
	@FXML
	private void handleAddAlertesAction(ActionEvent event) {
		HandleActionController.getInstance().handleAddAlertes(nomCapteur, conditionComboBox, valeurAlertes, etatAlertes,enregistrerAlertes);
	}
	

}
