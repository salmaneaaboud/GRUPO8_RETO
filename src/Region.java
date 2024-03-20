import java.util.Objects;

public class Region {

    private String idRegion;
    private String nombre;
    private String descripcion;
    private String recomendacion;
    private String enemigo;

    public Region(String idRegion, String nombre, String descripcion, String recomendacion, String enemigo) {
        this.idRegion = idRegion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recomendacion = recomendacion;
        this.enemigo = enemigo;
    }

    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
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

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(String enemigo) {
        this.enemigo = enemigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Region))
            return false;
        Region region = (Region) o;
        return Objects.equals(getIdRegion(), region.getIdRegion()) && Objects.equals(getNombre(), region.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdRegion(), getNombre());
    }
}
