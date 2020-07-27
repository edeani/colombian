<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-sm-9">
        <div class="rst-view"> <a href="#" class="rst-tile active"></a> <a href="#" class="rst-list"></a> </div>
    </div>
</div>
<div class="row product-list view-tile">
    <c:set var="seccion" value="">
    </c:set>
    <c:set var="seccion_temp" value="">
    </c:set>
    <c:forEach items="${productos}" var="p" varStatus="indice">
        <c:if test="${seccion_temp ne p.nombreTipo}">
            <c:set var="seccion" value="id='${p.nombreTipo}'">
            </c:set>
            <c:set var="seccion_temp" value="${p.nombreTipo}">
            </c:set>
        </c:if>
        <c:if test="${indice.index == 0}">
            <!--Start row 0-->
            <div class="row">
        </c:if>
        <div class="col-sm-10 product-item col-sm-4" ${seccion}>
            <div class="rst-thumbnail"> <a class="addCar addtocard" data-id="${p.idproducto}" data-nombre="${p.nombreproducto}" data-cantidad="1" data-data-nombprecio="${p.precioproducto}" href="#">
                    <c:choose>
                        <c:when test="${not empty p.imagen}"> <img src="${urlImg}/${p.imagen}" alt="${p.nombreproducto}" /> </c:when>
                        <c:otherwise> <img src="${urlImg}/gallery10.jpg" alt="default image" style="width: 240px; height: 143px;"/> </c:otherwise>
                    </c:choose>
                </a>
                <div class="rst-hover"> ${p.nombreTipo} <a class="addCar addtocard" data-id="${p.idproducto}" data-nombre="${p.nombreproducto}" data-cantidad="1" data-precio="${p.precioproducto}" href="#"></a> </div>
            </div>
            <div class="rst-product-info">
                <h3><a class="addCar" data-id="${p.idproducto}" data-nombre="${p.nombreproducto}" data-cantidad="1" data-precio="${p.precioproducto}" href="#">${p.nombreproducto}</a></h3>
                <span class="price-product"><sup>$</sup> ${p.precioproducto}</span> <span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
                <hr/>
                <div class="rst-product-content">
                    <p>${p.descripcion}</p>
                </div>
            </div>
        </div>
        <c:set var="seccion" value="">
        </c:set>
        <c:set var="statusMod" value="${(indice.index + 1) mod 3}"/>
        <!-- ======================= ${statusMod} ===Indice ${indice.index} -->
        <c:if test="${statusMod == 0 and indice.index lt (productsNumber -1) and indice.index != 0}"> 
            </div>
            <!--End row 1--> 
            <div class="row">
            <!--Start row 1-->
        </c:if>
        <c:if test="${indice.index eq productsNumber -1}">
            <!--End row 2--> 
          </div>
        </c:if>
    </c:forEach>
</div>
<!-- End Product List-->
<nav class="wp-pagenavi">
    
    <c:choose>
        <c:when test="${empty totalFilterProducts}">
            <c:set var="finalPages" value="${pages}"/>
        </c:when>
        <c:otherwise>
            <c:set var="finalPages" value="${totalFilterProducts}"/>
        </c:otherwise>
    </c:choose>
    <c:if test="${finalPages  > 1}"> <a class="btn btn-sm btn-success page-prev" href="#">Atr&aacute;s</a> </c:if>
    <c:forEach var="indice" begin="1" end="${finalPages}">
        <c:choose>
            <c:when test="${indice==actualPage}"> <span class="btn btn-sm btn-success page-numbers current">${indice}</span> </c:when>
            <c:otherwise> <a class="page-numbers elem-page" data-href="${indice}" href="javascript:void(0);">${indice}</a> </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${finalPages  > 1}"> <a class="btn btn-sm btn-success page-next" href="#">Siguiente</a> </c:if>
</nav>