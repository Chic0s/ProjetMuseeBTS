package securite;

public class TypeCamera {
	
	private int id;
	private String flux;
	private String angledevue;
	private String typedecamera;
	private String description;
	
	public TypeCamera(int id, String Flux, String AngleDeVue, String TypeDeCamera, String Description) {
		super();
		this.id = id;
		this.flux = Flux;
		this.angledevue = AngleDeVue;
		this.typedecamera = TypeDeCamera;
		this.description = Description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlux() {
		return flux;
	}

	public void setFlux(String flux) {
		this.flux = flux;
	}

	public String getAngledevue() {
		return angledevue;
	}

	public void setAngledevue(String angledevue) {
		this.angledevue = angledevue;
	}

	public String getTypedecamera() {
		return typedecamera;
	}

	public void setTypedecamera(String typedecamera) {
		this.typedecamera = typedecamera;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return typedecamera;
	}
	

	
}