<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12 col-sd-12 center">
	<h1>Copa ${temporada}</h1>
	</div>
</div>
<div class="row">
	
	<div class="col-md-12 col-sd-12 center">
	<c:forEach var="partido" items="${ronda1}">
		<div class="thumbnail">
		<img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" />
			vs
		<img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" />
		</div>
		0.0 - 0.0
		</c:forEach>
	<main id="tournament">
		
		
		<ul class="round round-2">
		<c:forEach var="partido" items="${ronda2}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top winner"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span>-</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom "><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span>-</span></li>
		
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-3">
		<c:forEach var="partido" items="${cuartos}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top winner"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span>-</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom "><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span>-</span></li>
		
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-4">
		<c:forEach var="partido" items="${semi}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top winner"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span>-</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom "><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span>-</span></li>		
		</c:forEach>
		<li class="spacer">&nbsp;</li>	
		</ul>
		<ul class="round round-5">
		<c:forEach var="partido" items="${rondaFinal}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top winner"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" /><span>-</span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom "><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" /><span>-</span></li>
		
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		</main>
	</div>

</div>
