<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-2 col-sd-2">


	</div>
	<div class="col-md-6 col-sd-6 center">


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Derechos</h3>
				</div>
				<div class="panel-body table-responsive">

					<table id="tablaOrden" class="sort-table table">
						<thead>
							<tr>
								<th>Jugador</th>
								<th>Posicion</th>
								<th>Salario</th>
								<th>A&ntilde;os</th>
								<th>Equipo</th>
								<th>Minutos</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="derecho" items="${derechos}">
								<tr>

									<td>${derecho.jugador}</td>
									<td>${derecho.posicion}</td>
									<td>${derecho.salario}</td>
									<td>${derecho.anos}</td>
									<td>${derecho.equipo}</td>
									<td>${derecho.jugados}</td>
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
