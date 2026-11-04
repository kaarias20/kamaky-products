// Funciones generales que usan todas las vistas

document.addEventListener('DOMContentLoaded', function () {

    // Oculta los mensajes tipo toast después de 5 segundos
    setTimeout(function () {
        document.querySelectorAll('.toast').forEach(function (toast) {
            toast.classList.remove('show');
        });
    }, 5000);

    // Pasa el id y la descripción del registro al modal de eliminar (fragmento modalEliminar)
    const modalEliminar = document.getElementById('modalEliminar');
    if (modalEliminar) {
        modalEliminar.addEventListener('show.bs.modal', function (event) {
            const boton = event.relatedTarget;
            document.getElementById('modalEliminarId').value = boton.getAttribute('data-id');
            document.getElementById('modalEliminarDescripcion').textContent = boton.getAttribute('data-descripcion');
        });
    }
});
