
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

/**
 * FXML Controller class
 *
 * @author Nafees
 */
public class HamiltonianCycleFXMLController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextArea map;
    @FXML
    private TextField mapPoint;
    
    final int vertices = 16;
    int path[];
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /**
     * Shows the Hamilton Cycle when the button is pressed 
     * @param event 
     */
    @FXML
    public void showCycle(ActionEvent event){
        try{
            try{
                map.clear();
                int graph[][] = {{0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0}, 
                         {1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0}, 
                         {0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,0}, 
                         {0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0}, 
                         {0,1,0,0,0,0,1,0,0,0,1,0,0,0,0,0}, 
                         {1,0,0,0,0,0,1,1,0,0,0,0,1,0,0,0}, 
                         {0,1,0,0,1,1,0,0,1,0,0,0,0,0,0,1}, 
                         {1,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0}, 
                         {0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0}, 
                         {0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1}, 
                         {0,0,0,1,1,0,0,0,1,0,0,0,0,0,1,0}, 
                         {0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0}, 
                         {0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0}, 
                         {0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0}, 
                         {0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1}, 
                         {0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0}};
                int start = Integer.parseInt(String.valueOf(mapPoint.getText()));
                hamCycle(graph, start);
            }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Kindly enter the correct value!!!");
                if(alert.showAndWait().get() == ButtonType.OK){
                    map.clear();
                    mapPoint.clear();
                }
            }
        }
        catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Sorry! No path found on the map!!!");
            if(alert.showAndWait().get() == ButtonType.OK){
                map.clear();
                mapPoint.clear();
            }
        }
        
    }
    
//    function to check if the vertex v can be added at index 'pos'in the Hamiltonian Cycle
    boolean isSafeToAdd(int v, int graph[][], int path[], int pos){
        
        /* Checks if this vertex is an adjacent vertex of
	the previously added vertex. */
	if (graph[path[pos - 1]][v] == 0) return false;
	
        // Checks if the vertex has already been included.
	for (int i = 0; i < pos; i++)
            if (path[i] == v) return false;
	return true;
    }
    
    boolean boolHamCycle(int graph[][], int path[], int position){
        
        if(position == vertices) return graph[path[position - 1]][path[0]] == 1;
        
        for (int i = 0; i < vertices; i++) {
            if(isSafeToAdd(i, graph, path, position)){
                path[position] = i;
		// recurtion to construct rest of the path
		if (boolHamCycle(graph, path, position + 1) == true) return true;
		// If adding vertex v doesn't lead to a solution, then remove it
		path[position] = -1;
            }
        }
        
        /* If no vertex can be added to Hamiltonian Cycle
	constructed so far, then return false */
	return false;
    }
    
    int hamCycle(int graph[][], int start){
        path = new int[vertices];
	for (int i = 0; i < vertices; i++)
            path[i] = -1;
	path[0] = start;
	if (boolHamCycle(graph, path, 1) == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Sorry! No path found on the map!!!");
            if(alert.showAndWait().get() == ButtonType.OK){
                    map.clear();
                    map.clear();
            }
            return 0;
	}
	printSolution(path);
	return 1;
    }
    
    /*
      Prints the solution
    */
    @FXML
    private void printSolution(int[] path) {
         for (int i = 0; i < vertices; i++){
             map.appendText(String.valueOf(path[i]));
             map.appendText(" -->> ");
         }
         map.appendText(String.valueOf(path[0]));
    }
    
    public void clear(ActionEvent event){
        mapPoint.clear();
        map.clear();
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
