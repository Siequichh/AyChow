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

// Función para obtener los productos desde la API de Mercado Libre
async function obtenerProductos() {
    try {
        const response = await fetch('https://api.mercadolibre.com/sites/MPE/search?q=celulares');
        const data = await response.json();
        mostrarProductos(data.results);
    } catch (error) {
        console.error('Error al obtener productos:', error);
    }
}

// Función para mostrar los productos en la página
function mostrarProductos(productos) {
    const contenedorProductos = document.getElementById('productos-lista');
    contenedorProductos.innerHTML = '';

    productos.forEach(producto => {
        const productoHTML = `
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-img-container" style="height: 250px; overflow: hidden;">
                        <img src="${producto.thumbnail}" alt="${producto.title}" class="card-img-top img-fluid h-100 w-100 object-fit-cover">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${producto.title}</h5>
                        <p class="card-text">${producto.price} ${producto.currency_id}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <button class="btn btn-success add-to-cart" data-id="${producto.id}" data-title="${producto.title}" data-price="${producto.price}" data-thumbnail="${producto.thumbnail}">Añadir al carrito</button>
                            <button class="btn btn-primary btn-detalles" data-id="${producto.id}" data-bs-toggle="modal" data-bs-target="#detallesModal">Ver detalles</button>
                           <button class="btn btn-outline-danger mt-auto add-to-favorites"  data-id="${producto.id}" data-title="${producto.title}" data-price="${producto.price}" data-thumbnail="${producto.thumbnail}">Agregar a favoritos</button>

                        </div>
                    </div>
                </div>
            </div>
        `;
        contenedorProductos.insertAdjacentHTML('beforeend', productoHTML);


    });

    // Agregar eventos a los botones de detalles y carrito
    document.querySelectorAll('.btn-detalles').forEach(boton => {
        boton.addEventListener('click', mostrarDetalles);
    });

    document.querySelectorAll('.add-to-cart').forEach(boton => {
        boton.addEventListener('click', agregarAlCarrito);
    });

     document.querySelectorAll('.add-to-favorites').forEach(boton => {
            boton.addEventListener('click', agregarAFavoritos);
        });
}
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

// Función para mostrar los detalles del producto en el modal
function mostrarDetalles(event) {
    const productoId = event.target.getAttribute('data-id');

    // Hacemos una petición a la API de Mercado Libre para obtener los detalles del producto
    fetch(`https://api.mercadolibre.com/items/${productoId}`)
        .then(response => response.json())
        .then(producto => {
            document.getElementById('detalles-imagen').src = producto.pictures[0].url;
            document.getElementById('detalles-nombre').innerText = producto.title;
            document.getElementById('detalles-descripcion').innerText = producto.description || 'No hay descripción disponible.';
            document.getElementById('detalles-precio').innerText = `Precio: ${producto.price} ${producto.currency_id}`;
        })
        .catch(error => {
            console.error('Error al obtener los detalles del producto:', error);
        });
}

// Función para agregar productos al carrito (puedes implementar la lógica de carrito)
function agregarAlCarrito(event) {
    const productoId = event.target.getAttribute('data-id');
    const productoTitle = event.target.getAttribute('data-title');
    const productoPrice = event.target.getAttribute('data-price');

    console.log(`Producto agregado al carrito: ${productoTitle} - Precio: ${productoPrice}`);
    // Aquí pueden agregar la lógica para almacenar el producto en el carrito
}

// Ejecutar la función para obtener los productos al cargar la página
document.addEventListener('DOMContentLoaded', obtenerProductos);
