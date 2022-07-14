<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="result">
	<div class="row">
		<sec:authorize access="isAnonymous()">
		<div class="col-md-12 col-sd-12">
			<fieldset>
    		<c:if test="${not empty param.login_error}">
	    		<div class="ui-widget">
					<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
						<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
						Su identificacion no ha sido satisfactoria, pruebe de nuevo por favor.<br/><strong>Alert:</strong> <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.</p>

					</div>
				</div>
		    </c:if>
			<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST" class="form-signin">
				<table>
				    <tr><td  class="sr-only">User:</td><td><input type='text' name='j_username' class="form-control" placeholder="User" required autofocus/></td></tr>
				    <tr><td  class="sr-only">Password:</td><td><input type='password' name='j_password' class="form-control" placeholder="Password" required></td></tr>
				    <tr>
				    	<td colspan='2' align="center">
					    	<button name="submit" type="submit" class="btn btn-lg btn-primary btn-block">Submit</button>&nbsp;

				    	</td>
				    </tr>
				</table>
			</form>
		</fieldset>

		</div>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		</sec:authorize>
	</div>
</div>
