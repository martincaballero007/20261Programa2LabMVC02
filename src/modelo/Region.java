
package modelo;


public class Region {
    private String nombre;
    private String abreviatura;

    public Region(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    @Override
    public String toString() {
        return  nombre ;
    }
    
    
}
