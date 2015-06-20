<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br />
<div class="row">
	<div class="col-md-12 col-sd-12 center">

		<table class="table">
			<thead>
				<tr>
					<td>Equipo</td>
					<td>Salarios</td>
					<td>Limite</td>
					<td>Jugadores</td>
					<td>FA</td>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="eval" items="${evaluacion}">
					<tr>
						<td><img src="${eval.logo}" alt="Logo" /></td>
						<td>${eval.salarios}</td>
						<td>${eval.limite}</td>
						<td>${eval.jugadores}</td>
						<td>${eval.fa}</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
