(function () {
  'use strict';
   myapp.controller('FacturaController',
        function($scope, $http, $window, NgTableParams) {
            var self = this;
            var facturaUrl = '../facturaController/';
            var sindicatoSeleccionado = null;

            $scope.factura = [];
            $scope.nuevoArticulo = [];
            $scope.listaArticulos = [];
            $scope.listaProductos = [];
            $scope.totales = [];
            $scope.controlStock = [];

            $scope.factura.fecha = new Date();
            $scope.factura.nroComprobante = '';
            $scope.fechaMinimaFactura = '1900-01-01';

            $scope.direccion = 'Calle 1589 - CP AA1111 - CABA';
            $scope.telefono = 'Tel. 236-1258-1245';
            $scope.factura.listaPrecio = 'C';
            $scope.listaPreciosDeshabilitada = false;
            $scope.afiliadoDeshabilitado = true;
            $scope.agregarArticuloDeshabilitado = true;

            var modalSindicato = document.getElementById('modalSindicato');
            var modalAfiliado = document.getElementById('modalAfiliado');
            var modalProducto = document.getElementById('modalProducto');
            var modalError = document.getElementById('modalError');
            var modalCargando = document.getElementById('modalCargando');
            var span = document.getElementsByClassName("close")[0];
            $scope.cerrarModal = function() {
                modalSindicato.style.display = "none";
                modalAfiliado.style.display = "none";
                modalProducto.style.display = "none";
                modalError.style.display = "none";
            };
            window.onclick = function(event) {
                if (event.target == modalSindicato) {
                    modalSindicato.style.display = "none";
                }
                if (event.target == modalAfiliado) {
                    modalAfiliado.style.display = "none";
                }
                if (event.target == modalProducto) {
                    modalProducto.style.display = "none";
                }
                if (event.target == modalError) {
                    modalError.style.display = "none";
                }
            };

            function mostrarCargando () {
                modalCargando.style.display = "block";
            };

            function ocultarCargando () {
                modalCargando.style.display = "none";
            };

            function mostrarError (mensaje) {
                $scope.mensajeError = mensaje;
                modalError.style.display = "block";
            };

            $scope.buscarSindicatoModal = function() {
                $http({method: 'GET',url: facturaUrl + 'cargarSindicatos'}).then(
                    function successCallback(response) {
                        $scope.sindicatosTable = new NgTableParams({}, { dataset: response.data});
                        modalSindicato.style.display = "block";
                    }, function errorCallback(response) {
                });
            };

            $scope.buscarSindicatoInput = function() {
                $http({method: 'GET',url: facturaUrl + 'buscarSindicatoPorCodigo?codigo=' + $scope.factura.sindicato}).then(
                    function successCallback(response) {
                        sindicatoSeleccionado = response.data.value;
                        $scope.sindicatoNombre = response.data.name;
                        $scope.sindicatoCuit = response.data.cuit;
                        $scope.factura.afiliado = null;
                        $scope.afiliadoNombre = null;
                        $scope.afiliadoDni = null;
                        if (response.data.name != "NO EXISTE"){
                            $scope.afiliadoDeshabilitado = false;
                        } else {
                            $scope.afiliadoDeshabilitado = true;
                        }
                    }, function errorCallback(response) {
                });
            };

            $scope.buscarAfiliadoModal = function() {
                if (sindicatoSeleccionado === null) {
                    $http({method: 'GET',url: facturaUrl + 'cargarAfiliados'}).then(
                        function successCallback(response) {
                            $scope.afiliadosTable = new NgTableParams({}, { dataset: response.data});
                            modalAfiliado.style.display = "block";
                        }, function errorCallback(response) {
                    });
                } else {
                    $http({method: 'GET',url: facturaUrl + 'cargarAfiliadosPorSindicato?sindicato=' + sindicatoSeleccionado}).then(
                        function successCallback(response) {
                            $scope.afiliadosTable = new NgTableParams({}, { dataset: response.data});
                            modalAfiliado.style.display = "block";
                        }, function errorCallback(response) {
                    });
                }
            };

            $scope.buscarAfiliadoInput = function() {
                if (sindicatoSeleccionado === null) {
                    $http({method: 'GET',url: facturaUrl + 'buscarAfiliadoPorId?id=' + $scope.factura.afiliado }).then(
                        function successCallback(response) {
                            $scope.afiliadoNombre = response.data.name;
                            $scope.afiliadoDni = response.data.dni;
                        }, function errorCallback(response) {
                    });
                } else {
                    $http({method: 'GET',url: facturaUrl + 'buscarAfiliadoPorIdYSindicato?id=' + $scope.factura.afiliado + "&sindicato=" + sindicatoSeleccionado}).then(
                        function successCallback(response) {
                            $scope.afiliadoNombre = response.data.name;
                            $scope.afiliadoDni = response.data.dni;
                        }, function errorCallback(response) {
                    });
                }
            };

            $scope.buscarProductoModal = function() {
                $http({method: 'GET',url: facturaUrl + 'cargarProductos?fecha=' + formatearFecha($scope.nuevoArticulo.fecha)}).then(
                    function successCallback(response) {
                        $scope.productosTable = new NgTableParams({}, { dataset: response.data});
                        modalProducto.style.display = "block";
                    }, function errorCallback(response) {
                });
            };

            $scope.buscarProductoInput = function() {
                $http({method: 'GET',url: facturaUrl + 'cargarProductosPorId?id=' + $scope.nuevoArticulo.codigo + '&fecha=' + formatearFecha($scope.nuevoArticulo.fecha)}).then(
                    function successCallback(response) {
                        $scope.nuevoArticulo.descripcion = response.data.name;
                        if (response.data.name != "NO EXISTE"){
                            controlarStock(response.data.value,1,true);
                            $scope.nuevoArticulo.cantidad = 1;
                            $scope.agregarArticuloDeshabilitado = false;
                            $scope.nuevoArticulo.value = response.data.value;
                        } else {
                            $scope.nuevoArticulo.cantidad = null;
                            $scope.agregarArticuloDeshabilitado = true;
                        }
                        if ($scope.factura.listaPrecio === "A"){
                            $scope.nuevoArticulo.precio = response.data.precioA;
                            $scope.nuevoArticulo.total = response.data.precioA;
                        } else if ($scope.factura.listaPrecio === "B"){
                            $scope.nuevoArticulo.precio = response.data.precioB;
                            $scope.nuevoArticulo.total = response.data.precioB;
                        } else {
                            $scope.nuevoArticulo.precio = response.data.precioC;
                            $scope.nuevoArticulo.total = response.data.precioC;
                        }
                    }, function errorCallback(response) {
                });
            };

            function controlarStock(value, cantidad, busqueda){
                var encontrado = false;
                for (var i = 0; i < $scope.controlStock.length; i++){
                    if ($scope.controlStock[i].value == Number(value)){
                        encontrado = true;
                        if (!busqueda){
                            if ($scope.controlStock[i].stock >= cantidad){
                                $scope.controlStock[i].stock = Number($scope.controlStock[i].stock) - Number(cantidad);
                            } else {
                                mostrarError("Se ha excedido el Stock disponible: " + $scope.controlStock[i].stock);
                                return false;
                            }
                        }
                    }
                }
                if (!encontrado){
                    $scope.controlStock.push({value:Number(value),stock:cantidad});
                }
                return true;
            };

            function recuperarStock(value, cantidad){
                for (var i = 0; i < $scope.controlStock.length; i++){
                    if ($scope.controlStock[i].value == Number(value)){
                        $scope.controlStock[i].stock = Number($scope.controlStock[i].stock) + Number(cantidad);
                    }
                }
            };

            function formatearFecha(fecha) {
                var dia = ("0" + fecha.getDate()).slice(-2);
                var mes = ("0" + (fecha.getMonth() + 1)).slice(-2);
                return (dia)+"/"+(mes)+"/"+ fecha.getFullYear();
            };

            function obtenerFecha(fechaStr) {
                var dia = Number(fechaStr.substring(0,2));
                var mes = Number(fechaStr.substring(3,5))-1;
                var anio = Number(fechaStr.substring(6,10));
                return new Date(anio,mes,dia);
            };

            mostrarCargando();

            $http({method: 'GET',url: facturaUrl + 'cargarInformacion'}).then(
                function successCallback(response) {
                    ocultarCargando();
                    $scope.comprobantes = response.data.tipoComprobanteDTOList;
                    $scope.situacionesIva = response.data.situacionesIVADTOList;
                    $scope.condicionesVenta = response.data.condicionVentaDTOList;
                }, function errorCallback(response) {
            });

            limpiarNuevoArticulo();
            function limpiarNuevoArticulo () {
                $scope.nuevoArticulo.fecha = new Date();
                $scope.nuevoArticulo.codigo = null;
                $scope.nuevoArticulo.descripcion = null;
                $scope.nuevoArticulo.cantidad = null;
                $scope.nuevoArticulo.precio = null;
                $scope.nuevoArticulo.total = null;
                $scope.agregarArticuloDeshabilitado = true;
            };

            var articuloId = 0;
            $scope.guardarArticulo = function () {
                if (controlarStock($scope.nuevoArticulo.codigo,$scope.nuevoArticulo.cantidad, false)){
                    var nuevo = {
                        fecha:formatearFecha($scope.nuevoArticulo.fecha),
                        codigo:$scope.nuevoArticulo.codigo,
                        descripcion:$scope.nuevoArticulo.descripcion,
                        cantidad:$scope.nuevoArticulo.cantidad,
                        precio:$scope.nuevoArticulo.precio,
                        total:$scope.nuevoArticulo.total,
                        id: articuloId
                    }
                    articuloId++;
                    $scope.listaArticulos.push(nuevo);
                    limpiarNuevoArticulo();
                    $scope.articulosTable = new NgTableParams({}, { dataset: $scope.listaArticulos});
                    $scope.listaPreciosDeshabilitada = true;
                    actualizarTotales();
                }
            };

            $scope.borrarArticulo = function (id) {
                var nuevoArray = [];
                var tamanioLista = $scope.listaArticulos.length;
                for (var i = 0; i < tamanioLista; i++){
                    var articulo = $scope.listaArticulos.shift();
                    if (articulo.id != id) {
                        nuevoArray.push(articulo);
                    } else {
                        recuperarStock(articulo.codigo,articulo.cantidad);
                    }
                }
                $scope.listaArticulos = nuevoArray;
                $scope.articulosTable = new NgTableParams({}, { dataset: $scope.listaArticulos});
                if (nuevoArray.length == 0) $scope.listaPreciosDeshabilitada = false;
                actualizarTotales();
            };

            $scope.editarArticulo = function (id) {
                var nuevoArray = [];
                var articuloAEditar;
                var tamanioLista = $scope.listaArticulos.length;
                for (var i = 0; i < tamanioLista; i++){
                    var articulo = $scope.listaArticulos.shift();
                    if (articulo.id != id) {
                        nuevoArray.push(articulo);
                    } else {
                        articuloAEditar = articulo;
                    }
                }
                $scope.listaArticulos = nuevoArray;
                $scope.articulosTable = new NgTableParams({}, { dataset: $scope.listaArticulos});
                if (nuevoArray.length == 0) $scope.listaPreciosDeshabilitada = false;
                $scope.nuevoArticulo.fecha = obtenerFecha(articuloAEditar.fecha);
                $scope.nuevoArticulo.codigo = articuloAEditar.codigo;
                $scope.nuevoArticulo.descripcion = articuloAEditar.descripcion;
                $scope.nuevoArticulo.cantidad = articuloAEditar.cantidad;
                $scope.nuevoArticulo.precio = articuloAEditar.precio;
                $scope.nuevoArticulo.total = articuloAEditar.total;
                $scope.agregarArticuloDeshabilitado = false;
                recuperarStock(articuloAEditar.codigo,articuloAEditar.cantidad);
                actualizarTotales();
            };

            $scope.seleccionSindicato = function(codigo, nombre, cuit) {
                sindicatoSeleccionado = codigo;
                $scope.factura.sindicato = codigo;
                $scope.sindicatoNombre = nombre;
                $scope.sindicatoCuit = cuit;
                $scope.factura.afiliado = null;
                $scope.afiliadoNombre = null;
                $scope.afiliadoDni = null;
                $scope.afiliadoDeshabilitado = false;
                modalSindicato.style.display = "none";
            };

            $scope.deseleccionarSindicato = function(){
                sindicatoSeleccionado = null;
                $scope.factura.sindicato = null;
                $scope.sindicatoNombre = null;
                $scope.sindicatoCuit = null;
                $scope.factura.afiliado = null;
                $scope.afiliadoNombre = null;
                $scope.afiliadoDni = null;
                $scope.afiliadoDeshabilitado = true;
                modalSindicato.style.display = "none";
            };

            $scope.seleccionAfiliado = function(id, nombre, dni) {
                $scope.factura.afiliado = id;
                $scope.afiliadoNombre = nombre;
                $scope.afiliadoDni = dni;
                modalAfiliado.style.display = "none";
            };

            $scope.seleccionProducto = function(codigo, descripcion, precioA, precioB, precioC, stock) {
                controlarStock(codigo,stock,true);
                $scope.nuevoArticulo.codigo = codigo;
                $scope.nuevoArticulo.descripcion = descripcion;
                $scope.nuevoArticulo.cantidad = 1;
                if ($scope.factura.listaPrecio === "A"){
                    $scope.nuevoArticulo.precio = precioA;
                    $scope.nuevoArticulo.total = precioA;
                } else if ($scope.factura.listaPrecio === "B"){
                    $scope.nuevoArticulo.precio = precioB;
                    $scope.nuevoArticulo.total = precioB;
                } else {
                    $scope.nuevoArticulo.precio = precioC;
                    $scope.nuevoArticulo.total = precioC;
                }
                $scope.agregarArticuloDeshabilitado = false;
                modalProducto.style.display = "none";
            };

            $scope.enviar = function () {
                var data = {
                    fecha:formatearFecha($scope.factura.fecha),
                    tipoComprobante:$scope.factura.tipoComprobante,
                    puntoVenta:$scope.factura.puntoVenta,
                    sindicato:$scope.factura.sindicato,
                    afiliado:$scope.factura.afiliado,
                    situacionesIva:$scope.factura.situacionIVA,
                    condicionesVenta:$scope.factura.condicionVenta,
                    bonificacion:$scope.factura.bonificacion,
                    listaPrecio:$scope.factura.listaPrecio,
                    total:$scope.totales.total
                };
                $http({method: 'POST',url: facturaUrl + 'generarFactura', data: data}).then(
                    function successCallback(response) {

                    }, function errorCallback(response) {
                });
            };

            $scope.actualizarNuevoTotal = function() {
                $scope.nuevoArticulo.total = $scope.nuevoArticulo.cantidad * $scope.nuevoArticulo.precio;
                if ($scope.nuevoArticulo.cantidad < 1) {
                    $scope.agregarArticuloDeshabilitado = true;
                } else {
                    $scope.agregarArticuloDeshabilitado = false;
                }
            };

            $scope.actualizarDescuento = function () {
                actualizarTotales();
            };

            function actualizarTotales() {
                var sum = 0;
                for (var i=0; i<$scope.listaArticulos.length; i++){
                    sum += $scope.listaArticulos[i].total;
                }
                $scope.totales.subtotal = sum;
                if (typeof $scope.factura.bonificacion === 'undefined'){
                    $scope.totales.descuento = null;
                    $scope.totales.total = sum;
                } else {
                    $scope.totales.descuento = $scope.factura.bonificacion;
                    $scope.totales.total = sum - (sum * $scope.factura.bonificacion / 100);
                }
            };

            $scope.actualizarComprobante = function () {
                mostrarCargando();
                if ($scope.factura.tipoComprobante != null){
                    $http({method: 'GET',url: facturaUrl + 'cargarLeyendaComprobante?codigo=' + $scope.factura.tipoComprobante}).then(
                        function successCallback(response) {
                            ocultarCargando();
                            $scope.leyenda = response.data.name;
                        }, function errorCallback(response) {
                    });
//                    $http({method: 'GET',url: facturaUrl + 'buscarComprobante?comp=' + $scope.factura.tipoComprobante}).then(
//                        function successCallback(response) {
//                            ocultarCargando();
//                            $scope.factura.nroComprobante = ('0000000' + Number(response.data.numero.substring(4,12))+1).slice(-8);
//                                if (response.data.numero != "1"){
//                                var anio = response.data.fecha.substring(0,4);
//                                var mes = response.data.fecha.substring(4,6);
//                                var dia = response.data.fecha.substring(6,8);
//                                $scope.fechaMinimaFactura = anio+'-'+mes+'-'+dia;
//                            } else {
//                                $scope.fechaMinimaFactura = '1900-01-01';
//                            }
//                        }, function errorCallback(response) {
//                    });
                }
            }
        })
}());