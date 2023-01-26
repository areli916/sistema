<%-- 
    Document   : modificarusuario
    Created on : 17/01/2023, 01:03:18 AM
    Author     : Windows 10
--%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Map"%>
<%@page import="administradorDB.Usuarios"%>


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
            Usuarios misusuarios = new Usuarios();
            LinkedList<Map<String, String>> usuarios= misusuarios.consultarUsuarios("Activo");
            
        %>
        <%
        
        String correo = request.getParameter("correo");
        String nombre = request.getParameter("nombre");
        String pw = request.getParameter("pw");
        String error = request.getParameter("error");
        %>
        <form action="SrvProcesosUsuarios?correo=<%=correo%>" method="post" name="frmAgregaralumno" id="frmAgregaralumno">     
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
        <tr>
          <td colspan="2" bgcolor="#FFCC33"><div align="center">MODIFICAR USUARIO</div></td>
        </tr>
         <tr bordercolor="0">
          <td colspan="5" bgcolor="#FFCC33"><div align="right"><a href="catalogousuarios.jsp" target="_self">REGRESAR</a></div></td>
        </tr>
        <tr>
          <td>correo</td>
          <td><input disabled type="text" id="correo" size="20" maxlength="10" 
                     <%
                     if(correo != null){
                     %>
                     value="<%=correo%>"
                     <%
                     }
                     %>
          >
            <%
            if(correo!=null){
                if(correo.isEmpty()){
                    
            %>
            <span class="Estilo1">*</span> 
            <%
                }
            }else if(error!=null){
                if(error.equals("el correo que proporcionaste ya esta en el sistema")){
                   %>
                   <span class="Estilo1">*</span>  
                   <%
                }
            }
            %>
          </td>
        </tr>
        <tr>
          <td>nombre </td>
          <td><input name="nombre" type="text" id="nombre" size="50" maxlength="256"
                     <%
                     if(nombre != null){
                     %>
                     value="<%=nombre%>" 
                     <%
                     }
                     %>  
          >
            <%
            if(correo!=null){
                if(correo.isEmpty()){
                    
            %>
          <span class="Estilo1">*</span> 
          <%
                }
            }else if(error!=null){
                if(error.equals("El nombre que proporcionaste ya esta en el sistema")){
                   %>
                   <span class="Estilo1">*</span>  
                   <%
                }
            }
            %>
          </td>
        </tr>
        <tr>
          <td>password</td>
          <td><input name="pw" type="text" id="pw"  size="20" maxlength="10" 
                     <%
                     if(pw != null){
                     %>
                     value="<%=pw%>"
                     <%
                     }else{
                     %>
                     value="aaaa-mm-dd"
                     <%
                     }
                     %>
          >
            <%
            if(pw!=null){
                if(pw.isEmpty()){
                    
            %>
              <span class="Estilo1">*</span>
          <%
                }
            }else if(error!=null){
                if(error.equals("el pw que proporcionaste ya esta en el sistema")){
                   %>
                   <span class="Estilo1">*</span>  
                   <%
                }
            }
            %>
          </td>
        </tr>
        <tr>
         
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="submit" name="btnUsuarios" id="btnUsuarios" value="modificar"></td>
        </tr>
        <% 
            if(error!=null){
           
        %>
        <tr>
          <td colspan="2" bgcolor="#FFFFFF"><div align="center" class="Estilo1">ERROR: <%=error%> </div></td>
        </tr>
        <% 
            }
        %>
      </table>
    </form>
 
</body>
</html>
