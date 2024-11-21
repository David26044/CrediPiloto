/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ACER
 */
import mundo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mundo.Solicitud;

public class SolicitudDAO {

    private Connection connection;

    public SolicitudDAO() {
        try {
            this.connection = Conexion.getInstance().getConection();
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Guardar nueva solicitud
    public void registrarSolicitud(Solicitud solicitud) {
        String query = "INSERT INTO Solicitud (id_estudiante, monto, cuotas, fecha_solicitud, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, solicitud.getIdEstudiante());
            stmt.setDouble(2, solicitud.getMonto());
            stmt.setInt(3, solicitud.getCuotas());
            stmt.setDate(4, (Date) solicitud.getFechaSolicitud());
            stmt.setString(5, solicitud.getEstado());
            stmt.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                solicitud.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar la solicitud: " + e.getMessage());
        }
    }

    // Actualizar solicitud existente
    public void actualizar(Solicitud solicitud) {
        String query = "UPDATE Solicitud SET id_estudiante = ?, monto = ?, cuotas = ?, fecha_solicitud = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, solicitud.getIdEstudiante());
            stmt.setDouble(2, solicitud.getMonto());
            stmt.setInt(3, solicitud.getCuotas());
            stmt.setDate(4, (Date) solicitud.getFechaSolicitud());
            stmt.setString(5, solicitud.getEstado());
            stmt.setInt(6, solicitud.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la solicitud: " + e.getMessage());
        }
    }

    // Obtener solicitud por ID
    public Solicitud obtenerPorId(int id) {
        String query = "SELECT * FROM Solicitud WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Solicitud(
                        rs.getInt("id"),
                        rs.getInt("id_estudiante"),
                        rs.getDouble("monto"),
                        rs.getInt("cuotas"),
                        rs.getDate("fecha_solicitud"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la solicitud: " + e.getMessage());
        }
        return null;
    }
    
    // Obtener todas las solicitudes
    public List<Solicitud> obtenerTodas() {
        List<Solicitud> solicitudes = new ArrayList<>();
        String query = "SELECT * FROM Solicitud";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                solicitudes.add(new Solicitud(
                        rs.getInt("id"),
                        rs.getInt("id_estudiante"),
                        rs.getDouble("monto"),
                        rs.getInt("cuotas"),
                        rs.getDate("fecha_solicitud"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las solicitudes: " + e.getMessage());
        }
        return solicitudes;
    }
    
    // Obtener solicitudes por ID de estudiante
public List<Solicitud> obtenerSolicitudPorEstudiante(int idEstudiante) {
    List<Solicitud> solicitudes = new ArrayList<>();
    String query = "SELECT * FROM Solicitud WHERE id_estudiante = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, idEstudiante);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Solicitud solicitud = new Solicitud(
                    rs.getInt("id"),
                    rs.getInt("id_estudiante"),
                    rs.getDouble("monto"),
                    rs.getInt("cuotas"),
                    rs.getDate("fecha_solicitud"),
                    rs.getString("estado")
            );
            solicitudes.add(solicitud);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener las solicitudes por estudiante: " + e.getMessage());
    }
    return solicitudes;
}


    // Cerrar conexión
    public void cerrar() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}


