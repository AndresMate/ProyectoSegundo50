// archivo.js

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

// Función para mostrar los datos en una tabla ordenados por el campo "_id"
function mostrarDatosEnTabla(datos) {
    const dataDiv = document.getElementById('data');
    dataDiv.innerHTML = '';

    // Ordenar los datos por el campo "_id"
    datos.sort((a, b) => a._id - b._id);

    const table = document.createElement('table');
    const headerRow = table.insertRow();
    Object.keys(datos[0]).forEach(key => {
        const headerCell = document.createElement('th');
        headerCell.textContent = key;
        headerRow.appendChild(headerCell);
    });

    datos.forEach(item => {
        const row = table.insertRow();
        Object.values(item).forEach(value => {
            const cell = row.insertCell();
            cell.textContent = value;
        });
    });

    dataDiv.appendChild(table);
}

// Obtener los botones por su ID
const verTodosButton = document.getElementById('verTodosButton');
const verAfiliadosButton = document.getElementById('verAfiliadosButton');
const verDeportesButton = document.getElementById('verDeportesButton');
const verEventosButton = document.getElementById('verEventosButton');
const verResultadosButton = document.getElementById('verResultadosButton');

// Agregar eventos de clic a los botones
verTodosButton.addEventListener('click', cargarDatos);
verAfiliadosButton.addEventListener('click', verAfiliados);
verDeportesButton.addEventListener('click', verDeportes);
verEventosButton.addEventListener('click', verEventos);
verResultadosButton.addEventListener('click', verResultados);
