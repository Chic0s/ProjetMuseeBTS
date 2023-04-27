package controleur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import securite.ElementDeSecurite;

public class ModifierCameraController {
	


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
		
	}
	
	public void openWindow(ElementDeSecurite selectedItem) {
        
	        //Utilise les informations des Guetters et Setters pour les objets de type ElementDeSecurite
	        //Pour pré-remplir les champs
	    	
	        String nom = selectedItem.getNom();
	        String modele = selectedItem.getModele();
	        String emplacement = selectedItem.getEmplacement();
	        String serveur = selectedItem.getServeur();
	        Boolean etat = selectedItem.getEtat();
	        	        
	        nomCamera.setText(nom);
	        modeleCamera.setValue(modele);
	        emplacementCamera.setValue(emplacement);
	        serveurCamera.setValue(serveur);
	        etatCamera.setSelected(etat);
	       
	    }

}
