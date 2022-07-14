<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12 col-sd-12 center">
		<img alt="${jugador.nombre}"
			src="http://cdn.basketball.sports.ws/players/png/${jugador.nombreFoto}.png"
			style="height: 50px;" title="${jugador.nombre}" />
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sd-12 center">${jugador.nombre}
		(${jugador.posicion})</div>
</div>
<div class="row">
	<div class="col-md-12 col-sd-12 center">Obs: ${jugador.obs}</div>
</div>
<div class="row">
	<div class="col-md-12 col-sd-12 center">Cortado por:
		${jugador.cortadoPor}</div>
</div>
<form id="formFA" action="<c:url value="/jugadores/ficharFA.do"/>" method="post" name="formFA">
	<input type="hidden" id="idJugador" name="idJugador"
		value="${jugador.idJugador}" />
	<div class="form-group">
		<label for="salario" class="col-sm-4 col-sd-4 control-label">Salario</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="salario" name="salario" value="" class="form-control"/>
		</div>
	</div>
	<div class="form-group">
		<label for="duracion" class="col-sm-4 col-sd-4 control-label">Duracion</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="duracion" name="duracion" value="" class="form-control"/>
		</div>
	</div>
	<div class="form-group">
		<label for="fecha" class="col-sm-4 col-sd-4 control-label">Fecha (yyyy-MM-dd)</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="fecha" name="fecha" value="" class="form-control"/>
		</div>
	</div>
	<div class="form-group">
		<label for="equipo" class="col-sm-4 col-sd-4 control-label">Equipo</label>
		<div class="col-md-8 col-sd-8">
			<select id="equipo" name="equipo"  class="form-control">
				<option value="-">---</option>
				<c:forEach var="e" items="${equipos}">
					<option value="${e.key}">${e.value}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
	<div class="col-md-12 col-sd-12">
	<input type="submit" value="Enviar" class="btn btn-primary"/>
	</div>
	</div>
</form>

