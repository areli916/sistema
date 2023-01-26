

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
        <a href="carrerasinactivas.jsp" target="_self">MOSTRAR CARRERAS INACTIVAS</a>
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
                    <td colspan="4" bgcolor="#FFCC33"><div align="center" class="Estilo1 Estilo8">TABLA DE CARRERAS</div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="4" bgcolor="#FFCC33"><div align="right" class="Estilo8"><a href="registrarcarrera.jsp" title="REGISTRAR NUEVA CARRERA" target="_self">REGISTRAR NUEVA CARRERA</a></div></td>
                </tr>
                <tr bordercolor="0">
                    <td width="24%" bgcolor="#FFCC33"><div align="right" class="Estilo8">BUSCAR CARRERA</div></td>
                    <td colspan="2" bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
                    <td width="14%" bgcolor="#FFCC33"><input name="btnCarrera" type="submit" id="btnBuscar" value="Buscar"></td>
                </tr>
                <tr>
                    <td  class="Estilo8">CLAVE </td>
                    <td width="18%"  class="Estilo8">NOMBRE</td>
                    <td width="22%"  class="Estilo8">ELIMINAR</td> 
                    <td  class="Estilo8">MODIFICAR</td> 
                </tr>                     
                <% 
                String[][] carreras= null; //declaracion
                if(request.getParameter("carrerasEncontradas") != null){
                    carreras = misCarreras.consultarCarreras( request.getParameter("carrerasEncontradas") );
                } else {
                    carreras = misCarreras.consultarCarreras();
                }
                String colorCelda = ""; 
                int i=0;
                for(String[] carrera: carreras){ 
                    if(i%2==0){ //si es par le ponemos color 
                        colorCelda = "#FFCC66"; 
                    } else {
                        colorCelda = "#FFFFFF"; 
                    }
                    
                    if(!carrera[0].equals("0")){
                %>
                <tr bgcolor="<%= colorCelda %>" > 
                    <!-- 1:clave, 2:nombrecarrera -->
                    <td><div align="center" class="Estilo6"><%= carrera[0] %>&nbsp;</div></td>
                    <td><div align="center" class="Estilo6"><%= carrera[1] %>&nbsp;</div></td>
                    <td>
                        <a onClick="confirm('Seguro que desea eliminar este registro?') ? window.location.href='SrvProcesosCarreras?btnCarrera=eliminar&clave=<%= carrera[0] %>' : alert('Error al eliminar')" title="eliminarCarrera" target="_self">
                            <img src="./imagenes/eliminar.png" alt="" width="90" height="60"/>
                        </a>
                    </td>
                    <td>
                        <a href="modificarcarrera.jsp?clave=<%=carrera[0]%>&nombrecarrera=<%=carrera[1]%> "  target="_self">
                          <img src="./imagenes/modificar.png" alt="" width="90" height="60"/>              
                        </a>          
                    </td>
                </tr>
                <%  
                    }
                    i++;
                }
                %>
            </table>
        </form>

    </body>
</html>
