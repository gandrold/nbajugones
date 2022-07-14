<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
								<tr class="draftRow" data-year="${y}" data-ronda="1" data-equipo="${ronda.idEquipo}"
									data-toggle="modal" data-target="#pick">
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
								<tr class="draftRow" data-year="${y}" data-ronda="2" data-equipo="${ronda.idEquipo}"
									data-toggle="modal" data-target="#pick">
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
<div class="modal fade modal-box" id="pick" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-dialog-box">
		<div class="modal-content modal-content-box">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Pick</h4>
			</div>
			<div class="modal-body" id="pickBody">
			<div class="row">
<form id="formPick" action="<c:url value="/pick.do"/>"
	method="post" name="formPick">

	<input type="hidden" id="y"
		name="y" />
	<input type="hidden" id="round"
		name="round" />
	<input type="hidden" id="team"
		name="team" />

	<div class="form-group">
		<label for="salario" class="col-sm-4 col-sd-4 control-label">Jugador</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="jugador" name="jugador" class="form-control" />
		</div>
	</div>

	<div class="form-group">
		<label for="duracion" class="col-sm-4 col-sd-4 control-label">Letra</label>
		<div class="col-md-8 col-sd-8">
			<input type="text" id="posicion" name="posicion" class="form-control" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-md-12 col-sd-12">
			<input type="submit" value="Enviar" class="btn btn-primary" />
		</div>
	</div>
</form>
</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$().ready(function() {
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	$(".draftRow").each(function(){
		$(this).click(function(){
			loadPick($(this).data("year"),$(this).data("ronda"),$(this).data("equipo") );
		});
	});
	</sec:authorize>
	});
</script>