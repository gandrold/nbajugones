<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-md-2 col-sd-2 center" style="overflow:hidden;" >
	<div class="row" id="selectDate">
		<div class="form-group">
            <div class="col-md-12">
				<form class="form-inline" id="buscarFA"
						data-async data-target="#contenido" action="<c:url value="/schedule.do"/>" method="POST">
               <div class="input-group date">

			  </div>
			  <input type="hidden" id="inicio" name="inicio" value="${inicio}" data-date-format="dd/MM/YYYY"/>
			  <button type="submit" class="btn btn-primary">Buscar</button>
			  </form>
        </div>
    </div>
	<div class="row">
	<c:forEach var="season" items="${seasons}">

	<div class="col-md-6 col-sd-6 grid">
	<button onClick="javascript: setInicio('${season.key}');" class="btn btn-primary">${season.value}</button>
	</div>
	</c:forEach>
	</div>
	</div>

	</div>
	<div class="col-md-10 col-sd-10 center" id="schedule">
		<div class="row">
		<c:set var="elemento" value="0"/>
			<b><fmt:formatDate value="${day1}"/><a href = "<c:url value="/recalculate.do"/>">Recalculate last day stats</a></b>
			<c:forEach var="partido" items="${content1}">
				<c:if test="${elemento mod 6 eq 0}"><div class="row"></c:if>
					<div class="col-md-2 col-sd-3">
						<div class="thumbnail" style="cursor: pointer;" onClick="javascript: loadBoxScore('<c:url value="/boxscore.do"/>','<c:url value="/themes/img/loader.gif"/>',${partido.id});"  data-toggle="modal" data-target="#boxscore">
							<br/>
							<div class="row">
							<div class="col-md-4 col-sd-4">
							<span> <img alt="${partido.away.nombre}"
								src="${partido.away.logoDraft}" title="${partido.away.nombre}" class="logoEquipoSchedule"/> </span>
							<br/>
							<h4 <c:if test="${partido.awayWinner}">class="winner"</c:if>>${partido.awayScore}</h4>
							</div>
							<div class="col-md-2 col-sd-2" style="margin-top:20px;">vs</div>
							<div class="col-md-4 col-sd-4">
							<span> <img alt="${partido.home.nombre}"
								src="${partido.home.logoDraft}" title="${partido.home.nombre}" class="logoEquipoSchedule"/> </span>
							<br/>
							<h4 <c:if test="${partido.homeWinner}">class="winner"</c:if>>${partido.homeScore}</h4>
							</div>
							</div>
						</div>
					</div>
				<c:if test="${elemento mod 6 eq 5}"></div></c:if>
							<c:set var="elemento" value="${elemento +1}"/>
			</c:forEach>
				<c:if test="${elemento mod 6 ne 0}"></div></c:if>
		</div>
		<div class="row">
		<c:set var="elemento" value="0"/>
			<b><fmt:formatDate value="${day2}"/></b>
			<c:forEach var="partido" items="${content2}">
				<c:if test="${elemento mod 6 eq 0}"><div class="row"></c:if>
					<div class="col-md-2 col-sd-3">
						<div class="thumbnail" style="cursor: pointer;" onClick="javascript: loadBoxScore('<c:url value="/boxscore.do"/>','<c:url value="/themes/img/loader.gif"/>',${partido.id});"  data-toggle="modal" data-target="#boxscore">
							<br/>
							<div class="row">
							<div class="col-md-4 col-sd-4">
							<span> <img alt="${partido.away.nombre}"
								src="${partido.away.logoDraft}" title="${partido.away.nombre}" class="logoEquipoSchedule"/> </span>
							<br/>
							<h4 <c:if test="${partido.awayWinner}">class="winner"</c:if>>${partido.awayScore}</h4>
							</div>
							<div class="col-md-2 col-sd-2" style="margin-top:20px;">vs</div>
							<div class="col-md-4 col-sd-4">
							<span> <img alt="${partido.home.nombre}"
								src="${partido.home.logoDraft}" title="${partido.home.nombre}" class="logoEquipoSchedule"/> </span>
							<br/>
							<h4 <c:if test="${partido.homeWinner}">class="winner"</c:if>>${partido.homeScore}</h4>
							</div>
							</div>
						</div>
					</div>
				<c:if test="${elemento mod 6 eq 5}"></div></c:if>
							<c:set var="elemento" value="${elemento +1}"/>
			</c:forEach>
				<c:if test="${elemento mod 6 ne 0}"></div></c:if>
		</div>
		<div class="row">
		<c:set var="elemento" value="0"/>
			<b><fmt:formatDate value="${day3}"/></b>
			<c:forEach var="partido" items="${content3}">
				<c:if test="${elemento mod 6 eq 0}"><div class="row"></c:if>
					<div class="col-md-2 col-sd-3">
						<div class="thumbnail" style="cursor: pointer;" onClick="javascript: loadBoxScore('<c:url value="/boxscore.do"/>','<c:url value="/themes/img/loader.gif"/>',${partido.id});"  data-toggle="modal" data-target="#boxscore">
							<br/>
							<div class="row">
							<div class="col-md-4 col-sd-4">
							<span> <img alt="${partido.away.nombre}"
								src="${partido.away.logoDraft}" title="${partido.away.nombre}" class="logoEquipoSchedule"/> </span>
							<br/>
							<h4 <c:if test="${partido.awayWinner}">class="winner"</c:if>>${partido.awayScore}</h4>
							</div>
							<div class="col-md-2 col-sd-2" style="margin-top:20px;">vs</div>
							<div class="col-md-4 col-sd-4">
							<span> <img alt="${partido.home.nombre}"
								src="${partido.home.logoDraft}" title="${partido.home.nombre}" class="logoEquipoSchedule"/> </span>
							<br/>
							<h4 <c:if test="${partido.homeWinner}">class="winner"</c:if>>${partido.homeScore}</h4>
							</div>
							</div>
						</div>
					</div>
				<c:if test="${elemento mod 6 eq 5}"></div></c:if>
							<c:set var="elemento" value="${elemento +1}"/>
			</c:forEach>
				<c:if test="${elemento mod 6 ne 0}"></div></c:if>
		</div>
	</div>
</div>
<div class="modal fade modal-box" id="boxscore" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-dialog-box boxscore">
		<div class="modal-content modal-content-box">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Box score</h4>
			</div>
			<div class="modal-body" id="boxScoreBody">
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	$().ready(function() {
		$('.input-group.date').datepicker({format:"dd/MM/yyyy"});
		$(".input-group.date").datepicker("setDate", "${inicio}");
		$("#showCalendar").hide();
		$('form[data-async]').submit(function(event) {
			$("#inicio").val(Date.parse($('.input-group.date').datepicker("getUTCDate")));
	        var $form = $(this);
	        var $target = $($form.attr('data-target'));
	 		$target.html("<img src='<c:url value="/themes/img/loader.gif"/>' class='center'/>");
	        $.ajax({
	            type: $form.attr('method'),
	            url: $form.attr('action'),
	            data: $form.serialize(),

	            success: function(data, status) {
	                $target.html(data);
	            }
	        });

	        event.preventDefault();
	    });
	});
	function setInicio(value){
		$(".input-group.date").datepicker("setDate", value);
		$('form[data-async]').submit();
	}
</script>
