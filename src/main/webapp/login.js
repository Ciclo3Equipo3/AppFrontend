//JQuery para capturar el servicio
$(document).ready(function(){
$("#btn_Enviaru").click(function(){//El evento callback no tiene cuerpo solo se ejecuta en el momento
	let elUsuario = document.getElementById("usuario").value;//cada control de la pagina tiene un Id
	let laClave = document.getElementById("clave").value;
	//esta varialbe contine el valor del campo cedula
$.post("http://localhost:8080/loginusuario2",{ usuario: elUsuario, clave: laClave },
		function(data, status){
		// data1 = $.parseJSON( data );
		if(data.existe==true)
		{
			alert("Bienvenido "+elUsuario);
			window.location.href = "cliente.jsp";
		}
		else
		{
			alert("No xiste el Usuario");
		}
		});
	});
		function mostrarUno(item, index) {
			let valores = Object.values(item);
			$("#usuario").val(valores[0]);
			$("#clave").val(valores[1]);
	}
});