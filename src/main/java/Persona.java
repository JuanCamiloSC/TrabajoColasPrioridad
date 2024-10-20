public class Persona {
    String id;
    String nombre;
    String tipo;
    String puerta;


    public Persona(String nombre, String tipo, String puerta, String id) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.puerta = puerta;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    @Override
    public String toString(){
        return nombre + "-" + id + " (" + tipo + ")";
    }
}
