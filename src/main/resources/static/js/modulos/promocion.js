// Llena el modal de promoción según el botón presionado (nueva o editar)
document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('modalPromocion');
    if (!modal) {
        return;
    }
    const formulario = document.getElementById('formPromocion');

    modal.addEventListener('show.bs.modal', function (event) {
        const boton = event.relatedTarget;
        formulario.reset();
        document.getElementById('promocionId').value = '';
        document.getElementById('tituloModalPromocion').textContent = boton.getAttribute('data-titulo');

        if (boton.getAttribute('data-modo') === 'editar') {
            document.getElementById('promocionId').value = boton.getAttribute('data-id');
            document.getElementById('promocionTitulo').value = boton.getAttribute('data-titulo-promocion');
            document.getElementById('promocionDescripcion').value = boton.getAttribute('data-descripcion');
            document.getElementById('promocionDescuento').value = boton.getAttribute('data-descuento');
            document.getElementById('promocionInicio').value = boton.getAttribute('data-inicio');
            document.getElementById('promocionFin').value = boton.getAttribute('data-fin');
            document.getElementById('promocionActiva').checked = boton.getAttribute('data-activa') === 'true';
        } else {
            // Una promoción nueva inicia activa
            document.getElementById('promocionActiva').checked = true;
        }
    });
});
