package br.edu.ifpr.bsi.projetopdm.singleton;

import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;

public class UsuarioSistemaSingleton {

    private static UsuarioSistema instance;

    private UsuarioSistemaSingleton() {
    }

    // Retorna a instância atual
    public static UsuarioSistema getInstance() {
        return instance;
    }

    // Define a instância com um usuário autenticado
    public static void setInstance(UsuarioSistema usuario) {
        instance = usuario;
    }

    // Reseta a instância
    public static void resetInstance() {
        instance = null;
    }
}
