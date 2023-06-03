package controleur;


public enum ListIhm {
	IHMTELEPHONEAJOUTER("/ihm/AjouterTelephone.fxml"),
	IHMTELEPHONEMODIFIER("/ihm/ModifierTelephone.fxml"),
	IHMCAMERAAJOUTER("/ihm/AjouterCamera.fxml"),
	IHMCAMERAMODIFIER("/ihm/ModifierCamera.fxml"),
	IHMALERTEAJOUTER("/ihm/CreationModificationAlerte.fxml"),
	IHMALERTEMODIFIER("/ihm/CreationModificationAlerte.fxml"),
	IHMCAPTEURAJOUTER("/ihm/AjouterCapteur.fxml"),
	IHMCAPTEURMODIFIER("/ihm/ModifierCapteur.fxml");
	
	
	private String url;


	private ListIhm(String url) {
	    this.url = url;
	}

	public String getUrl() {
		return url;
	}
}