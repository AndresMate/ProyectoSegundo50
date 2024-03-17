document.addEventListener("DOMContentLoaded", function() {
    // Realizar una solicitud HTTP para obtener los datos de la base de datos
    fetch('/datos')
        .then(response => response.json())
        .then(data => {
            // Crear una tabla HTML y agregar los datos
            const tablaDatos = document.getElementById('tablaDatos');

            data.forEach(collectionData => {
                const encabezado = document.createElement('h2');
                encabezado.textContent = `Colección: ${collectionData.collection}`;
                tablaDatos.appendChild(encabezado);

                const tablaColeccion = document.createElement('table');
                tablaColeccion.border = 1;

                const encabezados = Object.keys(collectionData.documents[0]);
                const encabezadoRow = document.createElement('tr');
                encabezados.forEach(header => {
                    const th = document.createElement('th');
                    th.textContent = header;
                    encabezadoRow.appendChild(th);
                });
                tablaColeccion.appendChild(encabezadoRow);

                collectionData.documents.forEach(document => {
                    const row = document.createElement('tr');
                    encabezados.forEach(header => {
                        const cell = document.createElement('td');
                        cell.textContent = document[header];
                        row.appendChild(cell);
                    });
                    tablaColeccion.appendChild(row);
                });

                tablaDatos.appendChild(tablaColeccion);
            });
        })
        .catch(error => console.error('Error al obtener los datos:', error));
});
