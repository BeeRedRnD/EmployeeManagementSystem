package signin;

// Main class of Employee Management System

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Inherit Application to make this class JavaFx
public class SignIn extends Application
{

    // implement abstract method of Application class i.e. 'start'
    public void start(Stage stage) throws Exception
    {
        //create root node
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("signin.fxml")); //root node added

        //create new scene using root object
        Scene scene = new Scene(root);

        //set the scene to our stage
        stage.setScene(scene);

        //set title
        stage.setTitle("Employee Management System");

        //display stage
        stage.show();

    }

    //Main method to launch the application
    public static void main(String args[])
    {
        launch(args); //run application
    }

}
