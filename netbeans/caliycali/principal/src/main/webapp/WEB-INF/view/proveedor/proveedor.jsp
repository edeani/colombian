<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="/colombianCaliyCali/css/tabladinamica/estilos.css" rel="stylesheet" type="text/css">    
    <link href="/colombianCaliyCali/css/proveedores/proveedorescss.css" rel="stylesheet" type="text/css"> 
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/proveedor/proveedores.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    
</head>
<div id="contenidoHome"> 
    <div id="tituloPagina">Administraci&oacute;n de Proveedores</div>
    <div id="divproveedores" data-url="<%=request.getContextPath()%>/proveedor/ajax/buscar/proveedor.htm">
        <label>
            Proveedores
            <select id="selectProveedor" data-cargarselect="<%=request.getContextPath()%>/proveedor/ajax/listaProveedores.htm">
                <option value="">Seleccione Proveedor</option>
                <c:import url="/proveedor/ajax/listaProveedores.htm">
                </c:import>
            </select>
        </label>
        <label>
            <input type="button" value="Nuevo" id="nuevoProveedor"/>
        </label>
    </div>
    <div id="cargador"></div>
    <form id="formProveedores" name="formProveedores">
        <div id="divContenedorTabla" style="display: none;">
            <table id="tblproveedores" align="center" >
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Direcci&oacute;n</th>
                        <th>Tel&eacute;fono</th>
                        <th>Correo</th>
                        <th>Nit</th>
                    </tr>
                </thead>
                <tbody id="proveedores" data-nuevo="<%=request.getContextPath()%>/proveedor/ajax/contenido/nuevo/proveedor.htm" data-guardar="<%=request.getContextPath()%>/proveedor/ajax/guardar/proveedor.htm" data-actualizar="<%=request.getContextPath()%>/proveedor/ajax/actualizar/proveedor.htm" data-eliminar="<%=request.getContextPath()%>/proveedor/ajax/eliminar/proveedor.htm">
                </tbody>
            </table>
        </div>
    </form>
</div>
