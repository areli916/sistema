<%-- 
    Document   : Inactivos
    Created on : 12/11/2022, 12:31:33 AM
    Author     : areli
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Map"%>
<%-- 
    Document   : bienvenida
    Created on : 17/10/2022, 04:46:26 PM
    Author     : 52561
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
          <td colspan="8" bgcolor="#FFCC33"><div align="center" class="Estilo1">TABLA DE ALUMNOS INACTIVOS</div></td>
        </tr>
        <tr bordercolor="0">
          <td colspan="8" bgcolor="#FFCC33"><div align="right"><a href="bienvenida.jsp" target="_self">REGRESAR</a></div></td>
        </tr>
        <tr bordercolor="0">
          <td colspan="5" bgcolor="#FFCC33">&nbsp;</td>
          <td bgcolor="#FFCC33"><div align="right">BUSCAR ALUMNO</div></td>
          <td bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
          <td bgcolor="#FFCC33"><input name="btnAlumnos" type="submit" id="btnBuscar" value="Inactivos"></td>
        </tr>
        <tr>
          <td bordercolor="0" ><span class="Estilo8">MATRÍCULA</span></td>
          <td  class="Estilo8">CORREO </td>
          <td  class="Estilo8">FECHA DE NACIMIENTO </td>
          <td  class="Estilo8">ACTIVIDAD CARRERA</td>
          <td  class="Estilo8">CARRERA</td>
          <td  class="Estilo8">NOMBRE</td>
          <td  class="Estilo8">PASSWORD</td>
          <td  class="Estilo8">REINTEGRAR</td> 
        </tr>                     
        <% 
            //String[][] alumnos = null; //declaracion
            LinkedList<Map<String, String>> alumnos= null;
            //System.out.println("busqueda="+request.getParameter("busquedadeinactivosexitosa"));
            if(request.getParameter("busquedadeinactivosexitosa") != null){
                alumnos = misAlumnos.buscarAlumnos(request.getParameter("busquedadeinactivosexitosa"), "Inactivo"); 
                //alumnos = misAlumnos.buscarInactivos(request.getParameter("busquedadeinactivosexitosa")); 
            } else {
                alumnos = misAlumnos.consultarAlumnos("Inactivo");
            }
            String colorCelda = ""; 
            int i=0;
            for(Map<String, String> alumno: alumnos){
                if(i%2==0){ //si es par le ponemos color 
                    colorCelda = "#FFCC66"; 
                } else {
                    colorCelda = "#FFFFFF"; 
                }
        %>
        <tr bgcolor="<%= colorCelda %>"> 
          <td bordercolor="0" ><div align="center" class="Estilo6"><%= alumno.get("matricula") %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumno.get("correo") %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumno.get("f_nacimiento") %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumno.get("Actividadcarrera") %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumno.get("nombrecarrera") %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumno.get("nombre") %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= alumno.get("pw") %>&nbsp;</div></td>
          <td><a href="SrvProcesosAlumnos?btnAlumnos=reintegrar&matricula=<%= alumno.get("matricula") %>" title="reintegrarAlumno" target="_self"><img src="./imagenes/reintegrar.png" alt="" width="90" height="60"/></a></td>
        </tr>
        <%  
                i++;
            }
        %>
      </table>
    </form>
		
</body>
</html>
