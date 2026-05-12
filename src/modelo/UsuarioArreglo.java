package modelo;

public class UsuarioArreglo {

    private Usuario[] usuarios;
    private int indice;

    public UsuarioArreglo(int tamaño) {
        this.usuarios = new Usuario[tamaño];
        this.indice   = 0;
    }

    public boolean add(Usuario u) {
        boolean result = false;
        if (this.indice < this.usuarios.length) {
            this.usuarios[this.indice] = u;
            this.indice++;
            result = true;
        }
        return result;
    }

    // ── Método original (sigue existiendo para compatibilidad) ────────────────
    public Usuario ingresar(String login, String contraseña) {
        Usuario result = null;
        for (int i = 0; i < this.indice; i++) {
            if (this.usuarios[i].ingresar(login, contraseña)) {
                result = this.usuarios[i];
                break;
            }
        }
        return result;
    }


    public Usuario ingresarFlex(String login, String contraseña) {
        Usuario result = null;
        for (int i = 0; i < this.indice; i++) {
            Usuario u = this.usuarios[i];

            if (u.esUsuarioLibre()) {
                // Usuario libre: solo validar login (cualquier contraseña pasa)
                if (u.getLogin().equalsIgnoreCase(login) && !u.isConectado()) {
                    u.conectarLibre();   // marca como conectado sin verificar pass
                    result = u;
                    break;
                }
            } else {
                // Usuario normal: validación estándar
                if (u.ingresar(login, contraseña)) {
                    result = u;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "ArregloUsuario\n";
        for (int i = 0; i < this.indice; i++) {
            result += this.usuarios[i] + "\n";
        }
        return result;
    }
}
