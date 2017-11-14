<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<th id="${sede}">
<c:import url="/inventario/ajax/sede.htm">
    <c:param name="sede" value="${sede}"/>
    <c:param name="fecha" value="${fecha}"/>
</c:import>
</th>