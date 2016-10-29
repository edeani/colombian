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
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/producto/crearproducto.js"></script>
    </head>
    <body>
        <div id="content">
            <c:if test="${estado ne 'N'}">
                <div id="rst-banner" data-background="<%=request.getContextPath()%>/img/post/banner02.jpg">
                    <div class="container">
                        <div class="rst-inner-banner clearfix">
                            <div class="rst-banner-content pull-left">
                                <h1>Sushi</h1>
                                <p>We opened. Tasty food &amp; drinks.</p>
                            </div>
                            <ul class="breadcrumb pull-right">
                                <li><a href="index.html">Home</a></li>
                                <li><a href="products_list.html">Store</a> </li>
                                <li>Sushi</li>
                            </ul>
                        </div>
                    </div>
                </div><!-- Banner -->
            </c:if>
            <div class="container rst-main-content rst-product-detail">
                <springForm:form action="${pageContext.servletContext.contextPath}/productos/ingresar-producto.htm" method="post" commandName="productoDetailDto">
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="rst-product-images">
                                <ul class="bxslider">
                                    <li>

                                       <!--img style="width: 260px;" src="<%=request.getContextPath()%>/img/post/gallery10.jpg" alt="Foto" /-->

                                    </li>
                                </ul>
                                <div id="content-img" style="width: 100%; height: 100%;">
                                    <input id="uploadimg" type="file" >
                                </div>
                            </div>

                        </div>
                        <div class="col-sm-7">
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
                                    <springForm:select path="tipo" cssClass="form-control">
                                    <option value="">Categor&iacute;a</option>
                                    <c:forEach items="${categorias}" var="c">
                                        <option value="${c.cons}" <c:if test="${c.cons eq producto.tipo}"> selected </c:if>>${c.nombre}</option>
                                    </c:forEach>
                                </springForm:select>
                            </div>
                            <springForm:errors path="tipo" cssClass="text-danger" />
                            <br />
                            <springForm:hidden path="estado" />
                        </div>
                    </div>
                    <button class="btn btn-primary"><i class="fa fa-camera-retro fa-lg" aria-hidden="true"></i> Im&aacute;gen</button>
                </springForm:form>
            </div>
        </div><!-- End Content -->
        <%-- Se muestra si hay un error interno --%>
        <c:if test="${not empty mensaje}">
            <script type="text/javascript">
                $(document).ready(function () {
                $.alert({
                title: 'Mensaje',
                        content: '${mensaje}',
                        confirmButton: 'ok'
                        confirm: function () {
                        }
                });
                });
            </script>
        </c:if>
    </body>
</html>
