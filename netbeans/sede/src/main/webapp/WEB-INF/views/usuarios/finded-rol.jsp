<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<c:choose>
    <c:when test="${not empty mensaje}">
        ${mensaje}
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${usuario.estado=='A'}">
                <c:set var="fondo" value=" bg-success"></c:set>
            </c:when>
            <c:when test="${usuario.estado=='I'}">
                <c:set var="fondo" value=" bg-warning"></c:set>
            </c:when>
            <c:when test="${usuario.estado=='B'}">
                <c:set var="fondo" value=" bg-danger"></c:set>
            </c:when>
        </c:choose>
        <springForm:form action="${pageContext.request.contextPath}/usuarios/ajax/actualizar-usuario.htm" method="post" commandName="usuarioDto">
            <div id="datos1" class="col-sm-4${fondo}"> 
                <br/>
                <div class="form-group">
                    <input type="hidden" id="idusuario" name="idusuario" value="${usuario.idusuario}" class="form-control"/>
                    <div class="col-md-3">
                        <label>Nombre</label>
                    </div>
                    ${usuario.nombreusuario}
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <label>Correo</label>
                    </div>
                    ${usuario.correo}
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <label>ID</label>
                    </div>
                    ${usuario.identificacion}
                </div>
                <div class="form-group">
                    <div class="col-sm-3" style="height: 55px;" >
                        <label>Estado</label>
                    </div>
                    ${usuario.estado}
                </div>
            </div>
            <div id="datos2" class="col-sm-7${fondo}">
                <br/>
                <div class="form-group">
                    <div class="col-md-3">
                        <label>Rol Asignado</label>
                    </div>
                    <select id="idrol" name="idrol" class="form-control">
                        <c:forEach var="r" items="${roles}">
                            <option value="${r.idrol}" <c:if test="${r.idrol eq usuario.idrol}">selected</c:if>>${r.nombrerol}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="col-md-6" style="height: 55px;">
                        
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6" style="height: 55px;">
                        
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                        </div>
                        <button id="save-rol" class="btn btn-label">Guardar</button>  
                    </div>
                </div>
        </springForm:form>
    </c:otherwise>
</c:choose>
