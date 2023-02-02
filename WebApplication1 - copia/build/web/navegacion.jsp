

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema escolar</title>
    </head>
    
    <style type="text/css" >
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        /* Change the link color to #111 (black) on hover */
        li a:hover {
            background-color: #D9AD26;
        }
        li a#logout:hover {
            background-color: #808080;
        }
        
        li a:active{
            background-color: #0CF;
        }
    </style>
   
    <body>
        <navbar>
            <ul id="navAlumnos">
                <li><a href="bienvenida.jsp" target="navegacion">Administrar alumnos</a></li>
                <!--li><a href="catalogousuarios.jsp" target="navegacion">Administrar usuarios</a></li-->
                <li><a href="catalogocarreras.jsp" target="navegacion">Administrar carreras</a></li>
                <li><a href="index.jsp" target="_parent">CERRAR SESIÓN</a></li>
            </ul>
        </navbar>
        <iframe name="navegacion" id="navegacion" src="bienvenida.jsp" style="height:900px;width:100%"/>
    </body>
</html>
