<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BoringShop | Login</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="login.css" media="screen" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,500;1,400&display=swap"
	rel="stylesheet">

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="login.js"></script>
<!--Incorporo al proyecto javascript -->



</head>
<body>
<br>
<br>
<br>
	<div class="container fluid border">
		<div class="p-5 row vh-80 justify-content-center align-items-center">
			<div class="col form-login bloque text-center">
				<img src="images/BoringShop.png" alt="BoringShop"
					class="center-block" />
			</div>
			<div class="col bloque">
				<form>
					<h1 class="text-center">
						<strong>Bienvenidos a BoringShop</strong>
					</h1>
					<h2 class="text-center subtitulo">La manera sencilla de
						comprar.</h2>
					<div class="form-group">
						<label for="usuario">Usuario:</label> <input type="text"
							class="form-control" id="usuario"
							placeholder="Ingrese usuario">
					</div>
					<div class="form-group">
						<label for="clave">Contraseña:</label> <input type="password"
							id="clave" class="form-control" placeholder="Ingrese contraseña">
					</div>
					<button type="submit" id="btn_Enviar"
						class="btn btn-dark">Entrar</button>
				</form>
			</div>
		</div>
	</div>

	<div class="copyright" align="center">
		<p>Copyright &copy; 2021</p>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
		crossorigin="anonymous"></script>
</body>
</html>