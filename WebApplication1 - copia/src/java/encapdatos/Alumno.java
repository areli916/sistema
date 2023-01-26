package encapdatos;

public class Alumno extends Persona {
    private String matricula, correo, estado;
    Carrera carrera;

    public Alumno(int clave, String nombre, String apellidom, String apllidop, String fnacimiento, String matricula, String correo, String estado, Carrera carrera) {
        super(clave, nombre, apellidom, apllidop, fnacimiento);
        this.matricula = matricula;
        this.correo = correo;
        this.estado = estado;
        this.carrera = carrera;
    }
}
