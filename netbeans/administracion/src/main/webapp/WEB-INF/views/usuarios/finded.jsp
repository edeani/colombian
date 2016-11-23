<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${not empty mensaje}">
        ${mensaje}
    </c:when>
    <c:otherwise>
        <div class="col-sm-6">
            <div class="form-group">
                <input type="hidden" id="nombreusuario" name="nombreusuario" value="${usuario.correo}"/>
                <input id="nombreusuario" name="nombreusuario" value="${usuario.nombreusuario}"/>
            </div>
            <div class="form-group">
                <input id="correo" name="correo" value="${usuario.correo}"/>
            </div>
            <div class="form-group">
                <input id="telefono" name="nombreusuario" value="${usuario.nombreusuario}"/>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="form-group">
                <input id="identificacion" name="identificacion" value="${usuario.identificacion}"/>
            </div>
            <div class="form-group">
                <input id="estado" name="estado" value="${usuario.estado}"/>
            </div>
            <div class="form-group">
                <input type="hidden" id="password" name="password" value="${usuario.password}"/>
                <input id="direccion" name="direccion" value="${usuario.direccion}"/>
            </div>
        </div>
    </c:otherwise>
</c:choose>
