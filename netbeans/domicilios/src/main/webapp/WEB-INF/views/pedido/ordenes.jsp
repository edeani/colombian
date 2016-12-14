<%-- 
    Document   : pedido
    Created on : 13/09/2016, 06:05:27 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Administraci&oacute;n de Productos</title>
        <!-- Custom Css ================================================== -->
        <link rel="stylesheet" type="text/css" href="/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" type="text/css" href="/css/responsive.css">
        <link rel="stylesheet" type="text/css" href="/css/jquery-confirm.css">
        <link rel="stylesheet" type="text/css" href="/css/usuarios/usuarios-cuenta.css">

        <!-- Fonts ================================================== -->
        <link href='<%=request.getContextPath()%>/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/usuario/cuenta.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">
                <div class="text-center">
                    <h1 class="h1pedido">Domicilios</h1>
                </div>            
                <br />
                </br></br>
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-border-row table-card" id="tablaPedidos">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th class="product-name">Direcci&oacute;n</th>
                                    <th>Modo de pago</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Fecha</th>
                                    <th class="product-name">Direcci&oacute;n</th>
                                    <th>Modo de pago</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            <tbody id="updateListaDom">
                                <c:forEach items="${pedidos}" var="d" varStatus="indice">
                                    <tr>
                                        <td>${d.fecha}</td>
                                        <td>${d.direccion}</td>
                                        <td>${d.tipopago}</td>
                                        <td>${d.total}</td>
                                        <td>
                                            <div class="btn-controls">
                                                <a title="ver" data-idpedido="${d.idpedido}" class="edit btn btn-primary btn-sm viewOrder" href="javascript:void(0);" aria-label="Edit"><i  class="fa fa-eye fa-lg" aria-hidden="true"></i></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
                <br />
                <div class="text-center">
                    <h1 class="h1pedido">Datos</h1>
                </div> 
                <div id="datosUsuario" class="row">
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
                </div>
            </div>
        </div><!-- End Content -->
    </body>
</html>
