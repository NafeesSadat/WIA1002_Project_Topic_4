
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nafees
 */
public class CipherFXMLController implements Initializable {

    @FXML
    private TextField marleyText;
    @FXML
    private TextArea paradisText;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final MyHashMap map;

    public CipherFXMLController() {
        this.map = new MyHashMap();
    }
    
    public void decryptText(ActionEvent event){
        paradisText.clear();
        String text = marleyText.getText();
        if(!text.isEmpty()){
            try{
                JOptionPane.showMessageDialog(null, "Cipher Decoded!");
                for (int i = 0; i < text.length(); i++) { 
                    if(text.charAt(i) == '^'){
                        paradisText.appendText(map.get(new Key(text.charAt(i+1))).getValue().toString().toUpperCase());
                        i++;
                        continue;
                    }
                    if(text.charAt(i) == '('){
                        paradisText.appendText(" \"");
                        continue;
                    }
                    if(text.charAt(i) == ')'){
                        paradisText.appendText("\" ");
                        continue;
                    }
                    paradisText.appendText(map.get(new Key(text.charAt(i))).getValue().toString());//
                }
            }catch(RuntimeException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Kindly enter the correct Encrypted message!!!");
                if(alert.showAndWait().get() == ButtonType.OK){
                    paradisText.clear();
                    marleyText.clear();
                    marleyText.requestFocus();
                }

            }
        
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Kindly enter the Encrypted message!!!");
            if(alert.showAndWait().get() == ButtonType.OK || alert.showAndWait().get() == ButtonType.FINISH){
                paradisText.clear();
                marleyText.clear();
                marleyText.requestFocus();
            }
        }
    }
    
    public void clearAreas(){
        paradisText.clear();
        marleyText.clear();
        marleyText.requestFocus();
    }
   
    public void returnToMenu(){
        
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Puting values into the custom hash map everytime the program starts
        map.put(new Key('a'), new Value('j'));
        map.put(new Key('b'), new Value('c'));
        map.put(new Key('c'), new Value('t'));
        map.put(new Key('d'), new Value('a'));
        map.put(new Key('e'), new Value('k'));
        map.put(new Key('f'), new Value('z'));
        map.put(new Key('g'), new Value('s'));
        map.put(new Key('h'), new Value('i'));
        map.put(new Key('i'), new Value('w'));
        map.put(new Key('j'), new Value('x'));
        map.put(new Key('k'), new Value('o'));
        map.put(new Key('l'), new Value('n'));
        map.put(new Key('m'), new Value('g'));
        map.put(new Key('n'), new Value('b'));
        map.put(new Key('o'), new Value('f'));
        map.put(new Key('p'), new Value('h'));
        map.put(new Key('q'), new Value('l'));
        map.put(new Key('r'), new Value('d'));
        map.put(new Key('s'), new Value('e'));
        map.put(new Key('t'), new Value('y'));
        map.put(new Key('u'), new Value('m'));
        map.put(new Key('v'), new Value('v'));
        map.put(new Key('w'), new Value('u'));
        map.put(new Key('x'), new Value('p'));
        map.put(new Key('y'), new Value('q'));
        map.put(new Key('z'), new Value('r'));
        map.put(new Key('$'), new Value(' '));
        map.put(new Key(','), new Value(','));
        
        marleyText.requestFocus();
    }

     public void changeToMainMenu(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("MainMenuFXML.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Image icon = new Image(getClass().getResourceAsStream("AOT MainMenu.jpg"));
            stage.getIcons().add(icon);
            stage.setTitle("Main Menu");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TitanFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
