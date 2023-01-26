package encapdatos;

public class Carrera {
    private int clave;
    private String nombrecarrera;

    public int getClave() {
        return clave;
    }

    public String getNombrecarrera() {
        return nombrecarrera;
    }

    public void setNombrecarrera(String nombrecarrera) {
        this.nombrecarrera = nombrecarrera;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
}
