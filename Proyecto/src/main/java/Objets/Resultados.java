package Objets;

public class Resultados {
    private int _id;
    private int idAfiliado;
    private int idEvento;
    private int posicion;

    public Resultados(int _id, int idAfiliado, int idEvento, int posicion) {
        this._id = _id;
        this.idAfiliado = idAfiliado;
        this.idEvento = idEvento;
        this.posicion = posicion;
    }

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
}
