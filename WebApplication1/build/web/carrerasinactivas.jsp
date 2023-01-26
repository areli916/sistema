<%-- 
    Document   : carrerasinactivas
    Created on : 8/01/2023, 12:52:10 AM
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
        <script type="text/javascript">
            function accionEliminar(){
                let eliminar=confirm("seguro que quieress eliminar este registro");
                if(eliminar){
                    window.href=""
                }
            }
        </script>
</head>
    <body>
    <a href="catalogocarreras.jsp" target="_self">Regresar</a>
    <%! 
        Carreras misCarreras = new Carreras();
    %>
    <form action="SrvProcesosCarreras" method="post" name="formAlumnos" id="formAlumnos">
      <table width="100%" border="0">
        <% 
        String mensaje=request.getParameter("mensaje");
        if(mensaje!=null){
            
        %>
        <tr bordercolor="0">
          <td colspan="4" bgcolor="#FFFFFF"><div align="center" class="Estilo7">MENSAJE: <%=mensaje%></div></td>
        </tr>
        <% 
            }
        %>
        <tr bordercolor="0">
          <td colspan="4" bgcolor="#FFCC33"><div align="center" class="Estilo1 Estilo8">TABLA DE CARRERA INACTIVASS</div></td>
        </tr>
        <tr bordercolor="0">
          
        </tr>
        <tr bordercolor="0">
          <td width="24%" bgcolor="#FFCC33"><div align="right" class="Estilo8">BUSCAR CARRERA INACTIVA</div></td>
          <td colspan="1" bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
          <td width="14%" bgcolor="#FFCC33"><input name="btnCarrera" type="submit" id="btnBuscar" value="Inactivas"></td>
        </tr>
        <tr>
          <td  class="Estilo8">CLAVE </td>
          <td width="18%"  class="Estilo8">NOMBRE</td>
         
          <td  class="Estilo8">REINTEGRAR</td> 
        </tr>                     
        <% 
            LinkedList<Map<String, String>> carreras= null; //declaracion
            if(request.getParameter("busquedadeinactivasexitosa") != null){
                carreras = misCarreras.buscarCarreras(request.getParameter("busquedadeinactivasexitosa"),"Inactiva");
            } else {
                carreras = misCarreras.consultarCarreras("Inactiva");
            }
            String colorCelda = ""; 
            int i=0;
            for(Map<String, String> carrera: carreras){ 
                if(i%2==0){ //si es par le ponemos color 
                    colorCelda = "#FFCC66"; 
                } else {
                    colorCelda = "#FFFFFF"; 
                }
        %>
        <tr bgcolor="<%= colorCelda %>" > 
          <td><div align="center" class="Estilo6"><%= carrera.get("clave") %>&nbsp;</div></td>
          <td><div align="center" class="Estilo6"><%= carrera.get("nombrecarrera") %>&nbsp;</div></td>
          
          <td>
              <a href="SrvProcesosCarreras?btnCarrera=reintegrar&clave=<%=carrera.get("clave")%>&nombrecarrera=<%=carrera.get("nombrecarrera")%> "  target="_self" title="reintegrarcarrera">
                  <img src="./imagenes/reintegrar.png" alt=""  height="60"/>              
              </a>          
          </td>
        </tr>
        <%  
            i++;
            }
        %>
      </table>
    </form>
		
</body>
</html>
