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
            <div class="col-sm-6${fondo}"> 
                <div class="form-group">
                    <input type="hidden" id="idusuario" name="idusuario" value="${usuario.idusuario}" class="form-control"/>
                    <div class="col-md-3">
                        <label>Nombre</label>
                    </div>
                    <input id="nombreusuario" name="nombreusuario" value="${usuario.nombreusuario}" class="form-control"/>
                    <springForm:errors path="nombreusuario" cssClass="text-danger"></springForm:errors>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <label>Correo</label>
                    </div>
                    <input id="correo" name="correo" value="${usuario.correo}" class="form-control"/>
                    <springForm:errors path="correo" cssClass="text-danger"></springForm:errors>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <label>Tel&eacute;fono</label>
                    </div>
                    <input id="telefono" name="telefono" value="${usuario.telefono}" class="form-control"/>
                    <springForm:errors path="telefono" cssClass="text-danger"></springForm:errors>
                </div>
                <div class="form-group">
                    <div class="col-sm-6" style="height: 55px;" >
                    </div>
                    
                </div>
            </div>
            <div class="col-sm-6${fondo}">
                <div class="form-group">
                    <div class="col-md-3">
                        <label>Identificaci&oacute;n</label>
                    </div>
                    <input id="identificacion" name="identificacion" value="${usuario.identificacion}" class="form-control"/>
                    <springForm:errors path="identificacion" cssClass="text-danger"></springForm:errors>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <label>Estado</label>
                    </div>
                    <c:choose>
                        <c:when test="${usuario.estado=='A'}">
                            <c:set var="selectA" value="selected='selected'"></c:set>
                        </c:when>
                        <c:when test="${usuario.estado=='I'}">
                            <c:set var="selectI" value="selected='selected'"></c:set>
                        </c:when>
                        <c:when test="${usuario.estado=='B'}">
                            <c:set var="selectB" value="selected='selected'"></c:set>
                        </c:when>
                    </c:choose>
                    <select id="estado" name="estado" class="form-control">
                        <option value="A" ${selectA}>Activo</option>
                        <option value="I" ${selectI}>Inactivo</option>
                        <option value="B" ${selectB}>Bloqueado</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="hidden" id="password" name="password" value="${usuario.password}"/>
                    <input type="hidden" id="idrol" name="idrol" value="${usuario.idrol}"/>
                    <input type="hidden" id="nombrerol" name="nombrerol" value="${usuario.nombrerol}"/>
                    <div class="col-md-3">
                        <label>Direcci&oacute;n</label>
                    </div>
                    <input id="direccion" name="direccion" value="${usuario.direccion}" class="form-control"/>
                    <springForm:errors path="direccion" cssClass="text-danger"></springForm:errors>
                </div>
                <div class="form-group">
                    <div class="col-sm-6">
                    </div>
                    <button id="save-user" class="btn btn-label">Guardar</button>  
                </div>
            </div>
        </springForm:form>
    </c:otherwise>
</c:choose>
