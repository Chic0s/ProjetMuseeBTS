package controleur;

import java.util.List;

import dao.ElementDeSecuriteDAO;
import dao.SalleDAO;
import dao.ServeurDAO;
import dao.TypeCameraDAO;
import dao.TypeCapteurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import securite.ElementDeSecurite;
import securite.Salle;
import securite.Serveur;
import securite.TypeCamera;
import securite.TypeCapteur;

public class ComboBoxEdit {
	
	private static ComboBoxEdit instance = null;
	
    public static ComboBoxEdit getInstance() {
        if (instance == null) {
            instance = new ComboBoxEdit();
        }
        return instance;
    }
    
    private ComboBoxEdit() {
    	super();
    }
	
	// Liste des Noms de salles dans un Menu Déroulant
	public void populateSalleComboBox(ComboBox<String> box) {
	    List<Salle> sallelist = SalleDAO.getInstance().readAll();
	    ObservableList<String> salleNoms = FXCollections.observableArrayList();
	    for (Salle salle : sallelist) {
	        salleNoms.add(salle.getNom());
	    }
	    box.setItems(salleNoms);
	}

	//Liste des Objets caméra dans un Menu Déroulant
	public void populateTypeCameraListView(ListView<TypeCamera> box) {
	    List<TypeCamera> typeCameraList = TypeCameraDAO.getInstance().readAll();
	    ObservableList<TypeCamera> cameraType = FXCollections.observableArrayList();
	    for (TypeCamera camera : typeCameraList) {
	    	cameraType.add(camera);
	    }
	    box.setItems(cameraType);
	}
	
	//Liste des noms serveur dans un Menu Déroulant
	public void populateServeurComboBox(ComboBox<String> box) {
	    List<Serveur> serveurList = ServeurDAO.getInstance().readAll();
	    ObservableList<String> serveurNoms = FXCollections.observableArrayList();
	    for (Serveur serveur : serveurList) {
	        serveurNoms.add(serveur.getNom());
	    }
	    box.setItems(serveurNoms);
	}
	
	//Liste des Objets capteurs dans un Menu Déroulant
	 void populateTypeCapteurListView(ListView<TypeCapteur> box) {
	    List<TypeCapteur> typeCapteurList = TypeCapteurDAO.getInstance().readAll();
	    ObservableList<TypeCapteur> capteurType = FXCollections.observableArrayList();
	    for (TypeCapteur capteur : typeCapteurList) {
	    	capteurType.add(capteur);
	    }
	    box.setItems(capteurType);
	}
	//Liste des types camera dans un Menu Déroulant
	 public void modeleCameraComboBox(ComboBox<String> box) {
	    List<TypeCamera> cameralist = TypeCameraDAO.getInstance().readAll();
	    ObservableList<String> cameraModele = FXCollections.observableArrayList();
	    for (TypeCamera camera : cameralist) {
	    	cameraModele.add(camera.getTypedecamera());
	    }
	    box.setItems(cameraModele);
	}
	 
	 public void modeleCapteurComboBox(ComboBox<String> box) {
		    List<TypeCapteur> capteurlist = TypeCapteurDAO.getInstance().readAll();
		    ObservableList<String> capteurModele = FXCollections.observableArrayList();
		    for (TypeCapteur capteur : capteurlist) {
		    	capteurModele.add(capteur.getType());
		    }
		    box.setItems(capteurModele);
		}
	 public void ElementDeSecuriteComboBox(ComboBox<ElementDeSecurite> box) {
		    List<ElementDeSecurite> elemlist = ElementDeSecuriteDAO.getInstance().readAll();
		    ObservableList<ElementDeSecurite> elemNom = FXCollections.observableArrayList();
		    for (ElementDeSecurite elem : elemlist) {
		    	elemNom.add(elem);
		    }
		    box.setItems(elemNom);
		}
			
}