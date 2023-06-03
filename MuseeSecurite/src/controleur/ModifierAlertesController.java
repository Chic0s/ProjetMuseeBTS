package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import securite.Alertes;


public class ModifierAlertesController {
	
	@FXML 
	private Label idAlertes;

	@FXML 
	private Label nomCapteur;
		
	@FXML
	private ComboBox<String> alertesCondition;
	
	@FXML
	private CheckBox alertesEtat;
	
	@FXML
	private TextField alertesValeur;
	
	@FXML
	private Button enregistrerAlertes;
	
	
	@FXML
	public void initialize() {
	    ComboBoxEdit.getInstance().ConditionAlertes(alertesCondition);
	}
	
	public void openWindow(Alertes selectedItem) {
        
		
	    	String id = Integer.toString(selectedItem.getId());
	        String nom = selectedItem.getNom();
	        String condition = selectedItem.getCondition();
	        String valeur = selectedItem.getValeur();
	        Boolean etat = selectedItem.getEtat();
	        
	        idAlertes.setText(id);        
	        nomCapteur.setText(nom);
	        alertesCondition.setValue(condition);
	        alertesValeur.setText(valeur);
	        alertesEtat.setSelected(etat);

	    }

	@FXML
	private void handleUpdateAlertesAction(ActionEvent event) {
	    HandleActionController.getInstance().UpdateAlertes(nomCapteur, alertesCondition, alertesValeur, alertesEtat, idAlertes, enregistrerAlertes);
	}
	
	
	
	

}
