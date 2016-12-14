<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<springForm:form action="${pageContext.request.contextPath}/usuarios/ajax/actualizar-usuario.htm" method="post" commandName="usuarioDto">
    <div id="datos1" class="col-sm-4"> 
        <br/>
        <div class="form-group">
            <input type="hidden" id="idusuario" name="idusuario" value="${usuario.idusuario}" class="form-control"/>
            <div class="col-md-3">
                <label>Nombre</label>
            </div>
            <input id="nombreusuario" name="nombreusuario" value="${usuario.nombreusuario}" class="form-control" maxlength="100"/>
            <springForm:errors path="nombreusuario" cssClass="text-danger"></springForm:errors>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label>Correo</label>
            </div>
            <input id="correo" name="correo" value="${usuario.correo}" class="form-control" maxlength="100"/>
            <springForm:errors path="correo" cssClass="text-danger"></springForm:errors>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label>Tel&eacute;fono</label>
            </div>
            <input id="telefono" name="telefono" value="${usuario.telefono}" class="form-control numeric" maxlength="10"/>
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
            <input id="identificacion" name="identificacion" value="${usuario.identificacion}" class="form-control numeric" maxlength="15"/>
            <springForm:errors path="identificacion" cssClass="text-danger"></springForm:errors>
        </div>

        <div class="form-group">
            <input type="hidden" id="password" name="password" value="${usuario.password}"/>
            <input type="hidden" id="idrol" name="idrol" value="${usuario.idrol}"/>
            <input type="hidden" id="nombrerol" name="nombrerol" value="${usuario.nombrerol}"/>
            <div class="col-md-3">
                <label>Direcci&oacute;n</label>
            </div>
            <div class="form-inline">
                <select id="componente" class="form-control carrera">
                    <c:forEach var="opc" items="${coord}">
                        <option value="${opc}" <c:if test="${opc==componente}">selected="selected"</c:if>>${opc}</option>
                    </c:forEach>
                </select>
                <input type="text" id="datoComponente" class="form-control textnumber" style="width: 10%;" value="${datoComponente}"/>
                <label>#</label>
                <input type="text" id="datoComponente1" class="form-control textnumber" style="width: 10%;" value="${datoComponente1}"/>
                <label >-</label>
                <input type="text" id="datoComponente2" class="form-control textnumber" style="width: 10%;" value="${datoComponente2}"/>
                <input type="hidden" id="direccion" name="direccion" value="" class="form-control" maxlength="100"/>
                <springForm:errors path="direccion" cssClass="text-danger"></springForm:errors>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-8">
                <input type="hidden" id="estado" name="estado" value="${usuario.estado}" class="form-control" maxlength="100"/>
            </div>
            <button id="save-user" class="btn btn-label">Guardar</button>  
        </div>
    </div>
</springForm:form>