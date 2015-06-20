
function clientSideInclude(id, url) {
  var req = false;
  // For Safari, Firefox, and other non-MS browsers
  if (window.XMLHttpRequest) {
    try {
      req = new XMLHttpRequest();
    } catch (e) {
      req = false;
    }
  } else if (window.ActiveXObject) {
    // For Internet Explorer on Windows
    try {
      req = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        req = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e) {
        req = false;
      }
    }
  }
 var element = document.getElementById(id);
 if (!element) {
  alert("Bad id " + id +
   "passed to clientSideInclude." +
   "You need a div or span element " +
   "with this id in your page.");
  return;
 }
  if (req) {
    // Synchronous request, wait till we have it all
    req.open('GET', url, false);
    req.send(null);
    element.innerHTML = req.responseText;
  } else {
    element.innerHTML =
   "Sorry, your browser does not support " +
      "XMLHTTPRequest objects. This page requires " +
      "Internet Explorer 5 or better for Windows, " +
      "or Firefox for any system, or Safari. Other " +
      "compatible browsers may also exist.";
  }
}

function filtrar(){
	$(".filaJugador").each(function(){
		var jugador=$("#jugador").val().toLowerCase();
		var posicion=$("#posicion").val().toLowerCase();
		var puntos=$("#puntos").val().toLowerCase();
		var promedio=$("#promedio").val().toLowerCase();
		var minutosMas=$("#minutosMas").val().toLowerCase();
		var minutosMenos=$("#minutosMenos").val().toLowerCase();
		var jugados=$("#jugados").val().toLowerCase();
		var hide=false;
		if (jugador!=""){
			var valor=$(this).children(".nombre").children("a:first").html();
			if (valor.indexOf(jugador)==-1){
				hide=true;
			}
		}
		if (!hide){
			if(posicion!=""){		
				var valor=$(this).children(".posicion").html().toLowerCase();
				if (valor != posicion){
					hide=true;
				}
			}
			if (!hide){
				if (puntos!=""){
					var valor=parseFloat($(this).children(".puntos").html());
					if (valor<puntos){
						hide=true;
					}
				}
				if (!hide){
					if (promedio!=""){
						var valor=parseFloat($(this).children(".promedio").html());
						if (valor<promedio){
							hide=true;
						}
					}
					if (!hide){
						if (minutosMas!=""){
							var valor=parseFloat($(this).children(".minutos").html());
							if (valor<minutosMas){
								hide=true;
							}
						}
						if (!hide){
							if (minutosMenos!=""){
								var valor=parseFloat($(this).children(".minutos").html());
								if (valor>minutosMenos){
									hide=true;
								}
							}
							if (!hide){
								if (jugados!=""){
									var valor=parseFloat($(this).children(".jugados").html());
									if (valor<jugados){
										hide=true;
									}
								}
							}
						}
					}
				}
			}
		}
		if (hide){
			$(this).addClass("hide");
		} else {
			$(this).removeClass("hide");
		}
	});
}
