/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Victor Olave
 */
public class CtrlUsuarios extends javax.swing.JFrame {
    
   Connection con = null;
   Statement stmt = null;   
   String titulos[] = {"id","Usuario","Nombre","Correo","Tipo"};
   String fila[] = new String [5];
   String filtro="id";
   DefaultTableModel modelo;

   public void MostrarTabla(){
       try {
            
            String url = "jdbc:mysql://localhost:3306/usuarios";
            String usuario = "root";
            String contraseña = "030913";  
            
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               con = DriverManager.getConnection(url,usuario,contraseña);
               if (con!= null)
                   System.out.println("Se ha establecido una conexion a la base de datos"+"\n"+url);
               
               stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery("select* from tablausuarios");
               
               modelo = new DefaultTableModel(null,titulos);
            
               while(rs.next()) {
                   
                   fila[0] = rs.getString("id");
                   fila[1] = rs.getString("usuario");
                   fila[2] = rs.getString("nombre");
                   fila[3] = rs.getString("correo");
                   fila[4] = rs.getString("id_tipo");                  
                   modelo.addRow(fila);     
               }
               tabla_empleados.setModel(modelo);
                TableColumn ci = tabla_empleados.getColumn("id");
                ci.setMaxWidth(40);
                TableColumn cu = tabla_empleados.getColumn("Usuario");
                cu.setMaxWidth(200);
                TableColumn cn = tabla_empleados.getColumn("Nombre");
                cn.setMaxWidth(200);
                TableColumn cc = tabla_empleados.getColumn("Correo");
                cc.setMaxWidth(400);
                TableColumn cd = tabla_empleados.getColumn("Tipo");
                cd.setMaxWidth(200);
                
               
        }
        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
        }
       
   }  
   public void Seleccionar(){
        int fila = tabla_empleados.getSelectedRow();
        if(fila>=0)
        {
            id.setText(tabla_empleados.getValueAt(fila, 0).toString());
            txt_usuario.setText(tabla_empleados.getValueAt(fila, 1).toString());
            txt_nombre.setText(tabla_empleados.getValueAt(fila, 2).toString());
            txt_correo.setText(tabla_empleados.getValueAt(fila, 3).toString());
            txt_tipo.setText(tabla_empleados.getValueAt(fila, 4).toString());           
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
   }
   public void Actualizar() {
        
        String cadena1,cadena2,cadena3,cadena4,cadena5,cadena6,cadena7;
     
       cadena1 = id.getText();
       cadena2 = txt_usuario.getText();
       cadena3 = txt_nombre.getText();
       cadena4 = txt_correo.getText();
       cadena5 = txt_tipo.getText();      
     
    
     if (txt_usuario.getText().equals("")) {
         
         javax.swing.JOptionPane.showMessageDialog(this,"1-. Seleccione el empleado \n 2-. Actualice el dato deseado en el campo correspondiente","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
     }
     else {   
     
     try { 
                  String url = "jdbc:mysql://localhost:3306/usuarios"; 
                  String usuario = "root"; 
                  String contraseña = "030913"; 
                  
                  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  stmt.executeUpdate("update ignore tablausuarios set id= '"+cadena1+"' , usuario = '"+cadena2+"',nombre = '"+cadena3+"',correo = '"+cadena4+"', id_tipo = '"+cadena5+"' where id = '"+id.getText()+"' || usuario = '"+txt_usuario.getText()+"' || nombre = '"+txt_nombre.getText()+"' || correo = '"+txt_correo.getText()+"' || id_tipo = '"+txt_tipo.getText()+"'"); 

                  System.out.println("Los valores han sido Actualizados"); 
                  } 
                  catch( SQLException e ) { 
                      e.printStackTrace(); 
                  } catch (ClassNotFoundException ex) { 
            Logger.getLogger(CtrlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CtrlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CtrlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } 
  
              finally { 
                  if ( con != null ) { 
                      try    { 
                          con.close(); 
                          stmt.close(); 
                      } catch( Exception e ) { 
                          System.out.println( e.getMessage()); 
                      } 
                  } 
     }
     javax.swing.JOptionPane.showMessageDialog(this,"Actualizado correctamente!","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
     } 
        this.id.setText("");
        this.txt_usuario.setText("");
        this.txt_nombre.setText("");
        this.txt_correo.setText("");
        this.txt_tipo.setText("");       
        MostrarTabla();
    }
   public void Eliminar(){
     int fila = tabla_empleados.getSelectedRow();
  
       
       if(fila>=0)
       {      
           String valor = tabla_empleados.getValueAt(fila, 0).toString();
            try {       
                PreparedStatement pps = con.prepareStatement("DELETE FROM tablausuarios WHERE id='"+valor+"'");
                pps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CtrlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       }  
       else
        {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
       MostrarTabla();
   }
   public void Filtrado(String texto) {
       try { 
           if(ComboCriterio.getSelectedItem()== "Identificador")
           {
               filtro="id";
           }
           if(ComboCriterio.getSelectedItem()== "Usuario")
           {
               filtro="usuario";
           }
           if(ComboCriterio.getSelectedItem()== "Nombre")
           {
               filtro="nombre";
           }
           if(ComboCriterio.getSelectedItem()== "Correo")
           {
               filtro="correo";
           }
            if(ComboCriterio.getSelectedItem().toString() == "Tipo")
           {
               filtro="id_tipo";
           }          
                     
           String sql = "select *from tablausuarios where "+filtro+" like '%"+texto+"%'";          
           modelo = new DefaultTableModel(null,titulos);
           stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery(sql);          
           while (rs.next())
           {
                   fila[0] = rs.getString("id");
                   fila[1] = rs.getString("usuario");
                   fila[2] = rs.getString("nombre");
                   fila[3] = rs.getString("correo");
                   fila[4] = rs.getString("id_tipo");
                   modelo.addRow(fila);                 
           }
           tabla_empleados.setModel(modelo);
           rs.close();
           stmt.close();
                    
       } catch (Exception e) {
           System.err.println(""+e.getMessage());
       }
   }

    
    public CtrlUsuarios() {
        initComponents();
        this.setTitle("Administracion de Usuarios");
        this.setLocation(0,0);
        this.setResizable(false);
        MostrarTabla();
        
        
                


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        txt_tipo = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        btn_actualizar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_empleados = new javax.swing.JTable();
        ComboCriterio = new javax.swing.JComboBox<>();
        txt_buscar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btn_seleccionar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn_eliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1070, 750));
        setSize(new java.awt.Dimension(1070, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204), 3), "Nuevo Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Tipo :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, -1, 33));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Usuario :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 80, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Nombre :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 80, 33));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Correo :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, 33));

        txt_usuario.setEditable(false);
        txt_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 200, 30));

        txt_tipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tipoKeyTyped(evt);
            }
        });
        jPanel2.add(txt_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 200, 30));

        txt_correo.setEditable(false);
        txt_correo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoKeyTyped(evt);
            }
        });
        jPanel2.add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 200, 30));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 200, 30));

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_actualizar.setContentAreaFilled(false);
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 80, 80));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Actualizar");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 90, 30));

        id.setEditable(false);
        id.setBackground(new java.awt.Color(204, 204, 204));
        id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 30, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("ID :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 40, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/signopregunta3.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 30, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/empleado.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 290, 240));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 950, 280));

        tabla_empleados.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_empleados.setGridColor(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(tabla_empleados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 760, 250));

        ComboCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Identificador", "Usuario", "Nombre", "Correo", "Tipo" }));
        ComboCriterio.setToolTipText("Seleccionar...");
        jPanel1.add(ComboCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 430, 210, 40));

        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel1.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, 210, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 153))); // NOI18N

        btn_seleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/seleccion.png"))); // NOI18N
        btn_seleccionar.setContentAreaFilled(false);
        btn_seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionarActionPerformed(evt);
            }
        });
        jPanel4.add(btn_seleccionar);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 300, 100, 120));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eliminar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 51, 153))); // NOI18N

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/goma-de-borrar.png"))); // NOI18N
        btn_eliminar.setContentAreaFilled(false);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btn_eliminar);

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 300, 90, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1010, 560));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Transpipian Logo(2).png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 140, 120));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inicio.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, 70));

        jLabel2.setFont(new java.awt.Font("Quantify", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("USUARIOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 270, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/signopregunta2.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, 70, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo 1.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tipoKeyTyped
         char c = evt.getKeyChar();
         if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_tipoKeyTyped

    private void txt_correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyTyped
         
    }//GEN-LAST:event_txt_correoKeyTyped

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
                 Actualizar();
        
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                Login bus = new Login();
       		bus.setVisible(true);
       		this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        String buscar = txt_buscar.getText();
        Filtrado(buscar);
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void btn_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarActionPerformed
        Seleccionar();

    }//GEN-LAST:event_btn_seleccionarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        Eliminar();

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         JOptionPane.showMessageDialog(null, "Tipo 1: Administrativo"
                 + "Tipo 2: Conductor "
                 + "Tipo 3: Asesor "
                 + "Tipo 4: Sin confirmar");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CtrlUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CtrlUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CtrlUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CtrlUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CtrlUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCriterio;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_seleccionar;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_empleados;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_tipo;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
