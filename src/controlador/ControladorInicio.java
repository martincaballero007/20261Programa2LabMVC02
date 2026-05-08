package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioArreglo;
import vista.frmIniciar;
import vista.frmPais;


public class ControladorInicio {
    private UsuarioArreglo modelo;
    private frmIniciar vista;

    public ControladorInicio(UsuarioArreglo modelo, frmIniciar vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.btnSalir.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    
        this.vista.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sistema.conectado = modelo.ingresar(vista.txtUsuario.getText() , vista.txtContraseña.getText());
                 if (Sistema.conectado != null ){
                    
                    vista.dispose();
                    frmPais fPais = new frmPais();
                    ControladorPaises controlador = 
                                new ControladorPaises(Sistema.paises, fPais);
                    controlador.iniciar();
                 }else {
                     JOptionPane.showMessageDialog(vista, "Error de credenciales");
                     
                     
                 }
            }
        });
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
}
