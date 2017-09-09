<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id='cssmenu'>
    <ul>
        <!--Facturacion-->
        <li class='has-sub'><a href='#'><span>Facturaci&oacute;n</span></a>
            <ul>
                <li><a href='<%=request.getContextPath()%>/consolidado/comprobante/sede.htm'><span>Cierre Sedes</span></a></li>
                <li><a href='<%=request.getContextPath()%>/inventario/index.htm'><span>Inventario</span></a></li>
                <li class='has-sub'><a href='#'><span>Compras</span></a>
                    <ul>
                        <li><a href='<%=request.getContextPath()%>/compras/home.htm'><span>Compras</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/compras/edicion.htm'><span>Edici&oacute;n de compras</span></a></li>
                    </ul>
                </li>
                <li class='has-sub'><a href='#'><span>Ventas</span></a>
                    <ul>
                        <li><a href='<%=request.getContextPath()%>/factura/home.htm'><span>Ventas</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/factura/edicion.htm'><span>Edici&oacute;n ventas</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/factura/cambiarSede.htm'><span>Cambiar sede factura</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/traslados/home.htm'><span>Traslados</span></a></li>
                    </ul>
                </li>
                <li><a href='<%=request.getContextPath()%>/averia/home.htm'><span>Averias</span></a></li>
                <li><a href='<%=request.getContextPath()%>/proveedor/home.htm'><span>Proveedores</span></a></li>
            </ul>
        </li>
        <!--Contabilidad-->
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <li class='has-sub'><a href='#'><span>Contabilidad</span></a>
            <ul>
                <li><a href='<%=request.getContextPath()%>/cuentas/index.htm'><span>Cuentas</span></a></li>
                <li class='has-sub'><a href='#'><span>Tesorer&iacute;a</span></a>
                    <ul>
                        <li><a href='<%=request.getContextPath()%>/consolidado/comprobante/reporte/sede.htm'><span>Reporte Comprobante Sedes</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/pagos/terceros/index.htm'><span>Pagos Terceros</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/pagos/proveedor/index.htm'><span>Pagos Proveedor</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/pagos/sede/consolidado/index.htm'><span>Pagos Porcentaje Sedes</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/consolidado/comprobante/cajamayor.htm'><span>Caja Mayor</span></a></li>
                        <li class='has-sub'><a href='#'><span>Imprimir</span></a>
                            <ul>
                                <li><a href='<%=request.getContextPath()%>/imprimir/comprobante/pago.htm'><span>Comprobantes de Pago</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class='has-sub'><a href='#'><span>Bancos</span></a>
                    <ul>
                        <li><a href='<%=request.getContextPath()%>/cajamenor/terceros/index.htm'><span>Pagos Terceros Bancos</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/cajamenor/proveedor/index.htm'><span>Pagos Proveedor Bancos</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/cajamenor/sede/consolidado/index.htm'><span>Pagos Porcentaje Bancos</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/cajamenor/reporte/index.htm'><span>Movimiento Bancos</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/notas/debito.htm'><span>Notas Debito</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/notas/credito.htm'><span>Notas Cr&eacute;dito</span></a></li>
                    </ul>
                </li>
                <li class='has-sub'><a href='#'><span>Estado P&eacute;rdida Ganancias</span></a>
                    <ul>
                        <li><a href='<%=request.getContextPath()%>/consolidado/reporte/general/perdidaganancias.htm'><span>Resumen</span></a></li>
                        <li><a href='<%=request.getContextPath()%>/consolidado/reporte/perdidaganancias.htm'><span>Detalle</span></a></li>
                    </ul>
                </li>
            </ul>
        </li>
        </sec:authorize>
        <!--Reportes-->
        <li class='has-sub'><a href='#'><span>Reportes</span></a>
            <ul>
        <li><a href='<%=request.getContextPath()%>/inventario/reportes/inventario.htm'><span>Reporte de Inventario</span></a></li>
                <li><a href='<%=request.getContextPath()%>/compras/reportes/comprasTotales.htm'><span>Reporte de Compras Totales</span></a></li>
                <li><a href='<%=request.getContextPath()%>/compras/reportes/comprasTotalesProveedor.htm'><span>Reporte de Compras Totales por Proveedor</span></a></li>
                <li><a href='<%=request.getContextPath()%>/compras/reportes/comprasTotalesProducto.htm'><span>Listado facturas compra</span></a></li>
                <li><a href='<%=request.getContextPath()%>/compras/reportes/comprasTotalesProveedores.htm'><span>Listado Facturas compra por proveedor</span></a></li>
                <li><a href='<%=request.getContextPath()%>/compras/reportes/comprasProveedorFecha.htm'><span>Compras vs Pagos Proveedores</span></a></li>
                <li><a href='<%=request.getContextPath()%>/compras/reportes/cuentasPagarProveedor.htm'><span>Estado Cuenta proveedor</span></a></li>
                <li><a href='<%=request.getContextPath()%>/factura/reportes/ventasTotales.htm'><span>Reporte de Ventas Totales</span></a></li>
                <li><a href='<%=request.getContextPath()%>/factura/reportes/ventasTotalesSede.htm'><span>Reporte de Ventas Totales por Sede</span></a></li>
                <li><a href='<%=request.getContextPath()%>/factura/reportes/totalFacturasSede.htm'><span>Reporte Facturas Totales por Sede</span></a></li>
                <li><a href='<%=request.getContextPath()%>/factura/reportes/totalFacturas.htm'><span>Reporte Facturas Totales</span></a></li>
                <li><a href='<%=request.getContextPath()%>/inventario/reportes/inventarioTotal.htm'><span>Inventario Total</span></a></li>
                <li><a href='<%=request.getContextPath()%>/consolidado/sede.htm'><span>Consolidado</span></a></li>
            </ul>
        </li>
        <li class='has-sub'><a href='#'><span>Entrar Sedes</span></a>
            <ul>
                <li><a href='<%=request.getContextPath()%>/tiemporeal/cierres.htm'><span>Tiempo Real</span></a></li>
                <li><a href='<%=request.getContextPath()%>/inventario/colombian/reporte/inventarios.htm'><span>Inventario</span></a></li>
                <li><a href='<%=request.getContextPath()%>/compras/colombian/reportes/compras.htm'><span>Compras</span></a></li>
                <li><a href='<%=request.getContextPath()%>/mesasyllevar/ordenes.htm'><span>Mesas y Llevar</span></a></li>
            </ul>
        </li>
    </ul>

    <div id="cerrarSesion">
        <a href='<%=request.getContextPath()%>/logout.htm'>
            <span>Cerrar Sesi&oacute;n
            </span>
        </a>
    </div>

</div>
