package pruebamaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonaCRUD {
	    private static final String URL = "jdbc:mysql://localhost:3306/persona";
	    private static final String USER = "root";
	    private static final String PASSWORD = "76901613986";

	    public void crearPersona(String nombre, int edad, String email) {
	        String query = "INSERT INTO persona (nombre, edad, email) VALUES (?, ?, ?)";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, nombre);
	            stmt.setInt(2, edad);
	            stmt.setString(3, email);
	            stmt.executeUpdate();
	            System.out.println("Persona creada exitosamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void leerPersonas() {
	        String query = "SELECT * FROM persona";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String nombre = rs.getString("nombre");
	                int edad = rs.getInt("edad");
	                String email = rs.getString("email");
	                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Email: " + email);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void actualizarPersona(int id, String nombre, int edad, String email) {
	        String query = "UPDATE persona SET nombre = ?, edad = ?, email = ? WHERE id = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, nombre);
	            stmt.setInt(2, edad);
	            stmt.setString(3, email);
	            stmt.setInt(4, id);
	            stmt.executeUpdate();
	            System.out.println("Persona actualizada exitosamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void eliminarPersona(int id) {
	        String query = "DELETE FROM persona WHERE id = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	            System.out.println("Persona eliminada exitosamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) {
	        PersonaCRUD crud = new PersonaCRUD();
	        crud.crearPersona("Juan Pérez", 30, "juan.perez@example.com");
	        crud.leerPersonas();
	        crud.actualizarPersona(1, "Juan Pérez", 31, "juan.perez@example.com");
//	        crud.eliminarPersona(1);
	    }
	}
