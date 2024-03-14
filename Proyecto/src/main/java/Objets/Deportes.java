package Objets;

public class Deportes {
    private int _id;
    private String nombre;
    private String modalidad;
    private int inscritos;
    private int cupos;

    public Deportes(int _id, String nombre, String modalidad, int inscritos, int cupos) {
        this._id = _id;
        this.nombre = nombre;
        this.modalidad = modalidad;
        this.inscritos = inscritos;
        this.cupos = cupos;
    }

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
}
