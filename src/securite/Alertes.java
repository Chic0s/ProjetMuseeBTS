package securite;

public class Alertes {
	
	private int id;
	private String date;
	private String position;
	private String emplacement;
	private String etat;
	
	public Alertes(int id, String horodatage, String localisation, String emplacement, String io) {
		super();
		this.id = id;
		this.date = horodatage;
		this.position = localisation;
		this.emplacement = emplacement;
		this.etat = io;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	@Override
	public String toString() {
		return "Alertes [id=" + id + ", date=" + date + ", position=" + position + ", emplacement=" + emplacement
				+ ", etat=" + etat + "]";
	}


	
}