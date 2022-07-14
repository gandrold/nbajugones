<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row" style="padding:5px;">
     <div class="col-md-6 col-sd-6 center">
    <div class="col-md-12 col-sd-12 center" data-toggle="tooltip" data-placement="bottom" title="FG: <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 * boxscore.awayStats.fgm/boxscore.awayStats.fga}" />% / 3P: <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 * boxscore.awayStats.tpm/boxscore.awayStats.tpa}" />% / FT: <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 * boxscore.awayStats.ftm/boxscore.awayStats.fta}" />% / Asist: ${boxscore.awayStats.asistencias} / Reb of: ${boxscore.awayStats.rebOf} / Reb def: ${boxscore.awayStats.rebDef} / Robos: ${boxscore.awayStats.robos} / Tapones: ${boxscore.awayStats.tapones} / Faltas: ${boxscore.awayStats.faltas}">
        <img src="${boxscore.away.logoDraft}" title="${boxscore.away.nombre}"/>
        <h2>${boxscore.awayScore}</h2>
        </div>
	<div class="col-md-12 col-sd-12 center boxscoreTable">

	<table class="table" id="tablaRoster">
					<thead>
						<tr>
							<td>&nbsp;</td>
							<td>Jugador</td>
							<td>Minutos</td>
							<td>Hoops</td>
							<td>Puntos</td>
							<td>3PM/3PA</td>
							<td>FGM/FGA</td>
							<td>FTM/FTA</td>
							<td>Reb. of.</td>
							<td>Reb. def</td>
							<td>Asist.</td>
							<td>Robos</td>
							<td>Tapones</td>
							<td>Faltas</td>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="stats" items="${boxscore.awayStarters}">
							<tr>
								<td><img alt="${stats.jugador.nombre}"
									src="http://cdn.basketball.sports.ws/players/png/${stats.jugador.nombreFoto}.png"
									style="height: 40px;" title="${stats.jugador.nombre}" /></td>
								<td><a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank">${stats.jugador.nombre}</a></td>

								<td>${stats.minutos}</td>
								<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.puntuacionHoops}" /></td>
								<td>${stats.puntos}</td>
								<td>${stats.tpm} / ${stats.tpa}</td>
								<td>${stats.fgm} / ${stats.fga}</td>
								<td>${stats.ftm} / ${stats.fta}</td>
								<td>${stats.rebOf}</td>
								<td>${stats.rebDef}</td>
								<td>${stats.asistencias}</td>
								<td>${stats.robos}</td>
								<td>${stats.tapones}</td>
								<td>${stats.faltas}</td>
							</tr>
						</c:forEach>
						<tr class="bench">
						<td colspan="14">&nbsp;</td>
						</tr>
						<c:forEach var="stats" items="${boxscore.awayBench}">
							<tr>
								<td><img alt="${stats.jugador.nombre}"
									src="http://cdn.basketball.sports.ws/players/${stats.jugador.nombreFoto}.jpg"
									style="height: 40px;" title="${stats.jugador.nombre}" /></td>
								<td><a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank">${stats.jugador.nombre}</a></td>

								<td>${stats.minutos}</td>
								<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.puntuacionHoops}" /></td>
								<td>${stats.puntos}</td>
								<td>${stats.tpm} / ${stats.tpa}</td>
								<td>${stats.fgm} / ${stats.fga}</td>
								<td>${stats.ftm} / ${stats.fta}</td>
								<td>${stats.rebOf}</td>
								<td>${stats.rebDef}</td>
								<td>${stats.asistencias}</td>
								<td>${stats.robos}</td>
								<td>${stats.tapones}</td>
								<td>${stats.faltas}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	</div>
</div>

<div class="col-md-6 col-sd-6 center">
    <div class="col-md-12 col-sd-12 center" data-toggle="tooltip" data-placement="bottom" title="FG: <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 * boxscore.homeStats.fgm/boxscore.homeStats.fga}" />% / 3P: <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 * boxscore.homeStats.tpm/boxscore.homeStats.tpa}" />% / FT: <fmt:formatNumber type="number" maxFractionDigits="2" value="${100 * boxscore.homeStats.ftm/boxscore.homeStats.fta}" />% / Asist: ${boxscore.homeStats.asistencias} / Reb of: ${boxscore.homeStats.rebOf} / Reb def: ${boxscore.homeStats.rebDef} / Robos: ${boxscore.homeStats.robos} / Tapones: ${boxscore.homeStats.tapones} / Faltas: ${boxscore.homeStats.faltas}">
            <img src="${boxscore.home.logoDraft}" title="${boxscore.home.nombre}"/>
            <h2>${boxscore.homeScore}</h2>
            </div>
	<div class="col-md-12 col-sd-12 center boxscoreTable">
	<table class="table" id="tablaRoster">
					<thead>
						<tr>
							<td>&nbsp;</td>
							<td>Jugador</td>
							<td>Minutos</td>
							<td>Hoops</td>
							<td>Puntos</td>
							<td>3PM/3PA</td>
							<td>FGM/FGA</td>
							<td>FTM/FTA</td>
							<td>Reb. of.</td>
							<td>Reb. def</td>
							<td>Asist.</td>
							<td>Robos</td>
							<td>Tapones</td>
							<td>Faltas</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="stats" items="${boxscore.homeStarters}">
							<tr>
								<td><img alt="${stats.jugador.nombre}"
									src="http://cdn.basketball.sports.ws/players/${stats.jugador.nombreFoto}.jpg"
									style="height: 40px;" title="${stats.jugador.nombre}" /></td>
								<td><a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank">${stats.jugador.nombre}</a></td>

								<td>${stats.minutos}</td>
								<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.puntuacionHoops}" /></td>
								<td>${stats.puntos}</td>
								<td>${stats.tpm} / ${stats.tpa}</td>
								<td>${stats.fgm} / ${stats.fga}</td>
								<td>${stats.ftm} / ${stats.fta}</td>
								<td>${stats.rebOf}</td>
								<td>${stats.rebDef}</td>
								<td>${stats.asistencias}</td>
								<td>${stats.robos}</td>
								<td>${stats.tapones}</td>
								<td>${stats.faltas}</td>
							</tr>
						</c:forEach>
						<tr class="bench">
						<td colspan="14">&nbsp;</td>
						</tr>
						<c:forEach var="stats" items="${boxscore.homeBench}">
							<tr>
								<td><img alt="${stats.jugador.nombre}"
									src="http://cdn.basketball.sports.ws/players/${stats.jugador.nombreFoto}.jpg"
									style="height: 40px;" title="${stats.jugador.nombre}" /></td>
								<td><a href="<c:url value="/playerStats.action?id=${stats.jugador.playerId}"/>" target="_blank">${stats.jugador.nombre}</a></td>

								<td>${stats.minutos}</td>
								<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.puntuacionHoops}" /></td>
								<td>${stats.puntos}</td>
								<td>${stats.tpm} / ${stats.tpa}</td>
								<td>${stats.fgm} / ${stats.fga}</td>
								<td>${stats.ftm} / ${stats.fta}</td>
								<td>${stats.rebOf}</td>
								<td>${stats.rebDef}</td>
								<td>${stats.asistencias}</td>
								<td>${stats.robos}</td>
								<td>${stats.tapones}</td>
								<td>${stats.faltas}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	</div>
</div>
</div>
<script type="text/javascript">
$('[data-toggle="tooltip"]').tooltip()
</script>


