package encapdatos;

public class Persona {
    protected int clave;
    protected String nombre, apellidom, apllidop;
    protected String fnacimiento;

    public Persona(int clave, String nombre, String apellidom, String apllidop, String fnacimiento) {
        this.clave = clave;
        this.nombre = nombre;
        this.apellidom = apellidom;
        this.apllidop = apllidop;
        this.fnacimiento = fnacimiento;
    }

    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApllidop() {
        return apllidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApllidop(String apllidop) {
        this.apllidop = apllidop;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }    
}
