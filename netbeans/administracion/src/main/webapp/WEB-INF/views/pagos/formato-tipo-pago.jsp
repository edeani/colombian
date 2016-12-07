<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${estado eq 'A'}">
        <c:set var="estadoActivo" value="selected"></c:set>
    </c:when>
    <c:when test="${estado eq 'I'}">
        <c:set var="estadoInactivo" value="selected"></c:set>
    </c:when>
</c:choose>
<div class="panel panel-primary">
    <!-- Default panel contents -->
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-4">Tipo pago ${nombre}</div>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <form id="formProcess" class="form-inline">
                <div class="col-md-3 form-group"><input name="idtipopago" value="${idtipopago}"  type="hidden"/></div>
                <div class="form-group"><label for="nombreF">Nombre</label> <input id="nombreF" name="nombre" value="${nombre}"  class="form-control"/></div>
                <div class="form-group"><label for="estadoF">Estado</label> 
                    <select id="estadoF" name="estado"  class="form-control">
                        <option value="A" ${estadoActivo}>Activo</option>
                        <option value="I" ${estadoInactivo}>Inactivo</option>
                    </select>
                </div>
            </form>
        </div>
    </div>
</div>
