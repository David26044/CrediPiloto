/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import dao.EstudianteDAO;
import dao.SolicitudDAO;

/**
 * Clase que representa el sistema para procesar solicitudes.
 *
 * @author ACER
 */
public class Sistema implements IOperacionSistema {

    private SolicitudDAO solicitudDAO;
    private EstudianteDAO estudianteDAO;

    public Sistema() {
        this.solicitudDAO = new SolicitudDAO();
        this.estudianteDAO = new EstudianteDAO();
    }

    public void procesarSolicitud(Solicitud solicitud, int idEstudiante) {
        try {
            Estudiante estudiante = estudianteDAO.obtenerPorId(idEstudiante);

            solicitud.setEstado("Aprobada");
            solicitudDAO.registrarSolicitud(solicitud);

        } catch (Exception e) {
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    public Solicitud consultarSolicitud(int id) {
        return solicitudDAO.obtenerPorId(id);
    }

    protected void guardarRechazada(Solicitud solicitud, int idEstudiante) {
        Estudiante estudiante = estudianteDAO.obtenerPorId(idEstudiante);
        solicitud.setEstado("Rechazada");
        solicitudDAO.registrarSolicitud(solicitud);
    }
}
