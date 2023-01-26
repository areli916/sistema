
package administradorDB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class Carreras  extends AdministradorBD
{
 private Statement stmt = null; 
 private ResultSet res = null; 
 public boolean existeCarrera(String clave){ //MÉTODO para contar alumnos
    boolean existe = false; // con la consulta vemos is esta la matricula
   String sql= "SELECT clave FROM carreras where clave = '"+clave+"'AND estadoc = 'Activa'";
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
        System.out.println("error al verificar la existencia de una carrera: " + e);
    }
    return existe;
}
public int contarCarreras(){ //MÉTODO para contar alumnos
    int cantidadCarreras= 0;//
     String sql= "SELECT count(clave) FROM carreras WHERE estadoc = 'Activa'";//Consultamos todos los datos del alumno
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            if(res.next()){ //vamos de posicion a posicion
                cantidadCarreras=res.getInt(1);
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
  public LinkedList<Map<String, String>> consultarCarreras(String estado){ //MÉTODO con matriz para hacer las consultas
    LinkedList<Map<String, String>> datosCarreras = new LinkedList<>();//arreglo de matriz
        String sql ="SELECT C.clave,C.nombrecarrera " //campos con alias
            + "FROM carreras C " 
            +"WHERE C.estadoc='"+estado+"'";  //abrimos las tablas y este es el campo de union
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion 
            //res = null;
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            while(res.next()){ //vamos de posicion a posicion
                Map<String, String> valoresColumna= new HashMap<>();
                
                valoresColumna.put("clave", res.getString("clave"));
                valoresColumna.put("nombrecarrera", res.getString("nombrecarrera"));
                
                datosCarreras.add(valoresColumna);
            }
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al consultar carreras: " + e);
    }
    return datosCarreras;
}
public int contarCarreras(String carreraBuscada){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le manda-
        //mos un parámetro 
    int cantidadCarreras = 0;//
     String sql= "SELECT count(C.clave) FROM carreras C "
            
            + "WHERE "
            + "C.clave.f_nacimiento LIKE '%"+carreraBuscada+"%' or "
            + "C.nombrecarrera LIKE '%"+carreraBuscada+"%'AND estadoc = 'Activa'"; //Consultamos que campos hay en los que estamos buscando
    
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            if(res.next()){ //vamos de posicion a posicion
                cantidadCarreras=res.getInt(1);
            }
            
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al contar carreras buscadas: " + e);
    }
    return cantidadCarreras;
}
 public LinkedList<Map<String, String>> buscarCarreras(String carreraBuscada, String estado){
    //public String[][] buscarAlumnos(String alumnoBuscado, String estado){ //MÉTODO con matriz para hacer las consultas
    //LinkedList<String[]> datosAlumnos= new LinkedList<>();
    LinkedList<Map<String, String>> datosCarreras= new LinkedList<>();
    //String[][] datosAlumnos = new String[contarAlumnos(alumnoBuscado)][5];//arreglo de matriz
        System.out.println("PARAMETRO: "+carreraBuscada+"@@@");
       
    String sql= "SELECT C.clave, C.nombrecarrera " //campos con alias
            + "FROM carreras C " 
            
            + "WHERE "
            
            + "(C.clave LIKE '%"+carreraBuscada+"%' or "
            + "C.nombrecarrera LIKE '%"+carreraBuscada+"%') AND estadoc = '"+estado+"'"; //buscamos por cada campo 
    
     
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;
            res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
            //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
            
            while(res.next()){ //vamos de posicion a posicion
                Map<String, String> valoresColumna= new HashMap<>();
                
                valoresColumna.put("clave", res.getString("clave"));
                valoresColumna.put("nombrecarrera", res.getString("nombrecarrera"));
                
                datosCarreras.add(valoresColumna);
            }
            res.close();//cerramos la conexion de res
            stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
        }
    } catch (Exception e) {
        System.out.println("error al buscar carrera " + e);
    }
    return datosCarreras;   
 }      
 public boolean insertarCarrera(String clave, String nombrecarrera){ //MÉTODO con matriz para hacer las consultas
    boolean insertado = false; 
    String sql = "insert into  carreras values ('"+clave+"','"+nombrecarrera+"','Activa','en curso')"; //buscamos por cada campo 
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
        System.out.println("error al insertar carrera " + e);
    }
    return insertado;
}      
 public boolean eliminarCarrera(String clave){ //MÉTODO con matriz para hacer las consultas
    boolean eliminado = false; 
    String sql = "UPDATE carreras SET estadoc = 'Inactiva',actividadcarrera='suspndida' WHERE clave = '"+clave+"' ";
    try {
        if(conectar()){
            stmt = getConn().createStatement(); //administrador de transaccion
            //res = null;

            stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
            desconectar();//CERRAMOS LA CONEXION DE LA BD
            eliminado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
        }
        
    } catch (Exception e) {
        System.out.println("error al eliminar carrera " + e);
    }
    return eliminado;
}      
  public boolean modificarCarrera(String clave, String nombrecarrera){
    boolean modificado=false;
    String sql = "UPDATE carreras SET nombrecarrera= '"+nombrecarrera+"'WHERE clave='"+clave+"'";
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
        System.out.println("error al modificar0 carrera " + e);
    }
    return modificado;
      
}
 public boolean reintegrarCarrera(String clave){ //MÉTODO con matriz para hacer las consultas
    boolean reintegrado = false; 
    String sql = "UPDATE carreras SET estadoc = 'Activa',actividadcarrera='en curso' WHERE clave = '"+clave+"' ";
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