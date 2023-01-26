package administradorDB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Usuarios extends AdministradorBD{
    private String sql = ""; //en vacio porque es una cadena de texto 
    private Statement stmt = null; //no se que es por eso la inicializo en nulo 
    private ResultSet res = null; //no se que es por eso la inicializo en nulo
    
public boolean validarUsuario(String usuario,String pass){
    boolean valido=false;
    sql = "SELECT * FROM usuarios WHERE correo='"+usuario+"' and pw='"+pass+"'";//nos conectamos a la base
    //de datos 
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;
            res = stmt.executeQuery(sql); //ejecutamos consulta
            if(res.next()){ //si ya encontramos el registro entonces VLIDO es igua a verdadero 
                valido = true;//validamos con true 
            }
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al validar un usuario: " + e);
    }
        System.out.println(valido);
    return valido;
}

public boolean existeUsuario(String correo){ //MÉTODO para contar alumnos
    boolean existe = false; // con la consulta vemos is esta la matricula
   String sql= "SELECT correo FROM usuarios where correo = '"+correo+"'AND estadou = 'Activa'";
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            if(res.next()){ //vamos de posicion a posicion
                existe = true;
            }
            
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al verificar la existencia de un usuario: " + e);
    }
    return existe;
}

private LinkedList<Map<String, String>> executeSelect(String query){
    // Lista de mapas para respuesta
    LinkedList<Map<String, String>> data= new LinkedList<>();
    
    conectar();
    try{
        // Obtener resultado de la consulta al ejecutar el query
        stmt = getConn().createStatement(); //administrador de transaccion
        ResultSet res= stmt.executeQuery(query);
        
        // Obtener los metadatos del resultado para almacenar los nombres de columna
        ResultSetMetaData resultMeta= res.getMetaData();

        // Mientras existen resultados...
        while( res.next() ){
            // Cada fila tiene varias columnas con un valor asignado, se guardará en un mapa de la forma:
            // nombreCampo: valorCampo
            Map<String, String> row= new HashMap<>();
            
            // Recorrer los metadatos y colocarlos en el mapa row
            for(int i=1; i<=resultMeta.getColumnCount(); i++)
                row.put(resultMeta.getColumnLabel(i), res.getString(i));
            
            // Agregar la fila (row)  la lista de filas (lista de mapas para respuesta)
            data.add(row);
        }

    }catch(SQLException ex){
        System.out.println("ERROR -> Usuarios, executeSelect(): "+ex.getMessage());
    }
    
    desconectar();
    
    return data;
}

public LinkedList<Map<String, String>> consultarUsuarios(String estado){ //MÉTODO con matriz para hacer las consultas
    LinkedList<Map<String, String>> datosUsuarios= executeSelect("SELECT * FROM usuarios WHERE estadou='"+estado+"'");
    
    for(int i=0; i<datosUsuarios.size(); i++){
        LinkedList<Map<String, String>> datosAlumno= executeSelect(
                "SELECT u.nombre, a.f_nacimiento, c.nombrecarrera FROM alumnos a, carreras c, usuarios u WHERE a.carrera=c.clave AND a.correo='"+datosUsuarios.get(i).get("correo")+"' GROUP BY a.correo"
        );
        
        // Como el correo es único, el resultado de la consulta será una lista de un único elemento,
        // entonces se puede afirmar que la única posición existente sería la 0.
        // Como puede ser una lista vacía (cuando el usuario no es alumno), es necesario comprobar
        // que la lista tenga resultados para agregarlos al mapa de datos del usuario actual (i).
        // Por lo tanto, si existen datos, se regresarán para ser desplegados...
        if(datosAlumno.size()>0){
            datosUsuarios.get(i).put("f_nacimiento", datosAlumno.get(0).get("f_nacimiento"));
            datosUsuarios.get(i).put("nombrecarrera", datosAlumno.get(0).get("nombrecarrera"));
        }
        // ... en caso contrario, se devolverán cadenas vacías en los datos de alumno.
        else{
            datosUsuarios.get(i).put("f_nacimiento", "");
            datosUsuarios.get(i).put("nombrecarrera", "");
        }
    }
    
    return datosUsuarios;
}
public LinkedList<Map<String, String>> buscarUsuarios(String usuarioBuscado, String estado){
    LinkedList<Map<String, String>> datosUsuarios=  executeSelect(
        //buscamos por cada campo
        "SELECT * FROM usuarios WHERE "
                + "(correo LIKE '%"+usuarioBuscado+"%' or "
                + "nombre LIKE '%"+usuarioBuscado+"%' or "
                + "pw LIKE '%"+usuarioBuscado+"%') AND estadou = '"+estado+"'"
    );
    
    for(int i=0; i<datosUsuarios.size(); i++){
        LinkedList<Map<String, String>> datosAlumno= executeSelect(
                " SELECT u.nombre, a.f_nacimiento, c.nombrecarrera FROM alumnos a, carreras c, usuarios u WHERE a.carrera=c.clave AND a.correo='"+datosUsuarios.get(i).get("correo")+"' GROUP BY a.correo"
        );
        
        // Como el correo es único, el resultado de la consulta será una lista de un único elemento,
        // entonces se puede afirmar que la única posición existente sería la 0.
        // Como puede ser una lista vacía (cuando el usuario no es alumno), es necesario comprobar
        // que la lista tenga resultados para agregarlos al mapa de datos del usuario actual (i).
        // Por lo tanto, si existen datos, se regresarán para ser desplegados...
        if(datosAlumno.size()>0){
            datosUsuarios.get(i).put("f_nacimiento", datosAlumno.get(0).get("f_nacimiento"));
            datosUsuarios.get(i).put("nombrecarrera", datosAlumno.get(0).get("nombrecarrera"));
        }
        // ... en caso contrario, se devolverán cadenas vacías en los datos de alumno.
        else{
            datosUsuarios.get(i).put("f_nacimiento", "");
            datosUsuarios.get(i).put("nombrecarrera", "");
        }
    }
    
    return datosUsuarios;
 }      
public boolean insertarUsuario(String correo, String nombre, String pw){ //MÉTODO con matriz para hacer las consultas
    boolean insertado = false; 
    LinkedList<Map<String, String>> usuarios= executeSelect("SELECT * FROM usuarios WHERE correo='"+correo+"'");
    String sql="";
    //Si existe el usuario
    if(usuarios.size()>0){
        sql= "UPDATE usuarios SET nombre= '"+nombre+"',pw='"+pw+"', estadou= 'Activo' WHERE correo='"+correo+"'";
    }
    else{
        sql = "insert into  usuarios values ('"+correo+"','"+nombre+"','"+pw+"','Activo')"; //buscamos por cada campo 
    }
    
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;
            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla 
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            insertado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
        }
    } catch (Exception e) {
        System.out.println("error al insertar usuario " + e);
    }
    return insertado;
}      
 public boolean eliminarUsuario(String correo){ //MÉTODO con matriz para hacer las consultas
    boolean eliminado = false; 
    String sql = "UPDATE usuarios SET estadou = 'Inactivo' WHERE correo = '"+correo+"' ";
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;

            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            eliminado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
        }
        
    } catch (Exception e) {
        System.out.println("error al eliminar usuario " + e);
    }
    return eliminado;
}      
  public boolean modificarUsuario(String correo, String nombre,String pw){
    boolean modificado=false;
    String sql = "UPDATE usuarios SET nombre= '"+nombre+"',pw='"+pw+"'WHERE correo='"+correo+"'";
    System.out.println(sql);
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;

            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            modificado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
        }
        
    } catch (Exception e) {
        System.out.println("error al modificar0 usuario " + e);
    }
    return modificado;  
}
  
 public boolean reintegrarUsuario(String correo){ //MÉTODO con matriz para hacer las consultas
    boolean reintegrado = false; 
    String sql = "UPDATE usuarios SET estadou = 'Activo' WHERE correo = '"+correo+"' ";
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;

            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            reintegrado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
        }
        
    } catch (Exception e) {
        System.out.println("error al reintegrar carrera " + e);
    }
    return reintegrado;
}      
}
