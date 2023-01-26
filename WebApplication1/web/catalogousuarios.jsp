
<%@page import="administradorDB.Usuarios"%>
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
    <a href="InactivosUsuarios.jsp" target="_self">MOSTRAR USUARIOS INACTIVOS</a>
    <%! 
        Usuarios misusuarios = new Usuarios();
        Alumnos misalumnos= new Alumnos();
    %>
    <form action="SrvProcesosUsuarios" method="post" name="formAlumnos" id="formAlumnos">
      <table width="100%" border="0">
        <% 
        String mensaje=request.getParameter("mensaje");
        if(mensaje!=null){
            
        %>
        <tr bordercolor="0">
          <td colspan="7" bgcolor="#FFFFFF"><div align="center" class="Estilo7">MENSAJE: <%=mensaje%></div></td>
        </tr>
        <% 
            }
        %>
        <tr bordercolor="0">
          <td colspan="7" bgcolor="#FFCC33"><div align="center" class="Estilo1 Estilo8">TABLA DE USUARIOS</div></td>
        </tr>
        <tr bordercolor="0">
          <td colspan="7" bgcolor="#FFCC33"><div align="right" class="Estilo8"><a href="registrarusuario.jsp" title="REGISTRAR NUEVO USUARIO" target="_self">REGISTRAR NUEVO USUARIO</a></div></td>
        </tr>
        <tr bordercolor="0">
          <td width="20%" bgcolor="#FFCC33"><div align="right" class="Estilo8">BUSCAR USUARIO</div></td>
          <td colspan="5" bgcolor="#FFCC33"><input name="buscar" type="text" id="buscar" size="50"></td>
          <td width="10%" bgcolor="#FFCC33"><input name="btnUsuarios" type="submit" id="btnBuscar" value="Buscar"></td>
        </tr>
        <tr>
          <td width="11%"  class="Estilo8">CORREO </td>
          <td width="55%"  class="Estilo8">NOMBRE</td>
          <td width="8%"   class="Estilo8">PASSWORD</td>
          <td width="16%"  class="Estilo8">FECHA DE NACIMIENTO </td>
          <td width="16%"  class="Estilo8">CARRERA</td>
          <td width="12%"  class="Estilo8">ELIMINAR</td> 
          <td width="17%"  class="Estilo8">MODIFICAR</td> 
        </tr>                     
        <% 
            LinkedList<Map<String, String>> usuarios= null; //declaracion
            if(request.getParameter("usuariosEncontrados") != null){
                usuarios = misusuarios.buscarUsuarios(request.getParameter("usuariosEncontrados"),"Activo");
            } else {
                usuarios = misusuarios.consultarUsuarios("activo");
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
        <tr bgcolor="<%= colorCelda %>" >
            <td><div align="center" class="Estilo6"><%= usuario.get("correo") %>&nbsp;</div></td>
            <td><div align="center" class="Estilo6"><%= usuario.get("nombre") %>&nbsp;</div></td>
            <td><div align="center" class="Estilo6"><%= usuario.get("pw") %>&nbsp;</div></td>
            <td><div align="center" class="Estilo6"><%= usuario.get("f_nacimiento") %>&nbsp;</div></td>
            <td><div align="center" class="Estilo6"><%= usuario.get("nombrecarrera") %>&nbsp;</div></td>
            <td>
                <a onClick="confirm('Seguro que desea eliminar este registro?') ? window.location.href='SrvProcesosUsuarios?btnUsuarios=eliminar&correo=<%= usuario.get("correo") %>' : alert('Error al eliminar')" title="eliminarUsuario" target="_self">
                    <img src="./imagenes/eliminar.png" alt="" width="90" height="60"/>
                </a>
            </td>

            <td>
                <a href="modificarusuario.jsp?correo=<%=usuario.get("correo")%>&nombre=<%=usuario.get("nombre")%>&pw=<%=usuario.get("pw")%> " >
                   <img src="./imagenes/modificar.png" height="60" width="90"/> </a></td>      
        </tr>
        <%  
            i++;
            }
        %>
      
    </form>
		
</body>
</html>

