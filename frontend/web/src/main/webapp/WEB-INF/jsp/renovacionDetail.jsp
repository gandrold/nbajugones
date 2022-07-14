<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="formRenovacion" action="<c:url value="/renovacion.do"/>"
	method="post" name="formRenovacion">
	<input type="hidden" id="idJugador" name="idJugador"
		value="${renovacion.idJugador}" />
	<input type="hidden" id="year"
		name="year" value="${renovacion.year}" />
	<input type="hidden" id="tanda"
		name="tanda" value="${renovacion.tanda}" />
	<input type="hidden" id="idEquipoProp"
		name="idEquipoProp" value="${renovacion.idEquipoProp}" />
	<div class="form-group">
		<div class="col-sm-4 col-sd-4 control-label"><b>Jugador</b></div>
		<div class="col-md-8 col-sd-8">${renovacion.jugador}</div>
	</div>
	<div class="form-group">
		<label for="salario" class="col-sm-4 col-sd-4 control-label">Salario</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="salario" name="salario"
				value="${renovacion.salario}" class="form-control" />
		</div>
	</div>

	<div class="form-group">
		<label for="duracion" class="col-sm-4 col-sd-4 control-label">Duracion</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="years" name="years"
				value="${renovacion.years}" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="equipo" class="col-sm-4 col-sd-4 control-label">Equipo
			ganador</label>
		<div class="col-md-8 col-sd-8">
			<select id="idEquipoGanador" name="idEquipoGanador"
				class="form-control">
				<option value="-">---</option>
				<c:forEach var="e" items="${equipos}">
					<option value="${e.key}"
						<c:if test="${renovacion.idEquipoGanador eq e.key}">selected</c:if>>${e.value}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="renueva" class="col-sm-4 col-sd-4 control-label">Estado</label>
		<div class="col-md-8 col-sd-8">
			<select id="renueva" name="renueva"
				class="form-control">
				<option value="">---</option>
				<option value="RENUEVA"
					<c:if test="${renovacion.renueva eq 'RENUEVA'}">selected</c:if>>RENUEVA</option>
				<option value="FICHADO"
					<c:if test="${renovacion.renueva eq 'FICHADO'}">selected</c:if>>FICHADO</option>
				<option value="FA"
					<c:if test="${renovacion.renueva eq 'FA'}">selected</c:if>>FA</option>
			</select>
		</div>
	</div>
	<c:if test="${empty renovacion.renueva}">
	<div class="form-group">
		<div class="col-md-12 col-sd-12">
			<input type="submit" value="Enviar" class="btn btn-primary" />
		</div>
	</div>
	</c:if>
</form>