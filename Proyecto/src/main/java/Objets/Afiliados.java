package Objets;

import java.util.ArrayList;

public class Afiliados {
    private int _id;
    private String nombre;
    private String identificacion;
    private int edad;
    private int idDeporte;
    private ArrayList<Integer> idEventos;

    public Afiliados(int _id, String nombre, String identificacion, int edad, int idDeporte, ArrayList<Integer> idEventos) {
        this._id = _id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.idDeporte = idDeporte;
        this.idEventos = idEventos;
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

    public ArrayList<Integer> getIdEventos() {
        return idEventos;
    }

    public void setIdEventos(ArrayList<Integer> idEventos) {
        this.idEventos = idEventos;
    }
}
