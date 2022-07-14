<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-2 col-sd-2 center">
	<c:forEach var="year" items="${tempList}">
	<p><a href = "<c:url value="/copa.action?y=${year}"/>">Temporada ${year}</a></p>
	</c:forEach>
	</div>
	<div class="col-md-10 col-sd-10 center">
	<div class="row">
	<h1>Copa ${temporada}</h1>
	</div>
<div class="row">
	<c:set var="elemento" value="0"/>
	<div class="col-md-12 col-sd-12 center">
	<c:if test="${not empty ganador}">
	<h3>Ganador</h3>
	<img src="${ganador.logo}" class="logoHistorico"/>
			<br/>
	<b>${ganador.nombre}</b>
	</c:if>
	<div class="row">
	<h3>Cuadro</h3>
	<main id="tournament" class = "bracket">


		<ul class="round round-2">
		<c:forEach var="partido" items="${ronda2}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top partido <c:if test="${partido.fueraGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore"
		    data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom partido <c:if test="${partido.casaGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore"
		    data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</span></a></li>

		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-3">
		<c:forEach var="partido" items="${cuartos}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top partido <c:if test="${partido.fueraGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore"
            data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom partido <c:if test="${partido.casaGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore" data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</span></a></li>

		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-4">
		<c:forEach var="partido" items="${semi}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top partido <c:if test="${partido.fueraGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore" data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom partido <c:if test="${partido.casaGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore" data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</a></span></li>
		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		<ul class="round round-5">
		<c:forEach var="partido" items="${rondaFinal}">
		<li class="spacer">&nbsp;</li>
		<li class="game game-top partido <c:if test="${partido.fueraGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore" data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosFuera}</a></span></li>
		<li class="game game-spacer">&nbsp;</li>
		<li class="game game-bottom partido <c:if test="${partido.casaGanador}">winner</c:if>" data-toggle="modal" data-target="#boxscore" data-ronda="${partido.ronda}" data-partido="${partido.idPartido}"><img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" class="logoEquipo"/><span><a href="${partido.url}" target="_blank">${partido.puntosCasa}</span></a></li>

		</c:forEach>
		<li class="spacer">&nbsp;</li>
		</ul>
		</main>
		</div>
	<h3>Primera ronda</h3>
	<c:forEach var="partido" items="${ronda1}">
	<c:if test="${elemento mod 6 eq 0}"><div class="row"></c:if>
					<div class="col-md-2 col-sd-3">
		<div class="thumbnail partido" data-toggle="modal" data-target="#boxscore" class="btn btn-primary" data-ronda="${partido.ronda}" data-partido="${partido.idPartido}">
		<span>${partido.idPartido}</span>
		<br/>
		<span> <img alt="${partido.equipoFuera.nombre}"
			src="${partido.equipoFuera.logoDraft}" title="${partido.equipoFuera.nombre}" class="logoEquipo"/> vs <img alt="${partido.equipoCasa.nombre}"
			src="${partido.equipoCasa.logoDraft}" title="${partido.equipoCasa.nombre}" class="logoEquipo"/> </span>
		<br/>
		<span <c:if test="${partido.fueraGanador}">class="winner"</c:if>>${partido.puntosFuera}</span> -
		<span <c:if test="${partido.casaGanador}">class="winner"</c:if>>${partido.puntosCasa}</span>
		<br/>
		<span><a href="${partido.url}" target="_blank">Ver</a></span>
		</div>
		</div>
					<c:if test="${elemento mod 6 eq 5}"></div></c:if>
					<c:set var="elemento" value="${elemento +1}"/>
		</c:forEach>
		</div>

	</div>

</div>
</div>
<div class="modal fade modal-boxscore" id="boxscore" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-dialog-boxscore">
		<div class="modal-content modal-content-boxscore">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Box score</h4>
			</div>
			<div class="modal-body" id="boxScoreBody">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$().ready(function() {
	    $(".partido").each(function(){
	        $(this).click(function(){
            			loadBoxScoreCopa('<c:url value="/copaBoxscore.do"/>','<c:url value="/themes/img/loader.gif"/>','${temporada}',$(this).data("ronda"),$(this).data("partido") );
            		});
	    });
	});
</script>