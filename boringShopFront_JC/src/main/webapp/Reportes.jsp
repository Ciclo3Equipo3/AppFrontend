<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/formularios.css"media="screen" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Reportes</title>
</head>
<body style="background-color: transparent;">
	<div class="row">
		<div class="col-md-5 seccion1">
			<form class="form-sign" method="get" action="Controlador">
				<div class="card">
					<div class="card-body text-white bg-dark mb-3">
						<div class="form-group">
							<h4>Seleccione el tipo de reporte:</h4>
						</div>
						<input type="hidden" name="menu" value="Reportes">
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex p-3" align="center">
								<input type="submit" name="accion" value="ReporteUsuarios" class="btn btn-outline-dark text-white"> 
								<input type="submit" name="accion" value="ReporteClientes" class="btn btn-outline-dark text-white"> 
								<input type="submit" name="accion" value="ReporteVentas" class="btn btn-outline-dark text-white">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-7 seccion2">
			<div class="card">
				<div class="card-body text-white bg-dark mb-3">
					<div class="form-group">
						<h4>Detalle del Reporte</h4>
					</div>
					<!-- <table class="table table-dark table-striped">-->
					<table class="table table-striped table-dark table-hover text-white">
						<c:if test="${opcion==1}">
							<h4>Usuarios</h4>
							<thead>
								<tr>
									<th scope="col">Cedula</th>
									<th scope="col">Nombre</th>
									<th scope="col">Email</th>
									<th scope="col">Usuario</th>
									<th scope="col">Clave</th>
								</tr>
							</thead>
							<c:forEach var="lista" items="${listaUsuarios}">
								<tbody>
									<tr>
										<td>${lista.getCedula_usuario()}</td>
										<td>${lista.getNombre_usuario()}</td>
										<td>${lista.getEmail_usuario()}</td>
										<td>${lista.getUsuario()}</td>
										<td>${lista.getClave()}</td>
									</tr>
							</c:forEach>
							</tbody>
						</c:if>
						<c:if test="${opcion==2}">
							<h4>Clientes</h4>
							<thead>
								<tr>
									<th scope="col">Cedula</th>
									<th scope="col">Nombre</th>
									<th scope="col">Email</th>
									<th scope="col">Direccion</th>
									<th scope="col">Telefono</th>
								</tr>
							</thead>
							<c:forEach var="lista" items="${lista}">
								<tbody>
									<tr>
										<td>${lista.getCedula_cliente()}</td>
										<td>${lista.getDireccion_cliente()}</td>
										<td>${lista.getEmail_cliente()}</td>
										<td>${lista.getNombre_cliente()}</td>
										<td>${lista.getTelefono_cliente()}</td>
									</tr>
							</c:forEach>
							</tbody>
						</c:if>
						<c:if test="${opcion==3}">
							<h4>Ventas</h4>
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Cedula cliente</th>
									<th scope="col">Cedula usuario</th>
									<th scope="col">IVA</th>
									<th scope="col">Subtotal venta</th>
									<th scope="col">Total</th>
								</tr>
							</thead>
							<c:forEach var="lista" items="${listaVentas}">
								<tbody>
									<tr>
										<th>${lista.getCodigo_venta()}</th>
										<th>${lista.getCedula_cliente()}</th>
										<th>${lista.getCedula_usuario()}</th>
										<th>${lista.getIvaventa()}</th>
										<th>${lista.getValor_vental()}</th>
										<th>${lista.getTotal_venta()}</th>
									</tr>
							</c:forEach>
							</tbody>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>