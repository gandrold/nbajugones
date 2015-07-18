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
					
					<table class="table">
						<thead>
							<tr>
								<td>Jugador</td>
								<td>Posicion</td>
								<td>Salario</td>
								<td>A&ntilde;os</td>
								<td>Equipo</td>
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
		$(".seleccionarJugador").each(function() {
			$(this).change(function() {
				seleccionar($(this).val());
			});
		});
		$('form[data-async]').submit(function(event) {
	        var $form = $(this);
	        var $target = $($form.attr('data-target'));
	 		$target.html("<img src='/jugones-frontend/themes/img/loader.gif' class='center'/>");
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
