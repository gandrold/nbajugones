<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/" var="urlStatic" />
<html>
<head>
<title>NBA Jugones</title>

<meta http-equiv="content-type" content="text/html; charset=iso-8859-15">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<link rel="stylesheet" href="<c:url value="/themes/css/estilos.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<c:url value="/themes/css/bootstrap-datepicker3.min.css"/>"
	type="text/css" />
<script src="<c:url value="/themes/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/equipos.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/jugadores.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/jpages.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/jquery.tablesorter.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/jquery.flot.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/bootstrap-datepicker.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/themes/js/jquery-sortable-min.js"/>"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="cuerpo">
		<div class="cabecera">
			<div class="equipos">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<sec:authorize access="isAuthenticated()">
							<a class="navbar-brand" href="<c:url value="/dashboard.action"/>">NBA Jugones</a>
							</sec:authorize>
							<sec:authorize access="isAnonymous()">
							<a class="navbar-brand" href="#">NBA Jugones</a>
							</sec:authorize>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<sec:authorize access="isAuthenticated()">
							<ul class="nav navbar-nav">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">Equipos <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<c:forEach var="equipo" items="${equipos}">
											<li><a
												href="<c:url value="/roster.action"/>?id=${equipo.key}">
													${equipo.value} </a></li>
										</c:forEach>

									</ul>
								</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">Jugadores <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="<c:url value="/jugadores/fa.action"/>">Ver FA's</a></li>
                                        <li><a href="<c:url value="/jugadores/todos.action"/>">Ver todos</a></li>
                                        <li><a href="<c:url value="/jugadores/derechos.action"/>">Derechos</a></li>
                                        <li><a href="<c:url value="/renovacion.action"/>">Renovaciones</a></li>
                                        <li><a href="<c:url value="/draft.action"/>">Drafts</a></li>
									</ul>
								</li>
                                <li><a href="<c:url value="/trade.action"/>">Trade</a></li>
                                <li><a href="<c:url value="/rondas.action"/>">Rondas</a></li>
								<li><a href="<c:url value="/evaluar.action"/>">Evaluar</a></li>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">Admin <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="<c:url value="/exportar.action"/>">Generar rosters</a></li>
                                        <li><a href="<c:url value="/jugadores/new.action"/>">Nuevo jugador</a></li>
                                        <li><a href="<c:url value="/import.action"/>">Stats</a></li>
									</ul>
								</li>
								</sec:authorize>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">Stats <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="<c:url value="/schedule.action"/>">Schedule</a></li>
										<li><a href="<c:url value="/seasonStats.action"/>">NBA stats</a></li>
									</ul>
								</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">Historico <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="<c:url value="/copa.action"/>">Copa</a></li>
										<li><a href="<c:url value="/historico.action"/>">NBA Jugones</a></li>
									</ul>
								</li>
								<li><a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />">Logout</a></li>
							</ul>
							</sec:authorize>
						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>

				<!-- /header -->
			</div>
			<div class="separadorHorizontal"></div>
			<div id="contenido" class="container">
				<!-- contenido -->
				<decorator:body />
				<!-- fin contenido -->
			</div>
		</div>
	</div>
</body>
</html>

