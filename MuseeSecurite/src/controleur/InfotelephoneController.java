package controleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.control.Label;


public class InfotelephoneController {

	private static InfotelephoneController instance = null;

    public static InfotelephoneController getInstance() {
        if (instance == null) {
            instance = new InfotelephoneController();
        }
        return instance;
    }
	
	// Récupération du chemin d'accès du Fichier puis récupération de la balise démarche pour obtenir le texte
	public void updateTextFromFile(String filePath, Label textField) {
	    File file = new File(filePath);
	    if (file.exists() && file.isFile()) {
	        try {
	            FileInputStream fis = new FileInputStream(file);
	            byte[] data = new byte[(int) file.length()];
	            fis.read(data);
	            fis.close();

	            String jsonString = new String(data, StandardCharsets.UTF_8);
	            JSONObject jsonObject = new JSONObject(jsonString);

	            if (jsonObject.has("demarche")) {
	                String demarcheText = jsonObject.getString("demarche");
	                System.out.println(demarcheText);
	                textField.setText(demarcheText);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    }
	}
}



