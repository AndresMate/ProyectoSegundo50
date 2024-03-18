
package Persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Conexión a MongoDB
        String connectionString = "mongodb+srv://ADMIN:Andres&Dumar@cluster0.5apyxiy.mongodb.net/";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Obtiene la base de datos "ClubDeportivo"
            MongoDatabase database = mongoClient.getDatabase("ClubDeportivo");

            // Lista para almacenar los datos en formato JSON
            List<String> jsonData = new ArrayList<>();

            // Obtiene todas las colecciones de la base de datos
            MongoIterable<String> collectionNames = database.listCollectionNames();

            // Itera sobre cada colección
            for (String collectionName : collectionNames) {
                // Obtiene los documentos de la colección
                MongoIterable<Document> documents = database.getCollection(collectionName).find();
                // Convierte cada documento a su representación JSON y lo agrega a la lista
                for (Document document : documents) {
                    jsonData.add(document.toJson());
                }
            }

            // Escribir los datos en un archivo JSON
            try (FileWriter fileWriter = new FileWriter("datos.json")) {
                fileWriter.write("[\n");
                for (int i = 0; i < jsonData.size(); i++) {
                    fileWriter.write(jsonData.get(i));
                    // Agregar una coma si no es el último objeto
                    if (i < jsonData.size() - 1) {
                        fileWriter.write(",\n");
                    }
                }
                fileWriter.write("\n]");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo JSON: " + e.getMessage());
            }

            System.out.println("Archivo JSON generado con éxito.");
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos MongoDB: " + e.getMessage());
        }
    }
}
