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
public class EmpleadosBuses extends javax.swing.JFrame {
    
   Connection con = null;
   Statement stmt = null;   
   String titulos[] = {"id","Nombre","Apellido","Edad","Documento","Telefono","Tipo"};
   String fila[] = new String [7];
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
               ResultSet rs = stmt.executeQuery("select* from empleadosbuses");
               
               modelo = new DefaultTableModel(null,titulos);
            
               while(rs.next()) {
                   
                   fila[0] = rs.getString("id");
                   fila[1] = rs.getString("nombre");
                   fila[2] = rs.getString("apellido");
                   fila[3] = rs.getString("edad");
                   fila[4] = rs.getString("documento");
                   fila[5] = rs.getString("telefono");
                   fila[6] = rs.getString("tipo");
                   
                   modelo.addRow(fila);     
               }
               tabla_empleados.setModel(modelo);
                TableColumn ci = tabla_empleados.getColumn("id");
                ci.setMaxWidth(40);
                TableColumn cn = tabla_empleados.getColumn("Nombre");
                cn.setMaxWidth(165);
                TableColumn ca = tabla_empleados.getColumn("Apellido");
                ca.setMaxWidth(160);
                TableColumn ct = tabla_empleados.getColumn("Edad");
                ct.setMaxWidth(90);
                TableColumn cd = tabla_empleados.getColumn("Documento");
                cd.setMaxWidth(165);
                TableColumn cp = tabla_empleados.getColumn("Telefono");
                cp.setMaxWidth(165);
                TableColumn ctipo = tabla_empleados.getColumn("Tipo");
                ctipo.setMaxWidth(165);
               
        }
        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
        }
       
   }
   public void Registrar(){
        
        String cadena2,cadena3,cadena4,cadena5,cadena6,cadena7;
        
        cadena2 = txt_nombre.getText();
        cadena3 = txt_apellido.getText();
        cadena4 = txt_edad.getText().toString();
        cadena5 = txt_documento.getText().toString();
        cadena6 = txt_telefono.getText().toString();
        cadena7 = cmb_tipo.getSelectedItem().toString();
        
           if (txt_nombre.getText().equals("") || (txt_apellido.getText().equals("")) || (txt_edad.getText().equals("")) || (txt_documento.getText().equals(""))
           || (txt_telefono.getText().equals("")) || (cmb_tipo.getSelectedItem().equals(null))) {
            
            javax.swing.JOptionPane.showMessageDialog(this,"Debe llenar todos los campos \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txt_nombre.requestFocus();
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
                  stmt.executeUpdate("INSERT INTO empleadosbuses VALUES('" + 0 + "','"+cadena2+"','"+cadena3+"','"+cadena4+"','"+cadena5+"','"+cadena6+"','"+cadena7+"')");
                  System.out.println("Los valores han sido agregados a la base de datos ");
                 
                   
        } catch (InstantiationException ex) {
           Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
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
        this.txt_nombre.setText("");
        this.txt_apellido.setText("");
        this.txt_edad.setText("");
        this.txt_documento.setText("");
        this.txt_telefono.setText("");
        MostrarTabla();
   }
   public void Seleccionar(){
        int fila = tabla_empleados.getSelectedRow();
        if(fila>=0)
        {
            id.setText(tabla_empleados.getValueAt(fila, 0).toString());
            txt_nombre.setText(tabla_empleados.getValueAt(fila, 1).toString());
            txt_apellido.setText(tabla_empleados.getValueAt(fila, 2).toString());
            txt_edad.setText(tabla_empleados.getValueAt(fila, 3).toString());
            txt_documento.setText(tabla_empleados.getValueAt(fila, 4).toString());
            txt_telefono.setText(tabla_empleados.getValueAt(fila, 5).toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
   }
   public void Actualizar() {
        
        String cadena1,cadena2,cadena3,cadena4,cadena5,cadena6,cadena7;
     
       cadena1 = id.getText();
       cadena2 = txt_nombre.getText();
       cadena3 = txt_apellido.getText();
       cadena4 = txt_edad.getText();
       cadena5 = txt_documento.getText();
       cadena6 = txt_telefono.getText();
       cadena7 = cmb_tipo.getSelectedItem().toString();
     
    
     if (txt_nombre.getText().equals("")) {
         
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
                  stmt.executeUpdate("update ignore empleadosbuses set id= '"+cadena1+"' , nombre = '"+cadena2+"',apellido = '"+cadena3+"',edad = '"+cadena4+"', documento = '"+cadena5+"', telefono = '"+cadena6+"', tipo = '"+cadena7+"' where id = '"+id.getText()+"' || nombre = '"+txt_nombre.getText()+"' || apellido = '"+txt_apellido.getText()+"' || edad = '"+txt_edad.getText()+"' || documento = '"+txt_documento.getText()+"' || telefono = '"+txt_telefono.getText()+"' || tipo = '"+cmb_tipo.getSelectedItem()+"'"); 

                  System.out.println("Los valores han sido Actualizados"); 
                  } 
                  catch( SQLException e ) { 
                      e.printStackTrace(); 
                  } catch (ClassNotFoundException ex) { 
            Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
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
        this.txt_nombre.setText("");
        this.txt_apellido.setText("");
        this.txt_edad.setText("");
        this.txt_documento.setText("");
        this.txt_telefono.setText("");
        MostrarTabla();
    }
   public void Eliminar(){
     int fila = tabla_empleados.getSelectedRow();
  
       
       if(fila>=0)
       {      
           String valor = tabla_empleados.getValueAt(fila, 0).toString();
            try {       
                PreparedStatement pps = con.prepareStatement("DELETE FROM empleadosbuses WHERE id='"+valor+"'");
                pps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadosBuses.class.getName()).log(Level.SEVERE, null, ex);
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
           if(ComboCriterio.getSelectedItem()== "Nombre")
           {
               filtro="nombre";
           }
           if(ComboCriterio.getSelectedItem()== "Apellido")
           {
               filtro="apellido";
           }
           if(ComboCriterio.getSelectedItem().toString()== "Edad")
           {
               filtro="edad";
           }
           if(ComboCriterio.getSelectedItem()== "Documento")
           {
               filtro="documento";
           }
           if(ComboCriterio.getSelectedItem()== "Telefono")
           {
               filtro="telefono";
           }
           if(ComboCriterio.getSelectedItem()== "Tipo")
           {
               filtro="tipo";
           }
           
           
           String sql = "select *from empleadosbuses where "+filtro+" like '%"+texto+"%'";          
           modelo = new DefaultTableModel(null,titulos);
           stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery(sql);          
           while (rs.next())
           {
                   fila[0] = rs.getString("id");
                   fila[1] = rs.getString("nombre");
                   fila[2] = rs.getString("apellido");
                   fila[3] = rs.getString("edad");
                   fila[4] = rs.getString("documento");
                   fila[5] = rs.getString("telefono");
                   fila[6] = rs.getString("tipo");                  
                   modelo.addRow(fila);                 
           }
           tabla_empleados.setModel(modelo);
                TableColumn ci = tabla_empleados.getColumn("id");
                ci.setMaxWidth(40);
                TableColumn cn = tabla_empleados.getColumn("Nombre");
                cn.setMaxWidth(165);
                TableColumn ca = tabla_empleados.getColumn("Apellido");
                ca.setMaxWidth(160);
                TableColumn ct = tabla_empleados.getColumn("Edad");
                ct.setMaxWidth(90);
                TableColumn cd = tabla_empleados.getColumn("Documento");
                cd.setMaxWidth(165);
                TableColumn cp = tabla_empleados.getColumn("Telefono");
                cp.setMaxWidth(165);
                TableColumn ctipo = tabla_empleados.getColumn("Tipo");
                ctipo.setMaxWidth(165);
           rs.close();
           stmt.close();
                    
       } catch (Exception e) {
           System.err.println(""+e.getMessage());
       }
   }
    
    public EmpleadosBuses() {
        initComponents();
        this.setTitle("Empleados Buses");
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();
        txt_nombre = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_documento = new javax.swing.JTextField();
        txt_edad = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        btn_registrar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_empleados = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        ComboCriterio = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btn_seleccionar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn_eliminar = new javax.swing.JButton();
        txt_buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1210, 845));
        setMinimumSize(new java.awt.Dimension(1210, 845));
        setPreferredSize(new java.awt.Dimension(1210, 845));
        setSize(new java.awt.Dimension(1210, 845));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204), 3), "Nuevo Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Tipo :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, -1, 33));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nombre : ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 80, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Apellido :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, 33));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Edad :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, -1, 33));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Documento :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, 33));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Telefono :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, -1, 33));

        cmb_tipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\tAdministrativo", "\tAsesor", "\tConductor ", "\tMecánico", " " }));
        jPanel2.add(cmb_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 200, 30));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 200, 30));

        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });
        jPanel2.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 200, 30));

        txt_documento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_documentoKeyTyped(evt);
            }
        });
        jPanel2.add(txt_documento, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 200, 30));

        txt_edad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_edad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_edadKeyTyped(evt);
            }
        });
        jPanel2.add(txt_edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 200, 30));

        txt_apellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 200, 30));

        btn_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btn_registrar.setContentAreaFilled(false);
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 90, 80));

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btn_actualizar.setContentAreaFilled(false);
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 70, 70));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("ID :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 40, 30));

        id.setEditable(false);
        id.setBackground(new java.awt.Color(204, 204, 204));
        id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 30, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("GUARDAR");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 70, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("ACTUALIZAR");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 240, 100, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/autobus1.png"))); // NOI18N
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 260, 300));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1130, 360));

        tabla_empleados.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_empleados.setGridColor(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(tabla_empleados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 920, 250));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("ACTUALIZAR");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, 100, 30));

        ComboCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Identificador", "Nombre", "Apellido", "Edad", "Documento", "Telefono", "Tipo" }));
        ComboCriterio.setToolTipText("Seleccionar...");
        jPanel1.add(ComboCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 520, 210, 40));

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

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 380, 100, 120));

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

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 380, 90, 120));

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel1.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 570, 210, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1170, 640));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Transpipian Logo(2).png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 140, 120));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inicio.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 70, 70));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/signopregunta2.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 60, 70, 70));

        jLabel2.setFont(new java.awt.Font("Quantify", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BUSES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 170, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo 1.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
         char c = evt.getKeyChar();
         if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_telefonoKeyTyped

    private void txt_documentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_documentoKeyTyped
         char c = evt.getKeyChar();
         if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_documentoKeyTyped

    private void txt_edadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_edadKeyTyped
         char c = evt.getKeyChar();
         if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_edadKeyTyped

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        Registrar();
       
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarActionPerformed
        Seleccionar();
            
    }//GEN-LAST:event_btn_seleccionarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
                 Actualizar();
        
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
                 Eliminar();
       
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                Login bus = new Login();
       		bus.setVisible(true);
       		this.dispose(); 
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(EmpleadosBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpleadosBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpleadosBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpleadosBuses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmpleadosBuses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCriterio;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JButton btn_seleccionar;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTable tabla_empleados;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_documento;
    private javax.swing.JTextField txt_edad;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
