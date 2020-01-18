package signin;

import admin.AdminController;
import employees.EmployeesController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable
{

    SignInModel signInModel = new SignInModel();

    @FXML
    private Label dbstatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<option> combobox;

    @FXML
    private Button signinbutton;

    @FXML
    private Label loginstatus;


    public void initialize(URL url, ResourceBundle rb)
    {

        if(this.signInModel.isDatabaseConnected())
        {
            this.dbstatus.setText("Connected to Database!");
        }
        else
        {
            this.dbstatus.setText("Not Connected to Database!");
        }

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));

    }


    @FXML
    public void SignIn(ActionEvent event)
    {
        System.out.print("SignIn Button Click Start");

        try
        {
            System.out.print("xxxx" +this.username.getText() + "xxxx" +this.password.getText() + "xxxx" + ((option) this.combobox.getValue()).toString() );
            if(this.signInModel.isLogin(this.username.getText(), this.password.getText(), ((option) this.combobox.getValue()).toString()))
            {


                Stage stage = (Stage) this.signinbutton.getScene().getWindow();
                stage.close();

                switch (((option) this.combobox.getValue()).toString())
                {
                        case "Administrator":
                        adminLogin();
                        break;

                    case "Employee":
                        employeeLogin();
                        break;
                }
            }
            else
            {
                this.loginstatus.setText("Wrong Credentials");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void employeeLogin()
    {

        try
        {
            Stage employeeStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/employees/EmployeeFXML.fxml").openStream());

            EmployeesController employeesController = (EmployeesController) loader.getController();

            Scene scene = new Scene(root);
            employeeStage.setScene(scene);
            employeeStage.setTitle("Employee Dashboard");
            employeeStage.setResizable(false);
            employeeStage.show();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public void adminLogin()
    {
        try
        {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            TabPane adminroot = (TabPane) adminLoader.load(getClass().getResource("/admin/AdminFXML.fxml").openStream());
            AdminController adminController = (AdminController) adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }



    }


}
