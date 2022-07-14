<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
	<div class="col-md-8 col-sd-8 center">


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">FA's</h3>
				</div>
				<div class="panel-body table-responsive">
					<form class="form-inline" id="buscarFA"
						data-async data-target="#contenido" action='<c:url value="/jugadores/buscarFA.do"/>' method="POST">
						<div class="form-group">
							<label for="query">Nombre</label> <input type="text"
								class="form-control" id="query" name="query"
								placeholder="Jane Doe">
						</div>

						<button type="submit" class="btn btn-default">Buscar</button>
					</form>
					<table class="table">
						<thead>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>Jugador</td>
								<td>Puntos</td>
								<td>FPPM</td>
								<td>Jugados</td>
								<td>Minutos</td>
								<td>Observaciones</td>
								<td>Cortado por</td>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								<td>&nbsp;</td>
								</sec:authorize>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="jugador" items="${fa}">
								<tr>
									<td style="font-weight: bold; font-size: 1.1em">${jugador.posicion}</td>
									<td><img alt="${jugador.nombre}"
										src="http://sports.ws/img/headshots/png/${jugador.nombreFoto}.png"
										style="height: 50px;" title="${jugador.nombre}" /></td>
									<td><a
										href="<c:url value="/playerStats.action?id=${jugador.playerId}"/>"
										target="blank">${jugador.nombre} </a></td>
									<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${jugador.puntos}"/></td>
									<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${jugador.promedio}"/></td>
									<td>${jugador.jugados}</td>
									<td><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${jugador.minutos}"/></td>
									<td>${jugador.obs}</td>
									<td>${jugador.cortadoPor}</td>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
									<td><input type="checkbox" value="${jugador.idJugador}"
										class="seleccionarJugador" /></td>
									</sec:authorize>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<div class="col-md-4 col-sd-4" id="detalleJugador">


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Jugador</h3>
				</div>
				<div class="panel-body" id="datosJugador"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$().ready(function() {
		$("#detalleJugador").hide();
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		$(".seleccionarJugador").each(function() {
			$(this).change(function() {
				seleccionar('<c:url value="/jugadores/seleccionar.do"/>','<c:url value="/themes/img/loader.gif"/>',$(this).val());
			});
		});
		</sec:authorize>
		$('form[data-async]').submit(function(event) {
	        var $form = $(this);
	        var $target = $($form.attr('data-target'));
	 		$target.html("<img src='<c:url value='/themes/img/loader.gif'/>' class='center'/>");
	        $.ajax({
	            type: $form.attr('method'),
	            url: $form.attr('action'),
	            data: $form.serialize(),

	            success: function(data, status) {
	                $target.html(data);
	            }
	        });

	        event.preventDefault();
	    });
	});
</script>
