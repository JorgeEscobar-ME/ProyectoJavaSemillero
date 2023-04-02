package Temp;

import Entidades.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CuentasRepositorio implements Repositorio{
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
