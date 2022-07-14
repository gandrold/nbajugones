function seleccionar(seleccionarUrl, loaderUrl,jugador){
	$("#detalleJugador").show();
	$("html, body").animate({ scrollTop: 0 }, "fast");
	$("#datosJugador").html("<img src='/"+loaderUrl+"' class='center'/>");

	$.ajax({
		type : "POST",
		url : seleccionarUrl,
		data : "jugador="+jugador,
		success : function (data){
	        $("#datosJugador").html(data);
	        $(".seleccionarJugador").each(function(){
	        	if ($(this).val()!=jugador){
	        		$(this).attr('checked', false);
	        	}
	        });
	    },
	    error:function(data){
	    	$("#datosJugador").html("<p class='bg-danger'>Se ha producido un error al cargar al jugador</p>");
	    }
	});
}


function activar(activarUrl, loaderUrl, jugador, equipo){
	$("#contenido").html("<img src='"+loaderUrl+"' class='center'/>");

	$.ajax({
		type : "POST",
		url : activarUrl,
		data : "jugador="+jugador+"&equipo="+equipo,
		success : function (data){
	        $("#contenido").html(data);
	        initRosterPage(equipo);
	    },
	    error:function(data){
	    	loadTeam(equipo);
	    }
	});
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
