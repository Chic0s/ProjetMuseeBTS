package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import securite.ElementDeSecurite;


public class ModifierCapteurController {
	
	@FXML 
	private Label idCapteur;

	@FXML 
	private TextField nomCapteur;
	
	@FXML
	private ComboBox<String> modeleCapteur;
	
	@FXML
	private ComboBox<String> emplacementCapteur;
	
	@FXML
	private ComboBox<String> serveurCapteur;
		
	@FXML
	private CheckBox etatCapteur;
	
	@FXML
	private Button enregistrerCapteur;
	
	
	@FXML
	public void initialize() {
		ComboBoxEdit.getInstance().modeleCapteurComboBox(modeleCapteur);
		ComboBoxEdit.getInstance().populateServeurComboBox(serveurCapteur);
		ComboBoxEdit.getInstance().populateSalleComboBox(emplacementCapteur);
	}
	
	public void openWindow(ElementDeSecurite selectedItem) {
        
	        //Utilise les informations des Guetters et Setters pour les objets de type ElementDeSecurite
	        //Pour pré-remplir les champs
		
		
	    	String id = Integer.toString(selectedItem.getId());
	        String nom = selectedItem.getNom();
	        String modele = selectedItem.getModele();
	        String emplacement = selectedItem.getEmplacement();
	        String serveur = selectedItem.getServeur();
	        Boolean etat = selectedItem.getEtat();
	        
	        idCapteur.setText(id);        
	        nomCapteur.setText(nom);
	        modeleCapteur.setValue(modele);
	        emplacementCapteur.setValue(emplacement);
	        serveurCapteur.setValue(serveur);
	        etatCapteur.setSelected(etat);

	    }

	@FXML
	private void handleUpdateCameraAction(ActionEvent event) {
	    HandleActionController.getInstance().UpdateElementDeSecurite(nomCapteur, emplacementCapteur, modeleCapteur, emplacementCapteur, etatCapteur, nomCapteur, enregistrerCapteur);
	}
	
	

}
