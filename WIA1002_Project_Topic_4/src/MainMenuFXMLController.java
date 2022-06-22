
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nafees
 */
public class MainMenuFXMLController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Song song;

    public MainMenuFXMLController() {
        this.song = new Song();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        song.playSong();
    } 
    
    public void changeToAot_Characters(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CharactersFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResourceAsStream("AOT_Titan_Generator.jpg"));
        stage.getIcons().add(icon);
        stage.setTitle("Characters");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void changeToAot_Titans(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("TitanFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResourceAsStream("AOT_Titan_Generator.jpg"));
        stage.getIcons().add(icon);
        stage.setTitle("Titan Generator");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void changeToAot_Scouting(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("HamiltonianCycleFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Scouting Mission");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void changeToAot_BestPath(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("BestPathFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResourceAsStream("AOT_Title_Map.jpg"));
        stage.getIcons().add(icon);
        stage.setTitle("Best Path Calculator");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void changeToAot_Cipher(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("CipherFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResourceAsStream("Icon1.jpg"));
        stage.getIcons().add(icon);
        stage.setTitle("Cipher Decryptor");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void changeToAot_WallOfMaria(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("WallOfMariaFXML.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Wall Of Maria");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void play(ActionEvent event){
        song.playSong();
    }
    public void stop(ActionEvent event){
        song.stopSong();
    }
    
}
