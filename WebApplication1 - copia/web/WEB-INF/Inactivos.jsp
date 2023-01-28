<%-- 
    Document   : Inactivos
    Created on : 11/11/2022, 12:50:34 PM
    Author     : areli
--%>

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
.Estilo4 {color: #0000FF}
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
    <form action="SrvProcesosAlumnos" method="get" name="formAlumnos" id="formAlumnos">
      <table width="100%" border="0">
        <% 
        String mensaje=request.getParameter("mensaje");
        if(mensaje!=null){
            
        %>
        <tr bordercolor="0">
          <td colspan="5" bgcolor="#FFFFFF"><div align="center" class="Estilo7">MENSAJE: <%=mensaje%></div></td>
        </tr>
        <% 
            }
        %>
        <tr bordercolor="0">
          <td colspan="5" bgcolor="#F71766"><div align="center" class="Estilo1 Estilo8">TABLA DE ALUMNOS INACTIVOS </div></td>
        </tr>
        <tr bordercolor="0">
          <td colspan="5" bgcolor="#FF0080"><div align="right"><a href="registraralumno.jsp" title="REGISTRAR UN NUEVO ALUMNO" target="_self">REGISTRAR NUEVO ALUMNO</a><a href="Inactivos.jsp" title="MOSTRAR ALUMNO INACTIVO" target="_self">MOSTRAR ALUMNO INACTIVO</a></div></td>
        </tr>
        <tr bordercolor="0">
          <td colspan="2" bgcolor="#9999FF">&nbsp;</td>
          <td bgcolor="#9999FF"><div align="right">BUSCAR ALUMNO INACTIVO</div></td>
          <td bgcolor="#9999FF"><input name="buscar" type="text" id="buscar" size="50"></td>
          <td bgcolor="#9999FF"><input name="btnAlumnos" type="submit" id="btnBuscar" value="Buscar"></td>
        </tr>
        <tr>
          <td bordercolor="0" bgcolor="#FFCCCC"><span class="Estilo4">MATRÍCULA</span></td>
          <td bgcolor="#FFCCCC" class="Estilo4">CORREO </td>
          <td bgcolor="#FFCCCC" class="Estilo4">FECHA DE NACIMIENTO </td>
          <td bgcolor="#FFCCCC" class="Estilo4">CARRERA</td>
          <td bgcolor="#FFCCCC" class="Estilo4">MODIFICAR</td> 
        </tr>                     
        <% 
            String[][] alumnos = null; //declaracion
            if(request.getParameter("alumnosEncontrados") != null){
                alumnos = misAlumnos.buscarAlumnos(request.getParameter("alumnosEncontrados")); 
            } else {
                alumnos = misAlumnos.consultarInactivos();
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
          <td bordercolor="0"><div align="center" class="Estilo6"><%= alumnos[i][0] %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumnos[i][1] %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumnos[i][2] %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumnos[i][3] %>&nbsp;</div></td>
          
          <td><a href=" modificaralumno.jsp?matricula=<%= alumnos[i][0] %>" title="modificarAlumno" target="_self"><img src="./imagenes/EDITAR1.png" alt="" width="90" height="60"/></a></td>
        </tr>
        <%  
            }
        %>
      </table>
    </form>
		
</body>
</html>
