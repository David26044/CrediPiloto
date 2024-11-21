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
public abstract class NotificacionDecorador implements Notificacion {
    protected Notificacion notificacion;

    public NotificacionDecorador(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    @Override
    public void enviar(Estudiante estudiante, String mensaje) {
        notificacion.enviar(estudiante, mensaje); // Llama al comportamiento base
    }
}
