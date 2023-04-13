package securite;

public class Serveur {
	public Serveur(int id, String nom, int capacite, String usage) {
		super();
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;
		this.Usage = usage;
	}
	
	private int id;
	private String nom;
	private int capacite;
	private String Usage;
	
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
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getUsage() {
		return Usage;
	}
	public void setUsage(String usage) {
		Usage = usage;
	}
	@Override
	public String toString() {
		return "Serveur [id=" + id + ", nom=" + nom + ", capacite=" + capacite + ", Usage=" + Usage + "]";
	}
	
}
