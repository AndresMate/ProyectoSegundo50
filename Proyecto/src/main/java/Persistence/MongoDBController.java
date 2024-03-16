package Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MongoDBController {
    //***** aqui no se cual es la ruta de la base de datos del servidor****
    @GetMapping("mongodb+srv://ADMIN:Andres&Dumar@cluster0.5apyxiy.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")
    public List<Map<String, Object>> getDatabaseData() {
        List<Map<String, Object>> databaseData = new ArrayList<>();

        // Conexión a MongoDB
        String connectionString = "mongodb+srv://ADMIN:Andres&Dumar@cluster0.5apyxiy.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Obtiene la base de datos "ClubDeportivo"
            MongoDatabase database = mongoClient.getDatabase("ClubDeportivo");

            // Obtiene todas las colecciones de la base de datos
            MongoIterable<String> collectionNames = database.listCollectionNames();

            // Itera sobre cada colección
            for (String collectionName : collectionNames) {
                Map<String, Object> collectionData = new HashMap<>();
                collectionData.put("collectionName", collectionName);

                List<Document> documentsData = new ArrayList<>();
                // Obtiene todos los documentos de la colección actual
                MongoIterable<Document> documents = database.getCollection(collectionName).find();
                // Itera sobre cada documento
                for (Document document : documents) {
                    documentsData.add(document);
                }

                collectionData.put("documentsData", documentsData);
                databaseData.add(collectionData);
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos MongoDB: " + e.getMessage());
        }

        return databaseData;
    }
}

