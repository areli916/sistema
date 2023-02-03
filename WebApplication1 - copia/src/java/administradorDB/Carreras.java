
package administradorDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Carreras  extends AdministradorBD
{
    private String sql = ""; //en vacio porque es una cadena de texto 
    private ArrayList<Map<String, String[]>> carrerasDesactivadas= new ArrayList<>();
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
            System.out.println("existeCarrera(param) => error al verificar la existencia de una carrera: " + e);
        }
        return existe;
    }
 
    
    // CONTEO DE EXISTENCIAS //////////////////////////////
    public int contarCarrerasActivas(){ //MÉTODO con matriz para hacer las consultas
        int cantidadCarreras = 0;//arreglo de matriz 
        sql = "SELECT count(clave) FROM carreras WHERE estadoc='Activa'";
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
            System.out.println("contarCarrerasActivas() => error al contar carreras " + e);
        }
        return cantidadCarreras;
    }  
    
    public int contarCarrerasInactivas(){ //MÉTODO con matriz para hacer las consultas
        int cantidadCarreras = 0;//arreglo de matriz 
        sql = "SELECT count(clave) FROM carreras WHERE estadoc='Inactiva'";
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
            System.out.println("contarCarrerasInactivas() => error al contar carreras " + e);
        }
        return cantidadCarreras;
    }  
    
    public int contarCarrerasActivas(String carreraBuscada){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro 
        int cantidadCarreras = 0;//
        String sql= "SELECT count(clave) FROM carreras WHERE estadoc='Activa' AND "
                + "clave LIKE '%"+carreraBuscada+"%' OR "
                + "nombrecarrera LIKE '%"+carreraBuscada+"%'"; //Consultamos que campos hay en los que estamos buscando

        System.out.println(sql);
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
            System.out.println("contarCarrerasActivas(param) => error al contar carreras buscadas: " + e);
        }
        return cantidadCarreras;
    }
    
    public int contarCarrerasInactivas(String carreraBuscada){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro 
        int cantidadCarreras = 0;//
        String sql= "SELECT count(clave) FROM carreras WHERE estadoc='Inactiva' AND "
                + "clave LIKE '%"+carreraBuscada+"%' OR "
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
            System.out.println("contarCarrerasInactivas(param) => error al contar carreras buscadas: " + e);
        }
        return cantidadCarreras;
    }
    
    public int contarCorreosPorCarrera(String clave){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro 
        int cantidadCorreos = 0;
        String sql= "SELECT count(correo) FROM alumnos WHERE carrera='"+clave+"'";

        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                if(res.next()){ //vamos de posicion a posicion
                    cantidadCorreos=res.getInt(1);
                }

                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("contarCarrerasInactivas(param) => error al contar carreras buscadas: " + e);
        }
        return cantidadCorreos;
    }
    ///////////////////////////////////////////////////////
    
    
    
    // CONSULTAS //////////////////////////////
    public String[][] consultarCarrerasActivas(){ //MÉTODO con matriz para hacer las consultas
        String[][] carreras = new String[contarCarrerasActivas()][NUM_COLS];//arreglo de matriz
        sql = "SELECT * FROM carreras WHERE estadoc='Activa'"; //buscamos por cada campo 
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
            System.out.println("consultarCarrerasActivas() => error al consultar carreras " + e);
        }
        
        return carreras;
    }  
    
    public String[][] consultarCarrerasActivas(String carreraBuscada){ //MÉTODO con matriz para hacer las consultas
        String[][] carreras = new String[contarCarrerasActivas(carreraBuscada)][NUM_COLS];//arreglo de matriz
        String sql= "SELECT * FROM carreras WHERE estadoc='Activa' AND "
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
            System.out.println("consultarCarrerasActivas(param) => error al consultar carreras " + e);
        }
        return carreras;
    }  
    
    public String[][] consultarCarrerasInactivas(){ //MÉTODO con matriz para hacer las consultas
        String[][] carreras = new String[contarCarrerasInactivas()][NUM_COLS];//arreglo de matriz
        sql = "SELECT * FROM carreras WHERE estadoc='Inactiva'"; //buscamos por cada campo 
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
            System.out.println("consultarCarrerasInactivas() => error al consultar carreras " + e);
        }
        
        
        return carreras;
    }  
    
    public String[][] consultarCarrerasInactivas(String carreraBuscada){ //MÉTODO con matriz para hacer las consultas
        String[][] carreras = new String[contarCarrerasInactivas(carreraBuscada)][NUM_COLS];//arreglo de matriz
        String sql= "SELECT * FROM carreras WHERE estadoc='Inactiva' AND "
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
            System.out.println("consultarCarrerasInactivas(param) => error al consultar carreras " + e);
        }
        return carreras;
    }  
    
    private String[] obtenerCorreosPorCarrera(String clave){ //MÉTODO con matriz para hacer las consultas
        String[] carreras = new String[contarCorreosPorCarrera(clave)];//arreglo de matriz
        sql = "SELECT correo FROM alumnos WHERE carrera='"+clave+"'"; //buscamos por cada campo 
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    carreras[fila] = res.getString(1);
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (SQLException e) {
            System.out.println("consultarCarrerasInactivas() => error al consultar carreras " + e);
        }
        
        return carreras;
    }  
    ///////////////////////////////////////////////////////

 
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
            System.out.println("insertarCarrera(params) => error al insertar carrera " + e);
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
                if( actualizarCarrerasEnAlumnosDesactivar(clave) ){
                    eliminado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
                }
            }

        } catch (Exception e) {
            System.out.println("eliminarCarrera(param) => error al eliminar carrera " + e);
        }
        return eliminado;
    }   
    
    /*public boolean darDeBajaCarrera(String clave){
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
            System.out.println("darDeBajaCarrera(param) => error al eliminar carrera " + e);
        }
        return eliminado;
    }*/
    
    private boolean actualizarCarrerasEnAlumnosDesactivar(String clave){
        boolean actualizado = false; 
        // Actualizar el estado a espera en el lugar donde exista la carrera a eliminar
        String sql="";
        // Agregar a la lista los correos que anteriormente tenían esa carrera
        Map<String, String[]> m= new HashMap<>();
        m.put(clave, obtenerCorreosPorCarrera(clave));
        carrerasDesactivadas.add(m);

        //Definir el query de desactivación
        sql= "UPDATE alumnos SET carrera='0' WHERE carrera='"+clave+"'";
        
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                actualizado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
            }
        } catch (Exception e) {
            System.out.println("actualizarCarrerasEnAlumnosDesactivar(param) => error al eliminar carrera " + e);
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
            System.out.println("modificarCarrera(params) => error al modificar0 carrera " + e);
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
                actualizarCarrerasEnAlumnosActivar(clave);
            }

        } catch (Exception e) {
            System.out.println("reintegrarCarrera(param) => error al reintegrar carrera " + e);
        }
        return reintegrado;
    }     
    
    private boolean actualizarCarrerasEnAlumnosActivar(String clave){
        boolean actualizado = false; 
        
        //Obtener los alumnos que anteriormente tenían esa carrera (su correo)
        String[] correosConEsaCarrera=null;
        int indiceDeMapa=0;
        for(int i=0; i<carrerasDesactivadas.size(); i++){
            for(Map.Entry<String,String[]> correos: carrerasDesactivadas.get(i).entrySet()){
                if(correos.getKey().equals(clave)){
                    indiceDeMapa=i;
                    correosConEsaCarrera= correos.getValue();
                    break;
                }
            }
        }

        try {
            if(conectar()){
                String sql="";
                for(String c: correosConEsaCarrera){
                    // Definir el query de reactivación 
                    sql= "UPDATE alumnos SET carrera='"+clave+"' WHERE carrera='0' AND correo='"+c+"'";
                    
                    stmt = getConn().createStatement(); //administrador de transaccion
                    stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla  
                }
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                actualizado=true;
                // Eliminar la carrera reactivada de la lista de desactivadas
                carrerasDesactivadas.remove(indiceDeMapa);
            }
        } catch (Exception e) {
            System.out.println("actualizarCarrerasEnAlumnosActivar(param) => error al eliminar carrera " + e);
        }
        
        return actualizado;
    }
}