import general.Sistema;
import controlador.ControladorInicio;
import modelo.Pais;
import modelo.Region;
import modelo.Usuario;
import vista.frmIniciar;

public class App {
    
    public static void main(String[] args){
        
        Sistema.usuarios.add( new Usuario("jzavaleta", "secreto", "jzavaletac@unmsm.edu.pe"));
        Sistema.usuarios.add( new Usuario("jperez", "123456","algo2.20202@gmail.com"));
        Sistema.usuarios.add( new Usuario("abartra", "secreto","algo2.20202@gmail.com"));
        Sistema.usuarios.add( new Usuario("fisi", "secreto","algo2.20202@gmail.com"));
        Sistema.usuarios.add( new Usuario("invitado",  "",         ""));
        
        
        Sistema.regiones.add( new Region("AMERICA DEL SUR", "AM-SUR"));
        Sistema.regiones.add( new Region("AMERICA DEL NORTE", "AM-NOR"));
        Sistema.regiones.add( new Region("EUROPA", "EU"));
        Sistema.regiones.add( new Region("ASIA", "AS"));
        Sistema.regiones.add( new Region("AFRICA", "AF"));
        Sistema.regiones.add( new Region("OCEANIA", "OC"));
        Sistema.regiones.add( new Region("ANTARTIDA", "AT"));
        
        //Sistema.paises.add(new Pais("PERÚ", Sistema.regiones.getRegionxNombre("AMERICA DEL SUR") ));
        //Sistema.paises.add(new Pais("ECUADOR", Sistema.regiones.getRegionxNombre("AMERICA DEL SUR") ));
        //Sistema.paises.add(new Pais("CHILE", Sistema.regiones.getRegionxNombre("AMERICA DEL SUR") ));
        //Sistema.paises.add(new Pais("BOLIVIA", Sistema.regiones.getRegionxNombre("AMERICA DEL SUR") ));
        
        //Sistema.paises.add(new Pais("ESPAÑA", Sistema.regiones.getRegionxNombre("EUROPA") ));
        
        //Sistema.paises.add(new Pais("CHINA", Sistema.regiones.getRegionxNombre("ASIA") ));
        
        
        
        //System.out.println(Sistema.paises);
        //System.out.println(Sistema.regiones);
        
        
        frmIniciar fInicio = new frmIniciar();
        ControladorInicio controlador = new ControladorInicio(Sistema.usuarios, fInicio);
        controlador.iniciar();
        
    }  
}
