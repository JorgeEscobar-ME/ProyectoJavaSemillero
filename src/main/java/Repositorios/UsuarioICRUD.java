package Repositorios;

import Entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioICRUD implements ICRUD {

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
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Usuario usuario = (Usuario) objeto;
            String sentenciaSQL = "INSERT INTO USUARIOS (NOMBRE, APELLIDO, CEDULA) VALUES ('" + usuario.getNombre() + "', '" + usuario.getApellido() + "', '" + usuario.getCedula() + "')";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void eliminar(String identificador) {
        String cadenaConexion = "jdbc:sqlite:banco.db";
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM Usuarios WHERE cedula = '" + identificador + "';";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }
    @Override
    public void actualizar(Object objeto) {
        String cadenaConexion = "jdbc:sqlite:banco.db";
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Usuario usuarios = (Usuario) objeto;
            String sentenciaSql = "UPDATE Usuarios SET nombre = '" + usuarios.getNombre() + "', apellido = '"
                    + usuarios.getApellido() + "', cedula = '" + usuarios.getCedula() + "';";

            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }
    @Override
    public Usuario buscar(String identificador) {
        String cadenaConexion = "jdbc:sqlite:banco.db";
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM Usuarios WHERE CEDULA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, identificador);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                String nombre = resultadoConsulta.getString("NOMBRE");
                String apellido = resultadoConsulta.getString("APELLIDO");
                String cedulaResultado = resultadoConsulta.getString("CEDULA");

                return new Usuario(nombre, apellido, cedulaResultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        List<Usuario> usuariosList = new ArrayList<Usuario>();
        String cadenaConexion = "jdbc:sqlite:banco.db";
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM USUARIOS";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Usuario usuarios;
                    String nombre = resultadoConsulta.getString("NOMBRE");
                    String apellido = resultadoConsulta.getString("APELLIDO");
                    String cedula = resultadoConsulta.getString("CEDULA");
                    usuarios = new Usuario(nombre, apellido, cedula);
                    usuariosList.add(usuarios);
                }
                return usuariosList;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Usuario usuarios = (Usuario) objeto;
            String sentenciaSql = "UPDATE USUARIOS SET NOMBRE = '" + usuarios.getNombre() + "', APELLIDO = '"
                    + usuarios.getApellido() + "'CEDULA = '" + usuarios.getCedula() + "' WHERE ID = " + id
                    + ";";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
