<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
<div class="col-md-12 col-sd-12 center">
	<div class="row">
	<h1>Temporada ${temporada}</h1>

	</div>
	</div>
	</div>
<div class="row">
	<div class="col-md-2 col-sd-2 center">
	<c:forEach var="year" items="${tempList}">
	<p><a href = "<c:url value="/historico.action?y=${year}"/>">Temporada ${year}</a></p>
	</c:forEach>
	</div>

<div class="col-md-10 col-sd-10 center">
	<c:set var="elemento" value="0"/>
	<div class="col-md-12 col-sd-12 center">
	<c:if test="${not empty ganador}">
	<h3>Ganador</h3>
	${ganador.logo}
			<br/>
	<b>${ganador.nombre}</b>
	</c:if>
	</div>
	<div class="col-md-12 col-sd-12 center">
	<h3>Playoffs</h3>
	<main id="tournament" class = "bracket">


		<ul class="round round-2">
		<c:forEach var="partido" items="${ronda1}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.ganador1}">winner</c:if>"><img alt="${partido.equipo1.nombre}"
			src="${partido.equipo1.logoDraft}" title="${partido.equipo1.nombre}" /><span>${partido.resultado1}</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.ganador2}">winner</c:if>"><img alt="${partido.equipo2.nombre}"
			src="${partido.equipo2.logoDraft}" title="${partido.equipo2.nombre}" /><span>${partido.resultado2}</span></li>

		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-3">
		<c:forEach var="partido" items="${ronda2}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.ganador1}">winner</c:if>"><img alt="${partido.equipo1.nombre}"
			src="${partido.equipo1.logoDraft}" title="${partido.equipo1.nombre}" /><span>${partido.resultado1}</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.ganador2}">winner</c:if>"><img alt="${partido.equipo2.nombre}"
			src="${partido.equipo2.logoDraft}" title="${partido.equipo2.nombre}" /><span>${partido.resultado2}</span></li>

		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-4">
		<c:forEach var="partido" items="${semis}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.ganador1}">winner</c:if>"><img alt="${partido.equipo1.nombre}"
			src="${partido.equipo1.logoDraft}" title="${partido.equipo1.nombre}" /><span>${partido.resultado1}</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.ganador2}">winner</c:if>"><img alt="${partido.equipo2.nombre}"
			src="${partido.equipo2.logoDraft}" title="${partido.equipo2.nombre}" /><span>${partido.resultado2}</span></li>
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-5">
		<c:forEach var="partido" items="${rondaFinal}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.ganador1}">winner</c:if>"><img alt="${partido.equipo1.nombre}"
			src="${partido.equipo1.logoDraft}" title="${partido.equipo1.nombre}" /><span>${partido.resultado1}</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.ganador2}">winner</c:if>"><img alt="${partido.equipo2.nombre}"
			src="${partido.equipo2.logoDraft}" title="${partido.equipo2.nombre}" /><span>${partido.resultado2}</span></li>

		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		</main>
		</div>
	<div class="col-md-12 col-sd-12 center">
	<h3>Temporada regular</h3>
		<c:forEach var="conferencia" items="${rs}">
		<div class="col-md-6 col-sd-6 center">
			<h5>${conferencia.nombre}</h5>
			<c:forEach var="division" items="${conferencia.divisiones}">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Division ${division.nombre}</h3>
				</div>
				<div class="panel-body table-responsive">
					<table class="table">
					<c:forEach var="equipo" items="${division.datos}">
					<tr>

									<td>${equipo.nombreEquipo}</td>
									<td>${equipo.ganados}</td>
									<td>${equipo.perdidos}</td>
									<td>${equipo.media}</td>
					</tr>
					</c:forEach>
					</table>
				</div>
			</div>
			</c:forEach>
		</div>
		</c:forEach>
	</div>


		</div>
	</div>