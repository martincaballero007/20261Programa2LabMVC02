
package modelo;

public class RegionArreglo {
    Region regiones[];
    int indice;

    public RegionArreglo(int tamaño) {
        this.regiones = new Region[tamaño];
        this.indice =0;
    }
    
    public boolean add( Region o){
        boolean result = false;
        if(this.indice< this.regiones.length){
            this.regiones[this.indice] = o;
            this.indice++;
            result = true;
        }
        return result;       
    }
    
    public Region getRegionxNombre( String nombre){
        Region result = null;
        for(int i=0; i < this.indice; i++){
            if(this.regiones[i].getNombre().equalsIgnoreCase(nombre)){
                result =  this.regiones[i] ;
                break;
            }    
        }
        return result;       
    }
        
    @Override
    public String toString() {
        String result = "ArregloRegion\n";
        for(int i=0;i< this.indice ;i++){
            result += this.regiones[i] + "\n";
        }
        return result;
    }
    
    public Region[] getDatosCombo(){
        Region result[] = new Region[this.indice];
        for(int i=0; i< this.indice; i++ ){
            result[i] = this.regiones[i];
        }
        return result; 
    }
    
}
