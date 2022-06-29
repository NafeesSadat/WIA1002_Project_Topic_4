
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

/**
 * FXML Controller class
 *
 * @author User
 */
public class CharactersFXMLController implements Initializable {
    
    @FXML
    private ChoiceBox<String> choiceBox; // ChoiceBox for Displaying Sorting Attributes
    
    @FXML
    private ChoiceBox<String> sortingValues; // ChoiceBox for Displaying Sorting Values
    
    @FXML
    private ChoiceBox<String> searchBox; // ChoiceBox for Displaying Searching Attributes
    
    private final String[] sortingItems;
    private final String[] sortingOrder;
    
    @FXML
    private TextField txtAgility;

    @FXML
    private TextField txtCoordination;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtIntelligence;

    @FXML
    private TextField txtLeadership;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtPrint;  // Text area to Show the Sorted Character attributes
    
    @FXML
    private TextArea characterPrint; // Text area to Show added and deleted Characters
    
    @FXML
    private TextArea showAll; // Text area to show all the Characters in the list
    
    @FXML
    private TextArea searchArea; //Text area to display the Searched value

    @FXML
    private TextField txtStrength;
    
    @FXML
    private TextField txtWeight;
    
    @FXML
    private TextField searchField; // Text Field for Entering searching value
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private final MyLinkedList<CharacterAttributes> list = new MyLinkedList<>();
    private final MyStrengthPriorityQueue strengthList = new MyStrengthPriorityQueue();
    private final MyHeightPriorityQueue heightList = new MyHeightPriorityQueue();
    private final MyWeightPriorityQueue weightList = new MyWeightPriorityQueue();
    private final MyAgilityPriorityQueue agilityList = new MyAgilityPriorityQueue();
    private final MyIntelligencePriorityQueue intelligenceList = new MyIntelligencePriorityQueue();
    private final MyCoordinationPriorityQueue coordinationList = new MyCoordinationPriorityQueue();
    private final MyLeadershipPriorityQueue leadershipList = new MyLeadershipPriorityQueue();
    private final BinarySearch binarySearch = new BinarySearch();

    public CharactersFXMLController() {
        this.sortingOrder = new String[]{"Ascending", "Descending"};
        this.sortingItems = new String[]{"Height", "Weight", "Strength", "Agility", "Intelligence", "Coordination", "Leadership"};
    }
    
    /**
     * Method to restrict the user to enter only numbers in the text fields
     */
    void onlyNumeric(){
        // force the Agility text field to be numeric only
        txtAgility.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtAgility.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // force the Coordination text field to be numeric only
        txtCoordination.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtCoordination.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // force the Height text field to be numeric only
        txtHeight.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtHeight.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // force the Intelligence text field to be numeric only
        txtIntelligence.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtIntelligence.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // force the Leadership text field to be numeric only
        txtLeadership.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtLeadership.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // force the Strength text field to be numeric only
        txtStrength.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtStrength.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // force the Weight text field to be numeric only
        txtWeight.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtWeight.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    
    /**
     * Adds Character to the LinkedList and saves it to the text file
     * @param event
     */
    @FXML
    public void add(ActionEvent event) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("CharacterAttributes.txt", true);
        } catch (IOException ex) {
            Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter pw = new PrintWriter(fw);
        
        try{
            txtPrint.clear();
            characterPrint.clear();
            /**
            * Getting all the texts from the text fields and assigning them to the relevant variables
            */
           String name = txtName.getText();
           int height = Integer.parseInt(String.valueOf(txtHeight.getText()));
           int weight = Integer.parseInt(String.valueOf(txtWeight.getText()));
           int strength = Integer.parseInt(String.valueOf(txtStrength.getText()));
           int agility = Integer.parseInt(String.valueOf(txtAgility.getText()));
           int intelligence = Integer.parseInt(String.valueOf(txtIntelligence.getText()));
           int coordination = Integer.parseInt(String.valueOf(txtCoordination.getText()));
           int leadership = Integer.parseInt(String.valueOf(txtLeadership.getText()));
           int count = 0;
           for (int i = 0; i < list.getSize(); i++) {
               if(list.get(i).getName().equals(name)){
                   count++;
                   pw.close();
                   JOptionPane.showMessageDialog(null, "Character record already exists!");
                   break;
               }
           }
           if(count == 0){
               JOptionPane.showMessageDialog(null, "Character added successfully!");
               pw.println(name + " " + height + " " + weight + " " + strength + " " + agility + " " + intelligence + " "
                       + coordination + " " + leadership);

               /**
                * Adding the new elements to the LinkedList and Queues 
                */
               list.addLast(new CharacterAttributes(name, height, weight, strength, agility, intelligence, coordination, leadership));
               strengthList.offer(new Strength(name, strength));
               heightList.offer(new Height(name, height));
               weightList.offer(new Weight(name, weight));
               agilityList.offer(new Agility(name, agility));
               intelligenceList.offer(new Intelligence(name, intelligence));
               coordinationList.offer(new Coordination(name, coordination));
               leadershipList.offer(new Leadership(name, leadership));

               /**
                * Displaying the added Character attributes at text area characterPrint
                */
               characterPrint.appendText("Name:               " + name + "\n");
               characterPrint.appendText("Height:             " + height + "cm" + "\n");
               characterPrint.appendText("Weight:             " + weight + "kg" +"\n");
               characterPrint.appendText("Strenght:          " + strength + "\n");
               characterPrint.appendText("Agility:              " + agility + "\n");
               characterPrint.appendText("Intelligence:     " + intelligence + "\n");
               characterPrint.appendText("Coordination:  " + coordination + "\n");
               characterPrint.appendText("Leadership:      " + leadership + "\n");
           }
        } catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("An error occurred!");
            alert.setContentText("Kindly renter the character attributes!");
            if(alert.showAndWait().get() == ButtonType.OK){
                txtName.clear();
                txtHeight.clear();
                txtWeight.clear();
                txtStrength.clear();
                txtAgility.clear();
                txtIntelligence.clear();
                txtCoordination.clear();
                txtLeadership.clear();
                characterPrint.clear();
            }
        } 
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Removes the Character from the list and updates the text file
     * @param event 
     */
    @FXML
    public void remove(ActionEvent event){
        try{
            characterPrint.clear();
            

            /**
             * Getting all the texts from the text fields and assigning them to the relevant variables
             */
            String name = txtName.getText();
            int height = Integer.parseInt(String.valueOf(txtHeight.getText()));
            int weight = Integer.parseInt(String.valueOf(txtWeight.getText()));
            int strength = Integer.parseInt(String.valueOf(txtStrength.getText()));
            int agility = Integer.parseInt(String.valueOf(txtAgility.getText()));
            int intelligence = Integer.parseInt(String.valueOf(txtIntelligence.getText()));
            int coordination = Integer.parseInt(String.valueOf(txtCoordination.getText()));
            int leadership = Integer.parseInt(String.valueOf(txtLeadership.getText()));

            String lineToRemove = name + " " + height + " " + weight + " " + strength + " " + agility + " " + intelligence + " " 
                        + coordination + " " + leadership; 
            String currentLine;

            /**
             * Removing the selected elements from the LinkedList and Queues 
             */
            boolean exist = false;
            for (int i = 0; i < list.getSize(); i++) {
                /**
                 * checking if the Character is in the list
                 */
                if(list.get(i).getName().equals(name)){
                    list.remove(i);
                    strengthList.remove(i);
                    heightList.remove(i);
                    weightList.remove(i);
                    agilityList.remove(i);
                    intelligenceList.remove(i);
                    coordinationList.remove(i);
                    leadershipList.remove(i);
                    
                    exist = true;
                    
                    /**
                    * Displaying deleted Character attributes at the text area characterPrint
                    */
                    characterPrint.appendText("Name:               " + name + "\n");
                    characterPrint.appendText("Height:             " + height + "cm" + "\n");
                    characterPrint.appendText("Weight:             " + weight + "kg" +"\n");
                    characterPrint.appendText("Strenght:          " + strength + "\n");
                    characterPrint.appendText("Agility:              " + agility + "\n");
                    characterPrint.appendText("Intelligence:     " + intelligence + "\n");
                    characterPrint.appendText("Coordination:  " + coordination + "\n");
                    characterPrint.appendText("Leadership:      " + leadership + "\n");
                    
                    JOptionPane.showMessageDialog(null, "Character deleted successfully!");
                    
                    File inputFile = new File("CharacterAttributes.txt"); 
                    File tempFile = new File("myTempFile.txt"); 

                    BufferedReader reader = null; 
                    try {
                        reader = new BufferedReader(new FileReader(inputFile));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    BufferedWriter writer = null; 
                    try {
                        writer = new BufferedWriter(new FileWriter(tempFile));
                    } catch (IOException ex) {
                        Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        while((currentLine = reader.readLine()) != null) {
                            // trim newline when comparing with lineToRemove
                            String trimmedLine = currentLine.trim();
                            if(trimmedLine.equals(lineToRemove)) continue;
                            writer.write(currentLine + System.getProperty("line.separator")); 
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        reader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    inputFile.delete();
                    tempFile.renameTo(inputFile);
                }
            }

            /**
             * If entered attributes is not in the list
             */
            if(!exist){
                JOptionPane.showMessageDialog(null, "Your Character doesn't exist in the list!");
                txtName.clear();
                txtHeight.clear();
                txtWeight.clear();
                txtStrength.clear();
                txtAgility.clear();
                txtIntelligence.clear();
                txtCoordination.clear();
                txtLeadership.clear();
            }

            
            
        } catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("An error occurred!");
            alert.setContentText("Kindly renter the character attributes!");
            if(alert.showAndWait().get() == ButtonType.OK){
                txtName.clear();
                txtHeight.clear();
                txtWeight.clear();
                txtStrength.clear();
                txtAgility.clear();
                txtIntelligence.clear();
                txtCoordination.clear();
                txtLeadership.clear();
            }
        }
            
    }
    
    @FXML
    public void show(ActionEvent event){
        try{
            showAll.clear();
            for (int i = 0; i < list.getSize(); i++) {
                showAll.appendText("Name:              " + list.get(i).getName() + "\n");
                showAll.appendText("Height:            " + list.get(i).getHeight() + "\n");
                showAll.appendText("Weight:            " + list.get(i).getWeight() + "\n");
                showAll.appendText("Strenght:          " + list.get(i).getStrength() + "\n");
                showAll.appendText("Agility:             " + list.get(i).getAgility() + "\n");
                showAll.appendText("Intelligence:     " + list.get(i).getIntelligence() + "\n");
                showAll.appendText("Coordination:  " + list.get(i).getCoordination() + "\n");
                showAll.appendText("Leadership:      " + list.get(i).getLeadership() + "\n");
                showAll.appendText("\n");
            }
        } catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("An error occurred!");
            alert.setContentText("Kindly renter the character attributes!");
            if(alert.showAndWait().get() == ButtonType.OK){
                txtName.clear();
                txtHeight.clear();
                txtWeight.clear();
                txtStrength.clear();
                txtAgility.clear();
                txtIntelligence.clear();
                txtCoordination.clear();
                txtLeadership.clear();
            }
        }
    }
        
    
    /**
     * program receives sorting attribute(s) as input, and rank the soldiers based on the sorting attribute(s)
     * @param event 
     */
    @FXML
    public void sort(ActionEvent event){
        try{
            String choosenItem = choiceBox.getValue();
            txtPrint.clear();
            if(null != choosenItem && null !=sortingValues)switch (choosenItem) {
                case "Height" -> {
                    //Making a copy list of the current list
                    MyHeightPriorityQueue copyList = new MyHeightPriorityQueue();
                    for (int i = 0; i < heightList.getSize(); i++) {
                        copyList.offer(heightList.get(i));
                    }       //Sorting and displaying the copy list 
                    MyHeightPriorityQueue copy = copyList.sortTheMyHeightPriorityQueue(copyList);
                    if("Descending".equals(sortingValues.getValue())){
                        for (int i = 0; i < copy.getSize(); i++) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }
                    else{
                        for (int i = copy.getSize()-1; i >= 0; i--) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }                      }
                case "Strength" -> {
                    //Making a copy list of the current list
                    MyStrengthPriorityQueue copyList = new MyStrengthPriorityQueue();
                    for (int i = 0; i < strengthList.getSize(); i++) {
                        copyList.offer(strengthList.get(i));
                    }       //Sorting and displaying the copy list
                    MyStrengthPriorityQueue copy = copyList.sortTheMyStrengthPriorityQueue(copyList);
                    if("Descending".equals(sortingValues.getValue())){
                        for (int i = 0; i < copy.getSize(); i++) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }
                    else{
                        for (int i = copy.getSize()-1; i >= 0; i--) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }                      }
                case "Weight" -> {
                    //Making a copy list of the current list
                    MyWeightPriorityQueue copyList = new MyWeightPriorityQueue();
                    for (int i = 0; i < weightList.getSize(); i++) {
                        copyList.offer(weightList.get(i));
                    }       //Sorting and displaying the copy list
                    MyWeightPriorityQueue copy = copyList.sortTheMyWeightPriorityQueue(copyList);
                    if("Descending".equals(sortingValues.getValue())){
                        for (int i = 0; i < copy.getSize(); i++) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }
                    else{
                        for (int i = copy.getSize()-1; i >= 0; i--) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }                      }
                case "Agility" -> {
                    //Making a copy list of the current list
                    MyAgilityPriorityQueue copyList = new MyAgilityPriorityQueue();
                    for (int i = 0; i < agilityList.getSize(); i++) {
                        copyList.offer(agilityList.get(i));
                    }       //Sorting and displaying the copy list
                    MyAgilityPriorityQueue copy = copyList.sortTheMyAgilityPriorityQueue(copyList);
                    if("Descending".equals(sortingValues.getValue())){
                        for (int i = 0; i < copy.getSize(); i++) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }
                    else{
                        for (int i = copy.getSize()-1; i >= 0; i--) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }                      }
                case "Intelligence" -> {
                    //Making a copy list of the current list
                    MyIntelligencePriorityQueue copyList = new MyIntelligencePriorityQueue();
                    for (int i = 0; i < intelligenceList.getSize(); i++) {
                        copyList.offer(intelligenceList.get(i));
                    }       //Sorting and displaying the copy list
                    MyIntelligencePriorityQueue copy = copyList.sortTheMyIntelligencePriorityQueue(copyList);
                    if("Descending".equals(sortingValues.getValue())){
                        for (int i = 0; i < copy.getSize(); i++) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }
                    else{
                        for (int i = copy.getSize()-1; i >= 0; i--) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }                      }
                case "Coordination" -> {
                    //Making a copy list of the current list
                    MyCoordinationPriorityQueue copyList = new MyCoordinationPriorityQueue();
                    for (int i = 0; i < coordinationList.getSize(); i++) {
                        copyList.offer(coordinationList.get(i));
                    }       //Sorting and displaying the copy list
                    MyCoordinationPriorityQueue copy = copyList.sortTheMyCoordinationPriorityQueue(copyList);
                    if("Descending".equals(sortingValues.getValue())){
                        for (int i = 0; i < copy.getSize(); i++) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }
                    else{
                        for (int i = copy.getSize()-1; i >= 0; i--) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }                      }
                default -> {
                    //Making a copy list of the current list
                    MyLeadershipPriorityQueue copyList = new MyLeadershipPriorityQueue();
                    for (int i = 0; i < leadershipList.getSize(); i++) {
                        copyList.offer(leadershipList.get(i));
                    }       //Sorting and displaying the copy list
                    MyLeadershipPriorityQueue copy = copyList.sortTheMyLeadershipPriorityQueue(copyList);
                    if("Descending".equals(sortingValues.getValue())){
                        for (int i = 0; i < copy.getSize(); i++) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }
                    else{
                        for (int i = copy.getSize()-1; i >= 0; i--) {
                            txtPrint.appendText(copy.displayQueue(i) +"\n");
                        }
                    }                      
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Kindly select the sorting attributes");
            }
        } catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("An error occurred!");
            alert.setContentText("Kindly renter the attributes!");
            if(alert.showAndWait().get() == ButtonType.OK){
                txtName.clear();
                txtHeight.clear();
                txtWeight.clear();
                txtStrength.clear();
                txtAgility.clear();
                txtIntelligence.clear();
                txtCoordination.clear();
                txtLeadership.clear();
                txtPrint.clear();
                characterPrint.clear();
            }
        }
    }
    /**
     * program receives searching values as input, and shows the character name as
     * output if character exists in the list
     * @param event 
     */
    @FXML
    public void search(ActionEvent event){
        try{
            String searchItem = searchBox.getValue();
            searchArea.clear();
            int value = Integer.parseInt(String.valueOf(searchField.getText()));
            int[] array = new int[list.getSize()]; 
            if(null != searchItem)switch (searchItem) {
                case "Height" -> {
                    //Making a copy list of the current list
                    MyHeightPriorityQueue copyList = new MyHeightPriorityQueue();
                    for (int i = 0; i < heightList.getSize(); i++) {
                        copyList.offer(heightList.get(i));
                    }       //Sorting and displaying the copy list 
                    MyHeightPriorityQueue copy = copyList.sortTheMyHeightPriorityQueue(copyList);
                    for (int i = 0; i < copy.getSize(); i++) {
                        array[i] = copy.get(i).getHeight();
                    }
                    int firstOccurrence = binarySearch.findFirstOccurrence(array, value);
                    int lastOccurrence = binarySearch.findLastOccurrence(array, value);
                    if(firstOccurrence == lastOccurrence){
                        if(firstOccurrence != -1 && lastOccurrence != -1)
                            searchArea.appendText(copy.get(firstOccurrence).getName());
                        else{
                            JOptionPane.showMessageDialog(null, "No Character of such Height exists");
                            searchArea.clear(); 
                        }
                    }
                    else if(firstOccurrence != lastOccurrence){
                        for (int i = firstOccurrence ; i <= lastOccurrence; i++) {
                            searchArea.appendText(copy.get(i).getName() + "\n");
                            System.out.println(i);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Character of such Height exists fuck");
                        searchArea.clear();
                        searchField.clear();
                    }
                }
                case "Strength" -> {
                    //Making a copy list of the current list
                    MyStrengthPriorityQueue copyList = new MyStrengthPriorityQueue();
                    for (int i = 0; i < strengthList.getSize(); i++) {
                        copyList.offer(strengthList.get(i));
                    }       //Sorting and displaying the copy list
                    MyStrengthPriorityQueue copy = copyList.sortTheMyStrengthPriorityQueue(copyList);
                    for (int i = 0; i < copy.getSize(); i++) {
                        array[i] = copy.get(i).getStrength();
                    }
                    int firstOccurrence = binarySearch.findFirstOccurrence(array, value);
                    int lastOccurrence = binarySearch.findLastOccurrence(array, value);
                    if(firstOccurrence == lastOccurrence){
                        if(firstOccurrence != -1 && lastOccurrence != -1)
                            searchArea.appendText(copy.get(firstOccurrence).getName());
                        else{
                            JOptionPane.showMessageDialog(null, "No Character of such Strength exists");
                            searchArea.clear(); 
                        }
                    }
                    else if(firstOccurrence != lastOccurrence){
                        for (int i = firstOccurrence ; i <= lastOccurrence; i++) {
                            searchArea.appendText(copy.get(i).getName() + "\n");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Character of such Strength exists");
                        searchArea.clear();
                        searchField.clear();
                    }
                }
                case "Weight" -> {
                    //Making a copy list of the current list
                    MyWeightPriorityQueue copyList = new MyWeightPriorityQueue();
                    for (int i = 0; i < weightList.getSize(); i++) {
                        copyList.offer(weightList.get(i));
                    }       //Sorting and displaying the copy list
                    MyWeightPriorityQueue copy = copyList.sortTheMyWeightPriorityQueue(copyList);
                    for (int i = 0; i < copy.getSize(); i++) {
                        array[i] = copy.get(i).getWeight();
                    }
                    int firstOccurrence = binarySearch.findFirstOccurrence(array, value);
                    int lastOccurrence = binarySearch.findLastOccurrence(array, value);
                    if(firstOccurrence == lastOccurrence){
                        if(firstOccurrence != -1 && lastOccurrence != -1)
                            searchArea.appendText(copy.get(firstOccurrence).getName());
                        else{
                            JOptionPane.showMessageDialog(null, "No Character of such Weight exists");
                            searchArea.clear(); 
                        }
                    }
                    else if(firstOccurrence != lastOccurrence){
                        for (int i = firstOccurrence ; i <= lastOccurrence; i++) {
                            searchArea.appendText(copy.get(i).getName() + "\n");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Character of such Weight exists");
                        searchArea.clear();
                        searchField.clear();
                    }
                }
                case "Agility" -> {
                    //Making a copy list of the current list
                    MyAgilityPriorityQueue copyList = new MyAgilityPriorityQueue();
                    for (int i = 0; i < agilityList.getSize(); i++) {
                        copyList.offer(agilityList.get(i));
                    }       //Sorting and displaying the copy list
                    MyAgilityPriorityQueue copy = copyList.sortTheMyAgilityPriorityQueue(copyList);
                    for (int i = 0; i < copy.getSize(); i++) {
                        array[i] = copy.get(i).getAgility();
                    }
                    int firstOccurrence = binarySearch.findFirstOccurrence(array, value);
                    int lastOccurrence = binarySearch.findLastOccurrence(array, value);
                    if(firstOccurrence == lastOccurrence){
                        if(firstOccurrence != -1 && lastOccurrence != -1)
                            searchArea.appendText(copy.get(firstOccurrence).getName());
                        else{
                            JOptionPane.showMessageDialog(null, "No Character of such Agility exists");
                            searchArea.clear(); 
                        }
                    }
                    else if(firstOccurrence != lastOccurrence){
                        for (int i = firstOccurrence ; i <= lastOccurrence; i++) {
                            searchArea.appendText(copy.get(i).getName() + "\n");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Character of such Agility exists");
                        searchArea.clear();
                        searchField.clear();
                    }
                }
                case "Intelligence" -> {
                    //Making a copy list of the current list
                    MyIntelligencePriorityQueue copyList = new MyIntelligencePriorityQueue();
                    for (int i = 0; i < intelligenceList.getSize(); i++) {
                        copyList.offer(intelligenceList.get(i));
                    }       //Sorting and displaying the copy list
                    MyIntelligencePriorityQueue copy = copyList.sortTheMyIntelligencePriorityQueue(copyList);
                    for (int i = 0; i < copy.getSize(); i++) {
                        array[i] = copy.get(i).getIntelligence();
                    }
                    int firstOccurrence = binarySearch.findFirstOccurrence(array, value);
                    int lastOccurrence = binarySearch.findLastOccurrence(array, value);
                    if(firstOccurrence == lastOccurrence){
                        if(firstOccurrence != -1 && lastOccurrence != -1)
                            searchArea.appendText(copy.get(firstOccurrence).getName());
                        else{
                            JOptionPane.showMessageDialog(null, "No Character of such Intelligence exists");
                            searchArea.clear(); 
                        }
                    }
                    else if(firstOccurrence != lastOccurrence){
                        for (int i = firstOccurrence ; i <= lastOccurrence; i++) {
                            searchArea.appendText(copy.get(i).getName() + "\n");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Character of such Intelligence exists");
                        searchArea.clear();
                        searchField.clear();
                    }
                }
                case "Coordination" -> {
                    //Making a copy list of the current list
                    MyCoordinationPriorityQueue copyList = new MyCoordinationPriorityQueue();
                    for (int i = 0; i < coordinationList.getSize(); i++) {
                        copyList.offer(coordinationList.get(i));
                    }       //Sorting and displaying the copy list
                    MyCoordinationPriorityQueue copy = copyList.sortTheMyCoordinationPriorityQueue(copyList);
                    for (int i = 0; i < copy.getSize(); i++) {
                        array[i] = copy.get(i).getCoordination();
                    }
                    int firstOccurrence = binarySearch.findFirstOccurrence(array, value);
                    int lastOccurrence = binarySearch.findLastOccurrence(array, value);
                    if(firstOccurrence == lastOccurrence){
                        if(firstOccurrence != -1 && lastOccurrence != -1)
                            searchArea.appendText(copy.get(firstOccurrence).getName());
                        else{
                            JOptionPane.showMessageDialog(null, "No Character of such Coordination exists");
                            searchArea.clear(); 
                        }
                    }
                    else if(firstOccurrence != lastOccurrence){
                        for (int i = firstOccurrence ; i <= lastOccurrence; i++) {
                            searchArea.appendText(copy.get(i).getName() + "\n");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Character of such Coordination exists");
                        searchArea.clear();
                        searchField.clear();
                    }
                }
                default -> {
                    //Making a copy list of the current list
                    MyLeadershipPriorityQueue copyList = new MyLeadershipPriorityQueue();
                    for (int i = 0; i < leadershipList.getSize(); i++) {
                        copyList.offer(leadershipList.get(i));
                    }       //Sorting and displaying the copy list
                    MyLeadershipPriorityQueue copy = copyList.sortTheMyLeadershipPriorityQueue(copyList);
                    for (int i = 0; i < copy.getSize(); i++) {
                        array[i] = copy.get(i).getLeadership();
                    }
                    int firstOccurrence = binarySearch.findFirstOccurrence(array, value);
                    int lastOccurrence = binarySearch.findLastOccurrence(array, value);
                    if(firstOccurrence == lastOccurrence){
                        if(firstOccurrence != -1 && lastOccurrence != -1)
                            searchArea.appendText(copy.get(firstOccurrence).getName());
                        else{
                            JOptionPane.showMessageDialog(null, "No Character of such Leadership exists");
                            searchArea.clear(); 
                        }
                    }
                    else if(firstOccurrence != lastOccurrence){
                        for (int i = firstOccurrence ; i <= lastOccurrence; i++) {
                            searchArea.appendText(copy.get(i).getName() + "\n");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Character of such Leadership exists");
                        searchArea.clear();
                        searchField.clear();
                    }
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Kindly select the searching attributes");
            }
        } catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("An error occurred!");
            alert.setContentText("Kindly renter the attributes!");
            if(alert.showAndWait().get() == ButtonType.OK){
                txtName.clear();
                txtHeight.clear();
                txtWeight.clear();
                txtStrength.clear();
                txtAgility.clear();
                txtIntelligence.clear();
                txtCoordination.clear();
                txtLeadership.clear();
                txtPrint.clear();
                characterPrint.clear();
            }
        }
    }
    
    /*
    *  Clears the charPrint text field
    */
    @FXML
    public void clearCharPrint(ActionEvent e){
        characterPrint.clear();
    }
    
    /*
    *  Clears the txtPrint text field
    */
    @FXML
    public void cleartxtPrint(ActionEvent e){
        txtPrint.clear();
    }
    
    @FXML
    public void clearShowAll(ActionEvent e){
        showAll.clear();
    }
    
    @FXML
    public void clearTextFields(ActionEvent e){
        txtName.clear();
        txtHeight.clear();
        txtWeight.clear();
        txtStrength.clear();
        txtAgility.clear();
        txtIntelligence.clear();
        txtCoordination.clear();
        txtLeadership.clear();
        txtPrint.clear();
        txtName.requestFocus();
    }

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("CharacterAttributes.txt", true);
        } catch (IOException ex) {
            Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader("CharacterAttributes.txt"))) {
            String line = br.readLine();
            
            /**
             * Reading the text file line by line and splitting the Character Attributes from the text line 
             * and assigning them to the relevant variables
             */
            try{
                onlyNumeric();
                while (line != null) {
                String[] attributes = line.split(" ");
                String name = attributes[0] + " " + attributes[1];
                int height = Integer.parseInt(String.valueOf(attributes[2]));
                int weight = Integer.parseInt(String.valueOf(attributes[3]));
                int strength = Integer.parseInt(String.valueOf(attributes[4]));
                int agility = Integer.parseInt(String.valueOf(attributes[5]));
                int intelligence = Integer.parseInt(String.valueOf(attributes[6]));
                int coordination = Integer.parseInt(String.valueOf(attributes[7]));
                int leadership = Integer.parseInt(String.valueOf(attributes[8]));
                
                /**
                 * Loading data in to the list from the CharacterAttributes.txt file
                 */
                list.addLast(new CharacterAttributes(name, height, weight, strength, agility, intelligence, coordination, leadership));
                strengthList.offer(new Strength(name, strength));
                heightList.offer(new Height(name, height));
                weightList.offer(new Weight(name, weight));
                agilityList.offer(new Agility(name, agility));
                intelligenceList.offer(new Intelligence(name, intelligence));
                coordinationList.offer(new Coordination(name, coordination));
                leadershipList.offer(new Leadership(name, leadership));
                line = br.readLine();
                }
            } catch(RuntimeException e){
                    JOptionPane.showMessageDialog(null, "Kindly enter the attribute in a right way!");
                    txtName.clear();
                    txtHeight.clear();
                    txtWeight.clear();
                    txtStrength.clear();
                    txtAgility.clear();
                    txtIntelligence.clear();
                    txtCoordination.clear();
                    txtLeadership.clear();
                    txtPrint.clear();
                    characterPrint.clear();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CharactersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Adding all the items in the choice box
        choiceBox.getItems().addAll(sortingItems);
        searchBox.getItems().addAll(sortingItems);
        sortingValues.getItems().addAll(sortingOrder);
    }

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
