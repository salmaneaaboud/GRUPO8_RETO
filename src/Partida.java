import java.util.Date;
import java.util.Objects;

public class Partida {

    private String idPartida;
    private Date fechaIni;
    private int duracion;

    public Partida(String idPartida, Date fechaIni, int duracion) {
        this.idPartida = idPartida;
        this.fechaIni = fechaIni;
        this.duracion = duracion;
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Partida))
            return false;
        Partida partida = (Partida) o;
        return Objects.equals(getIdPartida(), partida.getIdPartida());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPartida());
    }

    @Override
    public String toString() {
        return "Partida{" +
                "idPartida='" + idPartida + '\'' +
                ", fechaIni=" + fechaIni +
                ", duracion=" + duracion +
                '}';
    }
}
