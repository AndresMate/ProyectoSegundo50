document.addEventListener('DOMContentLoaded', function () {
    // Variables para almacenar los afiliados
    let afiliados = [];

    // Función para mostrar el formulario para añadir afiliado
    function mostrarFormulario() {
        document.getElementById("addAfiliadoForm").style.display = "block";
    }

    // Función para ocultar el formulario para añadir afiliado
    function ocultarFormulario() {
        document.getElementById("addAfiliadoForm").style.display = "none";
    }

    // Función para guardar los afiliados en localStorage
    function guardarAfiliados() {
        localStorage.setItem('afiliados', JSON.stringify(afiliados));
    }

    // Función para cargar los afiliados desde localStorage
    function cargarAfiliados() {
        const storedAfiliados = localStorage.getItem('afiliados');
        afiliados = storedAfiliados ? JSON.parse(storedAfiliados) : [];
        mostrarDatosEnTabla(afiliados);
    }

    // Función para agregar un afiliado
    function agregarAfiliado() {
        const id = document.getElementById("idInput").value;
        const identificacion = document.getElementById("identificacionInput").value;
        const nombre = document.getElementById("nombreInput").value;
        const edad = document.getElementById("edadInput").value;
        const deporte = document.getElementById("deporteInput").value;

        // Agregar el nuevo afiliado a la lista
        afiliados.push({ id, identificacion, nombre, edad, deporte });
        guardarAfiliados();

        // Limpiar el formulario y ocultarlo
        document.getElementById("idInput").value = "";
        document.getElementById("identificacionInput").value = "";
        document.getElementById("nombreInput").value = "";
        document.getElementById("edadInput").value = "";
        document.getElementById("deporteInput").value = "";
        ocultarFormulario();

        // Actualizar la tabla de afiliados
        mostrarDatosEnTabla(afiliados);

        // Mostrar los afiliados actualizados después de agregar uno nuevo
        verAfiliados();
    }

    // Función para mostrar los datos de afiliados en una tabla
    function mostrarDatosEnTabla(afiliados) {
        const dataDiv = document.getElementById('data');
        dataDiv.innerHTML = '';

        const table = document.createElement('table');
        const headerRow = table.insertRow();
        Object.keys(afiliados[0]).forEach(key => {
            const headerCell = document.createElement('th');
            headerCell.textContent = key;
            headerRow.appendChild(headerCell);
        });

        afiliados.forEach(afiliado => {
            const row = table.insertRow();
            Object.values(afiliado).forEach(value => {
                const cell = row.insertCell();
                cell.textContent = value;
            });
        });

        dataDiv.appendChild(table);
    }

    // Event listener para el botón "Ver todos"
    document.getElementById("verTodosButton").addEventListener('click', cargarDatos);

    // Event listener para el botón "Ver afiliados"
    document.getElementById("verAfiliadosButton").addEventListener('click', verAfiliados);

    // Event listener para el botón "Ver deportes"
    document.getElementById("verDeportesButton").addEventListener('click', verDeportes);

    // Event listener para el botón "Ver eventos"
    document.getElementById("verEventosButton").addEventListener('click', verEventos);

    // Event listener para el botón "Ver resultados"
    document.getElementById("verResultadosButton").addEventListener('click', verResultados);

    // Event listener para el botón "Agregar Afiliado"
    document.getElementById("addAfiliadoButton").addEventListener("click", mostrarFormulario);

    // Event listener para el botón "Guardar"
    document.getElementById("guardarAfiliadoButton").addEventListener("click", agregarAfiliado);

    // Función para cargar y mostrar todos los datos del archivo JSON
    function cargarDatos() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => mostrarDatosEnTabla(data))
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Afiliados" en tabla
    function verAfiliados() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const afiliados = data.filter(item => item.hasOwnProperty('nombre') && item.hasOwnProperty('edad'));
                mostrarDatosEnTabla(afiliados);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Deportes" en tabla
    function verDeportes() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const deportes = data.filter(item => item.hasOwnProperty('modalidad') && item.hasOwnProperty('cupos'));
                mostrarDatosEnTabla(deportes);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Eventos" en tabla
    function verEventos() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const eventos = data.filter(item => item.hasOwnProperty('fecha') && item.hasOwnProperty('nombre'));
                mostrarDatosEnTabla(eventos);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Resultados" en tabla
    function verResultados() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const resultados = data.filter(item => item.hasOwnProperty('idEvento') && item.hasOwnProperty('idGanador') && item.hasOwnProperty('posicion'));
                mostrarDatosEnTabla(resultados);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Cargar los datos de afiliados al cargar la página
    cargarAfiliados();
});
