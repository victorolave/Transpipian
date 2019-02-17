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
public class VehiculosPreferencial extends javax.swing.JFrame {
    
   Connection con = null;
   Statement stmt = null;   
   String titulos[] = {"id","Marca","Placa","Capacidad","Modelo"};
   String fila[] = new String [5];
   String filtro="id";
   DefaultTableModel modelo;

   public void MostrarTabla(){
       try {
            
            String url = "jdbc:mysql://localhost:3306/transpipian";
            String usuario = "root";
            String contraseña = "030913";  
            
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               con = DriverManager.getConnection(url,usuario,contraseña);
               if (con!= null)
                   System.out.println("Se ha establecido una conexion a la base de datos"+"\n"+url);
               
               stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery("select* from vehiculospreferencial");
               
               modelo = new DefaultTableModel(null,titulos);
            
               while(rs.next()) {
                   
                   fila[0] = rs.getString("id");
                   fila[1] = rs.getString("marca");
                   fila[2] = rs.getString("placa");
                   fila[3] = rs.getString("capacidad");
                   fila[4] = rs.getString("modelo");                   
                   
                   modelo.addRow(fila);     
               }
               tabla_vehiculos.setModel(modelo);
                TableColumn ci = tabla_vehiculos.getColumn("id");
                ci.setMaxWidth(40);
                TableColumn cm = tabla_vehiculos.getColumn("Marca");
                cm.setMaxWidth(165);
                TableColumn cp = tabla_vehiculos.getColumn("Placa");
                cp.setMaxWidth(160);
                TableColumn cc = tabla_vehiculos.getColumn("Capacidad");
                cc.setMaxWidth(90);
                TableColumn cmod = tabla_vehiculos.getColumn("Modelo");
                cmod.setMaxWidth(165);
                
               
        }
        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
        }
       
   }
   public void Registrar(){
        
        String cadena2,cadena3,cadena4,cadena5;
        
        cadena2 = txt_marca.getText();
        cadena3 = txt_placa.getText();
        cadena4 = txt_capacidad.getText().toString();
        cadena5 = txt_modelo.getText().toString();       
        
           if (txt_marca.getText().equals("") || (txt_placa.getText().equals("")) || (txt_capacidad.getText().equals("")) || (txt_modelo.getText().equals(""))) 
           {
            
            javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar todos los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txt_marca.requestFocus();
        }
           
           else {
        try {
           
            String url = "jdbc:mysql://localhost:3306/transpipian";
            String usuario = "root";
            String contraseña = "030913";
            
             Class.forName("com.mysql.jdbc.Driver").newInstance(); 
             con = DriverManager.getConnection(url,usuario,contraseña); 
             if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  stmt.executeUpdate("INSERT INTO vehiculospreferencial VALUES('" + 0 + "','"+cadena2+"','"+cadena3+"','"+cadena4+"','"+cadena5+"')");
                  System.out.println("Los valores han sido agregados a la base de datos ");
                 
                   
        } catch (InstantiationException ex) {
           Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        finally {
            if (con != null) {
                try {
                    con.close();
                    stmt.close();
                } catch ( Exception e ) { 
                         System.out.println( e.getMessage());
                }
            }
        }
         javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        this.txt_marca.setText("");
        this.txt_placa.setText("");
        this.txt_capacidad.setText("");
        this.txt_modelo.setText("");     
        MostrarTabla();
   }
   public void Seleccionar(){
        int fila = tabla_vehiculos.getSelectedRow();
        if(fila>=0)
        {
            id.setText(tabla_vehiculos.getValueAt(fila, 0).toString());
            txt_marca.setText(tabla_vehiculos.getValueAt(fila, 1).toString());
            txt_placa.setText(tabla_vehiculos.getValueAt(fila, 2).toString());
            txt_capacidad.setText(tabla_vehiculos.getValueAt(fila, 3).toString());
            txt_modelo.setText(tabla_vehiculos.getValueAt(fila, 4).toString());          
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
   }
   public void Actualizar() {
        
        String cadena1,cadena2,cadena3,cadena4,cadena5,cadena6,cadena7;
     
       cadena1 = id.getText();
       cadena2 = txt_marca.getText();
       cadena3 = txt_placa.getText();
       cadena4 = txt_capacidad.getText();
       cadena5 = txt_modelo.getText();      
     
    
     if (txt_marca.getText().equals("")) {
         
         javax.swing.JOptionPane.showMessageDialog(this,"1-. Seleccione el empleado \n 2-. Actualice el dato deseado en el campo correspondiente","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
     }
     else {   
     
     try { 
                  String url = "jdbc:mysql://localhost:3306/transpipian"; 
                  String usuario = "root"; 
                  String contraseña = "030913"; 
                  
                  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
                  con = DriverManager.getConnection(url,usuario,contraseña); 
                  if ( con != null ) 
                    System.out.println("Se ha establecido una conexión a la base de datos " +  
                                       "\n " + url ); 
  
                  stmt = con.createStatement(); 
                  stmt.executeUpdate("update ignore vehiculospreferencial set id= '"+cadena1+"' , marca = '"+cadena2+"',placa = '"+cadena3+"',capacidad = '"+cadena4+"' where id = '"+id.getText()+"' || marca = '"+txt_marca.getText()+"' || placa = '"+txt_placa.getText()+"' || capacidad = '"+txt_capacidad.getText()+"' || modelo = '"+txt_modelo.getText()+"'"); 

                  System.out.println("Los valores han sido Actualizados"); 
                  } 
                  catch( SQLException e ) { 
                      e.printStackTrace(); 
                  } catch (ClassNotFoundException ex) { 
            Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
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
        this.txt_marca.setText("");
        this.txt_placa.setText("");
        this.txt_capacidad.setText("");
        this.txt_modelo.setText("");        
        MostrarTabla();
    }
   public void Eliminar(){
     int fila = tabla_vehiculos.getSelectedRow();
  
       
       if(fila>=0)
       {      
           String valor = tabla_vehiculos.getValueAt(fila, 0).toString();
            try {       
                PreparedStatement pps = con.prepareStatement("DELETE FROM vehiculospreferencial WHERE id='"+valor+"'");
                pps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(VehiculosPreferencial.class.getName()).log(Level.SEVERE, null, ex);
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
           if(ComboCriterio.getSelectedItem()== "Marca")
           {
               filtro="marca";
           }
           if(ComboCriterio.getSelectedItem()== "Placa")
           {
               filtro="placa";
           }
           if(ComboCriterio.getSelectedItem()== "Capacidad")
           {
               filtro="capacidad";
           }
           if(ComboCriterio.getSelectedItem()== "Modelo")
           {
               filtro="modelo";
           }         
           
           
           String sql = "select *from vehiculospreferencial where "+filtro+" like '%"+texto+"%'";          
           modelo = new DefaultTableModel(null,titulos);
           stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery(sql);          
           while (rs.next())
           {
                   fila[0] = rs.getString("id");
                   fila[1] = rs.getString("marca");
                   fila[2] = rs.getString("placa");
                   fila[3] = rs.getString("capacidad");
                   fila[4] = rs.getString("modelo");                                  
                   modelo.addRow(fila);                 
           }
           tabla_vehiculos.setModel(modelo);
           rs.close();
           stmt.close();
                    
       } catch (Exception e) {
           System.err.println(""+e.getMessage());
       }
   }

    
    public VehiculosPreferencial() {
        initComponents();
        this.setTitle("Vehiculos Preferencial");
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        txt_modelo = new javax.swing.JTextField();
        txt_capacidad = new javax.swing.JTextField();
        txt_placa = new javax.swing.JTextField();
        btn_registrar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_vehiculos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_seleccionar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn_eliminar = new javax.swing.JButton();
        ComboCriterio = new javax.swing.JComboBox<>();
        txt_buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(844, 750));
        setPreferredSize(new java.awt.Dimension(844, 750));
        setSize(new java.awt.Dimension(844, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(174, 39, 23), 3), "Nuevo Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(174, 39, 23))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Actualizar");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 80, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Placa :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, 33));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Capacidad:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, 33));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Modelo :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, 33));

        txt_marca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txt_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 200, 30));

        txt_modelo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_modelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_modeloKeyTyped(evt);
            }
        });
        jPanel2.add(txt_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 200, 30));

        txt_capacidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_capacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_capacidadKeyTyped(evt);
            }
        });
        jPanel2.add(txt_capacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 200, 30));

        txt_placa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txt_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 200, 30));

        btn_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btn_registrar.setContentAreaFilled(false);
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 70, -1));

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_actualizar.setContentAreaFilled(false);
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 70, 70));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("ID :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 40, 30));

        id.setEditable(false);
        id.setBackground(new java.awt.Color(204, 204, 204));
        id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 30, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Marca :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Registrar");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 70, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/coche-deportivo.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 290, 230));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 780, 280));

        tabla_vehiculos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla_vehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_vehiculos.setGridColor(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(tabla_vehiculos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 560, 250));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(174, 39, 23))); // NOI18N

        btn_seleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/seleccion.png"))); // NOI18N
        btn_seleccionar.setContentAreaFilled(false);
        btn_seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionarActionPerformed(evt);
            }
        });
        jPanel4.add(btn_seleccionar);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 100, 120));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eliminar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(174, 39, 23))); // NOI18N

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/goma-de-borrar.png"))); // NOI18N
        btn_eliminar.setContentAreaFilled(false);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btn_eliminar);

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 90, 120));

        ComboCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Identificador", "Marca", "Placa", "Capacidad", "Modelo", " " }));
        ComboCriterio.setToolTipText("Seleccionar...");
        jPanel1.add(ComboCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, 200, 40));

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel1.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 480, 200, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 810, 560));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Transpipian Logo(2).png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 140, 120));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inicio.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 70));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/signopregunta2.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 70, 70));

        jLabel2.setFont(new java.awt.Font("Quantify", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("VIP");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 90, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo 3.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_modeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_modeloKeyTyped
         char c = evt.getKeyChar();
         if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_modeloKeyTyped

    private void txt_capacidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_capacidadKeyTyped
         char c = evt.getKeyChar();
         if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_capacidadKeyTyped

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        Registrar();
       
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
                 Actualizar();
        
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                Login bus = new Login();
       		bus.setVisible(true);
       		this.dispose(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarActionPerformed
        Seleccionar();

    }//GEN-LAST:event_btn_seleccionarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        Eliminar();

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        String buscar = txt_buscar.getText();
        Filtrado(buscar);
    }//GEN-LAST:event_txt_buscarKeyReleased

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
            java.util.logging.Logger.getLogger(VehiculosPreferencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VehiculosPreferencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VehiculosPreferencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VehiculosPreferencial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VehiculosPreferencial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCriterio;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JButton btn_seleccionar;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_vehiculos;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_capacidad;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JTextField txt_placa;
    // End of variables declaration//GEN-END:variables
}
