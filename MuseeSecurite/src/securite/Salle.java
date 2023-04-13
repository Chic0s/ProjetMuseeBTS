package securite;

public class Salle {
	private int id;
	private String nom;
	private int humidite;
	private String etage;
	
	public Salle(int id, String nom, int humidite, String etage) {
		super();
		this.id = id;
		this.nom = nom;
		this.humidite = humidite;
		this.etage = etage;
	
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

	public int getHumidite() {
		return humidite;
	}

	public void setHumidite(int humidite) {
		this.humidite = humidite;
	}

	public String getEtage() {
		return etage;
	}

	public void setEtage(String etage) {
		this.etage = etage;
	}

	@Override
	public String toString() {
		return "Salle [id=" + id + ", nom=" + nom + ", humidite=" + humidite + ", etage=" + etage + "]";
	}
	
}
