<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">

	<div class="col-md-12 col-sd-12">
    <h3><img alt="${boxscore.away.nombre}" src="${boxscore.away.logoDraft}" title="${boxscore.away.nombre}" />${boxscore.away.nombre} <fmt:formatNumber type="number" maxFractionDigits="1" value="${boxscore.awayHoopsScore}"/></h3>
    <p>Minutos sobrantes: ${boxscore.minutesAway['G']} / ${boxscore.minutesAway['F']} / ${boxscore.minutesAway['C']}</p>
    <div class="row">
    <c:forEach var="stats" items="${boxscore.awayStarters}">
    <div class="col-md-1 col-sd-1">
    <c:choose>
    <c:when test="${stats.minutos > 0}">
    <div class="thumbnail center playerStats" data-toggle="tooltip" data-placement="top" title="${stats.jugador.nombre} contra ${stats.against} (<c:choose><c:when test='${stats.win}'>W</c:when><c:otherwise>L</c:otherwise></c:choose>): ${stats.minutos} minutos, ${stats.puntos} pts, ${stats.rebOf + stats.rebDef} reb, ${stats.asistencias} asist, ${stats.robos} robos, ${stats.tapones} tapones,${stats.faltas} faltas">
        <a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank"><img alt="${stats.jugador.nombre}" src="http://cdn.basketball.sports.ws/players/png/${stats.jugador.nombreFoto}.png"
            style="height: 50px;" title="${stats.jugador.nombre}" />
        </a><p><b>${stats.jugador.firstName}</b></p><p style="margin-top:-13px;"><b>${stats.jugador.lastName}</b></p> <p>${stats.posicionHoops}</p>
        <p><fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.realPuntuacionHoops}"/> FP</p>
        <p><fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.fppm}"/> FPPM</p>
        <p>${stats.minutosG} / ${stats.minutosF} / ${stats.minutosC}</p>
    </div>
    </c:when>
    <c:otherwise>
    <div class="thumbnail center playerStats" data-toggle="tooltip" data-placement="top" title="${stats.jugador.nombre}: DNP">
            <a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank"><img alt="${stats.jugador.nombre}" src="http://cdn.basketball.sports.ws/players/png/${stats.jugador.nombreFoto}.png"
                style="height: 50px;" title="${stats.jugador.nombre}" />
        </a><p><b>${stats.jugador.firstName}</b></p><p style="margin-top:-13px;"><b>${stats.jugador.lastName}</b></p> <p>${stats.posicionHoops}</p>
            <p>DNP</p>
        </div>
    </c:otherwise>
    </c:choose>
    </div>
    </c:forEach>
    </div>
	<div class="col-md-12 col-sd-12">
    <h3><img alt="${boxscore.home.nombre}" src="${boxscore.home.logoDraft}" title="${boxscore.home.nombre}" /> ${boxscore.home.nombre} <fmt:formatNumber type="number" maxFractionDigits="1" value="${boxscore.homeHoopsScore}"/></h3>
    <p>Minutos sobrantes: ${boxscore.minutesHome['G']} / ${boxscore.minutesHome['F']} / ${boxscore.minutesHome['C']}</p>
    <div class="row">
    <c:forEach var="stats" items="${boxscore.homeStarters}">
    <div class="col-md-1 col-sd-1">
    <c:choose>
    <c:when test="${stats.minutos > 0}">
    <div class="thumbnail center playerStats" data-toggle="tooltip" data-placement="top" title="${stats.jugador.nombre} contra ${stats.against} (<c:choose><c:when test='${stats.win}'>W</c:when><c:otherwise>L</c:otherwise></c:choose>): ${stats.minutos} minutos, ${stats.puntos} pts, ${stats.rebOf + stats.rebDef} reb, ${stats.asistencias} asist, ${stats.robos} robos, ${stats.tapones} tapones,${stats.faltas} faltas">
        <a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank"><img alt="${stats.jugador.nombre}" src="http://cdn.basketball.sports.ws/players/png/${stats.jugador.nombreFoto}.png"
            style="height: 50px;" title="${stats.jugador.nombre}" />
        </a><p><b>${stats.jugador.firstName}</b></p><p style="margin-top:-13px;"><b>${stats.jugador.lastName}</b></p> <p>${stats.posicionHoops}</p>
        <p><fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.realPuntuacionHoops}"/> FP</p>
        <p><fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.fppm}"/> FPPM</p>
        <p>${stats.minutosG} / ${stats.minutosF} / ${stats.minutosC}</p>
    </div>
    </c:when>
    <c:otherwise>
    <div class="thumbnail center playerStats" data-toggle="tooltip" data-placement="top" title="${stats.jugador.nombre}: DNP">
            <a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank"><img alt="${stats.jugador.nombre}" src="http://cdn.basketball.sports.ws/players/png/${stats.jugador.nombreFoto}.png"
                style="height: 50px;" title="${stats.jugador.nombre}" />
        </a><p><b>${stats.jugador.firstName}</b></p><p style="margin-top:-13px;"><b>${stats.jugador.lastName}</b></p> <p>${stats.posicionHoops}</p>
            <p>DNP</p>
        </div>
    </c:otherwise>
    </c:choose>
    </div>
    </c:forEach>
    </div>
</div>
<script type="text/javascript">
$('[data-toggle="tooltip"]').tooltip()
</script>
