package Persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        // Conexión a MongoDB
        String connectionString = "mongodb+srv://ADMIN:Andres&Dumar@cluster0.5apyxiy.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Obtiene la base de datos "ClubDeportivo"
            MongoDatabase database = mongoClient.getDatabase("ClubDeportivo");

            // Obtiene todas las colecciones de la base de datos
            MongoIterable<String> collectionNames = database.listCollectionNames();

            // Itera sobre cada colección
            for (String collectionName : collectionNames) {
                System.out.println("Colección: " + collectionName);

                // Obtiene todos los documentos de la colección actual
                MongoIterable<Document> documents = database.getCollection(collectionName).find();

                // Itera sobre cada documento e imprime sus datos
                for (Document document : documents) {
                    // Itera sobre cada campo del documento
                    for (String key : document.keySet()) {
                        System.out.println(key + ": " + document.get(key));
                    }
                    System.out.println(); // Agrega un salto de línea entre documentos
                }
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos MongoDB: " + e.getMessage());
        }
    }
}