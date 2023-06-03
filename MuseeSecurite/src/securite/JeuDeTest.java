package securite;

public class JeuDeTest {
	
	private int id;
	private String nom;
	private String valeur;
	
	public JeuDeTest(int id, String Nom, String Valeur) {
		super();
		this.id = id;
		this.nom = Nom;
		this.valeur = Valeur;
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

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	@Override
	public String toString() {
		return "JeuDeTest [id=" + id + ", nom=" + nom + ", valeur=" + valeur + "]";
	}

	

	
}