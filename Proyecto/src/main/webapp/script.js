document.addEventListener("DOMContentLoaded", function() {
    const opciones = document.querySelectorAll(".opcion");
    const panelCentral = document.getElementById("panelCentral");

    opciones.forEach(opcion => {
        opcion.addEventListener("click", () => {
            const textoOpcion = opcion.textContent;
            if (textoOpcion === "Ver Afiliados") {
                panelCentral.innerHTML = `
                    <h2>Contenido</h2>
                    <br><br>
                    <label for="filtrarPor">Filtrar por:</label>
                    <select id="filtrarPor">
                        <option value="cedula">Filtrar por cédula</option>
                        <option value="nombre">Filtrar por nombre</option>
                        <option value="disciplina">Filtrar por disciplina</option>
                    </select>
                    <br><br>
                    <button id="buscarBtn">Buscar</button>
                `;

                // Agregar event listener al botón de buscar
                const buscarBtn = document.getElementById("buscarBtn");
                buscarBtn.addEventListener("click", () => {
                    const buscarTexto = document.getElementById("buscar").value;
                    const filtrarPor = document.getElementById("filtrarPor").value;

                    // Realizar la lógica de búsqueda o filtro aquí
                    console.log("Texto a buscar:", buscarTexto);
                    console.log("Filtrar por:", filtrarPor);
                });
            } else {
                panelCentral.innerHTML = `<h2>Contenido</h2><p>Esta es la opción: ${textoOpcion}</p>`;
            }
        });
    });
});
