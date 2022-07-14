function initRosterPage(equipo, cortarUrl, loaderUrl) {
	$("div.holder").jPages({
		containerID: "historicos"
	});
	$(".corte").each(function () {
		$(this).change(function () {
			if ($(this).val() != "-") {
				if (confirm("Estas seguro que lo quieres cortar?")) {
					cortar(cortarUrl, loaderUrl, $(this).val(), $(this).data("jugador"), equipo);
				}
			}
		});
	});
	$("#tablaRoster").tablesorter();
}


function loadTeam(rosterUrl, cortarUrl, loaderUrl, equipo) {
	document.location.href=rosterUrl+"?id=" + equipo;
	$.ajax({
		type: "GET",
		url: rosterUrl,
		data: "id=" + equipo,
		success: function (data) {
			$("#contenido").html(data);
			initRosterPage(equipo, cortarUrl, loaderUrl);
		}
	});
}

function sendPlayerId(playerId){
	$("#idJugador").val(playerId);
}

function cortar(cortarUrl, loaderUrl, factor, idJugador, equipo) {

	$("#contenido").html("<img src='"+loaderUrl+"' class='center'/>");

	$.ajax({
		type: "POST",
		url: cortarUrl,
		data: "id=" + idJugador + "&factor=" + factor + "&equipo=" + equipo,
		success: function (data) {
			$("#contenido").html(data);
			initRosterPage(equipo);
		},
		error: function (data) {
			loadTeam(equipo);
		}
	});
}

function loadTeamTrade(rosterUrl, logoUrl, equipo, id) {
	$("#salarios_" + id).html(0);
	$("#puntos_" + id).html(0);
	$("#puntosProm_" + id).html(0);
	$("#eficiencia_" + id).html(0);
	$.ajax({
		type: "GET",
		url: rosterUrl,
		data: "id=" + equipo + "&posicion=" + id,
		success: function (data) {
			$("#datosEquipo" + id).html(data);
		}
	});
	$.ajax({
		type: "GET",
		url: logoUrl,
		data: "id=" + equipo,
		dataType: "json",
		success: function (data) {
			$("#logoEquipo" + id).html("<img src='" + data.logo + "'/>");
		}
	});

}

function calcularSalarios(posicion) {
	var salarios = 0;
	var puntos = 0;
	var eficiencia = 0;
	var jugadores = 0;
	var players = "";
	$(".jugadores_" + posicion).each(function () {
		if ($(this).is(':checked')) {
			//Nos quedamos con el id
			var ident = $(this).attr('id');
			var id = ident.substr(ident.indexOf("_") + 1, ident.length);
			if (players == "") {
				players = "" + id;
			} else {
				players = players + "," + id;
			}
			//Calculamos salarios, eficiencia y puntos
			jugadores++;
			salarios += parseFloat($("#salario_" + id).val());
			puntos += parseFloat($("#puntos_" + id).val());
			eficiencia += parseFloat($("#eficiencia_" + id).val());
		}
	});
	$("#salarios_" + posicion).html(salarios);
	var puntosProm = puntos / jugadores;
	var efiProm = eficiencia / jugadores;
	$("#salarios_" + posicion).html(salarios.toFixed(2));
	$("#puntos_" + posicion).html(puntos.toFixed(2));
	$("#puntosProm_" + posicion).html(puntosProm.toFixed(2));
	$("#eficiencia_" + posicion).html(efiProm.toFixed(2));
	$("#players" + posicion).value = players;
}

function checkTrade() {
	var sal1 = $("#salarios_1").html();
	var sal2 = $("#salarios_2").html();
	if (Math.abs(sal1 - sal2) <= 1) {
		alert('Trade valido');
		$("#datosTrade").submit();
		return true;
	} else {
		var max = Math.max(sal1, sal2);
		var limite = max * 0.2;
		if (Math.abs(sal1 - sal2) <= limite) {
			alert('Trade valido');
			$("#datosTrade").submit();
			return true;
		} else {
			alert('Trade no valido. La diferencia ha de ser menor de ' + limite.toFixed(2));
			return false;
		}
	}
}

function evaluarTrade() {
//    var puntos1=$("#puntos_1").html();
//    var puntos2=$("#puntos_2").html();
//    var puntosProm1=$("#puntosProm_1").html();
//    var puntosProm2=$("#puntosProm_2").html();
//    var efi1=$("#eficiencia_1").html();
//    var efi2=$("#eficiencia_2").html();
//    evaluar(puntos1,puntos2,"resultadoPuntos");
//    evaluar(puntosProm1,puntosProm2,"resultadoPuntosProm");
//    evaluar(efi1,efi2,"resultadoEfi");
	$('#evaluador').dialog('open');
}

function evaluar(valor1, valor2, resultadoDiv) {
	var logo1 = $("#logoEquipo1").html();
	var logo2 = $("#logoEquipo2").html();
	if (valor1 < valor2) {
		$("#" + resultadoDiv).html(logo1);
	} else {
		if (valor1 == valor2) {
			$("#" + resultadoDiv).html("<b>IGUALES</b>");
		} else {
			$("#" + resultadoDiv).html(logo2);
		}
	}
}

function loadBoxScore(boxscoreUrl, loaderUrl, id) {
	$("#boxScoreBody").html("<img src='"+loaderUrl+"' class='center'/>");
	$.ajax({
		type: "GET",
		url: boxscoreUrl,
		data: "id=" + id,
		success: function (data) {
			$("#boxScoreBody").html(data);
		}
	});
}

function ordenRoster(){
    var result = "";
    $(".rosterLista").each(function () {
        result += "Jugador "+$(this).data("idjugador")+", orden: "+$(this).index()+" \n";
    });
    alert(result);
}

function cambiarPosicion(idJugador){
    var posicion = $("#posicion"+idJugador).html().split("").reverse().join("");
    $(".rosterLista").each(function () {
       if ($(this).data("idjugador") == idJugador){
            $(this).data("posicion", posicion);
       }
    });
    $("#posicion"+idJugador).html(posicion);
}

function loadRoster(rosterUrl, loaderUrl, id) {
	$("#rosterBody").html("<img src='"+loaderUrl+"' class='center'/>");
	$.ajax({
		type: "GET",
		url: rosterUrl,
		data: "id=" + id,
		success: function (data) {
			$("#rosterBody").html(data);
		}
	});
}

function loadBoxScoreCopa(boxscoreUrl, loaderUrl, y, r, p) {
	$("#boxScoreBody").html("<img src='"+loaderUrl+"' class='center'/>");
	$.ajax({
		type: "GET",
		url: boxscoreUrl,
		data: "y=" + y+"&r="+r+"&p="+p,
		success: function (data) {
			$("#boxScoreBody").html(data);
		}
	});
}

function loadPick(y, ronda, equipo) {
	$("#y").val(y);
	$("#round").val(ronda);
	$("#team").val(equipo);
}

function loadTeamLogo(logoUrl, div, id) {
	$.ajax({
		type: "GET",
		url: logoUrl,
		data: "id=" + id,
		success: function (data) {
			if (data.logo !== "") {
				$("#" + div).html("<img alt=" + id + " src=" + data.logo + " title=" + id + " class='logoEquipoSchedule'/>");
			} else {
				$("#" + div).html("FA");
			}
		}
	});
}


