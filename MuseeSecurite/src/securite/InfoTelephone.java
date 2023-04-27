package securite;

public class InfoTelephone {
	private int id;
	private String nom;
	private String numerotelephone;
	
	public InfoTelephone(int id, String nom, String numero) {
		super();
		this.id = id;
		this.nom = nom;
		this.numerotelephone = numero;
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

	public String getNumerotelephone() {
		return numerotelephone;
	}

	public void setNumerotelephone(String numerotelephone) {
		this.numerotelephone = numerotelephone;
	}

	@Override
	public String toString() {
		return "InfoTelephone [id=" + id + ", nom=" + nom + ", numerotelephone=" + numerotelephone + "]";
	}

}