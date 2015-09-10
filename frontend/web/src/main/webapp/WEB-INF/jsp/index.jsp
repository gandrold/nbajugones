<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="result">
	<div class="row">
		<div class="col-md-3 col-sd-3">

		</div>
		<div class="col-md-9 col-sd-9">
			
			<div class="row">
				<div class="col-md-9 col-sd-9 circle-container">
				<c:set var="angle" value="0"></c:set>
					<c:forEach var="eval" items="${evaluacion}">
						<a href="javascript:loadTeam('${eval.equipo}');" class='deg${angle}'>
							<img src="${eval.logo}" ><c:if test="${eval.warning}">!!</c:if></a>
						<c:set var="angle" value="${angle+12}"></c:set>

					</c:forEach>
				</div>
			</div>
		</div>

	</div>
</div>
