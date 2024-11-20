/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

/**
 *
 * @author ACER
 */
import java.util.Date;

public class Solicitud {

    private int id;
    private int idEstudiante;
    private double monto;
    private int cuotas;
    private Date fechaSolicitud;
    private String estado;

    // Constructor
    public Solicitud(int id, int idEstudiante, double monto, int cuotas, Date fechaSolicitud, String estado) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.monto = monto;
        this.cuotas = cuotas;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Solicitud{"
                + "id=" + id
                + ", idEstudiante=" + idEstudiante
                + ", monto=" + monto
                + ", cuotas=" + cuotas
                + ", fechaSolicitud=" + fechaSolicitud
                + ", estado='" + estado + '\''
                + '}';
    }

}
