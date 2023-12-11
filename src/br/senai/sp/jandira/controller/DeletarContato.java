package br.senai.sp.jandira.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class DeletarContato implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathSegments = path.split("/");

        String nomeDelete = pathSegments[pathSegments.length - 1];
        ContatosController contatos = new ContatosController();

        try {
            contatos.deletarContato(nomeDelete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
