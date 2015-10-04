<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="result">
	<div class="row">
		<div class="col-md-12 col-sd-12">
			<c:set var="elemento" value="0"/>
			

				
				<c:forEach var="eval" items="${evaluacion}">
				<c:if test="${elemento mod 6 eq 0}"><div class="row"></c:if>
					<div class="col-md-2 col-sd-3">
						<div class="thumbnail <c:if test="${eval.warning}">alert alert-danger</c:if>">
							<a href="javascript:loadTeam('${eval.equipo}');"> <img
								src="${eval.logo}"></a>
							<div class="caption">
								<p>Cap disponible: ${eval.disponible}</p>
								<p>Jugadores: ${eval.jugadores-eval.fa}</p>
							</div>
						</div>

					</div>
					<c:if test="${elemento mod 6 eq 5}"></div></c:if>
					<c:set var="elemento" value="${elemento +1}"/>
				</c:forEach>

		</div>

	</div>
</div>
