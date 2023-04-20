package controleur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import securite.ElementDeSecurite;

public class ElementDeSecuriteFX extends ElementDeSecurite {
    private final IntegerProperty id;
    private final StringProperty nom;
    private final StringProperty modele;
    private final StringProperty emplacement;

    public ElementDeSecuriteFX(int id, String nom, String modele, String emplacement) {
        super(id, nom, modele, emplacement);
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.modele = new SimpleStringProperty(modele);
        this.emplacement = new SimpleStringProperty(emplacement);
    }

}
