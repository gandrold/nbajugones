<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-md-12 col-sd-12">
		<a href="<c:url value="/reduccion.action"/>">Ver reduccion de minutos</a>

		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Stats</h3>
				</div>
				<div class="panel-body table-responsive">
					<table class="table sort-table" id="tablaOrden">
						<thead>
							<tr>
								<th>Jugador</th>
								<th>Jugados</th>
								<th>Minutos</th>
								<th>Puntos</th>
								<th>Reb. of.</th>
								<th>Reb. def.</th>
								<th>Reb</th>
								<th>Asist.</th>
								<th>Robos</th>
								<th>Tapones</th>
								<th>Faltas</th>
								<th>Perdidas</th>
								<th>2P %</th>
								<th>3P %</th>
								<th>FT %</th>
								<th>Hoops</th>
								<th>FPPM</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="stat" items="${stats}">
								<tr>
									<td><a href="<c:url value="/playerStats.action?id=${stat.idJugador}"/>">${stat.nombre}</a></td>
									<td>${stat.jugados}</td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.minutos}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.puntos}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.rebOf}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.rebDef}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.rebDef + stat.rebOf}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.asistencias}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.robos}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.tapones}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.faltas}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.perdidas}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.fgPerc}" />%</td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.tpPerc}" />%</td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.ftPerc}" />%</td>
									<td> <fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.puntuacionHoops}" /></td>
									<td> <fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.fppm}" /></td>
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