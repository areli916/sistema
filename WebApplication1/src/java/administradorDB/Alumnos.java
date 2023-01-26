package administradorDB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Alumnos extends AdministradorBD{
    
    private Statement stmt = null; //no se que es por eso la inicializo en nulo 
    private ResultSet res = null; //no se que es por eso la inicializo en nulo
    /*
    String sql = "SELECT count(A.matricula) FROM alumnos A "
            + "INNER JOIN carreras C "
            + "on A.carrera = C.clave "
            + "WHERE "
            + " estado = 'Inactivo'";
    */

    public boolean existeAlumnos(String matricula){ //MÉTODO para contar alumnos
    boolean existe = false; // con la consulta vemos is esta la matricula
   String sql= "SELECT matricula FROM alumnos where matricula = '"+matricula+"'AND estado = 'Activo'";
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
        System.out.println("error al verificar la existencia de un alumno: " + e);
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
    public int contarAlumnos(){ //MÉTODO para contar alumnos
    int cantidadAlumnos = 0;//
     String sql= "SELECT count(matricula) FROM alumnos WHERE estado = 'Activo'";//Consultamos todos los datos del alumno
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            if(res.next()){ //vamos de posicion a posicion
                cantidadAlumnos=res.getInt(1);
            }
            
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al contar alumnos: " + e);
    }
    return cantidadAlumnos;
}

    public LinkedList<Map<String, String>> consultarAlumnos(String estado){ //MÉTODO con matriz para hacer las consultas
        LinkedList<Map<String, String>> datosAlumnos = executeSelect(
            "select u.correo,u.nombre,u.pw,a.matricula,a.f_nacimiento,c.nombrecarrera,c.actividadcarrera from usuarios" +
"INNER JOIN alumnos a on u.correo=a.correo  inner join carreras c on c.clave=carrera WHERE A.estado='"+estado+"'"  //abrimos las tablas y este es el campo de union
        );
        
        for(int i=0; i<datosAlumnos.size(); i++){
            LinkedList<Map<String, String>> datosAlumno= executeSelect(
                    //"SELECT * FROM usuarios WHERE correo='"+datosAlumnos.get(i).get("correo")+"' AND estadou='"+estado+"'"
                    "SELECT * FROM usuarios WHERE correo='"+datosAlumnos.get(i).get("correo")+"' AND estadou='Activo'"
            );

            // Como el correo es único, el resultado de la consulta será una lista de un único elemento,
            // entonces se puede afirmar que la única posición existente sería la 0.
            // Como puede ser una lista vacía (cuando el usuario no es alumno), es necesario comprobar
            // que la lista tenga resultados para agregarlos al mapa de datos del usuario actual (i).
            // Por lo tanto, si existen datos, se regresarán para ser desplegados...
            if(datosAlumno.size()>0){
                datosAlumnos.get(i).put("nombre", datosAlumno.get(0).get("nombre"));
                datosAlumnos.get(i).put("pw", datosAlumno.get(0).get("pw"));
            }
            // ... en caso contrario, se devolverán cadenas vacías en los datos de alumno.
            else{
                datosAlumnos.get(i).put("nombre", "");
                datosAlumnos.get(i).put("pw", "");
            }
        }
    
    return datosAlumnos;
}
    
    public int contarAlumnos(String alumnoBuscado){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le manda-
        //mos un parámetro 
    int cantidadAlumnos = 0;//
     String sql= "SELECT count(A.matricula) FROM alumnos A "
            + "INNER JOIN carreras C "
            + "on A.carrera = C.clave "
            + "WHERE "
            + "A.matricula LIKE '%"+alumnoBuscado+"%' or  " 
            + "A.correo LIKE '%"+alumnoBuscado+"%' or "
            + "A.f_nacimiento LIKE '%"+alumnoBuscado+"%' or "
            + "C.nombrecarrera LIKE '%"+alumnoBuscado+"%'AND estado = 'Activo'"; //Consultamos que campos hay en los que estamos buscando
    
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            if(res.next()){ //vamos de posicion a posicion
                cantidadAlumnos=res.getInt(1);
            }
            
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al contar alumnos buscados: " + e);
    }
    return cantidadAlumnos;
}

    public LinkedList<Map<String, String>> buscarAlumnos(String alumnoBuscado, String estado){
        LinkedList<Map<String, String>> datosAlumnos= executeSelect(
                "SELECT A.matricula, A.correo, A.f_nacimiento, C.Actividadcarrera, C.nombrecarrera " //campos con alias
                    + "FROM alumnos A " 
                    + "INNER JOIN carreras C "
                    + "on A.carrera = C.clave "
                    + "WHERE "
                    + "(A.matricula LIKE '%"+alumnoBuscado+"%' or  " 
                    + "A.correo LIKE '%"+alumnoBuscado+"%' or "
                    + "A.f_nacimiento LIKE '%"+alumnoBuscado+"%' or "
                    + "C.Actividadcarrera LIKE '%"+alumnoBuscado+"%' or "
                    + "C.nombrecarrera LIKE '%"+alumnoBuscado+"%') AND estado = '"+estado+"'"
        );
        
        for(int i=0; i<datosAlumnos.size(); i++){
            LinkedList<Map<String, String>> datosAlumno= executeSelect(
                    //"SELECT * FROM usuarios WHERE correo='"+datosAlumnos.get(i).get("correo")+"' AND estadou='"+estado+"'"
                    "SELECT * FROM usuarios WHERE correo='"+datosAlumnos.get(i).get("correo")+"' AND estadou='Activo'"
            );

            // Como el correo es único, el resultado de la consulta será una lista de un único elemento,
            // entonces se puede afirmar que la única posición existente sería la 0.
            // Como puede ser una lista vacía (cuando el usuario no es alumno), es necesario comprobar
            // que la lista tenga resultados para agregarlos al mapa de datos del usuario actual (i).
            // Por lo tanto, si existen datos, se regresarán para ser desplegados...
            if(datosAlumno.size()>0){
                datosAlumnos.get(i).put("nombre", datosAlumno.get(0).get("nombre"));
                datosAlumnos.get(i).put("pw", datosAlumno.get(0).get("pw"));
            }
            // ... en caso contrario, se devolverán cadenas vacías en los datos de alumno.
            else{
                datosAlumnos.get(i).put("nombre", "");
                datosAlumnos.get(i).put("pw", "");
            }
        }
        
        return datosAlumnos;
    }  
    
    public int contarCarreras(){ //MÉTODO con matriz para hacer las consultas
    int cantidadCarreras = 0;//arreglo de matriz 
     String sql = "SELECT count(clave) FROM carreras";
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            if(res.next()){ //vamos de posicion a posicion
                cantidadCarreras= res.getInt(1);
            } 
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al contar carreras " + e);
    }
    return cantidadCarreras;
}  
    
    public String[][] consultarCarreras(){ //MÉTODO con matriz para hacer las consultas
    String[][] carreras = new String[contarCarreras()][2];//arreglo de matriz
    String sql = "SELECT * FROM carreras"; //buscamos por cada campo 
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            int fila=0;
            while(res.next()){ //vamos de posicion a posicion
                for (int columna=0; columna<=1; columna++) {
                    carreras[fila][columna] = res.getString(columna+1);
                }
                fila++;
            }
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al consultar carreras " + e);
    }
    return carreras;
}  
    
public boolean insertarAlumno(String matricula, String correo, String fnacimiento, String carrera){ //MÉTODO con matriz para hacer las consultas
    boolean insertado = false; 
    String sql = "insert into  alumnos values ('"+matricula+"','"+correo+"','"+fnacimiento+"',"+carrera+",'Activo')"; //buscamos por cada campo 
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
        System.out.println("error al insertar alumno " + e);
    }
    return insertado;
}      
    
public boolean eliminarAlumno(String matricula){ //MÉTODO con matriz para hacer las consultas
    return eliminar("UPDATE alumnos SET estado = 'Inactivo' WHERE matricula = '"+matricula+"' ");
} 

public boolean eliminarUsuario(String correo){
    return eliminar("UPDATE usuarios SET estadou = 'Inactivo' WHERE correo = '"+correo+"' ");
}

private boolean eliminar(String sql){
    boolean eliminado=false;
    
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;

            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            eliminado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
        }
        
    } catch (Exception e) {
        System.out.println("error al eliminar alumno " + e);
    }
    
    return eliminado;
}
  

//modificar
public boolean modificarAlumno(String matricula,String correo, String correoAnterior, String fnacimiento,  String carrera){
    boolean modificado=false;
    String sql = "UPDATE alumnos SET correo= '"+correo+"',f_nacimiento='"+fnacimiento+"',carrera='"+carrera+"' WHERE matricula='"+matricula+"'";
    System.out.println(sql);
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion

            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            if( modificarCorreoDeUsuario(correoAnterior, correo) ){
                modificado=true; //ASEGURAMOS QUE LA INSERCCION SE REALIZÓ (SOLO SI TAMBIÉN SE REALIZÓ LA OTRA MODIFICACIÓN)
            }
        }
        
    } catch (Exception e) {
        System.out.println("error al modificar0 alumno " + e);
    }
    return modificado;
}

    /** Modificar el mismo correo del método modificarAlumno() si existe en la tabla usuarios
     * @param correoAnterior {String}
     * @param nuevoCorreo {String}
     * @return edoModificacion
    */
    private boolean modificarCorreoDeUsuario(String correoAnterior, String nuevoCorreo){
        boolean edoModificacion= true;
        
        //Armar la consulta 
        String sql="UPDATE usuarios SET correo='"+nuevoCorreo+"' WHERE correo='"+correoAnterior+"'";
        
        //Ejecutar proceso
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion

                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                edoModificacion=true; //ASEGURAMOS QUE LA INSERCCION SE REALIZÓ (SOLO SI TAMBIÉN SE REALIZÓ LA OTRA MODIFICACIÓN)
               
            }

        } catch (Exception e) {
            System.out.println("error al modificar alumno como usuario" + e);
        }
      
        return edoModificacion;
    }

public boolean reintegrarAlumno(String matricula){ //MÉTODO con matriz para hacer las consultas
    boolean reintegrado = false; 
    String sql = "UPDATE alumnos SET estado = 'Activo' WHERE matricula = '"+matricula+"' ";
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;

            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            reintegrado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
        }
        
    } catch (Exception e) {
        System.out.println("error al reintegrar alumno " + e);
    }
    return reintegrado;
}      

}//class
