
package modelo;

public class PaisArreglo {
    private Pais paises[];
    private int indice;
    private final String[] CABECERA =  {"NOMBRE","REGION"};
    

    public PaisArreglo(int tamaño) {
        this.paises = new Pais[tamaño];
        this.indice=0;
    }
    
    public boolean add( Pais o){
        boolean result = false;
        if(this.indice< this.paises.length){
            this.paises[this.indice] = o;
            this.indice++;
            result = true;
        }
        return result;       
    }
    
    @Override
    public String toString() {
        String result = "ArregloPais\n";
        for(int i=0;i< this.indice ;i++){
            result += this.paises[i] + "\n";
        }
        return result;
    }
    
    public String[][] getDatos(){
        String[][] result = new String[indice][2];
        for(int i=0; i<this.indice;i++ ){
            result[i][0]= this.paises[i].getNombre();
            result[i][1]= this.paises[i].getRegion();
        }
        return result;
    } 

    public String[] getCabecera() {
        return this.CABECERA;
    }
    
    public boolean delete(String nombre){
        boolean result = false;
        
        return result;
    }
    
    public boolean find(String nombre){
        boolean result = false;
        
        return result;
    }

    public Pais getPais(String nombre){
        Pais result = null;
        
        return result;
    }
    
    public void sort( int tipo){
    }

    private void aumentarTamanho(){
        
    }
    public boolean isVacio(){
        boolean result = false;
        
        return result;
    }
    public boolean isLleno(){
        boolean result = false;
        
        return result;
    }
    
    
}
