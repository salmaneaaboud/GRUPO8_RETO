import java.util.Objects;

public class Objeto {

    private String idObjeto;
    private String nombre;
    private String descripcion;
    private int precio;

    public Objeto(String idObjeto, String nombre, String descripcion, int precio) {
        this.idObjeto = idObjeto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Objeto))
            return false;
        Objeto objeto = (Objeto) o;
        return Objects.equals(getIdObjeto(), objeto.getIdObjeto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdObjeto());
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "idObjeto='" + idObjeto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
