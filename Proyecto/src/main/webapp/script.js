/*document.addEventListener("DOMContentLoaded", function() {
    const opciones = document.querySelectorAll(".opcion");
    const panelCentral = document.getElementById("panelCentral");

    opciones.forEach(opcion => {
        opcion.addEventListener("click", () => {
            const textoOpcion = opcion.textContent;
            if (textoOpcion === "Ver Afiliados") {
                // Realizar una solicitud al servidor Java para obtener los datos de la base de datos
                fetch('http://localhost:8080/datosBaseDeDatos')             //***** aqui no se cual es la ruta de la base de datos del servidor****
                    .then(response => response.json())
                    .then(data => {
                        // Generar la estructura de la tabla con los datos obtenidos
                        let tablaHtml = '<h2>Contenido</h2><table border="1"><tr><th>Colección</th><th>Datos</th></tr>';
                        data.forEach(item => {
                            tablaHtml += `<tr><td>${item.collectionName}</td><td>${item.documentsData}</td></tr>`;
                        });
                        tablaHtml += '</table>';

                        // Mostrar la tabla en el panel central
                        panelCentral.innerHTML = tablaHtml;
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            } else {
                panelCentral.innerHTML = `<h2>Contenido</h2><p>Esta es la opción: ${textoOpcion}</p>`;
            }
        });
    });
});*/

document.addEventListener("DOMContentLoaded", function() {
    cargarDatos();
});

function cargarDatos() {
    // Realizar una solicitud al servidor Java para obtener los datos de la base de datos
    fetch('http://localhost:8080/datosBaseDeDatos') // URL del endpoint de tu controlador en Java
        .then(response => response.json())
        .then(data => {
            // Crear una cadena HTML para mostrar los datos
            let html = '';

            // Iterar sobre cada colección
            data.forEach(collection => {
                html += `<h2>Colección: ${collection.collectionName}</h2>`;

                // Iterar sobre cada documento de la colección
                collection.documentsData.forEach(document => {
                    // Crear una lista de clave-valor para mostrar los campos del documento
                    html += '<ul>';
                    for (const key in document) {
                        html += `<li><b>${key}:</b> ${document[key]}</li>`;
                    }
                    html += '</ul>';
                });
            });

            // Mostrar los datos en el contenedor HTML
            document.getElementById('datos').innerHTML = html;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

