
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
public class BestPathFXMLController implements Initializable {
    
    @FXML
    private TextField mapNodeText;
    @FXML
    private  TextArea pathTextArea;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void showPaths(ActionEvent event){
        try{
            if(Integer.parseInt(String.valueOf(mapNodeText.getText())) <= 15 && Integer.parseInt(String.valueOf(mapNodeText.getText())) >= 0){
            try{
                pathTextArea.clear();
                pathTextArea.setEditable(false);
                //Number of vertices
                int vertices = 16;

                // array of vectors is used to store the graph in the form of an adjacencyListacency list-->>
                ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
                for(int i = 0; i < vertices; i++){
                        adjacencyList.add(new ArrayList<>());
                } 
                // Given Map in the Question
                addEdge(adjacencyList, 0, 1);
                addEdge(adjacencyList, 0, 5);
                addEdge(adjacencyList, 0, 7);
                addEdge(adjacencyList, 1, 2);
                addEdge(adjacencyList, 1, 4);
                addEdge(adjacencyList, 1, 6);
                addEdge(adjacencyList, 2, 3);
                addEdge(adjacencyList, 2, 13);
                addEdge(adjacencyList, 2, 11);
                addEdge(adjacencyList, 3, 10);
                addEdge(adjacencyList, 4, 6);
                addEdge(adjacencyList, 4, 10);
                addEdge(adjacencyList, 5, 6);
                addEdge(adjacencyList, 5, 12);
                addEdge(adjacencyList, 5, 7);
                addEdge(adjacencyList, 6, 8);
                addEdge(adjacencyList, 6, 15);
                addEdge(adjacencyList, 7, 9);
                addEdge(adjacencyList, 8, 10);
                addEdge(adjacencyList, 9, 12);
                addEdge(adjacencyList, 9, 15);
                addEdge(adjacencyList, 10, 14);
                addEdge(adjacencyList, 11, 13);
                addEdge(adjacencyList, 13, 14);
                addEdge(adjacencyList, 14, 15);

                // Given source and destination
                int source = 0;
                int destination = Integer.parseInt(String.valueOf(mapNodeText.getText()));

                // Calling the print function to show the best paths-->>	
                printShortestPaths(adjacencyList, vertices, source, destination);
            }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Kindly enter the Titan location!!!");
                if(alert.showAndWait().get() == ButtonType.OK){
                    mapNodeText.clear();
                    pathTextArea.clear();
                }
            }
        }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Your entered a location that doesn't exist on the map!!!");
                if(alert.showAndWait().get() == ButtonType.OK){
                    mapNodeText.clear();
                    pathTextArea.clear();
                }
            }
        }
        catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please enter a valid location!!!");
            if(alert.showAndWait().get() == ButtonType.OK){
                mapNodeText.clear();
                pathTextArea.clear();
            }
        }
    }
    
    // Function to form edge between two vertices source and destination-->>
    void addEdge(ArrayList<ArrayList<Integer>> adj, int source, int destination){
	adj.get(source).add(destination);
	adj.get(destination).add(source);
    }
    static void find_paths(ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> parent, int vertices, int destination) {
        
	// Base Case-->>
	if (destination == -1) {
            paths.add(new ArrayList<>(path));
		return;
	}
        
	// Loop for all the parents of the given vertex-->>
	
	for (int par : parent.get(destination)) {
            // Insert the current vertex in path-->>
            path.add(destination);
            // Recursive call for its parent-->>
            find_paths(paths, path, parent, vertices, par);
            // Remove the current vertex
            path.remove(path.size()-1);
	}
    }
    static void bfs(ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> parent, int vertices, int source) {
        	
	// dist will contain shortest distance from start to every other vertex-->>
	int[] dist = new int[vertices];
	Arrays.fill(dist, Integer.MAX_VALUE);
	Queue<Integer> queue = new LinkedList<>();
        
	// Insert source vertex in queue and make its parent -1 and distance 0
	queue.offer(source);
	parent.get(source).clear();
	parent.get(source).add(-1);
	dist[source] = 0;
        
	// Until Queue is empty
	while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
		if (dist[v] > dist[u] + 1) {
		// A shorter distance is found. So erase all the previous parents and insert new parent u in parent[v]-->>
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                    parent.get(v).clear();
                    parent.get(v).add(u);
		}
		else if (dist[v] == dist[u] + 1) {
                    // Another candidate parent for shortes path found-->>
                    parent.get(v).add(u);
		}
            }
        }
    }
    void printShortestPaths(ArrayList<ArrayList<Integer>> adj, int vertices, int source, int destination){
        
	ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
	ArrayList<Integer> path = new ArrayList<>();
	ArrayList<ArrayList<Integer>> parent = new ArrayList<>();
	for(int i = 0; i < vertices; i++){
            parent.add(new ArrayList<>());
	}
        
	// Function call to bfs
	bfs(adj, parent, vertices, source);
	
        // Function call to find_paths
	find_paths(paths, path, parent, vertices, destination);
        
	for (ArrayList<Integer> v : paths) {
            // Since paths contain eachpath in reverse order, so reverse it-->>
            Collections.reverse(v);
            // Print node for the current path-->>
            for (int i = 0; i < v.size(); i++) {
                pathTextArea.appendText(v.get(i).toString());
                if(i == v.size()-1)
                    continue;
                pathTextArea.appendText(" -->> ");
            }
            pathTextArea.appendText("\n");
            pathTextArea.appendText("\n");
	}
    }
    
    public void clear(ActionEvent event){
        pathTextArea.clear();
        mapNodeText.clear();
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
