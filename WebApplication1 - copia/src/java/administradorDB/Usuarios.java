package administradorDB;

import java.sql.ResultSet;
import java.sql.Statement;


public class Usuarios extends AdministradorBD{
    private String sql = ""; //en vacio porque es una cadena de texto 
    private Statement stmt = null; //no se que es por eso la inicializo en nulo 
    private ResultSet res = null; //no se que es por eso la inicializo en nulo
    private final int NUM_COLS= 4;
    
    // Validación de credenciales para inicio de sesión
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
    
    // Validación de existencia del registro
    public boolean existeUsuario(String correo){ //MÉTODO para contar alumnos
        boolean existe = false; // con la consulta vemos is esta la matricula
        String sql= "SELECT correo FROM usuarios where correo = '"+correo+"'";
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
    
    
    // INSERCIÓN
    public boolean insertarUsuario(String correo, String nombre, String pw){ //MÉTODO con matriz para hacer las consultas
        boolean insertado = false; 
        //Si existe el usuario
        if(existeUsuario(correo)){
            sql= "UPDATE usuarios SET nombre= '"+nombre+"',pw='"+pw+"', estadou= 'Activo' WHERE correo='"+correo+"'";
        }
        else{
            sql = "insert into  usuarios values ('"+correo+"','"+nombre+"','"+pw+"','Activo')"; //buscamos por cada campo 
        }
        
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
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
