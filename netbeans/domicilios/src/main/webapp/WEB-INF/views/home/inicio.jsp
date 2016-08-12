<%-- 
    Document   : inicio
    Created on : 7/08/2016, 05:05:04 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Domicilios Colombian Broaster</title>
    <script type="text/javascript">console.log("Fusion");</script>
</head>
<body>
    <h1>Lista de productos</h1>
    <div id="contenedorProductos">
        <c:forEach var="p" items="${productos}" varStatus="indice">
            ${indice.index} - ${p.idproducto},${p.nombreproducto},${p.precioproducto}
        </c:forEach>
    </div>
</body>
