package Temp;

import Entidades.Usuario;
import Repositorios.UsuarioICRUD;

public class MainTemp {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("Jorge", "Esc", "15386812163");
        CuentasRepositorio prueba = new CuentasRepositorio();
        prueba.guardar(usuario1);

    }
}
