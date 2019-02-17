
package Login;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionLogin {
    
    private final String base = "usuarios";
    private final String user = "root";
    private final String password = "030913";
    private final String url = "jdbc:mysql://localhost:3306/"+base;
    private Connection con = null;
    
    public Connection getConexion()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(this.url, this.user, this.password);
        }
        catch(SQLException e)
        {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    
}
