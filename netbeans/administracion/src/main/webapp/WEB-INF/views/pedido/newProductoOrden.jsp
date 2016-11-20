<div class="panel panel-primary">
    <!-- Default panel contents -->
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-8">Agregar producto a la orden</div>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-6" >
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa  fa-cutlery"></i></span>
                    <input id="textoProducto" class="form-control" placeholder="escribe el nombre del producto" type="text" value="" maxlength="45">
                </div>
            </div>
            <div class="col-md-2" >
                <div class="quantity buttons_added">
                    <input type="button" value="-" class="minus">
                    <input  value="1" type="number" min="1" step="1" id="viewAddCantidad" title="Cantidad" class="input-text qty text" size="4">
                    <input type="button" value="+" class="plus">
                </div>
            </div>
            <div class="col-md-3" >
                <fieldset disabled>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa  fa-usd fa-lg"></i></span>
                        <input id="totalProducto" class="form-control" placeholder="total" type="text" value="0" maxlength="45">
                    </div>
                </fieldset>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center" style="margin: 2px;">
                <button id="addProductoOrder" class="btn btn-success" href="javascript:void(0);" disabled="disabled"><i class="fa fa-check"></i>Agregar</button>
            </div>
        </div>
    </div>
</div>
