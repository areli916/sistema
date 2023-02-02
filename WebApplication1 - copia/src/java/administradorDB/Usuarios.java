package administradorDB;

import java.sql.ResultSet;
import java.sql.Statement;

public class Usuarios extends AdministradorBD{
    private String sql = ""; //en vacio porque es una cadena de texto 
    private Statement stmt = null; //no se que es por eso la inicializo en nulo 
    private ResultSet res = null; //no se que es por eso la inicializo en nulo 
    private final int NUM_COLS=5;
    
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
            System.out.println("validarUsuario(params) => error al validar un usuario: " + e);
        }
            System.out.println(valido);
        return valido;
    }
    
    // Validación de existencia del registro
    public boolean existeUsuario(String correo){ //MÉTODO para contar alumnos
        boolean existe = false; // con la consulta vemos is esta la matricula
        sql = "SELECT correo FROM usuarios WHERE correo = '"+correo+"'";
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
            System.out.println("existeUsuario(param) => error al verificar la existencia de un usuario: " + e);
        }
        return existe;
    }

    
    // CONTEO DE EXISTENCIAS //////////////////////////////
    public int contarUsuariosActivos(){ //MÉTODO para contar alumnos
        int cantidadAlumnos = 0;//
        sql = "SELECT count(correo) FROM usuarios WHERE estadou = 'Activo'";//Consultamos todos los datos del alumno
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
            System.out.println("contarUsuariosActivos() => error al contar usuarios: " + e);
        }
        return cantidadAlumnos;
    }
    
    public int contarUsuariosInactivos(){ //MÉTODO para contar alumnos
        int cantidadAlumnos = 0;//
        sql = "SELECT count(correo) FROM usuarios WHERE estadou = 'Inactivo'";//Consultamos todos los datos del alumno
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
            System.out.println("contarUsuariosInactivos() => error al contar usuarios: " + e);
        }
        return cantidadAlumnos;
    }
    
    public int contarUsuariosActivos(String usuarioBuscado){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro
        int cantidadUsuarios = 0;
        //Consultamos que campos hay en los que estamos buscando
        sql = "SELECT count(correo) FROM usuarios WHERE "
                + "correo LIKE '%"+usuarioBuscado+"%' or  " 
                + "nombre LIKE '%"+usuarioBuscado+"%' or "
                + "pwLIKE '%"+usuarioBuscado+"%' "
                + "AND estadou = 'Activo'";

        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                while(res.next()){ //vamos de posicion a posicion
                    cantidadUsuarios+=1;
                }

                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("contarUsuariosActivos(param) => error al contar usuarios buscados: " + e);
        }
        return cantidadUsuarios;
    }

    public int contarUsuariosInactivos(String usuarioBuscado){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro
        int cantidadUsuarios = 0;
        //Consultamos que campos hay en los que estamos buscando
        sql = "SELECT count(correo) FROM usuarios WHERE "
                + "correo LIKE '%"+usuarioBuscado+"%' or  " 
                + "nombre LIKE '%"+usuarioBuscado+"%' or "
                + "pw LIKE '%"+usuarioBuscado+"%' or "
                + "AND estadou = 'Inactivo'";

        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                while(res.next()){ //vamos de posicion a posicion
                    cantidadUsuarios+=1;
                }

                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("contarUsuariosInactivos(param) => error al contar usuarios buscados: " + e);
        }
        return cantidadUsuarios;
    }
    ///////////////////////////////////////////////////////
    
    
    
    // CONSULTAS //////////////////////////////
    public String[][] consultarUsuariosActivos(){ //MÉTODO con matriz para hacer las consultas
        String[][] datosUsuarios = new String[contarUsuariosActivos()][NUM_COLS];//arreglo de matriz
        sql ="SELECT * FROM usuarios WHERE estadou = 'Activo'";  //abrimos las tablas y este es el campo de union
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion 
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosUsuarios[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("consultarUsuariosActivos() => error al consultar usuarios: " + e);
        }
        return datosUsuarios;
    }
    
    public String[][] consultarUsuariosInactivos(){ //MÉTODO con matriz para hacer las consultas
        String[][] datosUsuarios = new String[contarUsuariosInactivos()][NUM_COLS];//arreglo de matriz
        sql ="SELECT * FROM usuarios WHERE estadou = 'Inactivo'";  //abrimos las tablas y este es el campo de union
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion 
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosUsuarios[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("consultarUsuariosInactivos() => error al consultar usuarios: " + e);
        }
        return datosUsuarios;
    }
    
    public String[][] consultarUsuariosActivos(String usuarioBuscado){ //MÉTODO con matriz para hacer las consultas
        String[][] datosUsuarios = new String[contarUsuariosActivos(usuarioBuscado)][NUM_COLS];//arreglo de matriz
        sql = "SELECT * FROM usuarios WHERE "
                + "correo LIKE '%"+usuarioBuscado+"%' or  " 
                + "nombre LIKE '%"+usuarioBuscado+"%' or "
                + "pw LIKE '%"+usuarioBuscado+"%' or "
                + "AND estadou = 'Activo'";
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosUsuarios[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("consultarUsuariosActivos(param) => error al buscar usuarios: " + e);
        }
        return datosUsuarios;
    }  

    public String[][] consultarUsuariosInactivos(String usuarioBuscado){ //MÉTODO con matriz para hacer las consultas
        String[][] datosUsuarios = new String[contarUsuariosInactivos(usuarioBuscado)][NUM_COLS];//arreglo de matriz
        sql = "SELECT * FROM usuarios WHERE "
                + "correo LIKE '%"+usuarioBuscado+"%' or  " 
                + "nombre LIKE '%"+usuarioBuscado+"%' or "
                + "pw LIKE '%"+usuarioBuscado+"%' or "
                + "AND estadou = 'Inactivo'";
        
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosUsuarios[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("consultarUsuariosInactivos(param) => error al buscar usuarios: " + e);
        }
        return datosUsuarios;
    } 
    //////////////////////////////////////////////////
    
    
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
            System.out.println("insertarUsuario(params) => error al insertar alumno " + e);
        }
        return insertado;
    }       
    
    public boolean eliminarUsuario(String correo){ //MÉTODO con matriz para hacer las consultas
        boolean eliminado = false; 
        String sql = "UPDATE usuarios SET estadou = 'Inactivo' WHERE correo = '"+correo+"' ";
        System.out.println(sql);
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;

                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                eliminado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
            }

        } catch (Exception e) {
            System.out.println("eliminarUsuario(param) => error al eliminar usuario " + e);
        }
        return eliminado;
   }      
    
    public boolean modificarUsuario(String correo, String correoAnterior, String nombre,String pw){
        boolean modificado=false;
        String sql="";
        if(correo.equals(correoAnterior)){
            sql= "UPDATE usuarios SET nombre='"+nombre+"', pw='"+pw+"' WHERE correo='"+correoAnterior+"'";
        }
        else{
            sql= "UPDATE usuarios SET nombre='"+nombre+"', pw='"+pw+"' correo='"+correo+"' WHERE correo='"+correoAnterior+"'";
        }
        
        System.out.println("Modif usuario: "+sql);
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;

                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                modificado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
            }

        } catch (Exception e) {
            System.out.println("modificarUsuario(params) => error al modificar usuario " + e);
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
            System.out.println("reintegrarUsuario(param) => error al reintegrar carrera " + e);
        }
        return reintegrado;
    }      
}//class
