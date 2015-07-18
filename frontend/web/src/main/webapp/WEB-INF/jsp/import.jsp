<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<div class="col-md-6 col-sd-6">
		<form id="formFA" action="/jugones-frontend/upload.do" method="post"
			data-async data-target="#contenido"
			enctype="multipart/form-data" name="formFA">

			<div class="form-group">
				<label for="salario" class="col-sm-4 col-sd-4 control-label">Selecciona
					el fichero</label>
				<div class="col-md-8 col-sd-8">
					<input type="file" name="csv" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12 col-sd-12">
					<input type="submit" value="Enviar" class="btn btn-primary" />
				</div>
			</div>
		</form>
	</div>
	<c:if test="${not empty  export}">
		<div class="col-md-6 col-sd-6">
			<c:choose>
				<c:when test="${export.ok}">
					<h3>La carga se ha realizado correctamente</h3>
				</c:when>
				<c:otherwise>
					<h3>Se han registrado las siguientes incidencias</h3>
					<c:if test="${fn:length(export.derechos) gt 0}">
						<b>Derechos</b>
						<ul>
							<c:forEach var="derecho" items="${export.derechos}">
								<li>${derecho.jugador} (${derecho.equipo}), ha jugado ${derecho.jugados}
									partidos</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${fn:length(export.jugadoresNuevos) gt 0}">
						<b>Jugadores nuevos</b>
						<ul>
							<c:forEach var="jugador" items="${export.jugadoresNuevos}">
								<li>${jugador}</li>
							</c:forEach>
						</ul>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
</div>
