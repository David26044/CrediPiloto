/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import dao.EstudianteDAO;
import notificacion.Notificacion;
import notificacion.NotificacionBasica;
import notificacion.NotificacionGmail;
import notificacion.NotificacionWhatsApp;

/**
 *
 * @author ACER
 */
public class ProxySistema implements IOperacionSistema {
    
    Sistema sistema;
    EstudianteDAO estudianteDAO;
    
    public ProxySistema() {
        sistema = new Sistema();
        estudianteDAO = new EstudianteDAO();
    }
    
    @Override
    public void procesarSolicitud(Solicitud solicitud, int idEstudiante) {
        try {
            
            Estudiante estudiante = estudianteDAO.obtenerPorId(idEstudiante);
            Notificacion notificacion = new NotificacionBasica();
            
            if (estudiante == null) {
                System.out.println("Estudiante no encontrado. No se puede procesar la solicitud.");
                return;
            }
            notificacion = new NotificacionGmail(notificacion);
            notificacion = new NotificacionWhatsApp(notificacion);
            String msg;

            // 2. Validar el puntaje crediticio del estudiante
            if (estudiante.getPuntajeDatacredito() < 500) {
                msg = "Solicitud rechazada. Puntaje crediticio insuficiente.";
                solicitud.setEstado("Rechazado");
                notificacion.enviar(estudiante, msg);
                sistema.guardarRechazada(solicitud, idEstudiante);
                return;
            }
            
            if (validarSolicitud(solicitud)) {
//                solicitud.setEstado("Aprobado");
                msg = "Aprobado";
                sistema.procesarSolicitud(solicitud, idEstudiante);
                
            } else {
                msg = "Solicitud rechazada. Verifique los datos de la solicitud.";
                solicitud.setEstado("Rechazado");
                sistema.guardarRechazada(solicitud, idEstudiante);
            }
            notificacion.enviar(estudiante, msg);
            
        } catch (Exception e) {
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
        }
    }
    
    private boolean validarSolicitud(Solicitud solicitud) {
        // Validación básica: monto positivo y cuotas entre 1 y 5
        return solicitud.getMonto() > 0 && solicitud.getCuotas() > 0 && solicitud.getCuotas() <= 5;
    }
    
}
