<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	
	<div class="col-md-8 col-sd-8 center">
		<select id="ySelect" name="ySelect">
	<c:forEach var="year" items="${years}">
	<option value="${year}" <c:if test="${y eq year}">selected</c:if>>${year}</option>
	</c:forEach>
	</select>
	<select id="tandaSelect" name="tandaSelect">	
	<option value="1" <c:if test="${t eq 1}">selected</c:if>>1</option>
	<option value="2" <c:if test="${t eq 2}">selected</c:if>>2</option>
	<option value="3" <c:if test="${t eq 3}">selected</c:if>>3</option>
	<option value="4" <c:if test="${t eq 4}">selected</c:if>>4</option>
	<option value="5" <c:if test="${t eq 5}">selected</c:if>>5</option>
	</select>


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Tanda ${t} de renovaciones de ${y}</h3>
				</div>
				<div class="panel-body table-responsive">					
					<table class="table">
						<thead>
							<tr>
								<td>Jugador</td>
								<td>Posicion</td>
								<td>Propietario</td>
								<td>Ganador</td>
								<td>Puntos</td>
								<td>Promedio</td>
								<td>Salario</td>
								<td>A&ntilde;os</td>
								<td>Estado</td>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="r" items="${renovaciones}">
								<tr class="renovacionRow" data-year="${r.year}" data-idJugador="${r.idJugador}">
									<td>${r.jugador}</td>
									<td>${r.posicion}</td>
									<td><img alt="${r.idEquipoProp}"
										src="${r.logoEquipoProp}" title="${r.idEquipoProp}" /></td>
									<td><img alt="${r.idEquipoGanador}"
										src="${r.logoEquipoGanador}" title="${r.idEquipoGanador}" /></td>
									<td>${r.puntos}</td>
									<td>${r.promedio}</td>
									<td>${r.salario}</td>
									<td>${r.years}</td>
									<td>${r.renueva}</td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<div class="col-md-4 col-sd-4" id="detalleRenovacion">


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Renovacion</h3>
				</div>
				<div class="panel-body" id="datosRenovacion"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$().ready(function() {
		$("#detalleRenovacion").hide();
		$("#ySelect").change(function() {
			seleccionar($(this).val(),$("#tandaSelect").val());
		});
		$("#tandaSelect").change(function() {
			seleccionar($("#ySelect").val(),$(this).val());
		});
		$(".renovacionRow").each(function(){
	    	$(this).click(function(){
	    		$("#detalleRenovacion").show();
	    		$("html, body").animate({ scrollTop: 0 }, "fast");
	    		$("#datosRenovacion").html("<img src='/jugones-frontend/themes/img/loader.gif' class='center'/>");
	    		$.ajax({
	    			type : "POST",
	    			url : "/jugones-frontend/renovacionDetail.do",
	    			data : "y="+$(this).data("year")+"&idJugador="+$(this).data("idjugador"),
	    			success : function (data){
	    		        $("#datosRenovacion").html(data);	    		        
	    		    }
	    			
	    		});	
	    	});
		});
	});
	
	function seleccionar(y, tanda){
		document.location.href="/jugones-frontend/renovacion.action?y="+y+"&tanda="+tanda;
	}
</script>