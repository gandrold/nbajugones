<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
	<div class="col-md-12 col-sd-12 center">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		<button onClick="javascript: checkTrade();" class="btn btn-primary">Enviar
			trade</button>
		</sec:authorize>
		<button class="btn btn-primary" data-toggle="modal"
			data-target="#evaluador">Evaluar trade</button>
	</div>
</div>
<br />
<form id="datosTrade" name="datosTrade" method="post" data-async data-target="#contenido"
	action="<c:url value="/equipos/trade.do"/>">
<div class="row">
	<div class="col-md-12 col-sd-12 center">
	<div class="form-group">
		<label for="fecha" class="col-sm-4 col-sd-4 control-label">Fecha (yyyy-MM-dd)</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="fecha" name="fecha" value="" class="form-control"/>
		</div>
	</div>
	</div>
	<div class="col-md-6 col-sd-6">
		<div class="panel panel-primary tradeBox">
			<div class="panel-heading">
				<h3 class="panel-title">Equipo numero 1</h3>
			</div>
			<div class="panel-body">
				<div class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">Equipos
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<c:forEach var="equipo" items="${equipos}">
							<li><a href="javascript:loadTeamTrade('<c:url value="/equipos/rosterMin.do"/>','<c:url value="/ajax/logo.do"/>','${equipo.key}',1)">
									${equipo.value} </a></li>
						</c:forEach>

					</ul>
				</div>



				<div id="datosEquipo1"></div>
			</div>

		</div>
	</div>
	<div class="col-md-6 col-sd-6">
		<div class="panel panel-primary tradeBox">
			<div class="panel-heading">
				<h3 class="panel-title">Equipo numero 2</h3>
			</div>
			<div class="panel-body">
				<div class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">Equipos
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<c:forEach var="equipo" items="${equipos}">
							<li><a href="javascript:loadTeamTrade('<c:url value="/equipos/rosterMin.do"/>','<c:url value="/ajax/logo.do"/>','${equipo.key}',2)">
									${equipo.value} </a></li>
						</c:forEach>

					</ul>
				</div>



				<div id="datosEquipo2"></div>
			</div>

		</div>
	</div>


</div>

</form>
<div class="modal fade" id="evaluador" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Trade evaluator</h4>
			</div>
			<div class="modal-body">
				<p>Los valores que aparecen en esta tabla son los que entrega
					cada equipo</p>

				<table class="table">
					<thead>
						<tr>
							<th>&nbsp;</th>
							<th id="logoEquipo1"></th>
							<th id="logoEquipo2"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><b>Puntos totales</b></td>
							<td id="puntos_1"></td>
							<td id="puntos_2"></td>
						</tr>
						<tr>
							<td><b>Puntos promedio</b></td>
							<td id="puntosProm_1"></td>
							<td id="puntosProm_2"></td>
						</tr>
						<tr>
							<td><b>Eficiencia promedio</b></td>
							<td id="eficiencia_1"></td>
							<td id="eficiencia_2"></td>
						</tr>
					</tbody>
				</table>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

