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
public class NotificacionGmail extends NotificacionDecorador {

    public NotificacionGmail(Notificacion notificacion) {
        super(notificacion);
    }

    @Override
    public void enviar(Estudiante estudiante, String mensaje) {
        super.enviar(estudiante, mensaje);
        System.out.println("Enviado al correo: " + estudiante.getCorreo());
    }
}
