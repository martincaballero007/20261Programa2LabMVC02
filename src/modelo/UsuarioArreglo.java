
package modelo;

public class UsuarioArreglo {
    private Usuario[] usuarios;
    private int indice;

    public UsuarioArreglo(int tamaño) {
        this.usuarios = new Usuario[tamaño];
        this.indice = 0;
    }
    
    public boolean add( Usuario u){
        boolean result = false;
        if(this.indice< this.usuarios.length){
            this.usuarios[this.indice] = u;
            this.indice++;
            result = true;
        }
        return result;       
    }
    
    public Usuario ingresar( String login, String contraseña){
        Usuario result = null;
        for(int i=0;i< this.indice ;i++){
            if ( this.usuarios[i].ingresar(login, contraseña) ){
                result = this.usuarios[i];
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "ArregloUsuario\n";
        for(int i=0;i< this.indice ;i++){
            result += this.usuarios[i] + "\n";
        }
        return result;
    }
    
}
