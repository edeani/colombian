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
                <div id="datos2" class="col-sm-7${fondo}">
                <br/>
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
                    <div class="form-inline">
                        <select id="componente" class="form-control" style="width: 27%;">
                            <option value="Calle" selected="selected">Calle</option>
                            <option value="Carrera">Carrera</option>
                            <option value="Avenida">Avenida</option>
                            <option value="Avenida Carrera">Avenida Carrera</option>
                            <option value="Avenida Calle">Avenida Calle</option>
                            <option value="Circular">Circular</option>
                            <option value="Circunvalar">Circunvalar</option>
                            <option value="Diagonal">Diagonal</option>
                            <option value="Manzana">Manzana</option>
                            <option value="Transversal">Transversal</option>
                            <option value="Vía">Vía</option>
                        </select>
                        <input type="text" id="datoComponente" class="form-control" style="width: 10%;" value="${datoComponente}"/>
                        <label>#</label>
                        <input type="text" id="datoComponente1" class="form-control" style="width: 10%;" value="${datoComponente1}"/>
                        <label >-</label>
                        <input type="text" id="datoComponente2" class="form-control" style="width: 10%;" value="${datoComponente2}"/>
                        <input type="hidden" id="direccion" name="direccion" value="${usuario.direccion}" class="form-control"/>
                        <springForm:errors path="direccion" cssClass="text-danger"></springForm:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                        </div>
                        <button id="save-user" class="btn btn-label">Guardar</button>  
                    </div>
                </div>
        </springForm:form>
    </c:otherwise>
</c:choose>
