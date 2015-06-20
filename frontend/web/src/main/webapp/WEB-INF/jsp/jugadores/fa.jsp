<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
<div class="col-md-12 col-sd-12 center">


    <div>
        <div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">FA's</h3>
			</div>
			<div class="panel-body table-responsive">
				<table class="table">
					<thead>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>Jugador</td>
							<td>Puntos</td>
							<td>FPPM</td>
							<td>Jugados</td>
							<td>Minutos</td>
							<td>Observaciones</td>
							<td>Cortado por</td>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="jugador" items="${fa}">
							<tr >								
							<td style="font-weight: bold; font-size: 1.1em">${jugador.posicion}</td>
								<td><img alt="${jugador.nombre}"
									src="http://cdn.basketball.sports.ws/players/${jugador.nombreFoto}.jpg"
									style="height: 50px;" title="${jugador.nombre}" /></td>
								<td><a
									href="http://basketball.sports.ws/player/${jugador.nombreFoto}?league=140043"
									target="blank">${jugador.nombre} </a></td>								
								<td>${jugador.puntos}</td>
								<td>${jugador.promedio}</td>
								<td>${jugador.jugados}</td>
								<td>${jugador.minutos}</td>
								<td>${jugador.obs}</td>
								<td>${jugador.cortadoPor}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>  
    </div>
    
</div>        
</div>
              