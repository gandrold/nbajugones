function initRosterPage(equipo){
	$("div.holder").jPages({
        containerID : "historicos"
      });
    $(".corte").each(function(){
    	$(this).change(function(){
    		if ($(this).val()!="-"){
    			if (confirm("Estas seguro que lo quieres cortar?")){
    				cortar($(this).val(),$(this).data("jugador"),equipo);
    			}
    		}
    	});
    });
    $("#tablaRoster").tablesorter();
}


function loadTeam(equipo){
	$("#contenido").html("<img src='/jugones-frontend/themes/img/loader.gif' class='center'/>");
	$.ajax({
		type : "GET",
		url : "/jugones-frontend/equipos/roster.do",
		data : "id="+equipo,
		success : function (data){
	        $("#contenido").html(data);
	        initRosterPage(equipo);
	    }
	});	
}

function cortar(factor,idJugador, equipo){
	
	$("#contenido").html("<img src='/jugones-frontend/themes/img/loader.gif' class='center'/>");
	
	$.ajax({
		type : "POST",
		url : "/jugones-frontend/equipos/cortar.do",
		data : "id="+idJugador+"&factor="+factor+"&equipo="+equipo,
		success : function (data){
	        $("#contenido").html(data);
	        initRosterPage(equipo);
	    },
	    error:function(data){
	    	loadTeam(equipo);
	    }
	});	
}

function loadTeamTrade(equipo,id){
    $("#salarios_"+id).html(0);
    $("#puntos_"+id).html(0);
    $("#puntosProm_"+id).html(0);
    $("#eficiencia_"+id).html(0);
    $.ajax({
		type : "GET",
		url : "/jugones-frontend/equipos/rosterMin.do",
		data : "id="+equipo+"&posicion="+id,
		success : function (data){
			 $("#datosEquipo"+id).html(data);
	    }
	});
    $.ajax({
		type : "GET",
		url : "/jugones-frontend/ajax/logo.do",
		data : "id="+equipo,
		dataType: "json",
		success : function (data){
			$("#logoEquipo"+id).html("<img src='"+data.logo+"'/>");
	    }
	});
    
}

function calcularSalarios(posicion){
    var salarios=0;
    var puntos=0;
    var eficiencia=0;
    var jugadores=0;
    var players = "";
    $(".jugadores_"+posicion).each(function(){
        if ($(this).is(':checked')){
            //Nos quedamos con el id
            var ident=$(this).attr('id');
            var id=ident.substr(ident.indexOf("_")+1, ident.length);
            if (players == ""){
            	players = "" +id;
            } else {
            	players = players +","+id;
            }
            //Calculamos salarios, eficiencia y puntos
            jugadores++;
            salarios+=parseFloat($("#salario_"+id).val());
            puntos+=parseFloat($("#puntos_"+id).val());
            eficiencia+=parseFloat($("#eficiencia_"+id).val());
        }
    });
    $("#salarios_"+posicion).html(salarios);
    var puntosProm=puntos/jugadores;
    var efiProm=eficiencia/jugadores;
    $("#salarios_"+posicion).html(salarios.toFixed(2));
    $("#puntos_"+posicion).html(puntos.toFixed(2));
    $("#puntosProm_"+posicion).html(puntosProm.toFixed(2));
    $("#eficiencia_"+posicion).html(efiProm.toFixed(2));
    $("#players"+posicion).value = players;
}

function checkTrade(){
    var sal1=$("#salarios_1").html();
    var sal2=$("#salarios_2").html();
    if (Math.abs(sal1-sal2)<=1){
        alert('Trade valido');
        $("#datosTrade").submit();
        return true;
    } else {
        var max=Math.max(sal1,sal2);
        var limite=max*0.2;
        if (Math.abs(sal1-sal2)<=limite){
            alert('Trade valido');
            $("#datosTrade").submit();
            return true;
        } else {
            alert('Trade no valido. La diferencia ha de ser menor de '+limite.toFixed(2));
            return false;
        }
    }
}

function evaluarTrade(){
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

function evaluar(valor1,valor2,resultadoDiv){
    var logo1=$("#logoEquipo1").html();
    var logo2=$("#logoEquipo2").html();
    if (valor1<valor2){
        $("#"+resultadoDiv).html(logo1);
    } else {
        if (valor1==valor2){
            $("#"+resultadoDiv).html("<b>IGUALES</b>");
        } else {
            $("#"+resultadoDiv).html(logo2);
        }
    }
}