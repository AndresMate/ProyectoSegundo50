document.addEventListener("DOMContentLoaded", function() {
    const opciones = document.querySelectorAll(".opcion");
    const panelCentral = document.getElementById("panelCentral");

    opciones.forEach(opcion => {
        opcion.addEventListener("click", () => {
            const textoOpcion = opcion.textContent;
            if (textoOpcion === "Ver Afiliados") {
                panelCentral.innerHTML = `
                    <h2>Contenido</h2>
                    <label for="filtrarPor">Filtrar por:</label>
                    <select id="filtrarPor">
                        <option value="cedula">Filtrar por cédula</option>
                        <option value="nombre">Filtrar por nombre</option>
                        <option value="disciplina">Filtrar por disciplina</option>
                    </select>
                    <br><br>
                    <button id="buscarBtn">Buscar</button>
                    <div id="tablaResultados"></div>
                `;

                // Agregar event listener al botón de buscar
                const buscarBtn = document.getElementById("buscarBtn");
                buscarBtn.addEventListener("click", () => {
                    const buscarTexto = document.getElementById("buscar").value;
                    const filtrarPor = document.getElementById("filtrarPor").value;

                    // Generar la estructura de la tabla con columnas vacías
                    const tablaResultados = document.getElementById("tablaResultados");
                    tablaResultados.innerHTML = `
                        <table border="1">
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Edad</th>
                                <th>Deporte</th>
                                <th>Resultado</th>
                            </tr>
                        </table>
                    `;

                    // Realizar la lógica de búsqueda o filtro aquí
                    // Hacer una solicitud al servidor para obtener los datos de los afiliados
                    fetch('/afiliados')
                        .then(response => response.json())
                        .then(data => {
                            // Llenar la tabla si hay datos disponibles
                            const tabla = tablaResultados.querySelector("table");
                            if (data.length > 0) {
                                tabla.innerHTML += data.map(afiliado => `
                                    <tr>
                                        <td>${afiliado.id}</td>
                                        <td>${afiliado.nombre}</td>
                                        <td>${afiliado.edad}</td>
                                        <td>${afiliado.deporte}</td>
                                        <td>${afiliado.resultado}</td>
                                    </tr>
                                `).join('');
                            } else {
                                // Si no hay datos, mostrar una fila vacía
                                tabla.innerHTML += `
                                    <tr>
                                        <td colspan="5">No se encontraron resultados</td>
                                    </tr>
                                `;
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                });
            } else {
                panelCentral.innerHTML = `<h2>Contenido</h2><p>Esta es la opción: ${textoOpcion}</p>`;
            }
        });
    });
});
