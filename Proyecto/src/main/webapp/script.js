// archivo.js

// Función para cargar y mostrar los datos del archivo JSON
function cargarDatos() {
    fetch('/src/main/java/Persistence/datos.json')
        .then(response => response.json())
        .then(data => {
            // Obtener el elemento DIV donde se mostrarán los datos
            const dataDiv = document.getElementById('data');

            // Crear una lista para mostrar los datos
            const ul = document.createElement('ul');

            // Iterar sobre los datos y crear elementos de lista
            data.forEach(item => {
                const li = document.createElement('li');
                li.textContent = JSON.stringify(item);
                ul.appendChild(li);
            });

            // Agregar la lista al elemento DIV
            dataDiv.appendChild(ul);
        })
        .catch(error => console.error('Error al cargar los datos:', error));
}

// Función para filtrar y mostrar solo los datos de la colección "Afiliados"
function verAfiliados() {
    fetch('/src/main/java/Persistence/datos.json')
        .then(response => response.json())
        .then(data => {
            // Filtrar datos para mostrar solo la colección de afiliados
            const afiliados = data.filter(item => item.hasOwnProperty('nombre') && item.hasOwnProperty('edad'));

            // Mostrar los datos filtrados
            mostrarDatos(afiliados);
        })
        .catch(error => console.error('Error al cargar los datos:', error));
}

// Función para filtrar y mostrar solo los datos de la colección "Deportes"
function verDeportes() {
    fetch('/src/main/java/Persistence/datos.json')
        .then(response => response.json())
        .then(data => {
            // Filtrar datos para mostrar solo la colección de deportes
            const deportes = data.filter(item => item.hasOwnProperty('modalidad') && item.hasOwnProperty('cupos'));

            // Mostrar los datos filtrados
            mostrarDatos(deportes);
        })
        .catch(error => console.error('Error al cargar los datos:', error));
}

// Función para filtrar y mostrar solo los datos de la colección "Eventos"
function verEventos() {
    fetch('/src/main/java/Persistence/datos.json')
        .then(response => response.json())
        .then(data => {
            // Filtrar datos para mostrar solo la colección de eventos
            const eventos = data.filter(item => item.hasOwnProperty('fecha') && item.hasOwnProperty('nombre'));

            // Mostrar los datos filtrados
            mostrarDatos(eventos);
        })
        .catch(error => console.error('Error al cargar los datos:', error));
}

// Función para filtrar y mostrar solo los datos de la colección "Resultados"
function verResultados() {
    fetch('/src/main/java/Persistence/datos.json')
        .then(response => response.json())
        .then(data => {
            // Filtrar datos para mostrar solo la colección de resultados
            const resultados = data.filter(item => item.hasOwnProperty('idEvento') && item.hasOwnProperty('idGanador') && item.hasOwnProperty('posicion'));

            // Mostrar los datos filtrados
            mostrarDatos(resultados);
        })
        .catch(error => console.error('Error al cargar los datos:', error));
}

// Función para mostrar los datos en el elemento DIV
function mostrarDatos(datos) {
    // Limpiar el contenido actual del elemento DIV
    const dataDiv = document.getElementById('data');
    dataDiv.innerHTML = '';

    // Crear una lista para mostrar los datos
    const ul = document.createElement('ul');

    // Iterar sobre los datos y crear elementos de lista
    datos.forEach(item => {
        const li = document.createElement('li');
        li.textContent = JSON.stringify(item);
        ul.appendChild(li);
    });

    // Agregar la lista al elemento DIV
    dataDiv.appendChild(ul);
}

// Obtener los botones por su ID
const verAfiliadosButton = document.getElementById('verAfiliadosButton');
const verDeportesButton = document.getElementById('verDeportesButton');
const verEventosButton = document.getElementById('verEventosButton');
const verResultadosButton = document.getElementById('verResultadosButton');

// Agregar eventos de clic a los botones
verAfiliadosButton.addEventListener('click', verAfiliados);
verDeportesButton.addEventListener('click', verDeportes);
verEventosButton.addEventListener('click', verEventos);
verResultadosButton.addEventListener('click', verResultados);

// Llamar a la función para cargar todos los datos cuando se cargue la página
cargarDatos();
