package Servicios;

import Entidades.Usuario;
import Repositorios.ICRUD;
import Repositorios.UsuarioICRUD;

import java.util.List;
import java.util.Map;

public class UsuarioService {
    private ICRUD repositorioUsuario;

    public UsuarioService() {
        repositorioUsuario = new UsuarioICRUD();
    }

    public void guardarUsuarios(Map datos) {
        String nombre = (String) datos.get("NOMBRE");
        String apellido = (String) datos.get("APELLIDO");
        String cedula = (String) datos.get("CEDULA");

        Usuario nuevoUsuario = new Usuario(nombre, apellido, cedula);
        repositorioUsuario.guardar(nuevoUsuario);
    }

    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) repositorioUsuario.listar();
    }

    public Usuario buscarUsuarios(String identificador) throws Exception {
        Object usuario = repositorioUsuario.buscar(identificador);
        if (usuario == null) {
            throw new Exception("No se encontro el usuario");
        }
        return (Usuario) usuario;
    }

    public void eliminarUsuarios(String identificador) {
        repositorioUsuario.eliminar(identificador);
    }

    public void actualizarUsuarios(Map datos) {
        String nombre = (String) datos.get("NOMBRE");
        String apellido = (String) datos.get("APELLIDO");
        String cedula = (String) datos.get("CEDULA");

        Usuario nuevoUsuario = new Usuario(nombre, apellido, cedula);
        repositorioUsuario.actualizar(nuevoUsuario);
    }

    public void actualizarUsuariosId(Map datos, String id) {
        String nombre = (String) datos.get("NOMBRE");
        String apellido = (String) datos.get("APELLIDO");
        String cedula = (String) datos.get("CEDULA");

        Usuario nuevoUsuario = new Usuario(nombre, apellido, cedula);
        repositorioUsuario.actualizarId(nuevoUsuario, id);
    }
}