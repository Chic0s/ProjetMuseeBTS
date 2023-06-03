package controleur;

public enum ElementDeControle {
    CAPTEUR("Capteur"),
    CAMERA("Camera"),
    TELEPHONE("Telephone"),
    ALERTES("Alertes");
	
	private String type;

	private ElementDeControle(String type) {
	    this.type = type;
	}

	public String getType() {
	    return type;
	}
}