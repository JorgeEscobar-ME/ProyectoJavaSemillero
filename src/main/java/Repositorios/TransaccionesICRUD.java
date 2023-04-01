package Repositorios;

import Entidades.Transacciones;
import Entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionesICRUD implements ICRUD{
    private String cadenaConexion ="jdbc:sqlite:banco.db";

    public TransaccionesICRUD() {
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
            Transacciones transaccion = (Transacciones) objeto;
            String sentenciaSQL="INSERT INTO TRANSACCIONES (FECHA, HORA, TIPO_TRANSACCION, MONTO, ID_CUENTA, TIPO_CUENTA_DESTINO) VALUES " +
                    "('" + transaccion.getFecha() + "', '" + transaccion.getHora() + "', '" + transaccion.getTipoTransaccion() + "', '"+ transaccion.getMonto() + "', '" + transaccion.getIdCuenta() + "', '" + transaccion.getTipoCuentaDestino()+ "')";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(String idCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM TRANSACCIONES WHERE ID_CUENTA = '" + idCuenta + "';";
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
            Transacciones transacciones = (Transacciones) objeto;
            String sentenciaSql = "UPDATE Transacciones SET FECHA = '" + transacciones.getFecha() + "', HORA = '" + transacciones.getHora() + "', TIPO_TRANSACCION = '" + transacciones.getTipoTransaccion() + "', MONTO = " + transacciones.getMonto() + ", ID_CUENTA = '" + transacciones.getIdCuenta() + "', TIPO_CUENTA_DESTINO = '" + transacciones.getTipoCuentaDestino() + "'";

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
            String sentenciaSQL = "SELECT * FROM Transacciones WHERE ID_CUENTA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, identificador);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                Transacciones transacciones = null;
                String fecha = resultadoConsulta.getString("FECHA");
                String hora = resultadoConsulta.getString("HORA");
                String tipo_transaccion = resultadoConsulta.getString("TIPO_TRANSACCION");
                Double monto = Double.valueOf(resultadoConsulta.getString("MONTO"));
                String id_cuentaResultado = resultadoConsulta.getString("ID_CUENTA");
                String tipo_cuentaDestino = resultadoConsulta.getString("TIPO_CUENTA_DESTINO");

                transacciones = new Transacciones(fecha, hora, tipo_transaccion, monto, id_cuentaResultado, tipo_cuentaDestino);
                return transacciones;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public List<?> listar() {
        List<Transacciones> transaccionList = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM TRANSACCIONES";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Transacciones transacciones = null;
                    int id = resultadoConsulta.getInt("ID");
                    String fecha = resultadoConsulta.getString("FECHA");
                    String hora = resultadoConsulta.getString("HORA");
                    String tipo_transaccion = resultadoConsulta.getString("TIPO_TRANSACCION");
                    Double monto = Double.valueOf(resultadoConsulta.getString("MONTO"));
                    String id_cuenta = resultadoConsulta.getString("ID_CUENTA");
                    String tipo_cuentaDestino = resultadoConsulta.getString("TIPO_CUENTA_DESTINO");

                    transacciones = new Transacciones(fecha, hora, tipo_transaccion, monto, id_cuenta, tipo_cuentaDestino);
                    transaccionList.add(transacciones);
                }
                return transaccionList;
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

