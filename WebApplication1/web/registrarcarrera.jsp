<%-- 
    Document   : registrarcarrera
    Created on : 5/01/2023, 03:31:37 PM
    Author     : Windows 10
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Map"%>
<%@page import="administradorDB.Carreras"%>
<%@page import="administradorDB.Alumnos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
<!--
.Estilo1 {color: #FF0000}
.Estilo5 {color: #FFFFFF}
-->
        </style>
</head>
    <body>
        <%! 
            Carreras miscarreras = new Carreras();
            LinkedList<Map<String, String>> carreras= miscarreras.consultarCarreras("Activa");
            
        %>
        <%
        String clave = request.getParameter("clave");
        String nombrecarrera = request.getParameter("nombrecarrera");
        String error=request.getHeader("error");
        
        
        %>
    <form action="SrvValidarRegistroCarrera" method="post" name="frmAgregaralumno" id="frmAgregaralumno">     
      <table width="100%" border="0">
        <tr>
          <td colspan="2" bgcolor="#FFCC33"><div align="center">REGISTRAR CARRERA</div></td>
        </tr>
         <tr bordercolor="0">
          <td colspan="5" bgcolor="#FFCC33"><div align="right"><a href="catalogocarreras.jsp" target="_self">REGRESAR</a></div></td>
        </tr>
        <tr>
          <td>clave</td>
          <td><input name="clave" type="text" id="matricula" size="20" maxlength="10" 
                     <%
                     if(clave != null){
                     %>
                     value="<%=clave%>"
                     <%
                     }
                     %>
          >
            <%
            if(clave!=null){
                if(clave.isEmpty()){
                    
            %>
            <span class="Estilo1">*</span> 
            <%
                }
            }else if(error!=null){
                if(error.equals("La clave que proporcionaste ya esta en el sistema")){
                   %>
                   <span class="Estilo1">*</span>  
                   <%
                }
            }
            %>
          </td>
        </tr>
        <tr>
          <td>nombre carrera </td>
          <td><input name="nombrecarrera" type="text" id="correo" size="50" maxlength="256"
                     <%
                     if(nombrecarrera != null){
                     %>
                     value="<%=nombrecarrera%>"
                     <%
                     }
                     %>  
          >
            <%
            if(nombrecarrera!=null){
                if(nombrecarrera.isEmpty()){
                    
                    
            %>
          <span class="Estilo1">*</span> 
          <%
                }
            }else if(error!=null){
                if(error.equals("la carrera que proporcionaste ya esta en el sistema")){
                   %>
                   <span class="Estilo1">*</span>  
                   <%
                }
            }
            %>
        <tr>
          <td>&nbsp;</td>
          <td><input type="submit" name="btnagregar" id="btnagregar" value="Agregar carrera"></td>
        </tr>
          
        <tr>
          <td colspan="2" bgcolor="#FFFFFF"><div align="center" class="Estilo1">ERROR: <%=error%> </div></td>
        </tr>
        <tr>
            
            
        </tr>
        
      </table>
    </form>
 
</body>
</html>

