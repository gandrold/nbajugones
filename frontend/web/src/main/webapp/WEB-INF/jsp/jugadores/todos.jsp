<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-md-3 col-sd-3">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Filtros</h3>
			</div>
			<div class="panel-body table-responsive">
				<table class="table ">

					<tr>
						<td>Posicion:</td>
						<td><select id="posicion" onchange="javascript:filtrar();">
								<option value="">---</option>
								<option value="G">G</option>
								<option value="GF">GF</option>
								<option value="F">F</option>
								<option value="FC">FC</option>
								<option value="C">C</option>
						</select>
					</tr>
					<tr>
						<td>Puntos (mas que):</td>
						<td><input id="puntos" style="width: 30px;"
							onchange="javascript:filtrar();" />
					</tr>
					<tr>
						<td>Promedio (mas que):</td>
						<td><input id="promedio" style="width: 30px;"
							onchange="javascript:filtrar();" />
					</tr>
					<tr>
						<td>Minutos (mas que):</td>
						<td><input id="minutosMas" style="width: 30px;"
							onchange="javascript:filtrar();" />
					</tr>
					<tr>
						<td>Minutos (menos que):</td>
						<td><input id="minutosMenos" style="width: 30px;"
							onchange="javascript:filtrar();" />
					</tr>
					<tr>
						<td>Jugados (mas que):</td>
						<td><input id="jugados" style="width: 30px;"
							onchange="javascript:filtrar();" />
					</tr>

				</table>
			</div>
		</div>
	</div>
	<div class="col-md-9 col-sd-9">


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Jugadores</h3>
				</div>
				<div class="panel-body table-responsive">
					<table id="tablaOrden" class="sort-table table">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>Jugador</th>
								<th>Salario</th>
								<th>A&ntilde;os</th>
								<th>Puntos</th>
								<th>FPPM</th>
								<th>Jugados</th>
								<th>Minutos</th>
								<th>Pertenece a</th>
								<th>Observaciones</th>
								<th>Cortado por</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="jugador" items="${jugadores}">
								<tr
									class="filaJugador <c:if test="${jugador.obs eq 'FA'}">bg-info</c:if>">
									<td style="font-weight: bold; font-size: 1.1em"
										class='posicion'>${jugador.posicion}</td>
									<td><img alt="${jugador.nombre}"
										src="http://sports.ws/img/headshots/png/${jugador.nombreFoto}.png"
										style="height: 50px;" title="${jugador.nombre}" /></td>
									<td class='nombre'><a
										href="<c:url value="/playerStats.action?id=${jugador.playerId}"/>"
										target="blank">${jugador.nombre} </a></td>
									<td class='salario'><c:if test="${jugador.obs eq 'FA'}">-</c:if>
										<c:if test="${jugador.obs ne 'FA'}">${jugador.salario}m$</c:if></td>
									<td class='years'>${jugador.years}</td>
									<td class='puntos'><fmt:formatNumber type="number" maxFractionDigits="2"
														value="${jugador.puntos}"/></td>
									<td class='promedio'><fmt:formatNumber type="number" maxFractionDigits="2"
														value="${jugador.promedio}"/></td>
									<td class='jugados'>${jugador.jugados}</td>
									<td class='minutos'><fmt:formatNumber type="number" maxFractionDigits="2"
															value="${jugador.minutos}"/></td>
									<td>${jugador.equipo}</td>
									<td>${jugador.obs}</td>
									<td>${jugador.cortadoPor}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){$('#tablaOrden').tablesorter();});
</script>