<%-- 
    Document   : registraralumno
    Created on : 27/10/2022, 01:25:07 PM
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
.Estilo1 {color: #FF0000}
.Estilo5 {color: #FFFFFF}
-->
        </style>
</head>
    <body>
        <%! 
            Alumnos misAlumnos = new Alumnos();
            String carreras[][] = misAlumnos.consultarCarreras(); 
            
        %>
        <%
        String matricula = request.getParameter("matricula");
        String correo = request.getParameter("correo");
        String fnacimiento = request.getParameter("fnacimiento");
        String carrera = request.getParameter("carrera");
        String error = request.getParameter("error");
        %>
        <form action="SrvProcesosAlumnos?matricula=<%=matricula%>&correoAnterior=<%=correo%>" method="post" name="frmAgregaralumno" id="frmAgregaralumno">     
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
          <td colspan="2" bgcolor="#FFCC33"><div align="center">MODIFICAR ALUMNO</div></td>
        </tr>
         <tr bordercolor="0">
          <td colspan="5" bgcolor="#FFCC33"><div align="right"><a href="bienvenida.jsp" target="_self">REGRESAR</a></div></td>
        </tr>
        <tr>
          <td>MATRICULA</td>
          <td><input disabled type="text" id="matricula" size="20" maxlength="10" 
                     <%
                     if(matricula != null){
                     %>
                     value="<%=matricula%>"
                     <%
                     }
                     %>
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
          <td><input name="correo" type="text" id="correo" size="50" maxlength="256"
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
          <td><input name="fnacimiento" type="text" id="fnacimiento"  size="20" maxlength="10" 
                     <%
                     if(fnacimiento != null){
                     %>
                     value="<%=fnacimiento%>"
                     <%
                     }else{
                     %>
                     value="aaaa-mm-dd"
                     <%
                     }
                     %>
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
          <td><select name="carrera" id="carrera">
            <% 
                for (int i = 0; i < carreras.length; i++) {           
            %>
            <option value="<%=carreras[i][0]%>" 
                    <% //con .EQUALS comparamos las carreras
                    if(carreras[i][1].equals(carrera)){
                    %>
                    selected
                    <%
                    }
                    %>
                    ><%=carreras[i][1]%></option>
            <% 
                }
            %>
          </select></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="submit" name="btnAlumnos" id="btnAlumnos" value="modificar"></td>
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
