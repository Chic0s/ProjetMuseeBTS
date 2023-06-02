package controleur;

import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import securite.TypeCamera;


public class AjouterCameraController {
	
	@FXML
	private ListView<TypeCamera> typeCameraListView;
	
	@FXML
	private ComboBox<String> serveurComboBox;
	
	@FXML
	private ComboBox<String> salleComboBox;
	
	@FXML
	private Label fluxLabel;
	
	@FXML
	private Label angleDeVue;
	
	@FXML
	private Label typeDeCamera;
	
	@FXML
	private Label description;
	
	@FXML 
	private Button enregistrerCamera;
	
	@FXML 
	private TextField nomCamera;
	
	@FXML 
	private CheckBox etatCamera;
	
	@FXML
	public void initialize() {
	    ComboBoxEdit.getInstance().populateTypeCameraListView(typeCameraListView);
	    ComboBoxEdit.getInstance().populateServeurComboBox(serveurComboBox);
	    ComboBoxEdit.getInstance().populateSalleComboBox(salleComboBox);
	    
	    
	    //Ecouteur d'évènement 
	    typeCameraListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, NewSelection)-> {
	    	if(NewSelection != null) {
	    		TypeCamera selectedCamera = typeCameraListView.getSelectionModel().getSelectedItem();
	    		fluxLabel.setText(selectedCamera.getFlux());
	    		angleDeVue.setText(selectedCamera.getAngledevue());
	    		typeDeCamera.setText(selectedCamera.getTypedecamera());
	    		description.setText(selectedCamera.getDescription());
	    		
	    	}
	    });
	}

		
	@FXML
	private void handleAddCameraAction(ActionEvent event) {
		HandleActionController.getInstance().handleAddElementDeSecuriteAction(nomCamera, salleComboBox, typeDeCamera, serveurComboBox, etatCamera, enregistrerCamera);
	}
	

}
