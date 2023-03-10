
package administradorDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Carreras  extends AdministradorBD
{
    private String sql = ""; //en vacio porque es una cadena de texto 
    private Statement stmt = null; 
    private ResultSet res = null; 
    private final int NUM_COLS=3;
 
    public boolean existeCarrera(String clave){ //MÉTODO para contar alumnos
        boolean existe = false; // con la consulta vemos is esta la matricula
        String sql= "SELECT clave FROM carreras where clave = '"+clave+"'";
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
 
    
    public int contarCarreras(){ //MÉTODO con matriz para hacer las consultas
        int cantidadCarreras = 0;//arreglo de matriz 
        sql = "SELECT count(clave) FROM carreras";
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
    
    public int contarCarreras(String carreraBuscada){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro 
        int cantidadCarreras = 0;//
        String sql= "SELECT count(clave) FROM carreras WHERE "
                + "clave LIKE '%"+carreraBuscada+"%' or "
                + "nombrecarrera LIKE '%"+carreraBuscada+"%'"; //Consultamos que campos hay en los que estamos buscando

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
    
    
    public String[][] consultarCarreras(){ //MÉTODO con matriz para hacer las consultas
        String[][] carreras = new String[contarCarreras()][NUM_COLS];//arreglo de matriz
        sql = "SELECT * FROM carreras"; //buscamos por cada campo 
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<NUM_COLS; columna++) {
                        carreras[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (SQLException e) {
            System.out.println("error al consultar carreras " + e);
        }
        
        
        return carreras;
    }  
    
    public String[][] consultarCarreras(String carreraBuscada){ //MÉTODO con matriz para hacer las consultas
        String[][] carreras = new String[contarCarreras(carreraBuscada)][NUM_COLS];//arreglo de matriz
        String sql= "SELECT * FROM carreras WHERE "
                + "clave LIKE '%"+carreraBuscada+"%' or "
                + "nombrecarrera LIKE '%"+carreraBuscada+"%'";
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    System.out.println(fila);
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
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
  

 
    public boolean insertarCarrera(String clave, String nombrecarrera){ //MÉTODO con matriz para hacer las consultas
        boolean insertado = false; 
        String sql = "insert into  carreras values ('"+clave+"','"+nombrecarrera+"','Activa')"; //buscamos por cada campo 
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
        String sql = "UPDATE carreras SET estadoc = 'Inactiva' WHERE clave = '"+clave+"' ";
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;

                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                if( actualizarCarrerasEnAlumnos(clave) ){
                    eliminado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
                }
            }

        } catch (Exception e) {
            System.out.println("error al eliminar carrera " + e);
        }
        return eliminado;
    }   
    
    public boolean darDeBajaCarrera(String clave){
        boolean eliminado = false; 
        String sql = "DELETE FROM carreras WHERE clave = '"+clave+"' ";
        
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                if( actualizarCarrerasEnAlumnos(clave) ){
                    eliminado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
                }
            }
        } catch (Exception e) {
            System.out.println("error al eliminar carrera " + e);
        }
        return eliminado;
    }
    
    private boolean actualizarCarrerasEnAlumnos(String clave){
        boolean actualizado = false; 
        // Actualizar el estado a espera en el lugar donde exita la carrera a eliminar
        String sql= "UPDATE alumnos SET carrera=0 WHERE carrera='"+clave+"'";
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                actualizado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
            }
        } catch (Exception e) {
            System.out.println("error al eliminar carrera " + e);
        }
        return actualizado;
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
        String sql = "UPDATE carreras SET estadoc = 'Activa' WHERE clave = '"+clave+"' ";
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