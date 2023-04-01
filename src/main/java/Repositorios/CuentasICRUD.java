package Repositorios;

import Entidades.Cuentas;
import Entidades.Transacciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentasICRUD implements ICRUD {
    private String cadenaConexion = "jdbc:sqlite:banco.db";

    public CuentasICRUD() {
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
        String cadenaConexion = "jdbc:sqlite:banco.db";
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)){
            Cuentas cuenta = (Cuentas) objeto;
            String sentenciaSQL="INSERT INTO CUENTAS (NUMERO_CUENTA, SALDO, TIPO_CUENTA, ID_USUARIO) VALUES " +
                    "('" + cuenta.getNumeroCuenta() + "', '" + cuenta.getSaldo() + "', '" + cuenta.getTipoCuenta() + "', '"+ cuenta.getIdUsuario() + "')";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(String identificador) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM CUENTAS WHERE ID_USUARIO = '" + identificador + "';";
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
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Cuentas cuentas = (Cuentas) objeto;
            String sentenciaSql = "UPDATE CUENTAS SET NUMERO_CUENTA = '" + cuentas.getNumeroCuenta() + "', SALDO = '"
                    + cuentas.getSaldo() + "', TIPO_CUENTA = '" + cuentas.getTipoCuenta()+ "' + ID_USUARIO = '" + cuentas.getIdUsuario()+ "';";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public Object buscar(String identificador) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM CUENTAS WHERE ID_USUARIO = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, identificador);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                Cuentas cuentas = null;
                String numero_cuenta = resultadoConsulta.getString("NUMERO_CUENTA");
                Double saldo = Double.valueOf(resultadoConsulta.getString("SALDO"));
                String tipo_cuenta = resultadoConsulta.getString("TIPO_CUENTA");
                String id_usuarioResultado = resultadoConsulta.getString("ID_USUARIO");

                cuentas = new Cuentas(numero_cuenta, saldo, id_usuarioResultado, tipo_cuenta);
                return cuentas;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
            List<Cuentas> cuentasList = new ArrayList<Cuentas>();

            try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
                String sentenciaSql = "SELECT * FROM CUENTAS";
                PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
                ResultSet resultadoConsulta = sentencia.executeQuery();

                if (resultadoConsulta != null) {
                    while (resultadoConsulta.next()) {
                        Cuentas cuentas = null;
                        int id = resultadoConsulta.getInt("ID");
                        String numero_cuenta = resultadoConsulta.getString("NUMERO_CUENTA");
                        Double saldo = Double.valueOf(resultadoConsulta.getString("SALDO"));
                        String tipo_cuenta = resultadoConsulta.getString("TIPO_CUENTA");
                        String id_usuario = resultadoConsulta.getString("ID_USUARIO");

                        cuentas = new Cuentas(numero_cuenta, saldo, tipo_cuenta, id_usuario);
                        cuentasList.add(cuentas);
                    }
                    return cuentasList;
                }

            } catch (SQLException e) {
                System.err.println("Error de conexión: " + e);
            }
            return null;
        }


    @Override
    public void actualizarId(Object objeto, String id) {

    }
}