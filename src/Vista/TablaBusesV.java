/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Victor Olave
 */
public class TablaBusesV extends javax.swing.JFrame {
   
   Connection con = null;
   Statement stmt = null;
   String titulos[] = {"id","Marca","Placa","Capacidad","Modelo"};
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
               ResultSet rs = stmt.executeQuery("select* from vehiculosbuses");
               
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
           
           
           String sql = "select *from vehiculosbuses where "+filtro+" like '%"+texto+"%'";          
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
           rs.close();
           stmt.close();
                    
       } catch (Exception e) {
           System.err.println(""+e.getMessage());
       }
   }
    
    public TablaBusesV() {
        initComponents();
        this.setTitle("Buses");
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

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_vehiculos = new javax.swing.JTable();
        txt_buscar = new javax.swing.JTextField();
        ComboCriterio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(671, 630));
        setMinimumSize(new java.awt.Dimension(671, 630));
        setPreferredSize(new java.awt.Dimension(630, 630));
        setSize(new java.awt.Dimension(671, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Transpipian Logo(2).png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 120));

        jLabel2.setFont(new java.awt.Font("Quantify", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BUSES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 170, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/signopregunta2.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 70, 70));

        tabla_vehiculos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabla_vehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_vehiculos.setGridColor(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(tabla_vehiculos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 620, 400));

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 550, 210, 40));

        ComboCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Identificador", "Marca", "Placa", "Capacidad", "Modelo", " " }));
        ComboCriterio.setToolTipText("Seleccionar...");
        getContentPane().add(ComboCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 200, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo 1.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(706, 609));
        jLabel1.setMinimumSize(new java.awt.Dimension(706, 609));
        jLabel1.setPreferredSize(new java.awt.Dimension(706, 609));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TablaBusesV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaBusesV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaBusesV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaBusesV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablaBusesV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCriterio;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_vehiculos;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
