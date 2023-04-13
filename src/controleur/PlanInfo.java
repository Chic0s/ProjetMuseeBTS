package controleur;
import java.io.Serializable;

// Cette classe permets de sérialiser les informations.
public class PlanInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String imagePath;
    private String tabTitle;

    public PlanInfo(String imagePath, String tabTitle) {
        this.imagePath = imagePath;
        this.tabTitle = tabTitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTabTitle() {
        return tabTitle;
    }
}
