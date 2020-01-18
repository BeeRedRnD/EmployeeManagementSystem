package databaseutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{

    private static final String SQLCONN = "jdbc:sqlite:empmgtsys.sqlite";

    public static Connection getConnection() throws SQLException
    {
        try
        {
         Class.forName("org.sqlite.JDBC");
         return DriverManager.getConnection(SQLCONN);
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

}
