window.onload = inicio;

function inicio(){
	comprobarPreferenciaUsuario();
	var boton = document.querySelector("#modoOscuro");
	boton.addEventListener("click", function(){
		document.body.classList.toggle("dark");
		if(document.body.classList.contains("dark")){
			localStorage.setItem("dark-mode", 'true');
			boton.classList.remove("bi-lightbulb");
			boton.classList.add("bi-lightbulb-fill");
		}else{
			localStorage.setItem("dark-mode", 'false');
			boton.classList.remove("bi-lightbulb-fill");
			boton.classList.add("bi-lightbulb");
		}
	});
}
function comprobarPreferenciaUsuario(){
	var boton = document.querySelector("#modoOscuro");
	if(localStorage.getItem("dark-mode") == "true"){
		document.body.classList.add("dark");
		boton.classList.remove("bi-lightbulb");
		boton.classList.add("bi-lightbulb-fill");
	}else{
		document.body.classList.remove("dark");
		boton.classList.remove("bi-lightbulb-fill");
		boton.classList.add("bi-lightbulb");
	}
}