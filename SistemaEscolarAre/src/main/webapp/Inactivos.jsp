
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
                    color: #000000;
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
                        <td height="52" colspan="5" bgcolor="#FFFFFF"><div align="center" class="Estilo7">MENSAJE: <%=mensaje%></div></td>
                    </tr>
                <% 
                }
                %>
                <tr bordercolor="0">
                    <td colspan="5" bgcolor="#FFCC33"><div align="center" class="Estilo1">TABLA DE ALUMNOS INACTIVOS</div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="5" bgcolor="#FFCC33"><div align="right"><a href="bienvenida.jsp" target="_parent">REGRESAR</a></div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="2" bgcolor="#FFCC33">&nbsp;</td>
                    <td bgcolor="#FFCC33"><div align="right">BUSCAR ALUMNO</div></td>
                    <td bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
                    <td bgcolor="#FFCC33"><input name="btnAlumnos" type="submit" id="btnBuscar" value="Inactivos "></td>
                </tr>
                <tr>
                    <td bordercolor="0" bgcolor="#FFCC66"><span class="Estilo8">MATRÍCULA</span></td>
                    <td bgcolor="#FFCC66" class="Estilo8">CORREO </td>
                    <td bgcolor="#FFCC66" class="Estilo8">FECHA DE NACIMIENTO </td>
                    <td bgcolor="#FFCC66" class="Estilo8">CARRERA</td>
                    <td bgcolor="#FFCC66" class="Estilo8">REINTEGRAR</td> 
                </tr>                     
                <% 
                String[][] alumnos = null; //declaracion
                System.out.println("busqueda="+request.getParameter("busquedadeinactivosexitosa"));
                if(request.getParameter("busquedadeinactivosexitosa") != null){
                    alumnos = misAlumnos.consultarAlumnosInactivos(request.getParameter("busquedadeinactivosexitosa")); 
                } else {
                    alumnos = misAlumnos.consultarAlumnosInactivos();
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
                        <td bgcolor="#FFCC66"><a href="SrvProcesosAlumnos?btnAlumnos=reintegrar&matricula=<%= alumnos[i][0] %>" title="reintegrarAlumno" target="_self"><img src="./imagenes/reintegrar.png" alt="" width="90" height="60"/></a></td>
                    </tr>
                <%  
                }
                %>
            </table>
        </form>
    </body>
</html>
