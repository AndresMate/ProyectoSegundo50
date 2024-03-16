package Servelet;

import Objets.Afiliados;
import Objets.Persistencia;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la clase Persistencia
        Persistencia persistencia = new Persistencia();

        // Obtener los datos de los afiliados de la base de datos
        List<Afiliados> afiliados = persistencia.obtenerAfiliados();

        // Imprimir los afiliados obtenidos
        for (Afiliados afiliado : afiliados) {
            System.out.println(afiliado);
        }
    }
}