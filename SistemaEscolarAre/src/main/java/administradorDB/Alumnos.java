package administradorDB;

import java.sql.ResultSet;
import java.sql.Statement;

public class Alumnos extends AdministradorBD{
    private String sql = ""; //en vacio porque es una cadena de texto 
    private Statement stmt = null; //no se que es por eso la inicializo en nulo 
    private ResultSet res = null; //no se que es por eso la inicializo en nulo 
    private final int NUM_COLS=6;

    // Validación de existencia del registro
    public boolean existeAlumno(String matricula){ //MÉTODO para contar alumnos
        boolean existe = false; // con la consulta vemos is esta la matricula
        sql = "SELECT matricula FROM vistaAlumnos where matricula = '"+matricula+"'";
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

    
    // CONTEO DE EXISTENCIAS //////////////////////////////
    public int contarAlumnosActivos(){ //MÉTODO para contar alumnos
        int cantidadAlumnos = 0;//
        sql = "SELECT count(matricula) FROM vistaAlumnos WHERE estado = 'Activo'";//Consultamos todos los datos del alumno
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
    
    public int contarAlumnosInactivos(){ //MÉTODO para contar alumnos
        int cantidadAlumnos = 0;//
        sql = "SELECT count(matricula) FROM vistaAlumnos WHERE estado = 'Inactivo'";//Consultamos todos los datos del alumno
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
    
    public int contarAlumnosActivos(String alumnoBuscado){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro
        int cantidadAlumnos = 0;
        //Consultamos que campos hay en los que estamos buscando
        sql = "SELECT count(matricula) FROM vistaAlumnos WHERE "
                + "matricula LIKE '%"+alumnoBuscado+"%' or  " 
                + "correo LIKE '%"+alumnoBuscado+"%' or "
                + "f_nacimiento LIKE '%"+alumnoBuscado+"%' or "
                + "nombrecarrera LIKE '%"+alumnoBuscado+"%' or "
                + "pw LIKE '%"+alumnoBuscado+"%' or "
                + "nombre LIKE '%"+alumnoBuscado+"%' "
                + "AND estado = 'Activo'";

        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                while(res.next()){ //vamos de posicion a posicion
                    cantidadAlumnos+=1;
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

    public int contarAlumnosInactivos(String alumnoBuscado){ //MÉTODO para contar alumnos POLIMÓRFICO, porque le mandamos un parámetro
        int cantidadAlumnos = 0;
        //Consultamos que campos hay en los que estamos buscando
        sql = "SELECT count(matricula) FROM vistaAlumnos WHERE "
                + "matricula LIKE '%"+alumnoBuscado+"%' or  " 
                + "correo LIKE '%"+alumnoBuscado+"%' or "
                + "f_nacimiento LIKE '%"+alumnoBuscado+"%' or "
                + "nombrecarrera LIKE '%"+alumnoBuscado+"%' or "
                + "pw LIKE '%"+alumnoBuscado+"%' or "
                + "nombre LIKE '%"+alumnoBuscado+"%' "
                + "AND estado = 'Inactivo'";

        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                while(res.next()){ //vamos de posicion a posicion
                    cantidadAlumnos+=1;
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
    ///////////////////////////////////////////////////////
    
    
    
    // CONSULTAS //////////////////////////////
    public String[][] consultarAlumnosActivos(){ //MÉTODO con matriz para hacer las consultas
        String[][] datosAlumnos = new String[contarAlumnosActivos()][NUM_COLS];//arreglo de matriz
        sql ="SELECT * FROM vistaAlumnos WHERE estado = 'Activo'";  //abrimos las tablas y este es el campo de union
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion 
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosAlumnos[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("error al consultar alumnos: " + e);
        }
        return datosAlumnos;
    }
    
    public String[][] consultarAlumnosInactivos(){ //MÉTODO con matriz para hacer las consultas
        String[][] datosAlumnos = new String[contarAlumnosInactivos()][NUM_COLS];//arreglo de matriz
        sql ="SELECT * FROM vistaAlumnos WHERE estado = 'Inactivo'";  //abrimos las tablas y este es el campo de union
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion 
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosAlumnos[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("error al consultar alumnos: " + e);
        }
        return datosAlumnos;
    }
    
    public String[][] consultarAlumnosActivos(String alumnoBuscado){ //MÉTODO con matriz para hacer las consultas
        String[][] datosAlumnos = new String[contarAlumnosActivos(alumnoBuscado)][NUM_COLS];//arreglo de matriz
        sql = "SELECT * FROM vistaAlumnos WHERE "
                + "matricula LIKE '%"+alumnoBuscado+"%' or  " 
                + "correo LIKE '%"+alumnoBuscado+"%' or "
                + "f_nacimiento LIKE '%"+alumnoBuscado+"%' or "
                + "nombrecarrera LIKE '%"+alumnoBuscado+"%' or "
                + "pw LIKE '%"+alumnoBuscado+"%' or "
                + "nombre LIKE '%"+alumnoBuscado+"%' "
                + "AND estado = 'Activo'";
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosAlumnos[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("error al consultar alumnos: " + e);
        }
        return datosAlumnos;
    }  

    public String[][] consultarAlumnosInactivos(String alumnoBuscado){ //MÉTODO con matriz para hacer las consultas
        String[][] datosAlumnos = new String[contarAlumnosInactivos(alumnoBuscado)][NUM_COLS];//arreglo de matriz
        sql = "SELECT * FROM vistaAlumnos WHERE "
                + "matricula LIKE '%"+alumnoBuscado+"%' or  " 
                + "correo LIKE '%"+alumnoBuscado+"%' or "
                + "f_nacimiento LIKE '%"+alumnoBuscado+"%' or "
                + "nombrecarrera LIKE '%"+alumnoBuscado+"%' or "
                + "pw LIKE '%"+alumnoBuscado+"%' or "
                + "nombre LIKE '%"+alumnoBuscado+"%' "
                + "AND estado = 'Inactivo'";
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;
                res = stmt.executeQuery(sql); //solo es para los select el EXECUTEQUERY 
                //SE GUARDAN LOS DATOS DE LA CONSULTA AQUI EN EL RESULSET
                int fila=0;
                while(res.next()){ //vamos de posicion a posicion
                    for (int columna=0; columna<=NUM_COLS-1; columna++) {
                        datosAlumnos[fila][columna] = res.getString(columna+1);
                    }
                    fila++;
                }
                res.close();//cerramos la conexion de res
                stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
            }
        } catch (Exception e) {
            System.out.println("error al consultar alumnos: " + e);
        }
        return datosAlumnos;
    } 
    //////////////////////////////////////////////////
    
    
    // INSERCIÓN
    public boolean insertarAlumno(String matricula, String correo, String fnacimiento, String carrera){ //MÉTODO con matriz para hacer las consultas
        boolean insertado = false; 
        sql = "insert into alumnos values ('"+matricula+"','"+correo+"','"+fnacimiento+"',"+carrera+", 'Activo')"; //buscamos por cada campo 
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
    
    // ELIMINACIÓN
    public boolean eliminarAlumno(String matricula){ //MÉTODO con matriz para hacer las consultas
        boolean eliminado = false; 
        sql = "UPDATE alumnos SET estado = 'Inactivo' WHERE matricula = '"+matricula+"' ";
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

    // MODIFICACIÓN
    public boolean modificarAlumno(String matricula,String correo,String fnacimiento,String carrera){
        boolean modificado=false;
        sql="UPDATE alumnos SET correo= '"+correo+"',f_nacimiento='"+fnacimiento+"',nombrecarrera='"+carrera+"'";
        try {
            if(conectar()){
                stmt = getConn().createStatement(); //administrador de transaccion
                //res = null;

                stmt.executeUpdate(sql);//execute UPDATE para actualizar la tabla             stmt.close();//cerramos el stament
                desconectar();//CERRAMOS LA CONEXION DE LA BD
                modificado=true;//ASEGURAMOS QUE LA INSERCCION SE REALIZÓ
            }

        } catch (Exception e) {
            System.out.println("error al modificar alumno " + e);
        }
        return modificado;

    }
    
    public boolean reintegrarAlumno(String matricula){ //MÉTODO con matriz para hacer las consultas
        boolean reintegrado = false; 
        sql = "UPDATE alumnos SET estado = 'Activo' WHERE matricula = '"+matricula+"' ";
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
