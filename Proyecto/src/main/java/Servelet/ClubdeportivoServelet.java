package Servelet;

import com.google.gson.Gson;
import Objets.Afiliados;
import Objets.Persistencia;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/datosBaseDeDatos")
public class ClubdeportivoServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear una instancia de la clase Persistencia
        Persistencia persistencia = new Persistencia();

        // Obtener los datos de los afiliados de la base de datos
        List<Afiliados> afiliados = persistencia.obtenerAfiliados();

        // Convertir los datos de los afiliados a formato JSON
        Gson gson = new Gson();
        String json = gson.toJson(afiliados);

        // Escribir el JSON en la respuesta HTTP
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí puedes procesar la solicitud POST
    }
}