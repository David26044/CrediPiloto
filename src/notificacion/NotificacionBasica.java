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
public class NotificacionBasica implements Notificacion {

    @Override
    public void enviar(Estudiante estudiante, String mensaje) {
        System.out.println(mensaje);
    }
}