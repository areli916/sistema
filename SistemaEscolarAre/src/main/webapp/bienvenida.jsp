<%@page import="administradorDB.Alumnos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            <!--
            .Estilo1 {
                    color: #000099;
                    font-size: 24px;
                    font-weight: bold;
            }
            .Estilo6 {font-size: 18px; color: #000000; }
            .Estilo7 {color: #00FF00}
            .Estilo8 {color: #000000}
            -->
        </style>
    </head>
    
    <body>
        <a href="Inactivos.jsp" target="_self">MOSTRAR INACTIVOS</a>
        <%! 
            Alumnos misAlumnos = new Alumnos();
        %>
        <form action="SrvProcesosAlumnos" method="post" name="formAlumnos" id="formAlumnos">
            <table width="100%" border="0">
                <% 
                String mensaje=request.getParameter("mensaje");
                if(mensaje!=null){
                %>
                    <tr bordercolor="0">
                        <td colspan="6" bgcolor="#FFFFFF"><div align="center" class="Estilo7">MENSAJE: <%=mensaje%></div></td>
                    </tr>
                <% 
                }
                %>
                <tr bordercolor="0">
                    <td colspan="6" bgcolor="#FFCC33"><div align="center" class="Estilo1 Estilo8">TABLA DE ALUMNOS</div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="6" bgcolor="#FFCC33"><div align="right" class="Estilo8"><a href="registraralumno.jsp" title="REGISTRAR UN NUEVO ALUMNO" target="_self">REGISTRAR NUEVO ALUMNO</a></div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="2" bgcolor="#FFCC33"><span class="Estilo8"></span></td>
                    <td bgcolor="#FFCC33"><div align="right" class="Estilo8">BUSCAR ALUMNO</div></td>
                    <td colspan="2" bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
                    <td bgcolor="#FFCC33"><input name="btnAlumnos" type="submit" id="btnBuscar" value="Buscar"></td>
                </tr>
                <tr>
                    <td bordercolor="0" bgcolor="#FFCC66"><span class="Estilo8">MATRÍCULA</span></td>
                    <td bgcolor="#FFCC66" class="Estilo8">CORREO </td>
                    <td bgcolor="#FFCC66" class="Estilo8">FECHA DE NACIMIENTO </td>
                    <td bgcolor="#FFCC66" class="Estilo8">CARRERA</td>
                    <td bgcolor="#FFCC66" class="Estilo8">ELIMINAR</td> 
                    <td bgcolor="#FFCC66" class="Estilo8">MODIFICAR</td> 
                </tr>                     
                <% 
                String[][] alumnos = null; //declaracion
                if(request.getParameter("alumnosEncontrados") != null){
                    alumnos = misAlumnos.consultarAlumnosActivos(request.getParameter("alumnosEncontrados")); 
                } else {
                    alumnos = misAlumnos.consultarAlumnosActivos();
                }
                String colorCelda = "#E2FAFC"; 
                for (int i = 0; i < alumnos.length; i++) {
                    if(i%2==0){ //si es par le ponemos color 
                        colorCelda = "#E2FAFC"; 
                    } else {
                        colorCelda = "#FFFFFF"; 
                    }
                %>
                    <tr bgcolor="<%= colorCelda %>"> 
                        <td bordercolor="0" bgcolor="#FFCC66"><div align="center" class="Estilo6"><%= alumnos[i][0] %>&nbsp;</div></td>
                        <td bgcolor="#FFCC66"><div align="center" class="Estilo6"><%= alumnos[i][1] %>&nbsp;</div></td>
                        <td bgcolor="#FFCC66"><div align="center" class="Estilo6"><%= alumnos[i][2] %>&nbsp;</div></td>
                        <td bgcolor="#FFCC66"><div align="center" class="Estilo6"><%= alumnos[i][3] %>&nbsp;</div></td>
                        <td bgcolor="#FFCC66"><a href="SrvProcesosAlumnos?btnAlumnos=eliminar&matricula=<%= alumnos[i][0] %>" title="eliminarAlumno" target="_self"><img src="./imagenes/ELIMINAR.png" alt="" width="90" height="60"/></a></td>
                        <td bgcolor="#FFCC66"><a href="modificaralumno.jsp" title="modificarAlumno" target="_self"><img src="./imagenes/modificar.png" alt="" width="90" height="60"/></a></td>
                    </tr>
                <%  
                }
                %>
            </table>
        </form>
    </body>
</html>
