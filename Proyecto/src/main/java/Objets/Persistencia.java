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
            MongoCollection<Document> collection = database.getCollection("Afiliados");

            for (Document doc : collection.find()) {
                int _id = doc.getInteger("_id");
                String nombre = doc.getString("nombre");
                String identificacion = doc.getString("identificacion");
                int edad = doc.getInteger("edad");
                int idDeporte = doc.getInteger("idDeporte");
                List<Integer> idEventos = doc.getList("idResultados", Integer.class);

                Afiliados afiliado = new Afiliados(_id, nombre, identificacion, edad, idDeporte, new ArrayList<>(idEventos));
                afiliados.add(afiliado);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        data.put("idEventos", afiliado.getIdResultados());
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