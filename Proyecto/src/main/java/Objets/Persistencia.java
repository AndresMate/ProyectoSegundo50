package Objets;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persistencia {
    private static final String connectionString = "mongodb+srv://ADMIN:Andres&Dumar@cluster0.5apyxiy.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    public List<Afiliados> obtenerAfiliados() {
        List<Afiliados> afiliados = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("ClubDeportivo");
            MongoCollection<Document> collectionAfiliados = database.getCollection("Afiliados");
            MongoCollection<Document> collectionDeportes = database.getCollection("Deportes");
            MongoCollection<Document> collectionEventos = database.getCollection("Eventos");
            MongoCollection<Document> collectionResultados = database.getCollection("Resultados");

            for (Document doc : collectionAfiliados.find()) {
                int _id = doc.getInteger("_id");
                String nombre = doc.getString("nombre");
                String identificacion = doc.getString("identificacion");
                int edad = doc.getInteger("edad");
                int idDeporte = doc.getInteger("idDeporte");
                List<String> idEventos = (List<String>) doc.get("idEventos");

                // Obtener el nombre del deporte correspondiente al ID
                Document docDeporte = collectionDeportes.find(new Document("_id", idDeporte)).first();
                String nombreDeporte = docDeporte != null ? docDeporte.getString("nombre") : null;

                // Obtener los nombres de los eventos y la posición que obtuvo el afiliado
                List<String> nombresEventos = new ArrayList<>();
                if (idEventos != null) {
                for (String idEvento : idEventos) {
                    Document docEvento = collectionEventos.find(new Document("_id", idEvento)).first();
                    String nombreEvento = docEvento != null ? docEvento.getString("nombre") : null;

                    Document docResultado = collectionResultados.find(new Document("idAfiliado", _id).append("idEvento", idEvento)).first();
                    int posicion = docResultado != null ? docResultado.getInteger("posicion") : -1;

                    nombresEventos.add(nombreEvento + " (Posición: " + posicion + ")");
                }}

                Afiliados afiliado = new Afiliados(_id, nombre, identificacion, edad, idDeporte, idEventos);
                afiliados.add(afiliado);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener los afiliados de la base de datos MongoDB: " + e.getMessage());
        }

        return afiliados;
    }



    public void guardarAfiliado(Afiliados afiliado) {
        Map<String, Object> data = new HashMap<>();
        data.put("_id", afiliado.get_id());
        data.put("nombre", afiliado.getNombre());
        data.put("identificacion", afiliado.getIdentificacion());
        data.put("edad", afiliado.getEdad());
        data.put("idDeporte", afiliado.getIdDeporte());
        data.put("idEventos", afiliado.getIdEventos());
        guardar("Afiliados", data);
    }

    public void guardarDeporte(Deportes deporte) {
        Map<String, Object> data = new HashMap<>();
        data.put("_id", deporte.get_id());
        data.put("nombre", deporte.getNombre());
        data.put("modalidad", deporte.getModalidad());
        data.put("inscritos", deporte.getInscritos());
        data.put("cupos", deporte.getCupos());
        guardar("Deportes", data);
    }

    public void guardarEvento(Eventos evento) {
        Map<String, Object> data = new HashMap<>();
        data.put("_id", evento.get_id());
        data.put("nombre", evento.getNombre());
        data.put("fecha", evento.getFecha());
        guardar("Eventos", data);
    }

    public void guardarResultado(Resultados resultado) {
        Map<String, Object> data = new HashMap<>();
        data.put("_id", resultado.get_id());
        data.put("idAfiliado", resultado.getIdAfiliado());
        data.put("idEvento", resultado.getIdEvento());
        data.put("posicion", resultado.getPosicion());
        guardar("Resultados", data);
    }

    private void guardar(String collectionName, Map<String, Object> data) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("ClubDeportivo");
            Document document = new Document(data);
            database.getCollection(collectionName).insertOne(document);
        } catch (Exception e) {
            System.err.println("Error al guardar los datos en la base de datos MongoDB: " + e.getMessage());
        }
    }

    // Otros métodos de la clase Persistencia...
}