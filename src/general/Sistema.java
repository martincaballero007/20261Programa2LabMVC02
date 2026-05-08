package general;


import modelo.PaisArreglo;
import modelo.RegionArreglo;
import modelo.Usuario;
import modelo.UsuarioArreglo;


public class Sistema {
     public static UsuarioArreglo usuarios = new UsuarioArreglo(10);
     public static Usuario  conectado = null;
     public static RegionArreglo regiones = new RegionArreglo(10);
     public static PaisArreglo paises = new PaisArreglo(10);
     
     
}
