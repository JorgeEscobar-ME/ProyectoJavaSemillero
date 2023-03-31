package Repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            System.out.println("Conexión exitosa");

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
    @Override
    public void guardar(Object objeto) {


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
