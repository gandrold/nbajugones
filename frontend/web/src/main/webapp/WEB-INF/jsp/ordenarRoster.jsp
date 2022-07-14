<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
     <button onClick="javascript: ordenRoster();" class="btn btn-primary">Ordenar</button>
    <ol class='vertical'>
    <c:forEach var="jugador" items="${plantilla}">
    <li class="rosterLista" data-idjugador="${jugador.idJugador}" data-posicion="${jugador.posicion}">
        <div class="row">
         <div class="col-md-10 col-sd-10">
            <div class="col-md-2 col-sd-2">
                <h4 onclick="javascript: cambiarPosicion(${jugador.idJugador});" id="posicion${jugador.idJugador}">${jugador.posicion}</h4>
            </div>
            <div class="col-md-2 col-sd-2">
                <img alt="${jugador.nombre}" src="http://cdn.basketball.sports.ws/players/png/${jugador.nombreFoto}.png" style="height: 50px;" title="${jugador.nombre}" />
            </div>
            <div class="col-md-6 col-sd-6">
               <p>${jugador.nombre}</p>
               <p><fmt:formatNumber type="number"	maxFractionDigits="2" value="${jugador.puntos}"/> FP / <fmt:formatNumber type="number" maxFractionDigits="2" value="${jugador.promedio}"/> FPPM / <fmt:formatNumber type="number"  maxFractionDigits="1" value="${jugador.minutos}"/> min</p>
            </div>
         </div>
        </div>
    </li>
    </c:forEach>
    </ol>
</div>
<script type="text/javascript">
$("ol.vertical").sortable();
</script>

