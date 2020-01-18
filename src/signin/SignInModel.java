package signin;

import databaseutil.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInModel
{
    Connection connection;

    //constructor for the class
    public SignInModel()
    {
       try
       {
           this.connection = DatabaseConnection.getConnection();
       }
       catch(SQLException ex)
       {
           ex.printStackTrace();
       }

       if(this.connection == null)
       {
           System.exit(1);
       }

    }


    public boolean isDatabaseConnected()
    {
        return this.connection !=null;
    }

    public boolean isLogin(String user, String pass, String opt) throws Exception
    {

        System.out.print("Query is executing" +user + "" +pass  + "" +opt);

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM signin where username = ? and password = ? and division = ?";



        try
        {

         pr = this.connection.prepareStatement(sql);
         pr.setString(1, user);
         pr.setString(2, pass);
         pr.setString(3, opt);


         rs = pr.executeQuery();




         boolean boll1;

         if(rs.next())
         {

             return true;
         }
        return false;
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }

        finally
        {
            pr.close();
            rs.close();
        }


    }



}
