package Objets;

import java.util.Objects;

/**
 * Clase que representa a un resultado en el sistema.
 */
public class Resultados {
    private int _id;
    private int idAfiliado;
    private int idEvento;
    private int posicion;

    /**
     * Constructor de la clase Resultados.
     */
    public Resultados(int _id, int idAfiliado, int idEvento, int posicion) {
        this._id = _id;
        this.idAfiliado = idAfiliado;
        this.idEvento = idEvento;
        this.posicion = posicion;
    }

    // Getters y setters con comentarios explicativos

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    // Métodos toString, equals y hashCode

    @Override
    public String toString() {
        return "Resultados{" +
                "_id=" + _id +
                ", idAfiliado=" + idAfiliado +
                ", idEvento=" + idEvento +
                ", posicion=" + posicion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resultados resultados = (Resultados) o;
        return _id == resultados._id &&
                idAfiliado == resultados.idAfiliado &&
                idEvento == resultados.idEvento &&
                posicion == resultados.posicion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, idAfiliado, idEvento, posicion);
    }
}