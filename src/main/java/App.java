
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            String cadenaConexion = "jdbc:sqlite:banco.db";
            String sql =
                    "CREATE TABLE TRANSACCIONES(\n" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "FECHA TEXT NOT NULL,\n" +
                    "HORA TEXT NOT NULL,\n" +
                    "TIPO_TRANSACCION TEXT NOT NULL,\n" +
                    "MONTO REAL NOT NULL,\n" +
                    "ID_CUENTA INTEGER NOT NULL,\n" +
                    "TIPO_CUENTA_DESTINO TEXT,\n" +
                    "FOREIGN KEY(ID_CUENTA) REFERENCES CUENTAS(ID)\n" +
                    ");";

            Connection conexion = DriverManager.getConnection(cadenaConexion);
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);

        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }
    }
}
