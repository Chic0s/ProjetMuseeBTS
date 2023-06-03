package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import securite.ElementDeSecurite;


public class ModifierCameraController {
	
	@FXML 
	private Label idCamera;

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
		
	    	String id = Integer.toString(selectedItem.getId());
	        String nom = selectedItem.getNom();
	        String modele = selectedItem.getModele();
	        String emplacement = selectedItem.getEmplacement();
	        String serveur = selectedItem.getServeur();
	        Boolean etat = selectedItem.getEtat();
	        
	        idCamera.setText(id);        
	        nomCamera.setText(nom);
	        modeleCamera.setValue(modele);
	        emplacementCamera.setValue(emplacement);
	        serveurCamera.setValue(serveur);
	        etatCamera.setSelected(etat);

	    }

	@FXML
	private void handleUpdateCameraAction(ActionEvent event) {
		HandleActionController.getInstance().UpdateElementDeSecurite(nomCamera, emplacementCamera, modeleCamera, serveurCamera, etatCamera,idCamera, enregistrerCamera);	
	}
	
}
