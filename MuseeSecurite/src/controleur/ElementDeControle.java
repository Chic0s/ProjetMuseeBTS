package controleur;

public enum ElementDeControle {
    CAPTEUR("Capteur"),
    CAMERA("Camera"),
    CAMERAALERTE("CameraAlerte"),
    ALERTE("Alerte"),
    TELEPHONE("Telephone");
	
	private String type;

	private ElementDeControle(String type) {
	    this.type = type;
	}

	public String getType() {
	    return type;
	}
}