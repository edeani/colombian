
<head>
    <link href="<%=request.getContextPath()%>/css/upload/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/upload/fileinput.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/logos/logoimagen.js"></script>
</head>
<body>

    <form id="formpho" method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/${sessionScope.path}/logo/guardar.htm">
        <div id="content-img" style="width: 100%; height: 100%;" >
            <input id="imagen" name="imagen" type="file"/>
            <p>Selecione la im&aacute;gen del Producto(Tama&ntilde;o m&aacute;ximo permitido 600k)</p>
        </div>    
    </form>
    <button id="subir">Aceptar</button>
</body>

