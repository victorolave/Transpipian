
package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sqlusuarios extends ConexionLogin {
    
     
     
     public boolean registrar (usuarios usr){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO tablausuarios (usuario, contraseña, nombre, correo, id_tipo) VALUES ( ?,?,?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getContraseña());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getCorreo());
            ps.setInt(5, usr.getId_tipo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Sqlusuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
     
     public boolean login(usuarios usr){
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT u.id, u.usuario, u.contraseña, u.nombre, u.id_tipo, t.nombre FROM tablausuarios AS u INNER JOIN tipo_usuario AS t ON u.id_tipo=t.id WHERE usuario = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                if(usr.getContraseña().equals(rs.getString(3)))
                {            
                    
                    
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setId_tipo(rs.getInt(5));
                    usr.setNombre_tipo(rs.getString(6));
                    return true;
                }
                else
                {
                    return false;
                }              
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Sqlusuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
          
    }
    
     public int validacion(String usuario){
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT count(id) FROM tablausuarios WHERE usuario = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                return rs.getInt(1);
            }
            
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Sqlusuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
          
    }
     
     public boolean ValidacionEmail(String correo){
         
         Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
         
         Matcher mather = pattern.matcher(correo);
         
         return mather.find();
                        
     }
     
     
     
    
}
