package ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecuriteGUI extends Application {

	    @Override
	    public void start(Stage primaryStage) throws Exception{
	        // Chargement de l'interface graphique à partir du fichier FXML
	        Parent root = FXMLLoader.load(getClass().getResource("plan.fxml"));

	        // Création d'une scène à partir de l'interface graphique
	        Scene scene = new Scene(root, 600, 400);

	        // Ajout de la scène à la fenêtre et affichage de la fenêtre
	        primaryStage.setTitle("Securité du Musée");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	    
	    public SecuriteGUI() {
	    	super();
	    }


	    public static void main(String[] args) {
	        launch(args);
	    }
	}