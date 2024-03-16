document.addEventListener("DOMContentLoaded", function() {
    const opciones = document.querySelectorAll(".opcion");
    const panelCentral = document.getElementById("panelCentral");

    opciones.forEach(opcion => {
        opcion.addEventListener("click", () => {
            const textoOpcion = opcion.textContent;
            if (textoOpcion === "Ver Afiliados") {
                // Realizar una solicitud al servidor Java para obtener los datos de la base de datos
                fetch('/datosBaseDeDatos')             //***** aqui no se cual es la ruta de la base de datos del servidor****
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
});
