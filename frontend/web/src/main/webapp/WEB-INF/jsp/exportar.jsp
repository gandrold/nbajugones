<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br />
<div class="row">
	<div class="col-md-12 col-sd-12 center">
		<p>Elige los equipos a exportar:</p>
		<select name="equipos" size="30" multiple>
		<c:forEach var="e" items="${equipos}">
			<option value="${e.key}">${e.value}</option>
		</c:forEach>
		</select>
	</div>
</div>
