<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr>
    <input id="idproveedor" name="idproveedor" type="hidden" value="">      

    <td>
        <input class="contentRequired" id="nombre" name="nombre" type="text" value="">
    </td>
    <td>
        <input id="direccion" name="direccion" type="text" value="">
    </td>
    <td>
        <input id="telefono" name="telefono" type="text" onkeypress="return validarNUM(event);" value="">
    </td>
    <td>
        <input id="correo" name="correo" type="text" value="">
    </td>
    <td>
        <input id="nit" name="nit" type="text" onkeypress="return validarNUM(event);" value="">
    </td>
    <td>
        <input type="submit"  id="agregarProveedor"value="Guardar">
    </td>
</tr>