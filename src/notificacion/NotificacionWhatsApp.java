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
public class NotificacionWhatsApp extends NotificacionDecorador {
    public NotificacionWhatsApp(Notificacion notificacion) {
        super(notificacion);
    }

    @Override
    public void enviar(Estudiante estudiante, String mensaje) {
        super.enviar(estudiante, mensaje); // Mensaje base
        System.out.println("Enviado por WhatsApp al número: " + estudiante.getTelefono());
    }
}