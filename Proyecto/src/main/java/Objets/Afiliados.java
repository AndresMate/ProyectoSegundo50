package Objets;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa a un afiliado en el sistema.
 */
public class Afiliados {
    private int _id;
    private String nombre;
    private String identificacion;
    private int edad;
    private int idDeporte;
    private List<String> idEventos;

    /**
     * Constructor de la clase Afiliados.
     */
    public Afiliados(int _id, String nombre, String identificacion, int edad, int idDeporte, List<String> idEventos) {
        this._id = _id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.idDeporte = idDeporte;
        this.idEventos = idEventos;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(int idDeporte) {
        this.idDeporte = idDeporte;
    }

    public ArrayList<String> getIdEventos() {
        return (ArrayList<String>) idEventos;
    }

    public void setIdEventos(ArrayList<String> idEventos) {
        this.idEventos = idEventos;
    }

    // Métodos toString, equals y hashCode

    @Override
    public String toString() {
        return "Afiliados{" +
                "_id=" + _id +
                ", nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", edad=" + edad +
                ", idDeporte=" + idDeporte +
                ", idEventos=" + idEventos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Afiliados afiliados = (Afiliados) o;
        return _id == afiliados._id &&
                edad == afiliados.edad &&
                idDeporte == afiliados.idDeporte &&
                Objects.equals(nombre, afiliados.nombre) &&
                Objects.equals(identificacion, afiliados.identificacion) &&
                Objects.equals(idEventos, afiliados.idEventos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, nombre, identificacion, edad, idDeporte, idEventos);
    }
}