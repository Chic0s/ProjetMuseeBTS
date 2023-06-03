package securite;

public class Alertes {
	
	private int id;
	private String nom;
	private String condition;
	private String valeur;
	private Boolean etat;
	
	public Alertes(int id, String Nom, String Condition, String Valeur, Boolean io) {
		super();
		this.id = id;
		this.nom = Nom;
		this.condition = Condition;
		this.valeur = Valeur;
		this.etat = io;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public Boolean getEtat() {
		return etat;
	}

	public void setEtat(Boolean etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Alertes [id=" + id + ", nom=" + nom + ", condition=" + condition + ", valeur=" + valeur + ", etat="
				+ etat + "]";
	}
	

	
}