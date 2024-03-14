package Persistence;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoClientConnection {
    private static MongoClient mongoClient = null;

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            // usar una forma segura de manejar la cadena de conexión.
            String connectionString = "mongodb+srv://ADMIN:Andres&Dumar@cluster0.5apyxiy.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            try {
                mongoClient = MongoClients.create(connectionString);
            } catch (Exception e) {
                // Manejo de excepciones adecuado
                e.printStackTrace();
            }
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = getMongoClient();
        }
        return mongoClient.getDatabase(dbName);
    }

    // Asegúrate de cerrar el cliente de MongoDB cuando ya no sea necesario
    public static void closeMongoClient() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

}

