<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="header_nd" >
    <h1 class="logo_herramientas">
    	
    </h1>
    <div id="contenidoHome">
    <div id="content_actions_user">
         <div class="actions_user">
             <p>Bienvenido
                 <a href="#" id="userLink"  >
                     <secured:authentication property="principal.username"/> 
                 </a>
             </p>
          </div>
     </div>
    </div>
	
    <!-- Inicio navigation -->
    <div class="menu_gnral_nd">
         <div class="line_red_nd"></div> 
         <ul class="level1">
              
              
                  
          </ul>
	</div> 
	<!-- Fin navigation -->
    <div class="clear"></div>
    
</div>      