

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Map"%>
<%@page import="administradorDB.Usuarios"%>
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
            Usuarios misUsuarios = new Usuarios();
        %>
        <form action="SrvProcesosUsuarios" method="post" name="formUsuarios" id="formUsuarios">
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
              <td colspan="6" bgcolor="#FFCC33"><div align="center" class="Estilo1">TABLA DE USUARIOS INACTIVOS</div></td>
            </tr>
            <tr bordercolor="0">
              <td colspan="6" bgcolor="#FFCC33"><div align="right"><a href="catalogousuarios.jsp" target="_self">REGRESAR</a></div></td>
            </tr>
            <tr bordercolor="0">
              <td colspan="3" bgcolor="#FFCC33">&nbsp;</td>
              <td bgcolor="#FFCC33"><div align="right">BUSCAR USUARIO</div></td>
              <td bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
              <td bgcolor="#FFCC33"><input name="btnUsuarios" type="submit" id="btnBuscar" value="Inactivos"></td>
            </tr>
            <tr>
              <td  class="Estilo8">CORREO </td>
              <td  class="Estilo8">NOMBRE</td>
              <td  class="Estilo8">PASSWORD</td>
              <td  class="Estilo8">FECHA DE NACIMIENTO </td>
              <td  class="Estilo8">CARRERA</td>
              <td  class="Estilo8">REINTEGRAR</td> 
            </tr>                     
            <%
                LinkedList<Map<String, String>> usuarios= null;
                if(request.getParameter("busquedadeinactivosexitosa") != null){
                    usuarios = misUsuarios.buscarUsuarios(request.getParameter("busquedadeinactivosexitosa"), "Inactivo"); 
                } else {
                    usuarios = misUsuarios.consultarUsuarios("Inactivo");
                }
                String colorCelda = ""; 
                int i=0;
                for(Map<String, String> usuario: usuarios){
                    if(i%2==0){ //si es par le ponemos color 
                        colorCelda = "#FFCC66"; 
                    } else {
                        colorCelda = "#FFFFFF"; 
                    }
            %>
            <tr bgcolor="<%= colorCelda %>"> 
              <td><div align="center" class="Estilo6"><%= usuario.get("correo") %>&nbsp;</div></td>
              <td><div align="center" class="Estilo6"><%= usuario.get("nombre") %>&nbsp;</div></td>
              <td><div align="center" class="Estilo6"><%= usuario.get("pw") %>&nbsp;</div></td>
              <td><div align="center" class="Estilo6"><%= usuario.get("f_nacimiento") %>&nbsp;</div></td>
              <td><div align="center" class="Estilo6"><%= usuario.get("nombrecarrera") %>&nbsp;</div></td>
              <td><a href="SrvProcesosUsuarios?btnUsuarios=reintegrar&correo=<%= usuario.get("correo") %>" title="reintegrarUsuario" target="_self"><img src="./imagenes/reintegrar.png" alt="" width="90" height="60"/></a></td>
            </tr>
            <%  
                    i++;
                }
            %>
          </table>
        </form>

    </body>
</html>
