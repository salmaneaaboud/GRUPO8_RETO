import java.util.ArrayList;
import java.util.Objects;

public class Gremio {

    private String idGremio;
    private String nombre;
    private String tipo;
    private int lider;
    private ArrayList<String> raids;

    public Gremio(String idGremio, String nombre, String tipo, int lider, ArrayList<String> raids) {
        this.idGremio = idGremio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.lider = lider;
        this.raids = raids;
    }

    public String getIdGremio() {
        return idGremio;
    }

    public void setIdGremio(String idGremio) {
        this.idGremio = idGremio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLider() {
        return lider;
    }

    public void setLider(int lider) {
        this.lider = lider;
    }

    public ArrayList<String> getRaids() {
        return raids;
    }

    public void setRaids(ArrayList<String> raids) {
        this.raids = raids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Gremio))
            return false;
        Gremio gremio = (Gremio) o;
        return Objects.equals(getIdGremio(), gremio.getIdGremio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGremio());
    }

    @Override
    public String toString() {
        return "Gremio{" +
                "idGremio='" + idGremio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", lider=" + lider +
                ", raids=" + raids +
                '}';
    }
}
