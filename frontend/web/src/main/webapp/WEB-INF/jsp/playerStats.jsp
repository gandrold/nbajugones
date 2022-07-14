<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-md-3 col-sd-3 center">
		<div class="row roundedDiv">
		    <div class="row center">
		    <h3>${playerStats.jugador.nombre}</h3>
		    </div>
		    <div class="row">
			<div class="col-md-3 col-sd-3 center">
				<img alt="${playerStats.jugador.nombre}"
					src="http://cdn.basketball.sports.ws/players/png/${playerStats.jugador.nombreFoto}.png"
					style="margin-left: 50%; width:75px;" title="${playerStats.jugador.nombre}" />
			</div>
			<div class="col-md-9 col-sd-9 center">
				<p>Equipo Jugones: <span id="equipoJugones"></span></p>
				<p>Equipo NBA: <c:forEach var="equipo" items="${playerStats.currentSeasonTeams}"><span id="equipoNBA${equipo}"></span></c:forEach></p>
			</div>
			</div>
		</div>
		<div class="row roundedDiv">
			<h4>Media temporada actual</h4>
			<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.lastSeasonAvg.puntos}"/> pts /
			<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.lastSeasonAvg.rebOf + playerStats.lastSeasonAvg.rebDef}"/> reb /
			<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.lastSeasonAvg.asistencias}"/> ast /
			<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.lastSeasonAvg.minutos}"/> min.
			<h4>Media hoops</h4>
			<p><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.hoopsAvg}"/> /
				<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.fppmAvg}"/></p>
			<h4>Reduccion de minutos</h4>
			<p><fmt:formatNumber type="number"
            									maxFractionDigits="2" value="${reduccion.minutosPasada}"/> - (${reduccion.factor} *
            				<fmt:formatNumber type="number"
            									maxFractionDigits="2" value="${reduccion.minutosActual}"/>) =
            <fmt:formatNumber type="number" maxFractionDigits="2" value="${reduccion.resultado}"/>
            </p>
            <p><b><c:choose><c:when test="${reduccion.resultado > 20}">CORTE GRATIS</c:when>
            <c:otherwise><c:choose><c:when test="${reduccion.resultado > 12}">CORTE AL 25%</c:when>
                                     <c:otherwise>CORTE NORMAL</c:otherwise></c:choose></c:otherwise></c:choose></b></p>
		</div>
		<div class="row roundedDiv">
			<div class="col-md-4 col-sd-4 center">
			<h4>Last 5 </h4>
			<p><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.hoopsLast5}"/> <c:if test="${playerStats.hoopsLast5 - playerStats.hoopsAvg gt 3}">&#8679;</c:if><c:if test="${playerStats.hoopsAvg - playerStats.hoopsLast5 gt 3}">&#8681;</c:if> /
				<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.fppmLast5}"/> <c:if test="${playerStats.fppmLast5 - playerStats.fppmAvg gt 0.1}">&#8679;</c:if><c:if test="${playerStats.fppmAvg - playerStats.fppmLast5 gt 0.1}">&#8681;</c:if></p>

			</div>
			<div class="col-md-4 col-sd-4 center">
			<c:if test="${playerStats.lastSeasonAvg.jugados gt 9}">
			<h4>Last 10 </h4>
			<p><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.hoopsLast10}"/> <c:if test="${playerStats.hoopsLast10 - playerStats.hoopsAvg gt 3}">&#8679;</c:if><c:if test="${playerStats.hoopsAvg - playerStats.hoopsLast10 gt 3}">&#8681;</c:if> /
				<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.fppmLast10}"/> <c:if test="${playerStats.fppmLast10 - playerStats.fppmAvg gt 0.1}">&#8679;</c:if><c:if test="${playerStats.fppmAvg - playerStats.fppmLast10 gt 0.1}">&#8681;</c:if></p>
			</c:if>
			&nbsp;
			</div>
			<div class="col-md-4 col-sd-4 center">
			<c:if test="${playerStats.lastSeasonAvg.jugados gt 19}">
			<h4>Last 20 </h4>
			<p><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.hoopsLast20}"/> <c:if test="${playerStats.hoopsLast20 - playerStats.hoopsAvg gt 3}">&#8679;</c:if><c:if test="${playerStats.hoopsAvg - playerStats.hoopsLast20 gt 3}">&#8681;</c:if> /
				<fmt:formatNumber type="number"
									maxFractionDigits="2" value="${playerStats.fppmLast20}"/> <c:if test="${playerStats.fppmLast20 - playerStats.fppmAvg gt 0.1}">&#8679;</c:if><c:if test="${playerStats.fppmAvg - playerStats.fppmLast20 gt 0.1}">&#8681;</c:if></p>
			</c:if>
			&nbsp;
			</div>
			<div class="col-md-12 col-sd-12 center">
			<h4>Evolucion</h4>
			<div id="fppg" class="demo-placeholder" style="height: 300px;"></div>
			</div>
			<div class="col-md-12 col-sd-12 center">
			<h4>Otras temporadas</h4>
            	<c:forEach var="year" items="${years}">
            	    <c:if test="${year ne temporada}">
                	<p><a href="<c:url value="/playerStats.action?id=${playerStats.jugador.playerId}&temporada=${year}"/>">${year}-${year+1}</a></p>
                	</c:if>
                </c:forEach>
            </div>
		</div>
	</div>
	<div class="col-md-9 col-sd-9 center">
		<div class="col-md-12 col-sd-12 center boxscoreTable roundedDiv">
			<h4>Temporada ${temporada}-${temporada+1}</h4>
			<table class="table">
				<thead>
					<tr>
						<td>N</td>
						<td>Fecha</td>
						<td>Contra</td>
						<td>Minutos</td>
						<td>Puntos</td>
						<td>3PM/3PA</td>
						<td>2PM/2PA</td>
						<td>FTM/FTA</td>
						<td>Rebotes</td>
						<td>Asist.</td>
						<td>Robos</td>
						<td>Tapones</td>
						<td>Faltas</td>
						<td>Perdidas</td>
						<td>Hoops</td>
						<td>FPPM</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stats" items="${playerStats.currentSeasonStats}">
						<tr>
							<td>${stats.match}</td>
							<td>${stats.date}</td>
							<td>${stats.against} (<c:choose><c:when test="${stats.win}">W</c:when><c:otherwise>L</c:otherwise></c:choose>)</td>
							<td>${stats.minutos}</td>
							<td>${stats.puntos}</td>
							<td>${stats.tpm} / ${stats.tpa}</td>
							<td>${stats.fgm} / ${stats.fga}</td>
							<td>${stats.ftm} / ${stats.fta}</td>
							<td>${stats.rebOf + stats.rebDef}</td>
							<td>${stats.asistencias}</td>
							<td>${stats.robos}</td>
							<td>${stats.tapones}</td>
							<td>${stats.faltas}</td>
							<td>${stats.perdidas}</td>
							<td> <fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.puntuacionHoops}" /></td>
							<td> <fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.fppm}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br/>
		<div class="col-md-12 col-sd-12 center boxscoreTable roundedDiv">
			<h4>Media historica</h4>
			<table class="table">
				<thead>
					<tr>
						<td>Temp.</td>
						<td>Jugados</td>
						<td>Minutos</td>
						<td>Puntos</td>
						<td>Reb. of.</td>
						<td>Reb. def</td>
						<td>Asist.</td>
						<td>Robos</td>
						<td>Tapones</td>
						<td>Faltas</td>
						<td>Perdidas</td>
						<td>2P %</td>
						<td>3P %</td>
						<td>FT %</td>
						<td>Hoops</td>
						<td>FPPM</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stats" items="${playerStats.careerStatsDTO}">
						<tr>
							<td>${stats.season}</td>
							<td>${stats.jugados}</td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.minutos}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.puntos}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.rebOf}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.rebDef}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.asistencias}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.robos}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.tapones}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.faltas}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.perdidas}" /></td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.fgPerc}" />%</td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.tpPerc}" />%</td>
							<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.ftPerc}" />%</td>
							<td> <fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.puntuacionHoops}" /></td>
							<td> <fmt:formatNumber type="number"
									maxFractionDigits="2" value="${stats.fppm}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	$().ready(function() {
		loadTeamLogo('<c:url value="/ajax/logo.do"/>', "equipoJugones","${equipoJugones}");
		<c:forEach var="equipo" items="${playerStats.currentSeasonTeams}">
		loadTeamLogo('<c:url value="/ajax/logo.do"/>',"equipoNBA${equipo}","${equipo}");
		</c:forEach>
		var d1 = [];
		var d2 = [];
		<c:set var="match" value="${playerStats.lastSeasonAvg.jugados}"/>
		<c:forEach var="stats" items="${playerStats.currentSeasonStats}">
		d1.push(['${match}', <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.puntuacionHoops}" />]);
		d2.push(['${match}', <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.fppm}" />]);
		<c:set var="match" value="${match - 1}"/>
		</c:forEach>
		$.plot("#fppg", [ {data: d1, label: "FPPG"}, {data: d2, label: "FPPM", yaxis: 2} ] , { grid: {
				hoverable: true,
				clickable: true
			}, legend: { position: "sw" },yaxes: [ { min: 0 }, {
					// align if we are to the right
					alignTicksWithAxis: 1,
					position: "right"
				} ],});
		$("<div id='tooltip'></div>").css({
			position: "absolute",
			display: "none",
			border: "1px solid #fdd",
			padding: "2px",
			"background-color": "#fee",
			opacity: 0.80
		}).appendTo("body");
		$("#fppg").bind("plothover", function (event, pos, item) {
			if (item) {
				var x = item.datapoint[0].toFixed(2),
						y = item.datapoint[1].toFixed(2);

				$("#tooltip").html("Day " + x + " = " + y)
						.css({top: item.pageY + 5, left: item.pageX + 5})
						.fadeIn(200);
			} else {
				$("#tooltip").hide();
			}
		});
	});
</script>