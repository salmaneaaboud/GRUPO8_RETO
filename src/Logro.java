import java.util.Objects;

public class Logro {
    private String idLogro;
    private String descripcion;
    private int experiencia;

    public Logro(String idLogro, String descripcion, int experiencia) {
        this.idLogro = idLogro;
        this.descripcion = descripcion;
        this.experiencia = experiencia;
    }

    public String getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(String idLogro) {
        this.idLogro = idLogro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Logro logro = (Logro) o;
        return Objects.equals(idLogro, logro.idLogro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogro);
    }

    @Override
    public String toString() {
        return "Logro{" +
                "idLogro='" + idLogro + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", experiencia=" + experiencia +
                '}';
    }
}
