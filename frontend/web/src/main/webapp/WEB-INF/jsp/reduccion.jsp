<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-md-12 col-sd-12">
		<a href="<c:url value="/seasonStats.action"/>">Ver stats</a>

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
								<th>Equipo</th>
								<th>Partidos temp. actual</th>
								<th>Minutos temp. actual</th>
								<th>Partidos temp. pasada</th>
								<th>Minutos temp. pasada</th>
								<th>Variacion de partidos</th>
								<th>Coeficiente</th>
								<th>Resultado</th>
								<th>Tipo de corte</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="stat" items="${stats}">
								<tr>
									<td>${stat.nombre} (${stat.contrato})</td>
									<td>${stat.equipo}</td>
									<td>${stat.partidosActual}</td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.minutosActual}" /></td>
									<td>${stat.partidosPasada}</td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.minutosPasada}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.percPartidos *100}" />%</td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.factor}" /></td>
									<td><fmt:formatNumber type="number"
											maxFractionDigits="2" value="${stat.resultado}" /></td>
									<td><c:choose><c:when test="${stat.resultado > 20}">GRATIS</c:when><c:otherwise>25%</c:otherwise></c:choose></td>
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