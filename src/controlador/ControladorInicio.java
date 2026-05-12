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
                String loginIngresado = vista.txtUsuario.getText().trim();
                String passIngresada  = new String(vista.txtContraseña.getPassword());
                Sistema.conectado = modelo.ingresarFlex(loginIngresado, passIngresada);
                
                 if (Sistema.conectado != null ){
                    
                    vista.dispose();
                    frmPais fPais = new frmPais();
                    ControladorPaises controlador = 
                                new ControladorPaises(Sistema.paises, fPais);
                    controlador.iniciar();
                 }else {
                     JOptionPane.showMessageDialog(vista,
                            "Error de credenciales.\n" +
                            "Si desea acceso libre, use el usuario: invitado");
                     
                     
                 }
            }
        });
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
}
