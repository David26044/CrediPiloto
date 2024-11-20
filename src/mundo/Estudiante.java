/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

/**
 *
 * @author ACER
 */
public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private int puntajeDatacredito;
    private String documentoIdentidad;
    private String correo;
    private String telefono;

    // Constructor
    public Estudiante(int id, String nombre, String apellido, int puntajeDatacredito, String documentoIdentidad, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntajeDatacredito = puntajeDatacredito;
        this.documentoIdentidad = documentoIdentidad;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getPuntajeDatacredito() {
        return puntajeDatacredito;
    }

    public void setPuntajeDatacredito(int puntajeDatacredito) {
        this.puntajeDatacredito = puntajeDatacredito;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
public String toString() {
    return "Estudiante{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", puntajeDatacredito=" + puntajeDatacredito +
            ", documentoIdentidad='" + documentoIdentidad + '\'' +
            ", correo='" + correo + '\'' +
            ", telefono='" + telefono + '\'' +
            '}';
}

}
