<table id="tblDatos" class="tblPagosTerceros" align="center" style="display: none;">
    <thead>
        <tr>
            <th>Sede</th>
            <th>Cuenta</th>
            <th>Concepto</th>
            <th>Detalle</th>
            <th>Total</th>
            <th width="22">&nbsp;</th>
        </tr>
    </thead>
    <tbody id="contenidoFactura" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/selectProducto.htm">
        <tr>
            <td>
                <select data-numero="0" class="claseSelectSede" id="identificadorSede0" >
                    <!--Aquí van las opciones-->
                </select>
                <input type="hidden" id="inputIdentificadorSede0" name="detallePagosTerceros[0].idSede" value=""/>    
            </td>
            <td>
                <input id="idpagotercero0" name="detallePagosTerceros[0].idpagotercero" value="" type="hidden"/>
                <input data-numero="0" id="numerocuenta0" name="detallePagosTerceros[0].idCuenta" value="" class="ui-autocomplete-input claseValidarNum claseCuenta" autocomplete="off"/>
            </td>
            <td>
                <input class="claseConceptoCuenta" id="concepto0" name="detallePagosTerceros[0].conceptoCuenta" value=""/>
            </td>   
            <td>
                <input class="clasedescripcion claseproximocampo" id="detalle0" name="detallePagosTerceros[0].detalle" value="" maxlength="500"/>
            </td>
            <td>
                <input class="claseValidarNum claseFormatDec claseTotalProveedor" id="total0" name="detallePagosTerceros[0].total" value=""/>
                <input id="fechaPago0" name="detallePagosTerceros[0].fecha" value="" type="hidden"/>
            </td>
            <td align="right">
                <input type="button" value="-" class="clsEliminarFila">
                <input type="hidden" id="numero0" name="detallePagosTerceros[0].numero" value="1"/>
            </td>
        </tr>
    </tbody>
    <tfoot>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>Total</td>
            <td><input name="totalPago" id="totalPago" value="0"/></td>
        </tr>
        <tr>    
            <td></td>
            <td></td>
            <td colspan="4" align="right">
                <input type="button" id="generar" value="Generar" />
                <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
            </td>
        </tr>
    </tfoot>
</table>