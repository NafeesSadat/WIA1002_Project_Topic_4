import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
public class WallOfMariaFXMLController implements Initializable {
    @FXML
    private TextField limit;
    @FXML
    private TextField numString;
    @FXML
    private Label text;
    @FXML
    private Label ansText;
    @FXML
    private Button enterClear;
    @FXML
    private TextField ans;
    @FXML
    private TextArea textArea;
    private final MyLinkedList<Integer> list = new MyLinkedList<>();
    private int size = 0;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Decodes the cipher
     * @param event 
     */
    public void enter(ActionEvent event){
        try{
            limit.requestFocus();
            int range = Integer.parseInt(String.valueOf(limit.getText()));
            String edges = numString.getText();
            size++;
            if(size <= range){
                numString.requestFocus();
                String[] numArray = numString.getText().split(" ");
                for (String num : numArray)
                    list.addLast(Integer.parseInt(String.valueOf(num)));
                textArea.appendText(edges + "\n");
                numString.clear();
                text.setText("Enter brick edges of layer " + String.valueOf(size+1));
                if(size == range){
                    int maxNum = 0,tmpAns = 0;
                    for (int i = 0; i < list.getSize(); i++) {
                        int tmpNum = 0;
                        for (int j = 0; j < list.getSize(); j++) {
                            if(i == j)
                                continue;
                            if(Objects.equals(list.get(i), list.get(j)))
                                tmpNum++;
                        }
                        if(tmpNum > maxNum){
                            tmpAns = list.get(i);
                            maxNum = tmpNum;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Weakesk part of the wall found!");
                    limit.setEditable(false);
                    numString.setEditable(false);
                    ansText.setText("Weakest part of the wall is at position");
                    ans.setText(String.valueOf(tmpAns));
                    enterClear.setText("Clear");
                    enterClear.requestFocus();
                    text.setText("");
                    numString.clear();
                }
            }
            else{
                ans.clear();
                limit.clear();
                enterClear.setText("Enter");
                text.setText("Enter brick edges of layer 1");
                ansText.setText("");
                size = 0;
                list.clear();
                textArea.clear();
                limit.requestFocus();
                numString.setEditable(true);
                limit.setEditable(true);
            }
        }
        catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error! kindly enter in correct format!");
            if(alert.showAndWait().get() == ButtonType.OK){
                ans.clear();
                limit.clear();
                enterClear.setText("Enter");
                text.setText("Enter brick edges of layer 1");
                ansText.setText("");
                size = 0;
                list.clear();
                textArea.clear();
            }
        }
        
    }
    
    /*
        Returns to main menu
    */
    @FXML
    public void changeToMainMenu(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("MainMenuFXML.fxml"));
            stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
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
