package administradorDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorBD {
//CONTROL + SHIFT + FLECHA ABAJO, DUPLICA LA LINEA Y LA HACE ABAJO 
private String loginBD = "manbenit";
private String passBD = "shuriken";
private String urlBD = "jdbc:mysql://localhost:3306/ejemplo"; //url para conectar a la BD
private Connection conn = null;

public boolean conectar(){
    boolean conectado = false; 
        try { 
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            conn = null; 
            conn = DriverManager.getConnection(urlBD, loginBD, passBD);
            if(conn!=null) 
                conectado=true;
        } catch (Exception e) {
            System.out.println("Error al conectar: "+e);
        }
        return conectado;  
}//Método conectar

public void desconectar(){ 
        try { 
            conn.close();  
        } catch (Exception e) {
            System.out.println("Error al desconectar: "+e);
        }
}//Método desconectar

    public Connection getConn() {
        return conn; 
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}//clase
