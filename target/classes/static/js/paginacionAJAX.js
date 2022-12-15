window.onload = primera;
var pagina = 0;

function primera(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", "/api/canciones?page="+pagina+"&size=3", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
	inicio();
}
function inicio(){
	var btnPrevious = document.getElementById("previous");
	var btnNext = document.getElementById("next");
	if(pagina == 0){
		btnNext.addEventListener("click", siguiente);
	}else{
		btnPrevious.addEventListener("click", anterior);
		btnNext.addEventListener("click", siguiente);
	}
}
function anterior() {
	pagina--;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", "/api/canciones?page="+pagina+"&size=3", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    inicio();
}
function siguiente() {
	pagina++;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", "/api/canciones?page="+pagina+"&size=3", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    inicio();
}