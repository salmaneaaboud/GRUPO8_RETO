public class Jugador {
    private String idJugador;
    private String rango;
    private int nivel;
    private String nombre;
    private String clave;
    private String email;

    public Jugador(String idJugador, String rango, int nivel, String nombre, String clave, String email) {
        this.idJugador = idJugador;
        this.rango = rango;
        this.nivel = nivel;
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
