package securite;

public class Capteur {
	
	private int id;
	private String nom;
	private String modele;
	private String emplacement;
	
	public Capteur(int id, String nom, String modele, String emplacement) {
		super();
		this.id = id;
		this.nom = nom;
		this.modele = modele;
		this.emplacement = emplacement;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getModele() {
		return modele;
	}
	
	public void setModele(String modele) {
		this.modele = modele;
	}
	
	public String getEmplacement() {
		return emplacement;
	}
	
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	@Override
	public String toString() {
		return "Capteur [id=" + id + ", nom=" + nom + ", modele=" + modele + ", emplacement=" + emplacement + "]";
	}
}
