import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 * @author Nafees
 */
public class MainMenu extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
             //Edit the Attack_ON_Titan.wav file path below to run your program.
            Parent root = FXMLLoader.load(getClass().getResource("MainMenuFXML.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Main Menu");
            Image icon = new Image(getClass().getResourceAsStream("AOT MainMenu.jpg"));
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
