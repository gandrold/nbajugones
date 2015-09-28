<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">

</script>
<div class="row">
	<div class="col-md-12 col-sd-12 center">
		${equipo.logo}
		<p>
			<b>GM:</b> <a
				href="http://privados.miarroba.es/#!new&to=${equipo.propietario}"
				target="blank">${equipo.propietario}</a>
		</p>
		<c:if test="${evaluacion.warning}">
		<p class="alert alert-danger">Este equipo esta por encima de algun limite</p>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-md-2 col-sd-2">

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Balance salarial</h3>
			</div>
			<div class="panel-body">
				<table class="table">
					<tr>
						<td>Suma de salarios</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.sumaSalarios}" /></td>
					</tr>
					<tr>
						<td>Cortes</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.cortes}" /></td>
					</tr>
					<tr>
						<td>Lesionados</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.lesionados}" /></td>
					</tr>
					<tr>
						<td><b>TOTAL SALARIOS</b></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.totalSalarios}" /></td>
					</tr>
					<tr>
						<td>Bonus temp. anterior</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.bonusAnt}" /></td>
					</tr>
					<tr>
						<td>Bonus temp. actual</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.bonusAct}" /></td>
					</tr>
					<tr>
						<td>Sanciones</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.sanciones}" /></td>
					</tr>
					<tr>
						<td><b>LIMITE SALARIAL</b></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.limite}" /></td>
					</tr>
					<tr>
						<td><b>BALANCE</b></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2"
								value="${equipo.limite - equipo.totalSalarios}" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Historico</h3>
			</div>
			<div class="panel-body">
				<c:forEach var="hist" items="${equipo.historico}" varStatus="status">

					<p>
						<b>${hist.temporada}:</b> <br />
						${hist.ganados}-${hist.perdidos}(${hist.media}) <br />
						${hist.logros}
					</p>
					<%-- <c:if test="${hist.liga eq true}">
                                    <img alt="Campeon de liga" src="<c:url value="/themes/img/flags/flagChampion.jpg"/>"
                                         style="width:100px;"/>
                                </c:if>
                                <c:if test="${hist.conferencia eq true}">
                                    <img alt="Campeon de conferencia" src="<c:url value="/themes/img/flags/flagConf${hist.conf}.jpg"/>"
                                         style="width:100px;"/>
                                </c:if>
                                <c:if test="${hist.division eq true}">
                                    <img alt="Campeon de division" src="<c:url value="/themes/img/flags/flagDiv${hist.divi}.jpg"/>"
                                         style="width:100px;"/>
                                </c:if>
                                <c:if test="${hist.playoff eq true}">
                                    <img alt="Playoff" src="<c:url value="/themes/img/flags/flagPlayoff.jpg"/>"
                                         style="width:100px;"/>
                                </c:if>
                                <c:if test="${hist.copa eq true}">
                                    <img alt="Campeon de copa" src="<c:url value="/themes/img/flags/flagCopa.jpg"/>"
                                         style="width:100px;"/>
                                </c:if> --%>




				</c:forEach>
				<div class="page_navigation"></div>
			</div>
		</div>
	</div>
	<div class="col-md-8 col-sd-8">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Roster</h3>
			</div>
			<div class="panel-body">
				<table class="table tablesorter" id="tablaRoster">
					<thead>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>Jugador</td>
							<td>Salario</td>
							<td>A&ntilde;os</td>
							<td>Puntos</td>
							<td>FPPM</td>
							<td>Jugados</td>
							<td>Minutos</td>
							<td>Observaciones</td>
							<td>Cortar</td>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="jugador" items="${equipo.plantilla}">
							<tr id="player${jugador.idJugador}" <c:if test="${jugador.obs eq 'FA'}">class="bg-info"</c:if>>
								<td style="font-weight: bold; font-size: 1.1em">${jugador.posicion}</td>
								<td><img alt="${jugador.nombre}"
									src="http://cdn.basketball.sports.ws/players/${jugador.nombreFoto}.jpg"
									style="height: 50px;" title="${jugador.nombre}" /></td>
								<td><a
									href="http://basketball.sports.ws/player/${jugador.nombreFoto}?league=160091"
									target="blank">${jugador.nombre} </a></td>
								<td><c:if test="${jugador.obs eq 'FA'}">-</c:if> <c:if
										test="${jugador.obs ne 'FA'}">${jugador.salario}m$</c:if></td>
								<td>${jugador.years}</td>
								<td>${jugador.puntos}</td>
								<td>${jugador.promedio}</td>
								<td>${jugador.jugados}</td>
								<td>${jugador.minutos}</td>

								<td>${jugador.obs}</td>
								<td>
									<c:if test="${jugador.obs ne 'FA'}">
									<select data-jugador="${jugador.idJugador}" class="corte">
										<option value="-">----</option>
										<option value="0">Normal</option>
										<option value="0.25">25%</option>
										<option value="1">Gratis</option>
									</select>	
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			
		</div>
		<c:if test="${fn:length(equipo.derechos) gt 0}">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Derechos</h3>
					</div>
					<div class="panel-body table-responsive">
						<table class="table">
							<thead>
								<tr>
									<td>Jugador</td>
									<td>Posicion</td>
									<td>Salario</td>
									<td>A&ntilde;os</td>
									<td>Activar</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="derecho" items="${equipo.derechos}">
									<tr>
										<td>${derecho.jugador}</td>
										<td>${derecho.posicion}</td>
										<td>${derecho.salario}</td>
										<td>${derecho.anos}</td>
										<td><button onClick="javascript: activar('${derecho.jugador}','${equipo.idEquipo}');" class="btn btn-primary">Activar</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<%-- <div class="separadorHorizontal"></div>
	<div class="contenedor">
		<b>Elecciones pasadas</b>
		<table>
			<thead>
				<tr>
					<td>A&ntilde;o</td>
					<td>Ronda</td>
					<td>Equipo</td>
					<td>Jugador</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ronda" items="${equipo.elecciones}">
					<tr>
						<td>${ronda.year}</td>
						<td>${ronda.ronda}</td>
						<td>${ronda.nombreEquipo}</td>
						<td>${ronda.jugador}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> --%>
			</div>
		</c:if>
	</div>
	<div class="col-md-2 col-sd-2">
		<c:if test="${fn:length(equipo.rondas) gt 0}">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Rondas</h3>
					</div>
					<div class="panel-body table-responsive">
						<table class="table">
							<thead>
								<tr>
									<td>A&ntilde;o</td>
									<td>Ronda</td>
									<td>Equipo</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ronda" items="${equipo.rondas}">
									<tr>
										<td>${ronda.ano}</td>
										<td>${ronda.ronda}</td>
										<td>${ronda.equipo}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</c:if>
		
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Movimientos</h3>
				</div>
				<div class="panel-body">

					<ul class="content" id="historicos">
						<c:forEach var="log" items="${equipo.log}">
							<li><b><fmt:formatDate type="both" value="${log.fecha}" />:
							</b> ${log.texto}</li>
						</c:forEach>
					</ul>
					<div class="holder"></div>
				</div>
			</div>
		</div>
	</div>
</div>