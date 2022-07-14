<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br />
<div class="row">
	<div class="col-md-6 col-sd-6 center">
	<form id="exportFA" action="<c:url value="/export.do"/>" method="post" name="exportFA">
	<div class="form-group">
		<label for="equipos" class="col-sm-4 col-sd-4 control-label">
		Elige los equipos a exportar:</label>
		<select name="equipos" size="30" multiple class="form-control">
		<c:forEach var="e" items="${equipos}">
			<option value="${e.key}">${e.value}</option>
		</c:forEach>
		</select>
		</div>
		<div class="form-group">
	<div class="col-md-12 col-sd-12">
	<input type="submit" value="Enviar" class="btn btn-primary"/>
	</div>
	</div>
		</form>
	</div>
</div>
