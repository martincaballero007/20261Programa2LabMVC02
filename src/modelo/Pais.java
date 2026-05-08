
package modelo;

public class Pais {
    private String nombre;
    private Region region;

    public Pais(String nombre, Region region) {
        this.nombre = nombre;
        this.region = region;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRegion() {
        return region.getNombre();
    }

    @Override
    public String toString() {
        return  "nombre=" + nombre + ", region=" + getRegion()  +" {"+ region +"}" ;
    }
    
    
    
    
    
}
