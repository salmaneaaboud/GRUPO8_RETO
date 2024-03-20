import java.util.Objects;

public class Personaje {

    private String idPersonaje;
    private String nombre;
    private int mana;
    private int salud;
    private int velocidad;
    private int fuerza;
    private int nivel;
    private int puntosExperiencia;
    private int puntosValor;
    private String monedas;

    public Personaje(String idPersonaje, String nombre, int mana, int salud, int velocidad, int fuerza, int nivel, int puntosExperiencia, int puntosValor, String monedas) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.mana = mana;
        this.salud = salud;
        this.velocidad = velocidad;
        this.fuerza = fuerza;
        this.nivel = nivel;
        this.puntosExperiencia = puntosExperiencia;
        this.puntosValor = puntosValor;
        this.monedas = monedas;
    }

    public String getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(String idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPuntosExperiencia() {
        return puntosExperiencia;
    }

    public void setPuntosExperiencia(int puntosExperiencia) {
        this.puntosExperiencia = puntosExperiencia;
    }

    public int getPuntosValor() {
        return puntosValor;
    }

    public void setPuntosValor(int puntosValor) {
        this.puntosValor = puntosValor;
    }

    public String getMonedas() {
        return monedas;
    }

    public void setMonedas(String monedas) {
        this.monedas = monedas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Personaje))
            return false;
        Personaje personaje = (Personaje) o;
        return Objects.equals(getIdPersonaje(), personaje.getIdPersonaje());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersonaje());
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "idPersonaje='" + idPersonaje + '\'' +
                ", nombre='" + nombre + '\'' +
                ", mana=" + mana +
                ", salud=" + salud +
                ", velocidad=" + velocidad +
                ", fuerza=" + fuerza +
                ", nivel=" + nivel +
                ", puntosExperiencia=" + puntosExperiencia +
                ", puntosValor=" + puntosValor +
                ", monedas='" + monedas + '\'' +
                '}';
    }
}
