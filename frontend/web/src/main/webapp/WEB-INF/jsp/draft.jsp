<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12 col-sd-12 center">
	<h1>Draft ${y}</h1>
	</div>
</div>
<div class="row">
	<div class="col-md-2 col-sd-2 center">
	<c:forEach var="year" items="${years}">
	<p><a href = "<c:url value="/draft.action?y=${year}"/>">Draft ${year}</a></p>
	</c:forEach>
	</div>
	<div class="col-md-5 col-sd-5 center">


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Primera ronda</h3>
				</div>
				<div class="panel-body table-responsive">					
					<table class="table">
						<thead>
							<tr>
								<td>Pick</td>
								<td>Equipo</td>
								<td>Pertenece a</td>
								<td>Eleccion</td>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="ronda" items="${primeraRonda}">
								<tr>
									<td>${ronda.orden}</td>
									<td><img alt="${ronda.equipo}"
										src="${ronda.equipoLogo}" title="${ronda.equipo}" /></td>
									<td><img alt="${ronda.equipoProp}"
										src="${ronda.equipoPropLogo}" title="${ronda.equipoProp}" /></td>
									<td>${ronda.jugador} <c:if test="${not empty ronda.posicion}">(${ronda.posicion})</c:if></td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<div class="col-md-5 col-sd-5 center">


		<div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Segunda ronda</h3>
				</div>
				<div class="panel-body table-responsive">					
					<table class="table">
						<thead>
							<tr>
								<td>Pick</td>
								<td>Equipo</td>
								<td>Pertenece a</td>
								<td>Eleccion</td>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="ronda" items="${segundaRonda}">
								<tr>
									<td>${ronda.orden}</td>
									<td><img alt="${ronda.equipo}"
										src="${ronda.equipoLogo}" title="${ronda.equipo}" /></td>
									<td><img alt="${ronda.equipoProp}"
										src="${ronda.equipoPropLogo}" title="${ronda.equipoProp}" /></td>
									<td>${ronda.jugador} <c:if test="${not empty ronda.posicion}">(${ronda.posicion})</c:if></td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</div>
