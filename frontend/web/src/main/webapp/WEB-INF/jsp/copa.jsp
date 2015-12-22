<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12 col-sd-12 center">
	<h1>Copa ${temporada}</h1>
	</div>
</div>
<div class="row">
	<c:set var="elemento" value="0"/>
	<div class="col-md-12 col-sd-12 center">
	<c:if test="${not empty ganador}">
	<h3>Ganador</h3>
	${ganador.logo}
			<br/>
	<b>${ganador.nombre}</b>
	</c:if>
	<div class="row">
	<h3>Cuadro</h3>
	<main id="tournament" class = "bracket">
		
		
		<ul class="round round-2">
		<c:forEach var="partido" items="${ronda2}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.fueraGanador}">winner</c:if>"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.casaGanador}">winner</c:if>"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</span></a></li>
		
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-3">
		<c:forEach var="partido" items="${cuartos}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.fueraGanador}">winner</c:if>"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.casaGanador}">winner</c:if>"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</span></a></li>
		
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-4">
		<c:forEach var="partido" items="${semi}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.fueraGanador}">winner</c:if>"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.casaGanador}">winner</c:if>"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</span></a></li>		
		</c:forEach>
		<li class="spacer">&nbsp;</li>	
		</ul>
		<ul class="round round-5">
		<c:forEach var="partido" items="${rondaFinal}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top <c:if test="${partido.fueraGanador}">winner</c:if>"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom <c:if test="${partido.casaGanador}">winner</c:if>"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</span></a></li>
		
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		</main>
		</div>
	<h3>Primera ronda</h3>
	<c:forEach var="partido" items="${ronda1}">
	<c:if test="${elemento mod 6 eq 0}"><div class="row"></c:if>
					<div class="col-md-2 col-sd-3">
		<div class="thumbnail">
		<span>${partido.idPartido}</span>
		<br/>
		<span> <img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /> vs <img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /> </span>
		<br/>
		<span <c:if test="${partido.fueraGanador}">class="winner"</c:if>>${partido.puntosFuera}</span> - 
		<span <c:if test="${partido.casaGanador}">class="winner"</c:if>>${partido.puntosCasa}</span>
		<br/>
		<a href="${partido.url}" target="_blank">Ver</a>
		</div>
		</div>
					<c:if test="${elemento mod 6 eq 5}"></div></c:if>
					<c:set var="elemento" value="${elemento +1}"/>
		</c:forEach>
		</div>
	
	</div>

</div>
