package securite;

import java.sql.Date;

public class Alertes {
	
	private int id;
	private Date date;
	private String position;
	private String emplacement;
	private boolean etat;
	private Action action;
	private String nom;
	private String description;
	
	public Alertes(int id, Date horodatage, String localisation, String emplacement, boolean io, Action action, String nom, String description) {
		super();
		this.id = id;
		this.date = horodatage;
		this.position = localisation;
		this.emplacement = emplacement;
		this.etat = io;
		this.action = action;
		this.nom = nom;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public boolean getEtat() {
		return etat;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Action getAction() {
		return action;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Alertes [id=" + id + ", nom=" + nom + ", description=" + description + ", date=" + date + ", position=" + position + ", emplacement=" + emplacement
				+ ", etat=" + etat + ", action=" + action.getCapteur().getNom() + action.getCondition() + action.getValeur() + "]";
	}


	
}