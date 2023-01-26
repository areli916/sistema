package administradorDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//CRUD general
public class ControladorBD extends AdministradorBD{
    private String sql = ""; //en vacio porque es una cadena de texto 
    private Statement stmt = null; //no se que es por eso la inicializo en nulo 
    private ResultSet res = null; //no se que es por eso la inicializo en nulo 
    
    public void seleccionarTodo(String tabla){
        sql= "SELECT * FROM "+tabla;
        ejecutarSeleccion();
        ArrayList<Object> resultado= new ArrayList<>();
        try{
            while(res.next()){ //vamos de posicion a posicion
                
            }
        }catch(SQLException e){
            System.out.println("EROR: ControladorBD, seleccionarTodo");
        }
    }
    
    private void ejecutarSeleccion(){
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                //if(res.next()){ //vamos de posicion a posicion
                //    existe = true;
                //}

                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("error al verificar la existencia de un alumno: " + e);
        }
    }
    
    public boolean test(){
        boolean existe = false; // con la consulta vemos is esta la matricula
        
        return existe;
    }
}
