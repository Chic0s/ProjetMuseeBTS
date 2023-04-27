package securite;

public class ElementDeSecurite {
	private int id;
	private String nom;
	private String modele;
	private String emplacement;
	private String serveur;
	private Boolean etat;
	
	public ElementDeSecurite(int id, String nom, String modele, String emplacement, String serveur, Boolean etat) {
		super();
		this.id = id;
		this.nom = nom;
		this.modele = modele;
		this.emplacement = emplacement;
		this.serveur = serveur;
		this.etat = etat;
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
		return "ElementDeSecurite [id=" + id + ", nom=" + nom + ", modele=" + modele + ", emplacement=" + emplacement
				+ ", serveur=" + serveur + ", etat=" + etat + "]";
	}


	public String getServeur() {
		return serveur;
	}


	public void setServeur(String serveur) {
		this.serveur = serveur;
	}


	public Boolean getEtat() {
		return etat;
	}


	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
}