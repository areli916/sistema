
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
                    <td colspan="7" bgcolor="#FFCC33"><div align="center" class="Estilo1">TABLA DE ALUMNOS INACTIVOS</div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="7" bgcolor="#FFCC33"><div align="right"><a href="bienvenida.jsp" target="_self">REGRESAR</a></div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="4" bgcolor="#FFCC33">&nbsp;</td>
                    <td bgcolor="#FFCC33"><div align="right">BUSCAR ALUMNO</div></td>
                    <td bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
                    <td bgcolor="#FFCC33"><input name="btnAlumnos" type="submit" id="btnBuscar" value="Inactivos"></td>
                </tr>
                <tr>
                    <td bordercolor="0" ><span class="Estilo8">MATRÍCULA</span></td>
                    <td  class="Estilo8">CORREO </td>
                    <td  class="Estilo8">FECHA DE NACIMIENTO </td>
                    <td  class="Estilo8">CARRERA</td>
                    <td  class="Estilo8">NOMBRE</td>
                    <td  class="Estilo8">PASSWORD</td>
                    <td  class="Estilo8">REINTEGRAR</td> 
                </tr>                          
                <% 
                String[][] alumnos = null; //declaracion
                System.out.println("busqueda="+request.getParameter("busquedadeinactivosexitosa"));
                if(request.getParameter("busquedadeinactivosexitosa") != null){
                    alumnos = misAlumnos.consultarAlumnosInactivos(request.getParameter("busquedadeinactivosexitosa")); 
                } else {
                    alumnos = misAlumnos.consultarAlumnosInactivos();
                }
                String colorCelda = ""; 
                int i=0;
                for (String[] alumno: alumnos) {
                    if(i%2==0){ //si es par le ponemos color 
                        colorCelda = "#FFCC66"; 
                    } else {
                        colorCelda = "#FFFFFF"; 
                    }
                %>
                    <tr bgcolor="<%= colorCelda %>" > 
                        <td bordercolor="0" ><div align="center" class="Estilo6"><%= alumno[5] %>&nbsp;</div></td>
                        <td><div align="center" class="Estilo6"><%= alumno[2] %>&nbsp;</div></td>
                        <td><div align="center" class="Estilo6"><%= alumno[6] %>&nbsp;</div></td>
                        <td><div align="center" class="Estilo6"><%= alumno[7] %>&nbsp;</div></td>
                        <td><div align="center" class="Estilo6"><%= alumno[3] %>&nbsp;</div></td>
                        <td><div align="center" class="Estilo6"><%= alumno[4] %>&nbsp;</div></td>
                        <td><a href="SrvProcesosAlumnos?btnAlumnos=reintegrar&matricula=<%= alumno[2] %>" title="reintegrarAlumno" target="_self"><img src="./imagenes/reintegrar.png" alt="" width="90" height="60"/></a></td>

                    </tr>
                <%  
                    i++;
                }
                %>
            </table>
        </form>
    </body>
</html>
