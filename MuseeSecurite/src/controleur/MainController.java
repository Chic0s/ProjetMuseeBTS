package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import dao.Connexion;
import dao.ElementDeSecuriteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import securite.ElementDeSecurite;


public class MainController {
    @FXML
    private TableView<ElementDeSecuriteFX> tableView;

    @FXML
    private TableColumn<ElementDeSecuriteFX, Integer> idColumn;

    @FXML
    private TableColumn<ElementDeSecuriteFX, String> nomColumn;

    @FXML
    private TableColumn<ElementDeSecuriteFX, String> modeleColumn;

    @FXML
    private TableColumn<ElementDeSecuriteFX, String> emplacementColumn;
    
    @FXML
    private Button removeEtage;

    @FXML
    private TabPane etage;
    
    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;
    
    @FXML
    private Button addRedCircleButton;
    
    @FXML
    private Button addCaptorButton;

    private int tabCounter = 0;

    @FXML
    private TextField circleNameInput;

    
    @FXML
    public void initialize() {
    	Connexion.getInstance();
    	fillTableView();
        
        // Enleve un nouvel onglet lors du clic sur le bouton
        removeEtage.setOnAction(e -> {
            Tab selectedTab = etage.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                etage.getTabs().remove(selectedTab);
                tabCounter = tabCounter-1;
            }
        });
        etage.setOnDragOver(event -> handleDragOver(event));
        etage.setOnDragDropped(event -> handleDragDropped(event));
        addRedCircleButton.setOnAction(e -> {(new PlanItems()).addItems(circleNameInput,etage,Color.RED);});
        addCaptorButton.setOnAction(e -> {(new PlanItems()).addItems(circleNameInput,etage,Color.GREEN);});

        
    }
    
    private void fillTableView() {
        // Récupérez les données à l'aide de ElementDeSecuriteDAO
        ElementDeSecuriteDAO dao = ElementDeSecuriteDAO.getInstance();
        ObservableList<ElementDeSecuriteFX> data = FXCollections.observableArrayList();

        for (int id : dao.getKeys()) {
            ElementDeSecurite elem = dao.read(id);
            data.add(new ElementDeSecuriteFX(elem.getId(), elem.getNom(), elem.getModele(), elem.getEmplacement()));
        }

        // Configurez les colonnes pour utiliser les propriétés JavaFX
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));  
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modeleColumn.setCellValueFactory(new PropertyValueFactory<>("modele"));
        emplacementColumn.setCellValueFactory(new PropertyValueFactory<>("emplacement"));

        // Ajoutez les données au TableView
        tableView.setItems(data);
    }

      
	private void addTabWithImage(String imagePath) {
        // Création d'une nouvelle image
        Image image;
        if (imagePath != null) {
            image = new Image("file:" + imagePath);
        } else {
            image = new Image("example_image.png");// A Modifier 
        }
        ImageView imageView = new ImageView(image);
        
        // Définition de la taille par défaut de l'image
        double defaultWidth = 300;
        double defaultHeight = 200;
        imageView.setFitWidth(defaultWidth);
        imageView.setFitHeight(defaultHeight);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        // Création d'un AnchorPane pour contenir l'image
        AnchorPane anchorPane = new AnchorPane(imageView);

        // Création d'un nouvel onglet et ajout de l'AnchorPane contenant l'image
        Tab newTab = new Tab("Etage " + tabCounter++, anchorPane);
        newTab.setUserData(imagePath); // Stocke le chemin de l'image en tant que donnée utilisateur
        etage.getTabs().add(newTab);
    }

    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != etage && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            try {
                String imagePath = db.getFiles().get(0).getAbsolutePath();
                addTabWithImage(imagePath);
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
}


