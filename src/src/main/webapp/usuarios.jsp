<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport"
	content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
	integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
	integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
	crossorigin="anonymous"></script>
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,500;1,400&display=swap" rel="stylesheet">
<title>Registro Usuarios</title>
</head>
<body>

	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">BoringShop</a>
				</div>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Inicio</a></li>
						<li class="nav-item"><a class="nav-link" href="usuarios.jsp">Usuarios</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Clientes</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Proveedores</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Productos</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Ventas</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Reportes</a></li>
					</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search"
							placeholder="Buscar" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Buscar</button>
					</form>
				</div>
			</div>
		</nav>
		<br>

		<h1>Registro de Usuarios</h1>
		<br>
		<form class="row g-3">
			<div class="col-md-6">
				<label class="form-label">Cédula</label> <input type="number"
					id="cedula" class="form-control" placeholder="Número de Cédula"
					aria-label="Cédula">
			</div>
			<div class="col-md-6">
				<label class="form-label">Usuario</label> <input type="text"
					id="usuario" class="form-control" placeholder="Usuario" aria-label="Usuario">
			</div>
			<div class="col-md-6">
				<label class="form-label">Nombre Completo</label> <input type="text"
					id="nombre"class="form-control" placeholder="Nombres y Apellidos"
					aria-label="Nombre Completo">
			</div>
			<div class="col-md-6">
				<label for="inputPassword4" class="form-label">Contraseña</label> <input
					type="password" class="form-control" placeholder="Contraseña"
					id="clave">
			</div>
			<div class="col-md-6">
				<label for="inputEmail4" class="form-label">Correo
					Electrónico</label> <input type="email" class="form-control"
					placeholder="ej@ejemplo.com" id="correo">
			</div>
		</form>
		<div class="p-5 text-center">
			<div class="flex-container center">
				<button style="margin: 10px" id="listarU" type="submit" class="btn btn-outline-success btn-lg">Consultar</button>
				<button style="margin: 10px" id="agregarU" type="submit" class="btn btn-outline-success btn-lg">Crear</button>
				<button style="margin: 10px" id="actualizarU" type="submit" class="btn btn-outline-success btn-lg">Actualizar</button>
				<button style="margin: 10px" id="eliminarU" type="submit" class="btn btn-outline-success btn-lg">Borrar</button>
			</div>
	</div>
</div>
</body>
</html>