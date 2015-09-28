<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br />
<div class="row">
	<div class="col-md-12 col-sd-12 center">

		<table class="table">
			<thead>
				<tr>
					<td>Equipo</td>
					<td>Cap disponible</td>
					<td>Jugadores</td>
					<td>FA</td>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="eval" items="${evaluacion}">
					<c:if test="${eval.warning}">
						<tr class="alert alert-danger">
							<td><img src="${eval.logo}" alt="Logo" /></td>
							<td>${eval.disponible}</td>
							<td>${eval.jugadores}</td>
							<td>${eval.fa}</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
