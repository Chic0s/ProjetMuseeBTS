package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	    ComboBoxEdit.getInstance().populateTypeCapteurListView(typeCapteurListView);
	    ComboBoxEdit.getInstance().populateServeurComboBox(serveurComboBox);
	    ComboBoxEdit.getInstance().populateSalleComboBox(salleComboBox);
	    
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

	
	@FXML
	private void handleAddCameraAction(ActionEvent event) {
	  HandleActionController.getInstance().handleAddElementDeSecuriteAction(nomCapteur, salleComboBox, typeCapteur, serveurComboBox, etatCapteur, enregistrerCapteur);
	}
	

}
