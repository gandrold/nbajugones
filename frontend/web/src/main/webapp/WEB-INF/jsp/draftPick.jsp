<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
<form id="formPick" action="<c:url value="/pick.do"/>"
	method="post" name="formPick">

	<input type="hidden" id="ano"
		name="ano" value="${pick.ano}" />
	<input type="hidden" id="ronda"
		name="ronda" value="${pick.ronda}" />
	<input type="hidden" id="equipo"
		name="equipo" value="${pick.equipo}" />

	<div class="form-group">
		<label for="salario" class="col-sm-4 col-sd-4 control-label">Jugador</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="jugador" name="jugador"
				value="${pick.jugador}" class="form-control" />
		</div>
	</div>

	<div class="form-group">
		<label for="duracion" class="col-sm-4 col-sd-4 control-label">Letra</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="posicion" name="posicion"
				value="${pick.posicion}" class="form-control" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-md-12 col-sd-12">
			<input type="submit" value="Enviar" class="btn btn-primary" />
		</div>
	</div>
</form>
</div>