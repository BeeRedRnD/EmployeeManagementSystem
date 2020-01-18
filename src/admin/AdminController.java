package admin;

import databaseutil.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable
{

    @FXML
    private TextField id;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private DatePicker dob;

    @FXML
    private TableView<EmployeeData> employeetable;

    @FXML
    private TableColumn<EmployeeData, String> idcolumn;

    @FXML
    private TableColumn<EmployeeData, String> firstnamecolumn;

    @FXML
    private TableColumn<EmployeeData, String> lastnamecolumn;

    @FXML
    private TableColumn<EmployeeData, String> emailcolumn;

    @FXML
    private TableColumn<EmployeeData, String> dobcolumn;


    private DatabaseConnection dc;
    private ObservableList<EmployeeData> data;

    private String sql = "SELECT * FROM employees";

    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new DatabaseConnection();
    }

    //Load data to columns from SQLite database
    @FXML
    private void loadEmployeeData(ActionEvent event)
    {
        try
        {
            Connection conn = DatabaseConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next())
            {
                this.data.add(new EmployeeData(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)));
            }

        }
        catch (SQLException exp)
        {
            exp.printStackTrace();
        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("DOB"));

        this.employeetable.setItems(null);
        this.employeetable.setItems(this.data);
    }


    @FXML
    private void addEmployee(ActionEvent event)
    {
        String sql4Insertion = "INSERT INTO employees (id, firstname, lastname, email, dob) VALUES" +
                "(?,?,?,?,?)";

        try
        {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql4Insertion);

            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.firstname.getText());
            stmt.setString(3, this.lastname.getText());
            stmt.setString(4, this.email.getText());
            stmt.setString(5, this.dob.getEditor().getText());

            stmt.execute();
            conn.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    @FXML
    private void clearForm(ActionEvent event)
    {
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);
    }

}
