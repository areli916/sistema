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
            -->
        </style>
    </head>
    
    <body>
        <form action="srvValidaUsuario" method="post" name="formAcceso" id="formAcceso">
            <table width="100%" border="0">
                <tr>
                    <td width="2%" rowspan="3" bgcolor="#FFCC66">&nbsp;</td>
                    <td width="48%" align="right" bgcolor="#FFCC66"><p>USUARIO</p>        </td>
                    <td width="48%" bgcolor="#FFCC66"><input name="usuario" type="text" id="usuario" value="escribe tu correo electrónico" size="50" maxlength="256"></td>
                    <td width="2%" rowspan="3" bgcolor="#FFCC66">&nbsp;</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#FFCC66">CONTRASEÑA</td>
                    <td bgcolor="#FFCC66"><input name="passw" type="password" id="passw" size="50" maxlength="20"></td>
                </tr>
                <tr>
                    <td colspan="2" bgcolor="#FFCC66">
                        <div align="center">
                            <input type="submit" name="ingresar" id="ingresar" value="Ingresar">
                            <input name="nombreProgramador" type="hidden" id="nombreProgramador" value="DanielVillegas">
                        </div>
                    </td> 
                </tr>
                <tr>
                    <td colspan="4" bgcolor="#FFCC66">
                        <div align="center" class="Estilo1"> <!-- PROCESO PARA VERIFICAR EL ERROR -->
                            <% 
                            String error = request.getParameter("error");
                            if(error!=null){
                                if(!error.isEmpty()){ 
                            %> 
                            ERROR: <%=error%>
                            <%
                                }
                            }
                            %>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
