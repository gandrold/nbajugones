<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="formFA" action="<c:url value="/jugadores/new.do"/>" method="post" name="formFA">

	<div class="form-group">
		<label for="salario" class="col-sm-4 col-sd-4 control-label">Nombre</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="nombre" name="nombre" value="" class="form-control"/>
		</div>
	</div>
	<div class="form-group">
		<label for="duracion" class="col-sm-4 col-sd-4 control-label">Posicion</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="posicion" name="posicion" value="" class="form-control"/>
		</div>
	</div>

	<div class="form-group">
	<div class="col-md-12 col-sd-12">
	<input type="submit" value="Enviar" class="btn btn-primary"/>
	</div>
	</div>
</form>

