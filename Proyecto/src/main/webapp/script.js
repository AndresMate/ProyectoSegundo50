// script.js

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

        // Ordenar los afiliados por ID antes de mostrarlos en la tabla
        afiliados.sort((a, b) => parseInt(a.id) - parseInt(b.id));

        // Actualizar la tabla de afiliados
        mostrarDatosEnTabla(afiliados);
    }

    // Función para mostrar los datos de afiliados en una tabla
    function mostrarDatosEnTabla(datos) {
        const dataDiv = document.getElementById('data');
        dataDiv.innerHTML = '';

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
            .then(data => {
                const datosOrdenados = data.sort((a, b) => parseInt(a.id) - parseInt(b.id)); // Ordenar por ID de menor a mayor
                mostrarDatosEnTabla(datosOrdenados);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Afiliados" en tabla
    function verAfiliados() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const afiliados = data.filter(item => item.hasOwnProperty('nombre') && item.hasOwnProperty('edad'));
                const afiliadosOrdenados = afiliados.sort((a, b) => parseInt(a.id) - parseInt(b.id)); // Ordenar por ID de menor a mayor
                mostrarDatosEnTabla(afiliadosOrdenados);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Deportes" en tabla
    function verDeportes() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const deportes = data.filter(item => item.hasOwnProperty('modalidad') && item.hasOwnProperty('cupos'));
                const deportesOrdenados = deportes.sort((a, b) => parseInt(a.id) - parseInt(b.id)); // Ordenar por ID de menor a mayor
                mostrarDatosEnTabla(deportesOrdenados);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Eventos" en tabla
    function verEventos() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const eventos = data.filter(item => item.hasOwnProperty('fecha') && item.hasOwnProperty('nombre'));
                const eventosOrdenados = eventos.sort((a, b) => parseInt(a.id) - parseInt(b.id)); // Ordenar por ID de menor a mayor
                mostrarDatosEnTabla(eventosOrdenados);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para filtrar y mostrar solo los datos de la colección "Resultados" en tabla
    function verResultados() {
        fetch('/src/main/java/Persistence/datos.json')
            .then(response => response.json())
            .then(data => {
                const resultados = data.filter(item => item.hasOwnProperty('idEvento') && item.hasOwnProperty('idGanador') && item.hasOwnProperty('posicion'));
                const resultadosOrdenados = resultados.sort((a, b) => parseInt(a.id) - parseInt(b.id)); // Ordenar por ID de menor a mayor
                mostrarDatosEnTabla(resultadosOrdenados);
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    }

    // Función para mostrar los últimos afiliados agregados
    function verUltimosAfiliados() {
        const ultimosAfiliados = afiliados.slice(-5); // Obtener los últimos 5 afiliados
        mostrarDatosEnTabla(ultimosAfiliados);
    }

    // Event listener para el botón "Ver Últimos Afiliados"
    document.getElementById("verUltimosAfiliadosButton").addEventListener('click', verUltimosAfiliados);

    // Event listener para el botón "Borrar Afiliado"
    document.getElementById("borrarAfiliadoButton").addEventListener('click', () => {
        const id = prompt("Ingrese el ID del afiliado que desea borrar:");
        const index = afiliados.findIndex(afiliado => afiliado.id === id);
        if (index !== -1) {
            const deletedAfiliado = afiliados[index];
            afiliados.splice(index, 1);
            guardarAfiliados();
            mostrarDatosEnTabla(afiliados);
            alert(`Afiliado borrado exitosamente:\nID: ${deletedAfiliado.id}\nIdentificación: ${deletedAfiliado.identificacion}\nNombre: ${deletedAfiliado.nombre}\nEdad: ${deletedAfiliado.edad}\nDeporte: ${deletedAfiliado.deporte}`);
        } else {
            alert("No se encontró ningún afiliado con ese ID.");
        }
    });

    // Cargar los datos de afiliados al cargar la página
    cargarAfiliados();
});
