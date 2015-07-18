<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<input type="hidden" id="equipo${posicion}" name="equipo${posicion}" value="${idEquipo}"/>
<div class="row">
	<div class="col-md-12 col-sd-12 center">
		<p>
			Salarios seleccionados ${idEquipo}: <span id="salarios_${posicion}">0</span>
		</p>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sd-12 table-responsive">
		<table class="table">
			<thead>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="jugador" items="${equipo.plantilla}">
					<c:if test="${jugador.obs ne 'FA'}">
						<tr>
							<td><input type="checkbox" id="jugador_${jugador.idJugador}"
								class="jugadores_${posicion}" name="players${posicion}"
								value="${jugador.idJugador}"
								onclick="javascript:calcularSalarios(${posicion})"></td>
							<td><img alt="${jugador.nombre}"
								src="http://cdn.basketball.sports.ws/players/${jugador.nombreFoto}.jpg"
								style="height: 50px;" title="${jugador.nombre}" /></td>
							<td><span style="font-weight: bold"><a
									href="http://basketball.sports.ws/player/${jugador.nombreFoto}?league=140043"
									target="blank">${jugador.nombre} </a>(${jugador.posicion})
									${jugador.salario} m$ en ${jugador.years}</span>

								<p>
									${jugador.puntos}PG / ${jugador.promedio} PM,
									${jugador.jugados} jugados, ${jugador.minutos} minutos
									<c:if test="${not empty jugador.obs}">(${jugador.obs})</c:if>
								</p></td>


						</tr>
						<input type="hidden" id="salario_${jugador.idJugador}"
							value="${jugador.salario}" />
						<input type="hidden" id="puntos_${jugador.idJugador}"
							value="${jugador.puntos}" />
						<input type="hidden" id="eficiencia_${jugador.idJugador}"
							value="${jugador.promedio}" />
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<c:if test="${fn:length(equipo.rondas) gt 0}">
<div class="row">
	<div class="col-md-12 col-sd-12 table-responsive">
		<table class="table">

			<thead>
				<tr>
					<td>&nbsp;</td>
					<td>A&ntilde;o</td>
					<td>Ronda</td>
					<td>Equipo</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ronda" items="${equipo.rondas}">
					<tr>
						<td><input type="checkbox" name="rondas${posicion}"
								value="${ronda.ano}_${ronda.ronda}_${ronda.idEquipo}"
							id="ronda_${ronda.ano}_${ronda.ronda}_${ronda.idEquipo}" class="ronda_${posicion}"></td>
						<td>${ronda.ano}</td>
						<td>${ronda.ronda}</td>
						<td>${ronda.equipo}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</c:if>
<c:if test="${fn:length(equipo.derechos) gt 0}">
<div class="row">
	<div class="col-md-12 col-sd-12 table-responsive">
		<table class="table">
			<thead>
				<tr>
					<td>&nbsp;</td>
					<td>Jugador</td>
					<td>Posicion</td>
					<td>Salario</td>
					<td>A&ntilde;os</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="derecho" items="${equipo.derechos}">
					<tr>
						<td><input type="checkbox" id="derechos_${derecho.jugador}"
							name="derechos${posicion}"
								value="${derecho.jugador}"
							class="derechos_${posicion}"></td>

						<td>${derecho.jugador}</td>
						<td>${derecho.posicion}</td>
						<td>${derecho.salario}</td>
						<td>${derecho.anos}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>
</c:if>
