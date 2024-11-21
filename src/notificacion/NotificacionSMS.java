/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notificacion;

import mundo.Estudiante;

/**
 *
 * @author ACER
 */
public class NotificacionSMS extends NotificacionDecorador {
    public NotificacionSMS(Notificacion notificacion) {
        super(notificacion);
    }

    @Override
    public void enviar(Estudiante estudiante, String mensaje) {
        super.enviar(estudiante, mensaje); // Mensaje base
        System.out.println("Enviado por SMS al número: " + estudiante.getTelefono());
    }
}
