package Objets;

import java.util.Objects;

/**
 * Clase que representa a un deporte en el sistema.
 */
public class Deportes {
    private int _id;
    private String nombre;
    private String modalidad;
    private int inscritos;
    private int cupos;

    /**
     * Constructor de la clase Deportes.
     */
    public Deportes(int _id, String nombre, String modalidad, int inscritos, int cupos) {
        this._id = _id;
        this.nombre = nombre;
        this.modalidad = modalidad;
        this.inscritos = inscritos;
        this.cupos = cupos;
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

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    // Métodos toString, equals y hashCode

    @Override
    public String toString() {
        return "Deportes{" +
                "_id=" + _id +
                ", nombre='" + nombre + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", inscritos=" + inscritos +
                ", cupos=" + cupos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deportes deportes = (Deportes) o;
        return _id == deportes._id &&
                inscritos == deportes.inscritos &&
                cupos == deportes.cupos &&
                Objects.equals(nombre, deportes.nombre) &&
                Objects.equals(modalidad, deportes.modalidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, nombre, modalidad, inscritos, cupos);
    }
}