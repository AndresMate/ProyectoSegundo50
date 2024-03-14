package Persistence;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Main {
    public static void main(String[] args) {
        // Reemplaza "tuBaseDeDatos" con el nombre de tu base de datos
        String dbName = "ClubDeportivo";


        // Obteniendo la base de datos
        MongoDatabase database = MongoClientConnection.getDatabase(dbName);

        // Mostrando todas las colecciones en la base de datos
        MongoIterable<String> collections = database.listCollectionNames();
        System.out.println("Colecciones en la base de datos '" + dbName + "':");
        for (String collectionName : collections) {
            System.out.println(collectionName);
        }

        // No olvides cerrar el cliente de MongoDB cuando ya no sea necesario
        MongoClientConnection.closeMongoClient();
    }
}
