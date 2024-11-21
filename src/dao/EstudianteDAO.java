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
import mundo.Estudiante;

public class EstudianteDAO {

    private Connection connection;

    public EstudianteDAO() {
        try {
            this.connection = Conexion.getInstance().getConection();
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Registrar nuevo estudiante
    public void registrarEstudiante(Estudiante estudiante) {
        String query = "INSERT INTO Estudiante (nombre, apellido, puntaje_datacredito, documento_identidad, correo, telefono) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setInt(3, estudiante.getPuntajeDatacredito());
            stmt.setString(4, estudiante.getDocumentoIdentidad());
            stmt.setString(5, estudiante.getCorreo());
            stmt.setString(6, estudiante.getTelefono());
            stmt.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                estudiante.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar el estudiante: " + e.getMessage());
        }
    }

    // Actualizar estudiante existente
    public void actualizar(Estudiante estudiante) {
        String query = "UPDATE Estudiante SET nombre = ?, apellido = ?, puntaje_datacredito = ?, documento_identidad = ?, correo = ?, telefono = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setInt(3, estudiante.getPuntajeDatacredito());
            stmt.setString(4, estudiante.getDocumentoIdentidad());
            stmt.setString(5, estudiante.getCorreo());
            stmt.setString(6, estudiante.getTelefono());
            stmt.setInt(7, estudiante.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estudiante: " + e.getMessage());
        }
    }

    // Obtener estudiante por ID
    public Estudiante obtenerPorId(int id) {
        String query = "SELECT * FROM Estudiante WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("puntaje_datacredito"),
                        rs.getString("documento_identidad"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el estudiante: " + e.getMessage());
        }
        return null;
    }
    
    // Obtener todos los estudiantes
public List<Estudiante> obtenerTodos() {
    List<Estudiante> estudiantes = new ArrayList<>();
    String query = "SELECT * FROM Estudiante";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Estudiante estudiante = new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("puntaje_datacredito"),
                    rs.getString("documento_identidad"),
                    rs.getString("correo"),
                    rs.getString("telefono")
            );
            estudiantes.add(estudiante);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener todos los estudiantes: " + e.getMessage());
    }
    return estudiantes;
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
