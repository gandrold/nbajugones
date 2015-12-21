<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<link rel="stylesheet" href="/jugones-frontend/themes/css/estilos.css"
	type="text/css" />

<script src="/jugones-frontend/themes/js/jquery.min.js"></script>
<script type="text/javascript" src="/jugones-frontend/themes/js/equipos.js"></script>
<script type="text/javascript" src="/jugones-frontend/themes/js/jugadores.js"></script>
<script type="text/javascript" src="/jugones-frontend/themes/js/main.js"></script>
<script type="text/javascript" src="/jugones-frontend/themes/js/jpages.js"></script>
<script type="text/javascript" src="/jugones-frontend/themes/js/jquery.tablesorter.js"></script>
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
							<a class="navbar-brand" href="/jugones-frontend/index.action">NBA Jugones</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">								
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">Equipos <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<c:forEach var="equipo" items="${equipos}">
											<li><a
												href="javascript:loadTeam('${equipo.key}')">
													${equipo.value} </a></li>
										</c:forEach>
										
									</ul></li>
								<li><a href="<c:url value="/jugadores/fa.action"/>">Ver
												FA's</a></li>
										<li><a href="<c:url value="/jugadores/todos.action"/>">Ver
												todos</a></li>
										<li><a href="<c:url value="/trade.action"/>">Trade</a></li>
										<li><a href="<c:url value="/evaluar.action"/>">Evaluar</a>
										<li><a href="<c:url value="/exportar.action"/>">Generar rosters</a></li>
										<li><a href="<c:url value="/jugadores/new.action"/>">Nuevo jugador</a></li>
										<li><a href="<c:url value="/import.action"/>">Stats</a></li>
										<li><a href="<c:url value="/jugadores/derechos.action"/>">Derechos</a></li>
										<li><a href="<c:url value="/rondas.action"/>">Rondas</a></li>
										<li><a href="<c:url value="/renovacion.action"/>">Renovaciones</a></li>
										<li><a href="<c:url value="/draft.action"/>">Drafts</a></li>
										<li><a href="<c:url value="/copa.action"/>">Copa</a></li>
							</ul>

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

