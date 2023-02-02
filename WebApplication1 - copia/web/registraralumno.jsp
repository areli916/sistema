<%-- 
    Document   : registraralumno
    Created on : 27/10/2022, 01:25:07 PM
    Author     : 52561
--%>

<%@page import="administradorDB.Carreras"%>
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
        <a href="bienvenida.jsp" target="_self">Regresar</a>
        <%! 
        Carreras misCarreras = new Carreras();
        String carreras[][] = misCarreras.consultarCarrerasActivas(); 
        %>
        <%
        String matricula = request.getParameter("matricula");
        String correo = request.getParameter("correo");
        String fnacimiento = request.getParameter("fnacimiento");
        String carrera = request.getParameter("carrera");
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String error = request.getParameter("error");
        %>
        <form action="SrvValidaRegistroAlumno" method="post" name="frmAgregaralumno" id="frmAgregaralumno">     
            <table width="100%" border="0">
                <tr>
                    <td colspan="2" bgcolor="#FFCC33"><div align="center">REGISTRAR ALUMNO</div></td>
                </tr>
                <tr>
                    <td>MATRICULA</td>
                    <td>
                        <input name="matricula" type="text" id="matricula" size="20" maxlength="10" 
                           <% if(matricula != null){ %>
                                value="<%=matricula%>"
                           <% } %>
                        >
                        <%
                        if(matricula!=null){
                            if(matricula.isEmpty()){
                        %>
                        <span class="Estilo1">*</span> 
                        <%
                            }
                        }else if(error!=null){
                            if(error.equals("La matricula que proporcionaste ya esta en el sistema")){
                        %>
                                <span class="Estilo1">*</span>  
                        <%
                            }
                        }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>CORREO </td>
                    <td>
                        <input name="correo" type="text" id="correo" size="50" maxlength="256"
                           <% if(correo != null){ %>
                                value="<%=correo%>"
                           <% } %>  
                        >
                        <%
                        if(correo!=null){
                            if(correo.isEmpty()){
                        %>
                                <span class="Estilo1">*</span> 
                        <%
                            }
                        }else if(error!=null){
                            if(error.equals("El correo que proporcionaste ya esta en el sistema")){
                        %>
                                <span class="Estilo1">*</span>  
                        <%
                            }
                        }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>FECHA DE NACIMIENTO</td>
                    <td>
                        <input placeholder="aaaa-mm-dd" name="fnacimiento" type="text" id="fnacimiento"  size="20" maxlength="10" 
                           <% if(fnacimiento != null){ %>
                                value="<%=fnacimiento%>"
                           <% } %>
                        >
                        <%
                        if(fnacimiento!=null){
                            if(fnacimiento.isEmpty()){
                        %>
                              <span class="Estilo1">*</span>
                        <%
                            }
                        }else if(error!=null){
                            if(error.equals("La fnacimiento que proporcionaste ya esta en el sistema")){
                        %>
                               <span class="Estilo1">*</span>  
                        <%
                            }
                        }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>CARRERA</td>
                    <td>
                        <select name="carrera" id="carrera">
                        <% 
                        for (int i = 0; i < carreras.length; i++) {           
                        %>
                            <option value="<%=carreras[i][0]%>" 
                                <% if(carreras[i][0].equals(carrera)){ %>
                                      selected
                                <% }
                                %>
                            >
                                <%=carreras[i][1]%>
                            </option>
                        <% 
                        }
                        %>
                    </select></td>
                </tr>
                <tr>
                    <td>NOMBRE</td>
                    <td>
                        <input name="nombre" type="text" id="nombre" size="50" maxlength="256"
                           <% if(nombre != null){ %>
                                value="<%=nombre%>"
                           <% } %>  
                        >
                        <%
                        if(nombre!=null){
                            if(nombre.isEmpty()){
                        %>
                                <span class="Estilo1">*</span> 
                        <%
                            }
                        }else if(error!=null){
                            if(error.equals("El correo que proporcionaste ya esta en el sistema")){
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
                    <td>
                        <input name="password" type="text" id="password" size="50" maxlength="256"
                           <% if(password != null){ %>
                                value="<%=password%>"
                           <% } %>  
                        >
                        <%
                        if(password!=null){
                            if(password.isEmpty()){
                        %>
                                <span class="Estilo1">*</span> 
                        <%
                            }
                        }else if(error!=null){
                            if(error.equals("El correo que proporcionaste ya esta en el sistema")){
                               %>
                               <span class="Estilo1">*</span>  
                               <%
                            }
                        }
                        %>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" name="btnagregar" id="btnagregar" value="Agregar alumno"></td>
                </tr>
                <% if(error!=null){ %>
                    <tr>
                      <td colspan="2" bgcolor="#FFFFFF"><div align="center" class="Estilo1">ERROR: <%=error%> </div></td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
</html>
