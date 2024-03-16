document.addEventListener("DOMContentLoaded", function() {
    cargarDatos();
});

function mostrarAfiliados() {
    fetch('http://localhost:8080/datosBaseDeDatos') // Asegúrate de que esta es la ruta correcta a tu servlet
        .then(response => response.json())
        .then(afiliados => {
            let contenedorAfiliados = document.getElementById('contenedorAfiliados');
            contenedorAfiliados.innerHTML = ''; // Limpiar el contenedor

            for (let afiliado of afiliados) {
                let afiliadoElement = document.createElement('p');
                afiliadoElement.textContent = afiliado.nombre + ', ' + afiliado.identificacion;
                contenedorAfiliados.appendChild(afiliadoElement);
            }
        })
        .catch(error => console.error('Error:', error));
}

function cargarDatos() {
    fetch('http://localhost:8080/datosBaseDeDatos') // Asegúrate de que esta es la ruta correcta a tu servlet
        .then(response => response.json())
        .then(data => {
            let tablaHtml = '<table border="1"><tr><th>ID</th><th>Nombre</th><th>Identificación</th><th>Edad</th><th>Deporte</th><th>Eventos</th></tr>';
            data.forEach(afiliado => {
                tablaHtml += `<tr>
                                <td>${afiliado._id}</td>
                                <td>${afiliado.nombre}</td>
                                <td>${afiliado.identificacion}</td>
                                <td>${afiliado.edad}</td>
                                <td>${afiliado.nombreDeporte}</td>
                                <td>${afiliado.nombresEventos.join(', ')}</td>
                              </tr>`;
            });
            tablaHtml += '</table>';
            document.getElementById('datos').innerHTML = tablaHtml;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}