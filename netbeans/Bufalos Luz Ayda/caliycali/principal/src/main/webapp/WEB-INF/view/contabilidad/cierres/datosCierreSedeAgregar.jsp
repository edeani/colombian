<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<div id="fila" style="display: none;">
    <table id="generica">
        <thead></thead>
        <tbody>
            <tr style="background: green;   ">
                <td>
                    <input name="comprobanteConsolidadoSedeDto[@].idCuenta" value="${idCuenta}" class="claseValidarNum contentRequired claseCuentaCierre"/>
                </td>
                <td>
                    <input type="text" name="comprobanteConsolidadoSedeDto[@].concepto" value=""   />
                </td>
                <td>
                    $<input id="deber" value="0" class="claseFormatDec claseTotalDeber"/>
                </td>    
                <td>
                    $<input id="haber" value="0" class="claseFormatDec claseTotalHaber"/>
                    <input type="hidden" id="totalDetalle" name="comprobanteConsolidadoSedeDto[@].total" value="0"  class="claseFormatDec claseTotalDetalle"/>
                    <input type="hidden" name="comprobanteConsolidadoSedeDto[@].idConcepto" value="0"/>
                    <input type="hidden" name="comprobanteConsolidadoSedeDto[@].sede" value="${sede}"/>
                    <input type="hidden" name="comprobanteConsolidadoSedeDto[@].idSede" value="${idSede}"/>
                    <input type="hidden" name="comprobanteConsolidadoSedeDto[@].fecha" value="${fecha}"/>
                </td>
                <td>
                <td align="right"><input type="button" value="-" class="claseEliminarFilaCierre"></td>
                </td>
            </tr>
        </tbody> 
    </table>
</div>
<table id="tblDatos" class="tblComprobanteCierre" align="center" style="display: none;">
    <thead>
        <tr>
            <th>Cuenta</th>
            <th>Concepto</th>
            <th>Deber</th>
            <th>Haber</th>
        </tr>
    </thead>
    <tbody id="contenidoFactura" data-url ="<%=request.getContextPath()%>/inventario/ajax/selectProducto.htm">

    </tbody>
    <tfoot>
        <tr>
            <td></td>
            <td>Sumas Iguales</td>
            <td>$<input name="totalDeber" id="totalDeber" value="0" readonly="readonly" class="claseFormatDec"/></td>
            <td>$<input name="totalHaber" id="totalHaber" value="0" readonly="readonly" class="claseFormatDec"/></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="4" align="right">
                <input type="button" id="guardarComprobanteCierreSede" value="Generar" />
                <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
            </td>
        </tr>
    </tfoot>
</table>
<script type="text/javascript">
    agregarFila('tblDatos', 'fila');
    actualizarHaberDeber();
    $("#agregarFila").show();
    $("#tblDatos").show();
</script>
