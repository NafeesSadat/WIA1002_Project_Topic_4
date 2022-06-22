
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class TitanFXMLController implements Initializable {
    
    @FXML
    private TextArea titanTextArea;
    
    @FXML
    private TextField titanTextField;
    
    @FXML
    private TextArea killingTextArea;
    
    @FXML
    private TextField distanceTextField;
    
    @FXML
    private ChoiceBox<String> choiceBox;
    
    private final String[] characters;
    private final int[] charStrength;
    private final int[] charAgility;
    private final MyGenericStack<Integer> killingQueue;
    private int distanceMoved;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    public TitanFXMLController() {
        this.killingQueue = new MyGenericStack<>();
        this.characters = new String[]{"Reiner Braun", "Armin Arlert", "Annie Leonhart", "Bertholdt Hoove", "Jean Kristein", "Sasha Blouse",
                            "Connie Springer", "Mikasa Ackerman", "Eren Yeager", "Historia Reiss", "Levi Ackerman", "Erwin Smith", "Hange Zoë"};
        this.charStrength = new int[]{9,2,10,9,9,6,6,10,9,4,12,8,9};
        this.charAgility = new int[]{7,6,7,4,8,3,7,9,10,9,12,8,9};
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titanTextField.requestFocus();
        choiceBox.getItems().addAll(characters);
        
        /**
         * Initializing Strength and Agility values for the characters
         */
      
        
    }
    
    @FXML
    public void showEverything(ActionEvent event){
        try{
            distanceTextField.clear();
            killingQueue.clear();
            titanTextArea.clear();
            killingTextArea.clear();
            int number = Integer.parseInt(String.valueOf(titanTextField.getText()));
            JOptionPane.showMessageDialog(null, "Titans Created!");
            MyPriorityQueue danger = new MyPriorityQueue();
            for (int i = 1; i <= number; i++) {
                Titans titan = new Titans();
                titan.generateTitans(i);
                if("Abnormal Titan".equals(titan.getTitan())){
                    titanTextArea.appendText(titan.printAbnormalTitans() + "\n");
                }
                else if("One of Nine Titans".equals(titan.getTitan())){
                    titanTextArea.appendText(titan.printNineTitans() + "\n");
                }  
                else{
                    titanTextArea.appendText(titan.printNormalTitans() + "\n");
                }
                danger.offer(new DangerPair(titan.getTitanNumber(), titan.calculateDangerRisk()));
              }
            
            MyPriorityQueue copyDanger;
            copyDanger = sortTheMyPriorityQueue(danger);
            for (int i = 0; i < number; i++) {
                killingTextArea.appendText("Titan: "+ " " + copyDanger.get(i).getIndex() + "\n");
                killingQueue.push(copyDanger.get(i).getRisk());
            }
            int sum = copyDanger.get(0).getIndex();
            
            for (int i = 0; i < number-1; i++) {
                sum += Math.abs(copyDanger.get(i).getIndex()-copyDanger.get(i+1).getIndex());
            }
            distanceMoved = sum;
            
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Kindly enter the Titan number!!!");
            if(alert.showAndWait().get() == ButtonType.OK){
                titanTextField.clear();
                titanTextArea.clear();
                distanceTextField.clear();
            }
        }
    }
    
    @FXML
    public void kill(ActionEvent e){
        try{
            if(null != choiceBox.getValue())switch (choiceBox.getValue()) {
                case "Reiner Braun" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[0] + charAgility[0];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                        
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Armin Arlert" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[1] + charAgility[1];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Annie Leonhart" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[2] + charAgility[2];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Bertholdt Hoove" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[3] + charAgility[3];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Jean Kristein" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[4] + charAgility[4];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Sasha Blouse" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[5] + charAgility[5];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Connie Springer" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[6] + charAgility[6];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Mikasa Ackerman" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[7] + charAgility[7];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Eren Yeager" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[8] + charAgility[8];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Historia Reiss" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[9] + charAgility[9];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Levi Ackerman" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[10] + charAgility[10];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your  to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                case "Erwin Smith" -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[11] + charAgility[11];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }                          }
                default -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    int power = charStrength[12] + charAgility[12];
                    if(killingQueue.getElement(0)<= power){
                        alert.setHeaderText("Press Ok to kill the Titans! Press Cancel if you wish your to run!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            distanceTextField.setText(String.valueOf(distanceMoved));
                            JOptionPane.showMessageDialog(null, "Titans killed");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier Ran!");
                            distanceTextField.clear();
                        }
                    }
                    else{
                        alert.setHeaderText("Titans are too powerful! Press OK if you wish to run or else you are dead!");
                        if (alert.showAndWait().get() == ButtonType.OK){
                            JOptionPane.showMessageDialog(null, "Your Soldier ran");
                            distanceTextField.clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your Soldier is dead");
                            distanceTextField.clear();
                        }
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Select a character to kill the Titans!");
            }
            
        }  catch(RuntimeException c){
            JOptionPane.showMessageDialog(null, "An error occurred!");
            titanTextField.clear();
            titanTextArea.clear();
            distanceTextField.clear();
            
        }
    }
    
    
    public static MyPriorityQueue sortTheMyPriorityQueue(MyPriorityQueue theMyPriorityQueue) {
        MyPriorityQueue tmpMyPriorityQueue = new MyPriorityQueue();

        while (!theMyPriorityQueue.isEmpty()) {
            DangerPair tmpDangerPair = theMyPriorityQueue.poll();

            while (!tmpMyPriorityQueue.isEmpty() && tmpMyPriorityQueue.peek().getRisk() < tmpDangerPair.getRisk()) {
                theMyPriorityQueue.offer(tmpMyPriorityQueue.poll());
            }

            tmpMyPriorityQueue.offer(tmpDangerPair);
        }

        return tmpMyPriorityQueue;
    }
    
    public void clear(ActionEvent event){
        titanTextField.clear();
        titanTextArea.clear();
        killingTextArea.clear();
        distanceTextField.clear();
        titanTextField.requestFocus();
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
