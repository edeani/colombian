<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Datos Producto</title>
        <!-- Custom Css     ================================================== -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/responsive.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/jquery-confirm.css">

        <!-- Fonts         ================================================== -->
        <link href='<%=request.getContextPath()%>/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        <link href="<%=request.getContextPath()%>/css/upload/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

        <!--Las librerias para eluplñoad deben tener siempre el mismo orden -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/canvas-to-blob.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/sortable.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/purify.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/upload/fileinput.min.js"></script>
        <!--fin upload-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
        <c:choose>
            <c:when test="${estado eq 'E'}">
                <script type="text/javascript" src="<%=request.getContextPath()%>/js/producto/editarproducto.js"></script>        
            </c:when>
            <c:otherwise>
                <script type="text/javascript" src="<%=request.getContextPath()%>/js/producto/crearproducto.js"></script>
            </c:otherwise>
        </c:choose>
    </head>
    <body>
        <div id="content">


            <div class="container rst-main-content rst-product-detail">                   
                <springForm:form  method="post" commandName="productoDetailDto" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="rst-product-images">
                                <c:if test="${estado eq 'E'}">
                                    <div id="load-pic">
                                        <c:choose>
                                            <c:when test="${not empty productoDetailDto.rutaImagen}">
                                                <img style="width: 260px;" src="${urlImg}/${productoDetailDto.rutaImagen}" alt="${productoDetailDto.nombreproducto}" />
                                            </c:when>
                                            <c:otherwise>
                                                <img style="width: 260px;" src="<%=request.getContextPath()%>/img/post/gallery11.jpg" alt="${productoDetailDto.nombreproducto}" />
                                            </c:otherwise>
                                        </c:choose>
                                        
                                    </div>
                                    </br>
                                </c:if>
                                <div id="content-img" style="width: 100%; height: 100%;" >
                                    <input id="imagen" name="imagen" type="file"/>
                                    <p>Selecione la im&aacute;gen del Producto(Tama&ntilde;o m&aacute;ximo permitido 600k)</p>
                                </div>

                            </div>

                        </div>
                        <div class="col-sm-7">
                            <springForm:hidden path="rutaImagen" />
                            <springForm:hidden path="idproducto" />
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa  fa-cutlery"></i></span>
                                    <springForm:input cssClass="form-control" path="nombreproducto" maxlength="45" placeholder="Nombre"/>

                            </div>
                            <springForm:errors path="nombreproducto" cssClass="text-danger" />
                            <br />
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa  fa-usd fa-lg"></i></span>
                                    <springForm:input cssClass="form-control" path="precioproducto" maxlength="5" placeholder="Precio"/>
                            </div>
                            <springForm:errors path="precioproducto" cssClass="text-danger" />
                            <br />
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-comment"></i></span>
                                    <springForm:textarea cssClass="form-control" placeholder="Descripción" maxlength="200" rows="5" path="descripcion"/>
                            </div>
                            <springForm:errors path="descripcion" cssClass="text-danger" />
                            <br />
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa  fa-tag"></i></span>
                                    <springForm:select path="idCategoria" cssClass="form-control">
                                    <option value="">Categor&iacute;a</option>
                                    <c:forEach items="${categorias}" var="c">
                                        <option value="${c.cons}" <c:if test="${c.cons eq producto.idCategoria}"> selected </c:if>>${c.nombre}</option>
                                    </c:forEach>
                                </springForm:select>
                            </div>
                            <springForm:errors path="idCategoria" cssClass="text-danger" />
                            <br />
                            <springForm:hidden path="estado" />
                            <div class="rst-product-info main-product-detail">
                                <form action="/">
                                    <button class="btn btn-success btn-lg"><i class="fa fa-save fa-lg"></i> Guardar</button>
                                </form>
                                <hr />
                            </div>
                        </div>
                    </div>
                </springForm:form>
            </div>
        </div><!-- End Content -->
        <%-- Se muestra si hay un error interno --%>
        <c:if test="${not empty mensaje}">
            <script type="text/javascript">

                $.alert({
                    title: 'Mensaje',
                    content: '${mensaje}',
                    confirmButton: 'ok',
                    confirm: function () {
                    }
                });
                //reload img
                setTimeout(
                        reload
                        , 5000);
                function reload() {
                    var imagen = $("#load-pic").children()[0];
                    var rutaImg = $(imagen).attr("src");
                    d = new Date();
                    $(imagen).attr("src", rutaImg+"?"+d.getTime());
                }
            </script>
        </c:if>
    </body>
</html>
