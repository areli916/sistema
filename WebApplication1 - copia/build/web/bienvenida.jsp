<%@page import="administradorDB.Alumnos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenida</title>
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
        <a href="Inactivos.jsp" target="_self">MOSTRAR INACTIVOS</a>
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
                        <td colspan="7" bgcolor="#FFFFFF">
                            <div align="center" class="Estilo7">
                                MENSAJE: <%=mensaje%>
                            </div>
                        </td>
                    </tr>
                <% 
                }
                %>
                <tr bordercolor="0">
                    <td colspan="10" bgcolor="#FFCC33"><div align="center" class="Estilo1 Estilo8">TABLA DE ALUMNOS</div></td>
                </tr>
                <tr bordercolor="0">
                    <td height="23" colspan="10" bgcolor="#FFCC33"><div align="right" class="Estilo8"><a href="registraralumno.jsp" title="REGISTRAR UN NUEVO ALUMNO" target="_self">REGISTRAR NUEVO ALUMNO</a></div></td>
                </tr>
                <tr bordercolor="0">
                    <td colspan="5" bgcolor="#FFCC33"><span class="Estilo8"></span></td>
                    <td width="18%" bgcolor="#FFCC33"><div align="right" class="Estilo8">BUSCAR ALUMNO</div></td>
                    <td colspan="3" bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50">
                    <input name="btnAlumnos" type="submit" id="btnBuscar" value="Buscar"></td>
                    <td width="0%" bgcolor="#FFCC33">&nbsp;</td>
                </tr>
                <tr>
                    <td width="10%" bordercolor="0" ><span class="Estilo8">MATRÍCULA</span></td>
                    <td width="11%"  class="Estilo8">CORREO </td>
                    <td width="16%"  class="Estilo8">FECHA DE NACIMIENTO </td>
                    <td width="16%"  class="Estilo8">CARRERA</td>
                    <td width="16%"  class="Estilo8">NOMBRE</td>
                    <td width="16%"  class="Estilo8">PASSWORD</td>
                    <td width="12%"  class="Estilo8">ELIMINAR</td> 
                    <td width="17%"  class="Estilo8">MODIFICAR</td> 
                    <td width="17%"  class="Estilo8">ATRIBUTO USUARIO</td> 
                </tr>                     
                <% 
                String[][] alumnos = null; //declaracion
                if(request.getParameter("alumnosEncontrados") != null){
                    alumnos = misAlumnos.consultarAlumnosActivos(request.getParameter("alumnosEncontrados")); 
                } else {
                    alumnos = misAlumnos.consultarAlumnosActivos();
                }
                System.out.println("cantidad: "+alumnos.length);
                String colorCelda="";
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
                        <td>
                            <a onClick=" confirm('Seguro que desea eliminar este registro?') ? window.location.href='SrvProcesosAlumnos?btnAlumnos=eliminar&matricula=<%= alumno[5] %>&correo=<%= alumno[2] %>' : alert('Error al eliminar') " title="eliminarAlumno" target="_self">
                                <img src="./imagenes/eliminar.png" alt="" width="90" height="60"/>              </a>          </td>
                        <td>
                            <a href="modificaralumno.jsp?matricula=<%=alumno[5]%>&correo=<%=alumno[2]%>&fnacimiento=<%=alumno[6]%>&carrera=<%= alumno[7] %>&nombre=<%= alumno[3] %>&password=<%= alumno[4] %>" title="modificarAlumno" target="_self">
                                <img src="./imagenes/modificar.png" alt="" width="90" height="60"/>              
                            </a>          
                        </td>
                        <td>
                            <% if( alumno[4].equals("") ){ %>
                                <a href="registrarusuario.jsp?correo=<%=alumno[2]%>" title="registrarUsuario" target="_self">
                                    <img src="./imagenes/reintegrar.png" alt="" width="90" height="60"/> HACER USUARIO
                                </a>   
                            <% }else{ %>
                                <a href="" onClick="confirm('Seguro que desea eliminar este usuario?') ? window.location.href='SrvProcesosUsuarios?btnUsuarios=eliminar&correo=<%= alumno[2] %>' : alert('Error al eliminar usuario')" title=eliminarUsuario" target="_self">
                                    QUITAR USUARIO
                                </a>
                            <% } %>
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
