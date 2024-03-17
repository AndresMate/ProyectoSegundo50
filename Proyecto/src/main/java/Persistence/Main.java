

/*import com.mongodb.client.MongoClient;
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
}*/


/*package Persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Conexión a MongoDB
        String connectionString = "mongodb+srv://ADMIN:Andres&Dumar@cluster0.5apyxiy.mongodb.net/";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Obtiene la base de datos "ClubDeportivo"
            MongoDatabase database = mongoClient.getDatabase("ClubDeportivo");

            // Crear un StringBuilder para construir el contenido HTML
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Datos de la Base de Datos MongoDB</title>\n</head>\n<body>\n<h1>Datos de la Base de Datos MongoDB</h1>\n");

            // Obtiene todas las colecciones de la base de datos
            MongoIterable<String> collectionNames = database.listCollectionNames();

            // Itera sobre cada colección
            for (String collectionName : collectionNames) {
                htmlContent.append("<h2>Colección: ").append(collectionName).append("</h2>\n");

                // Formulario de filtro por ID
                htmlContent.append("<form method=\"get\" action=\"\">\n");
                htmlContent.append("Filtrar por ID: <input type=\"text\" name=\"id\">\n");
                htmlContent.append("<input type=\"submit\" value=\"Buscar\">\n");
                htmlContent.append("</form>\n");

                // Filtrado de documentos según el ID enviado en el formulario
                String idFilter = null;
                String query = System.getProperty("sun.java.command");
                if (query != null) {
                    String[] params = query.split(" ");
                    for (String param : params) {
                        if (param.startsWith("id=")) {
                            idFilter = param.substring(3);
                            break;
                        }
                    }
                }

                // Comienza la tabla
                htmlContent.append("<table border=\"1\">\n<tr>");

                // Obtiene los nombres de los campos del primer documento para usarlos como encabezados de la tabla
                MongoIterable<Document> documents = database.getCollection(collectionName).find();
                Document firstDocument = documents.first();
                if (firstDocument != null) {
                    for (String key : firstDocument.keySet()) {
                        htmlContent.append("<th>").append(key).append("</th>");
                    }
                    htmlContent.append("</tr>\n");

                    // Itera sobre cada documento e imprime sus datos en una fila de la tabla
                    for (Document document : documents) {
                        // Filtrar por id si se especifica en el formulario
                        if (idFilter == null || document.get("id").toString().equals(idFilter)) {
                            htmlContent.append("<tr>");
                            for (String key : document.keySet()) {
                                htmlContent.append("<td>").append(document.get(key)).append("</td>");
                            }
                            htmlContent.append("</tr>\n");
                        }
                    }
                } else {
                    htmlContent.append("</tr>\n");
                }

                // Finaliza la tabla
                htmlContent.append("</table>\n");
            }

            htmlContent.append("</body>\n</html>");

            // Escribir el contenido HTML en un archivo
            try (FileWriter fileWriter = new FileWriter("datos.html")) {
                fileWriter.write(htmlContent.toString());
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo HTML: " + e.getMessage());
            }

            System.out.println("Archivo HTML generado con éxito.");
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos MongoDB: " + e.getMessage());
        }
    }
}*/

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
