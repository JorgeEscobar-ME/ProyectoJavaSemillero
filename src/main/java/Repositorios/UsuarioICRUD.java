package Repositorios;

import Entidades.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioICRUD implements ICRUD{

    private String cadenaConexion;
    public UsuarioICRUD() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            String cadenaConexion = "jdbc:sqlite:banco.db";
            conectarATabla();

        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }
    }
    private void conectarATabla() {
        String cadenaConexion = "jdbc:sqlite:banco.db";
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            System.out.println("Conexión exitosa");

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
    @Override
    public void guardar(Object objeto) {
        String cadenaConexion = "jdbc:sqlite:banco.db";
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)){
            Usuario usuario = (Usuario) objeto;
            String sentenciaSQL="INSERT INTO USUARIOS (NOMBRE, APELLIDO, CEDULA) VALUES ('" + usuario.getNombre() + "', '" + usuario.getApellido() + "', '" + usuario.getCedula() + "')";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(String identificador) {

    }

    @Override
    public void actualizar(Object objeto) {

    }

    @Override
    public Object buscar(String identificador) {
        return null;
    }

    @Override
    public List<?> listar() {
        return null;
    }

    @Override
    public void actualizarId(Object objeto, String id) {

    }
}
