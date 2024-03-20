import java.util.Objects;

public class Mision {

    private String idMision;
    private String descripcion;
    private String recompensa;
    private String dificultad;

    public Mision(String idMision, String descripcion, String recompensa, String dificultad) {
        this.idMision = idMision;
        this.descripcion = descripcion;
        this.recompensa = recompensa;
        this.dificultad = dificultad;
    }

    public String getIdMision() {
        return idMision;
    }

    public void setIdMision(String idMision) {
        this.idMision = idMision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Mision))
            return false;
        Mision mision = (Mision) o;
        return Objects.equals(getIdMision(), mision.getIdMision()) && Objects.equals(getDescripcion(), mision.getDescripcion()) && Objects.equals(getRecompensa(), mision.getRecompensa()) && Objects.equals(getDificultad(), mision.getDificultad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMision(), getDescripcion(), getRecompensa(), getDificultad());
    }

    @Override
    public String toString() {
        return "Mision{" +
                "idMision='" + idMision + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", recompensa='" + recompensa + '\'' +
                ", dificultad='" + dificultad + '\'' +
                '}';
    }
}
