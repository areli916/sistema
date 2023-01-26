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
            Usuarios misUsuarios = new Usuarios();
             LinkedList<Map<String, String>> usuarios=misUsuarios.consultarUsuarios("Activo");
            
        %>
        <%
       
        String correo = request.getParameter("correo");
        String nombre = request.getParameter("nombre");
         String pw = request.getParameter("pw");
        String error = request.getParameter("error");
        
        %>
    <form action="SrvValidarRegistroUsuario" method="post" name="frmAgregaralumno" id="frmAgregaralumno">     
      <table width="100%" border="0">
        <tr>
          <td colspan="2" bgcolor="#FFCC33"><div align="center">REGISTRAR USUARIO</div></td>
        </tr>
         <tr bordercolor="0">
          <td colspan="5" bgcolor="#FFCC33"><div align="right"><a href="bienvenida.jsp" target="_self">REGRESAR</a></div></td>
        </tr>
        <tr>
          <td>CORREO</td>
          <td><input readonly name="correo" type="text" id="correo" size="30" 
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
                if(error.equals("LEl correo que proporcionaste ya esta en el sistema")){
                   %>
                   <span class="Estilo1">*</span>  
                   <%
                }
            }
            %>
          </td>
        </tr>
        <tr>
          <td>NOMBRE </td>
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
          <td>PASSWORD</td>
          <td><input name="pw" type="text" id="pw"  size="20" maxlength="10" >
            <%
            if(pw!=null){
                if(pw.isEmpty()){
                    
            %>
              <span class="Estilo1">*</span>
          <%
                }
            }else if(error!=null){
                if(error.equals("El password que proporcionaste ya esta en el sistema")){
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
          <td><input type="submit" name="btnUsuarios" id="btnagregar" value="Agregar usuario"></td>
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
        <tr>
            
            
        </tr>
        
      </table>
    </form>
 
</body>
</html>

