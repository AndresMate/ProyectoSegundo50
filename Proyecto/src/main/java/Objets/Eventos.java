package Objets;

import java.util.Objects;

/**
 * Clase que representa a un evento en el sistema.
 */
public class Eventos {
    private int _id;
    private String nombre;
    private String fecha;

    /**
     * Constructor de la clase Eventos.
     */
    public Eventos(int _id, String nombre, String fecha) {
        this._id = _id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    // Getters y setters con comentarios explicativos

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Métodos toString, equals y hashCode

    @Override
    public String toString() {
        return "Eventos{" +
                "_id=" + _id +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eventos eventos = (Eventos) o;
        return _id == eventos._id &&
                Objects.equals(nombre, eventos.nombre) &&
                Objects.equals(fecha, eventos.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, nombre, fecha);
    }
}