package securite;

public class Action {
	
	private int id;
	private String condition;
	private int valeur;
	private ElementDeSecurite capteur;
	
	public Action(int id, String condition, int valeur, ElementDeSecurite capteur) {
		super();
		this.id = id;
		this.condition = condition;
		this.valeur = valeur;
		this.capteur = capteur;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public ElementDeSecurite getCapteur() {
		return capteur;
	}
	public void setCapteur(ElementDeSecurite capteur) {
		this.capteur = capteur;
	}
	@Override
	public String toString() {
		return "Alertes [id=" + id + ", condition=" + condition + ", description=" + valeur + ", capteur=" + capteur.getNom() + "]";
	}


	
}