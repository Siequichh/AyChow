$('.carousel2 .carousel-item2').each(function(){
    var minPerSlide = 4;
    var next = $(this).next();
    if (!next.length) {
        next = $(this).siblings(':first');
    }
    next.children(':first-child').clone().appendTo($(this));

    for (var i=0;i<minPerSlide;i++) {
        next=next.next();
        if (!next.length) {
            next = $(this).siblings(':first');
        }

        next.children(':first-child').clone().appendTo($(this));
    }
});
//funcion para añadir a favortios
function agregarAFavoritos(event) {
    const id = event.target.getAttribute('data-id');
    const title = event.target.getAttribute('data-title');
    const price = event.target.getAttribute('data-price');
    const thumbnail = event.target.getAttribute('data-thumbnail');

    let favoritos = JSON.parse(localStorage.getItem('favoritos')) || [];

    const productoExistente = favoritos.find(item => item.id === id);
    if (productoExistente) {
        alert('Ya añadiste este producto a favoritos.');
    } else {
        favoritos.push({ id, title, price, thumbnail });
        alert('añadido a favoritos.');

        localStorage.setItem('favoritos', JSON.stringify(favoritos));

    }
}

$(document).ready(function() {
    // Evento para manejar clic en los botones de filtro
    $('.filter-btn').click(function() {
        let marcaSeleccionada = $(this).data('marca');


        $.ajax({
            url: '/productos/por-marca',
            type: 'GET',
            data: {
                marca: marcaSeleccionada
            },
            success: function(response) {
                console.log(response);
                // Limpia la lista actual de productos
                $('#productos-list').empty();

                // Iterar sobre los productos recibidos y agregarlos al DOM
                response.forEach(function(producto) {
                    let productCard = `
                        <div class="col mb-5" data-marca="${producto.marca}">
                            <div class="card h-100">
                                <img class="card-img-top" src="/productos/imagen/${producto.idProducto}" alt="${producto.nombre}" />
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <h5 class="fw-bolder">${producto.nombre}</h5>
                                        <p>${producto.detalle}</p>
                                        <p class="h4">S/. ${producto.precio}</p>
                                    </div>
                                </div>
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-outline-dark mt-auto add-to-cart-btn" data-id="${producto.idProducto}">Agregar</button>
                                        <a class="btn btn-outline-secondary mt-auto" href="#Detallesproducto">Detalles</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `;
                    $('#productos-list').append(productCard);
                });
            },
            error: function(err) {
                console.error('Error al obtener productos:', err);
            }
        });
    });
});