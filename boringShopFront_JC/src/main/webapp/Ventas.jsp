<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/formularios.css"media="screen" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Ventas</title>
</head>
<body style="background-color: transparent;">
	<div class="row">
		<div class="col-md-5 seccion1">
			<form method="get" action="Controlador">
				<div class="card text-white bg-dark">
					<div class="card-body">
					  	<div class="form-group">
							<label> Datos del Cliente:</label>
						</div>
						<input type="hidden" name="menu" value="Ventas"> 
						<input type="hidden" name="UsuarioActivo" value="${usuarioSeleccionado.getCedula_usuario()}" >
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="cedulacliente" class="form-control"
									placeholder="cedula cliente" value="${clienteSeleccionado.getCedula_cliente()}">
								<input type="submit" name="accion" value="BuscarCliente" class="btn-outline-dark text-white">							
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombrecliente" class="form-control"
									placeholder="Nombre Cliente" value="${clienteSeleccionado.getNombre_cliente()}">
							</div>
						</div>					  										
					</div>
				</div>
				<div class="card text-white bg-dark">
					<div class="card-body m-2">
						<div class="form-group">
							<label> Datos del Producto(s):</label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="codigoproducto" class="form-control"
									placeholder="codigo producto" value="${productoSeleccionado.getCodigo_producto()}"> 
								<input type="submit" name="accion" value="BuscarProducto" class="btn-outline-dark text-white">
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombreproducto" class="form-control"
									placeholder="Nombre producto" value="${productoSeleccionado.getNombre_producto()}">
							</div>
						</div>
						<div class="form-group d-flex pr-2">
							<div class="col-sm-6 d-flex">
								<input type="text" name="precioproducto" class="form-control"
									placeholder="$  0000.00" value="${productoSeleccionado.getPrecio_venta()}">
							</div>
							<div class="col-sm-3">
								<input type="number" name="cantidadproducto" class="form-control"
									placeholder="Cantidad" value="">
							</div>
							<div class="col-sm-3">
								<input type="text" name="ivaproducto" class="form-control"
									placeholder="Valor Iva" value="${productoSeleccionado.getIvacompra()}">
							</div>
						</div>
						<div class="form-group d-flex p-3">
							<input type="submit" name="accion" value="AgregarProducto" class="btn-outline-dark text-white">
						</div>
					</div>
				</div>
			</form>
		</div>
		
		<div class="col-md-7 seccion2">
			<div class="card">
				<div class="card-header text-white bg-dark">
					<div class="form-group row">
					<label class="col-sm-3 col-form-label">Número Factura</label>
					<input class="form-control col-md-2" type="text" name="numerofactura" value="${numerofactura}">			
					</div>				
				</div>
				<div class="card-body">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>#</th>
								<th>codigo</th>
								<th>producto</th>
								<th>precio</th>
								<th>cantidad</th>
								<th>iva</th>
								<th>total</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="lista" items="${listaventas}">
							<tr>
								<th>${lista.getCodigo_detalle_venta()}</th>
								<th>${lista.getCodigo_producto()}</th>
								<th>${lista.getDescripcion_producto()}</th>
								<th>${lista.getPrecio_producto()}</th>
								<th>${lista.getCantidad_producto()}</th>
								<th>${lista.getValoriva()}</th>
								<th>${lista.getValor_venta()}</th>
							</tr>
						</c:forEach>
						</tbody>
					</table>				
				</div>
				<div class="card-footer d-flex">
					<div class="col-md-4">
						<label>Subtotal</label></br> </br>
						<label>iva</label></br> </br>
						<label>total a pagar</label></br> </br>
					</div>
					<div class="col-md-4">
						<input type="text" name="txtsubtotal" class="form-control" placeholder="$ 00.000.00" disabled="disabled" value="${totalsubtotal}">
						<input type="text" name="txttotaliva" class="form-control" placeholder="$ 00.000.00" disabled="disabled" value="${totaliva}">
						<input type="text" name="txttotalapagar" class="form-control" placeholder="$ 00.000.00" disabled="disabled" value="${totalapagar}">
					</div>									
				</div>							
			</div>	
			
			<div class="card-footer" d-flex>
				<div class="col-md-8">
				 <!-- enviamos los tres valores al controlador --> 
					<a class="btn btn-outline-warning text-white" onclick="print()" href="Controlador?menu=Ventas&accion=GenerarVenta&cedulacliente=${clienteSeleccionado.getCedula_cliente()}&UsuarioActivo=${usuarioSeleccionado.getCedula_usuario()}&numerofactura=${numerofactura}">Generar Venta</a>
					<a class="btn btn-outline-danger text-white" href="Controlador?menu=Ventas&accion=NuevaVenta">Nueva Venta</a>
				</div>
			</div>				
		</div>		
	</div>
</body>
</html>