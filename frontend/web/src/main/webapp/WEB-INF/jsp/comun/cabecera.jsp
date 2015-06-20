
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div id="header">
    <div class="content">
        <table ><tr>
                <td>
                    Elige el equipo a mostrar:
                    <select onchange="">
                        <option value=""></option>
                        <c:forEach var="equipo" items="${equipos}">
                            <option value="${equipo.id}">${equipo.nombre}</option>
                        </c:forEach>
                    </select>
                </td>
                <td style="margin-left:5px;"><a href="fa.jsp">Ver FA's</a></td>
                <td style="margin-left:5px;"><a href="todos.jsp">Ver todos</a></td>
                <td style="margin-left:5px;"><a href="trade.jsp">Trade</a></td>
                <td style="margin-left:5px;"><a href="evaluar.jsp">Evaluar</a>
                <td style="margin-left:5px;"><a href="export.jsp">Generar rosters</a></td>
                <td style="margin-left:5px;"><a href="nuevo.jsp">Nuevo jugador</a></td>
                <td style="margin-left:5px;"><a href="upload.jsp">Stats</a></td>
                <td style="margin-left:5px;"><a href="renovacion.jsp">Renovaciones</a>
                <td style="margin-left:5px;"><a href="derechos.jsp">Derechos</a>
                <td style="margin-left:5px;"><a href="rondas.jsp">Rondas</a>
                <td style="margin-left:5px;"><a href="draft.jsp">Drafts</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- /content -->
</div>
<!-- /header -->

