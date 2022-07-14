<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
	<div class="widget center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Ultimos partidos</h3>
			</div>
			<div class="panel-body table-responsive">
				<c:set value="0" var="index"/>
				<c:forEach var="s" items="${schedule}">
					<div class="thumbnail matches" data-thumbindex="${index}">
						<c:set value="${index +1}" var="index"/>
						<br/>
						<div class="row center">

							<div class="col-md-6 col-sd-6 ">
								<span> <img alt="${s.match.away.nombre}"
											src="${s.match.away.logoDraft}" title="${s.match.away.nombre}" /> </span>
								<br/>
								<h4 <c:if test="${s.match.awayWinner}">class="winner"</c:if>>${s.match.awayScore}</h4>
								</div>
								<div class="col-md-6 col-sd-6">
									<span> <img alt="${s.match.home.nombre}"
											src="${s.match.home.logoDraft}" title="${s.match.home.nombre}" /> </span>
								<br/>
								<h4 <c:if test="${s.match.homeWinner}">class="winner"</c:if>>${s.match.homeScore}</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 col-sd-12 center">
									<h4>Mejor jugador</h4>
									<div class="row center">
										<div class="col-md-12 col-sd-12 center">
											<img alt="${s.bestPlayer.jugador.nombre}"
											 src="http://cdn.basketball.sports.ws/players/${s.bestPlayer.jugador.nombreFoto}.jpg"
											 style="margin: 25px;height:75px;" title="${s.bestPlayer.jugador.nombre}" />
									</div>
								</div>
								<div class="col-md-12 col-sd-12 center">
									<p>${s.bestPlayer.puntos} puntos / ${s.bestPlayer.asistencias} asist / ${s.bestPlayer.rebDef + s.bestPlayer.rebOf} reb</p>
									<p>${s.bestPlayer.puntuacionHoops} FPPG / <fmt:formatNumber type="number"
																							 maxFractionDigits="2" value="${s.bestPlayer.fppm}"/> FPPM</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var index = 0;
	$().ready(function () {
		showHide();
	});

	function showHide() {
		$(".matches").hide();
		$(".matches").each(function () {
			if ($(this).data("thumbindex") === index % ${fn:length(schedule)}) {
				$(this).fadeIn();
			}
		});
		index++;
		setTimeout(function () {
			showHide();
		}, 10000);
	}
</script>
