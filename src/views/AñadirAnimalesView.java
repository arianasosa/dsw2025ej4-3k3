/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import data.Persistencia;
import domain.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author prisc
 */
public class AñadirAnimalesView extends javax.swing.JFrame {

    /**
     * Creates new form AñadirAnimalesView
     */
    public AñadirAnimalesView() {
        initComponents();
        esconderComp();
        cargarEspeciesEnCombo();
        cargarPaisesEnCombo();
        
        //comboBoxEspecies.addActionListener(comboBoxPaises);
        BoxEspecies.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombreSeleccionado = (String) BoxEspecies.getSelectedItem();
            Especie especieSeleccionada = null;

            // Buscar la especie según el nombre
            for (Especie esp : Persistencia.getEspecies()) {
                if (esp.getNombre().equals(nombreSeleccionado)) {
                    especieSeleccionada = esp;
                    break;
                }
            }

            // Mostrar el tipo de alimentación
            if (especieSeleccionada != null) {
                tipoAlim.setText(especieSeleccionada.getTipoAlimentacion().toString());
                cargarSectoresEnCombo(especieSeleccionada.getTipoAlimentacion());
                
            }
        }
        });
        
        
    }
    
    private void cargarEspeciesEnCombo(){
        for (Especie e : Persistencia.getEspecies()) {
            BoxEspecies.addItem(e.getNombre());
        }
    }

       
    private void cargarPaisesEnCombo(){
        for (Pais p : Persistencia.getPaises()) {
            comboPaises.addItem(p.getNombre());
        }
    }
    
    private void cargarSectoresEnCombo(TipoAlimentacion tipo){
        comboSectores.removeAllItems();
        esconderComp();
        if (tipo == TipoAlimentacion.HERBIVORO) {
           comboSectores.addItem(Integer.toString(1));
            comboSectores.addItem(Integer.toString(3));
            tfValorFijo.setVisible(true);
            labelValorFijo.setVisible(true);
        } else if (tipo == TipoAlimentacion.CARNIVORO) {
            comboSectores.addItem(Integer.toString(2));
            comboSectores.addItem(Integer.toString(4));
            tfPorcentaje.setVisible(true);
            labelPorcentaje.setVisible(true);
        }            
    }
    
    private void esconderComp(){
        labelCompletar.setVisible(false);
        labelPorcentaje.setVisible(false);
        labelValorFijo.setVisible(false);
        tfPorcentaje.setVisible(false);
        tfValorFijo.setVisible(false);
    }
    
    private void guardarAnimal() {
    try {
        int edad = Integer.parseInt(tfEdad.getText());
        double peso = Double.parseDouble(tfPeso.getText());

        String nombreEspecie = (String) BoxEspecies.getSelectedItem();
        Especie especieSeleccionada = Persistencia.getEspecies().stream()
            .filter(e -> e.getNombre().equals(nombreEspecie))
            .findFirst()
            .orElseThrow();
        
        String numeroSectorStr = (String) comboSectores.getSelectedItem();
        int numeroSector = Integer.parseInt(numeroSectorStr);
        
        //Buscar el objeto Sector original segun el numero:
        Sector sectorSeleccionado = Persistencia.getSectores().stream()
                .filter(s -> s.getNumero()== numeroSector)
                .findFirst()
                .orElseThrow();
     
        String nombrePais = (String) comboPaises.getSelectedItem();
        Pais paisSeleccionado = Persistencia.getPaises().stream()
            .filter(p -> p.getNombre().equals(nombrePais))
            .findFirst()
            .orElseThrow();
        
        //Sector sectorSeleccionado = (Sector) comboBoxSectores.getSelectedItem();
        //Pais paisSeleccionado = (Pais) comboBoxPaises.getSelectedItem();

        Mamifero nuevo;

        if (especieSeleccionada.getTipoAlimentacion() == TipoAlimentacion.HERBIVORO) {
            double valorFijo = Double.parseDouble(tfValorFijo.getText());
            nuevo = new Herbivoro(edad, peso, especieSeleccionada, sectorSeleccionado, valorFijo, paisSeleccionado);
            Persistencia.agregarAnimal(nuevo);
        } else if(especieSeleccionada.getTipoAlimentacion() == TipoAlimentacion.CARNIVORO){
            nuevo = new Carnivoro(edad, peso, especieSeleccionada, sectorSeleccionado, paisSeleccionado);
            Persistencia.agregarAnimal(nuevo);
        }

        JOptionPane.showMessageDialog(this, "Animal agregado exitosamente.");
        
        System.out.println("Guardando..");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al ingresar los datos: " + e.getMessage());
    }
}

    private void limpiarCampos() {
        tfEdad.setText("");
        tfPeso.setText("");
        tfValorFijo.setText(""); 
        tfPorcentaje.setText("");
        BoxEspecies.setSelectedIndex(0);
        comboSectores.setSelectedIndex(0);
        comboPaises.setSelectedIndex(0);
        esconderComp();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonVolverMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tipoAlim = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelValorFijo = new javax.swing.JLabel();
        labelPorcentaje = new javax.swing.JLabel();
        BoxEspecies = new javax.swing.JComboBox<>();
        comboPaises = new javax.swing.JComboBox<>();
        comboSectores = new javax.swing.JComboBox<>();
        buttonAgregar = new javax.swing.JButton();
        tfEdad = new javax.swing.JTextField();
        tfPeso = new javax.swing.JTextField();
        tfPorcentaje = new javax.swing.JTextField();
        tfValorFijo = new javax.swing.JTextField();
        labelCompletar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonVolverMenu.setText("Volver al Menú");
        botonVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel1.setText("Especie");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Edad");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Peso");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Pais");

        tipoAlim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tipoAlim.setText("Tipo de Alimentación");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Sector");

        labelValorFijo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelValorFijo.setText("Valor Fijo");

        labelPorcentaje.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPorcentaje.setText("Porcentaje");

        BoxEspecies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxEspeciesActionPerformed(evt);
            }
        });

        comboPaises.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        comboPaises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPaisesActionPerformed(evt);
            }
        });

        comboSectores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSectoresActionPerformed(evt);
            }
        });

        buttonAgregar.setText("AGREGAR");
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarActionPerformed(evt);
            }
        });

        tfEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEdadActionPerformed(evt);
            }
        });

        tfPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesoActionPerformed(evt);
            }
        });

        tfPorcentaje.setText("0");
        tfPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPorcentajeActionPerformed(evt);
            }
        });

        tfValorFijo.setText("0");
        tfValorFijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValorFijoActionPerformed(evt);
            }
        });

        labelCompletar.setText("Campos Incompletos!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelPorcentaje)
                        .addGap(260, 260, 260))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelValorFijo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tipoAlim)
                                        .addGap(55, 55, 55)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboPaises, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(comboSectores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tfEdad)
                                            .addComponent(tfPeso)
                                            .addComponent(BoxEspecies, 0, 105, Short.MAX_VALUE))
                                        .addGap(126, 126, 126)
                                        .addComponent(tfPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(100, 100, 100)))
                        .addComponent(tfValorFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonVolverMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAgregar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCompletar)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BoxEspecies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPorcentaje)
                    .addComponent(tfPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorFijo)
                    .addComponent(tfValorFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoAlim)
                    .addComponent(jLabel6)
                    .addComponent(comboSectores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPaises, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCompletar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonVolverMenu)
                    .addComponent(buttonAgregar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverMenuActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botonVolverMenuActionPerformed

    private void BoxEspeciesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxEspeciesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxEspeciesActionPerformed

    private void comboPaisesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPaisesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPaisesActionPerformed

    private void comboSectoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSectoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSectoresActionPerformed

    private void buttonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarActionPerformed
        guardarAnimal();
        limpiarCampos();
    }//GEN-LAST:event_buttonAgregarActionPerformed

    private void tfEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEdadActionPerformed

    private void tfPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesoActionPerformed

    private void tfPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPorcentajeActionPerformed

    private void tfValorFijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValorFijoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValorFijoActionPerformed

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
            java.util.logging.Logger.getLogger(AñadirAnimalesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AñadirAnimalesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AñadirAnimalesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AñadirAnimalesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AñadirAnimalesView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxEspecies;
    private javax.swing.JButton botonVolverMenu;
    private javax.swing.JButton buttonAgregar;
    private javax.swing.JComboBox<String> comboPaises;
    private javax.swing.JComboBox<String> comboSectores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel labelCompletar;
    private javax.swing.JLabel labelPorcentaje;
    private javax.swing.JLabel labelValorFijo;
    private javax.swing.JTextField tfEdad;
    private javax.swing.JTextField tfPeso;
    private javax.swing.JTextField tfPorcentaje;
    private javax.swing.JTextField tfValorFijo;
    private javax.swing.JLabel tipoAlim;
    // End of variables declaration//GEN-END:variables
}
