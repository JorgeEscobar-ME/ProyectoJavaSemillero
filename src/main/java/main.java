import Entidades.Cuentas;
import Entidades.Transacciones;
import Entidades.Usuario;
import Repositorios.CuentasICRUD;
import Repositorios.TransaccionesICRUD;
import Repositorios.UsuarioICRUD;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class main {
    public static void main(String[] args) {
        UsuarioICRUD usuarioicrud= new UsuarioICRUD();
//        Usuario persona = new Usuario("Jorge", "Escobar", "1053868118");
//        usuarioicrud.guardar(persona);
        TransaccionesICRUD transaccionesICRUD = new TransaccionesICRUD();

        // Obtener la fecha actual

// Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

// Formatear la fecha en formato yyyy-MM-dd
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formatoFecha);

// Obtener la hora actual
        LocalTime horaActual = LocalTime.now();

// Formatear la hora en formato HH:mm:ss
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaActualFormateada = horaActual.format(formatoHora);

        Transacciones transacciones = new Transacciones(fechaActualFormateada, horaActualFormateada, "deposito", 5000, "10", "Ahorros" );
//        transaccionesICRUD.guardar(transacciones);
        Cuentas cuenta = new Cuentas("26", 1500, "Ahorros", "101");
        CuentasICRUD cuentasICRUD = new CuentasICRUD();
        cuentasICRUD.guardar(cuenta);
    }
}
