package controleur;

import java.util.List;

import dao.AlertesDAO;
import dao.JeuDeTestDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import securite.Alertes;
import securite.JeuDeTest;

public class AlertesController {

	private static AlertesController instance = null;

    public static AlertesController getInstance() {
        if (instance == null) {
            instance = new AlertesController();
        }
        return instance;
    }
       
    public void VerificationAlertes() {
    	List<Alertes> alertesList = AlertesDAO.getInstance().readAll();
        List<JeuDeTest> jeuDeTestList = JeuDeTestDAO.getInstance().readAll();

        for (Alertes alerte : alertesList) {
            String nomAlerte = alerte.getNom();
            String condition = alerte.getCondition();
            String valeurAlerte = alerte.getValeur();
            Boolean etatAlerte = alerte.getEtat();

            for (JeuDeTest jeuDeTest : jeuDeTestList) {
                String nomJeuDeTest = jeuDeTest.getNom();
                String valeurJeuDeTest = jeuDeTest.getValeur();

                if (nomAlerte.equals(nomJeuDeTest) && etatAlerte==true) {
                    boolean verification = comparerValeurs(condition, valeurAlerte, valeurJeuDeTest);
                    if (verification) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Alerte");
                        alert.setHeaderText("Valeur trop haute ou trop basse");
                        alert.setContentText("Le capteur :  " + nomAlerte + " indique que la valeur est hors limite.");
                        alert.showAndWait();
                        alerte.setEtat(false);
                    }
                    break;
                }
            }
        }

    }

    private boolean comparerValeurs(String condition, String valeurAlerte, String valeurJeuDeTest) {
        int valAlerte = Integer.parseInt(valeurAlerte);
        int valJeuDeTest = Integer.parseInt(valeurJeuDeTest);

        switch (condition) {
            case "=":
                return valAlerte == valJeuDeTest;
            case "!=":
                return valAlerte != valJeuDeTest;
            case ">":
                return valAlerte > valJeuDeTest;
            case "<":
                return valAlerte < valJeuDeTest;
            default:
                return false; 
        }
    }
}
