package securite;

public class TypeCapteur {
	
	private int id;
	private String type;
	private String formatdesortie;
	private String description;
	
	public TypeCapteur(int id, String Type, String FormatDeSortie, String Description) {
		super();
		this.id = id;
		this.type = Type;
		this.formatdesortie = FormatDeSortie;
		this.description = Description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormatdesortie() {
		return formatdesortie;
	}

	public void setFormatdesortie(String formatdesortie) {
		this.formatdesortie = formatdesortie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return type;
	}

}
	

	