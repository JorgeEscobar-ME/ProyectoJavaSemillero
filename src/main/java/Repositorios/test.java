package Repositorios;

import Servicios.UsuarioService;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();

        Map<String, Object> datos = new HashMap<>();
        datos.put("NOMBRE", "Juan");
        datos.put("APELLIDO", "Pérez");
        datos.put("CEDULA", "12345678");

        try {
            usuarioService.crearUsuario(datos);
            System.out.println("Usuario creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }
    }

}
