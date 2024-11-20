/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

/**
 * Clase que representa el sistema para procesar solicitudes.
 * 
 * @author ACER
 */
public class Sistema {

    private SolicitudDAO solicitudDAO;
    private EstudianteDAO estudianteDAO;

    
    public Sistema(SolicitudDAO solicitudDAO, EstudianteDAO estudianteDAO) {
        this.solicitudDAO = solicitudDAO;
        this.estudianteDAO = estudianteDAO;
    }

    public void procesarSolicitud(Solicitud solicitud, int idEstudiante) {
        try {
            // 1. Obtener el estudiante por su ID
            Estudiante estudiante = estudianteDAO.obtenerPorId(idEstudiante);

            if (estudiante == null) {
                System.out.println("Estudiante no encontrado. No se puede procesar la solicitud.");
                return;
            }

            // Asociar la solicitud al estudiante
            solicitud.setIdEstudiante(idEstudiante);

            // 2. Validar el puntaje crediticio del estudiante
            if (estudiante.getPuntajeDatacredito() < 500) {
                System.out.println("Solicitud rechazada. Puntaje crediticio insuficiente.");
                solicitud.setEstado("Rechazado");
                solicitudDAO.registrarSolicitud(solicitud);
                return;
            }

            // 3. Validar los detalles de la solicitud
            if (validarSolicitud(solicitud)) {
                solicitud.setEstado("Aprobado");

                // Guardar o actualizar según corresponda
                if (solicitud.getId() == 0) { // Si es una solicitud nueva
                    solicitudDAO.registrarSolicitud(solicitud);
                } else { // Si ya existe, actualizar
                    solicitudDAO.actualizar(solicitud);
                }

                System.out.println("Solicitud aprobada y procesada exitosamente.");
            } else {
                System.out.println("Solicitud rechazada. Verifique los datos de la solicitud.");
                solicitud.setEstado("Rechazado");
                solicitudDAO.registrarSolicitud(solicitud);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    private boolean validarSolicitud(Solicitud solicitud) {
        // Validación básica: monto positivo y cuotas entre 1 y 5
        return solicitud.getMonto() > 0 && solicitud.getCuotas() > 0 && solicitud.getCuotas() <= 5;
    }

    public Solicitud consultarSolicitud(int id) {
        return solicitudDAO.obtenerPorId(id);
    }
}
