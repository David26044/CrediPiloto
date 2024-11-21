/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import dao.EstudianteDAO;
import dao.SolicitudDAO;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Crear conexión y DAOs
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        SolicitudDAO solicitudDAO = new SolicitudDAO();
        ProxySistema sistema = new ProxySistema();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                // Menú de opciones
                System.out.println("Bienvenido a CrediPiloto");
                System.out.println("¿Qué deseas hacer? : \n"
                        + "1. Registrar estudiante\n"
                        + "2. Iniciar nueva solicitud\n"
                        + "3. Consultar estado de solicitud por estudiante\n"
                        + "4. Consultar lista de estudiantes\n"
                        + "5. Consultar lista de solicitudes\n"
                        + "6. Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        // Registro del estudiante
                        System.out.println("** Registro de estudiante **");
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();
                        System.out.print("Puntaje Datacrédito: ");
                        int puntajeDatacredito = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea
                        System.out.print("Documento de identidad: ");
                        String documentoIdentidad = scanner.nextLine();
                        System.out.print("Correo electrónico: ");
                        String correo = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = scanner.nextLine();

                        Estudiante estudiante = new Estudiante(0, nombre, apellido, puntajeDatacredito, documentoIdentidad, correo, telefono);
                        estudianteDAO.registrarEstudiante(estudiante);
                        System.out.println("Estudiante registrado con éxito. ID asignado: " + estudiante.getId());
                        break;

                    case 2:
                        // Procesamiento de una solicitud
                        System.out.println("\n** Creación de solicitud de crédito **");
                        System.out.print("Ingrese el ID del estudiante: ");
                        int estudianteId = scanner.nextInt();
                        System.out.print("Monto solicitado: ");
                        double monto = scanner.nextDouble();
                        System.out.print("Número de cuotas (máximo 5): ");
                        int cuotas = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea

                        // Crear la solicitud con los datos proporcionados
                        Solicitud solicitud = new Solicitud(0, estudianteId, monto, cuotas, new Date(System.currentTimeMillis()), "Pendiente");
                        sistema.procesarSolicitud(solicitud, estudianteId);

                        break;

                    case 3:
                        // Consultar estado de todas las solicitudes por estudiante
                        System.out.print("Ingrese el ID del estudiante para consultar sus solicitudes: ");
                        int consultaEstudianteId = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                        List<Solicitud> solicitudConsulta = solicitudDAO.obtenerSolicitudPorEstudiante(consultaEstudianteId);

                        if (solicitudConsulta != null && !solicitudConsulta.isEmpty()) {
                            System.out.println("\nSolicitudes del estudiante con ID " + consultaEstudianteId + ":");
                            for (Solicitud sol : solicitudConsulta) {
                                System.out.println("ID Solicitud: " + sol.getId());
                                System.out.println("Monto: " + sol.getMonto());
                                System.out.println("Cuotas: " + sol.getCuotas());
                                System.out.println("Fecha de solicitud: " + sol.getFechaSolicitud());
                                System.out.println("Estado: " + sol.getEstado());
                                System.out.println("-----------");
                            }
                        } else {
                            System.out.println("No se encontraron solicitudes para el estudiante con ID " + consultaEstudianteId);
                        }
                        break;

                    case 4:
                        // Consultar lista de estudiantes
                        System.out.println("\n** Lista de estudiantes **");
                        for (Estudiante e : estudianteDAO.obtenerTodos()) {
                            System.out.println(e);
                        }
                        break;

                    case 5:
                        // Consultar lista de solicitudes
                        System.out.println("\n** Lista de solicitudes **");
                        for (Solicitud s : solicitudDAO.obtenerTodas()) {
                            System.out.println(s);
                        }
                        break;

                    case 6:
                        // Salir
                        System.out.println("¡Gracias por usar CrediPiloto! Saliendo...");
                        estudianteDAO.cerrar();
                        solicitudDAO.cerrar();
                        Conexion.getInstance().cerrarConexion();
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opción no válida. Por favor ingresa una opción correcta.");
                }

            } catch (Exception e) {
                System.err.println("Ocurrió un error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
