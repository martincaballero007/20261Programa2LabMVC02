
package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Pais;
import modelo.PaisArreglo;
import modelo.Region;
import vista.frmIniciar;
import vista.frmPais;

public class ControladorPaises {
    private PaisArreglo modelo;
    private frmPais vista;

    public ControladorPaises(PaisArreglo modelo, frmPais vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vista,modelo );
            }
        });
        
        this.vista.btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vista.txtNombre.getText();
                String region = vista.cboRegion.getSelectedItem().toString();
                
                System.out.println("Datos del País->"+ nombre + "-" + 
                        region+" -" + Sistema.regiones.getRegionxNombre(region) );
                if (Sistema.regiones.getRegionxNombre(region)!= null){
                    Pais p = new Pais(nombre, Sistema.regiones.getRegionxNombre(region));
                    modelo.add(p);
                    limpiarControles();
                }else {
                    JOptionPane.showMessageDialog(vista, "Verifique los datos de entrada?");
                }
            }
            
        });
        
        this.vista.btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                frmIniciar fInicio = new frmIniciar();
                Sistema.conectado.salir();
                ControladorInicio controlador = new ControladorInicio(Sistema.usuarios, fInicio);
                controlador.iniciar();

                
            }
        });
        
        this.vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int col = vista.tblPaises.getSelectedColumn();
                int fil = vista.tblPaises.getSelectedRow();
                String nombre = vista.tblPaises.getValueAt(fil, 0).toString();
                int opcion = JOptionPane.showConfirmDialog(vista, "Desea eliminar le país:" + nombre +"?",
                                            "Sistema Prueba",0,JOptionPane.OK_CANCEL_OPTION );
                if(opcion == JOptionPane.OK_OPTION){
                    Sistema.paises.delete(nombre);
                    limpiarControles();
                }
                
            }
        });
                
        
    }
    
    private void limpiarControles() {
        vista.txtNombre.setText("");
        vista.cboRegion.setSelectedIndex(-1);
        String[][] datos =  Sistema.paises.getDatos();
        String[] cabecera = Sistema.paises.getCabecera(); 
        DefaultTableModel modeloPaises = new DefaultTableModel(datos, cabecera);
        vista.tblPaises.setModel(modeloPaises);

    }
    
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        limpiarControles();
        DefaultComboBoxModel modeloRegiones  = new DefaultComboBoxModel();
        for( Region r : Sistema.regiones.getDatosCombo()){
            modeloRegiones.addElement(r);
        }
        this.vista.cboRegion.setModel(modeloRegiones);
        
    }
    
}
