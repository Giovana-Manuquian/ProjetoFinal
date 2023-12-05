package br.senai.sp.jandira.controller;

import br.senai.sp.jandira.model.Contato;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class ConsultarContatos implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ContatosController contatos = new ContatosController();
        String mensagemContatos = "";
        try {
            mensagemContatos = contatos.consultarContatos();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, mensagemContatos.length());
        OutputStream out = exchange.getResponseBody();

        try {
            out.write(mensagemContatos.getBytes());
        } catch (Exception e){
            System.out.println(e);
        } finally {
            out.close();
        }
    }
}
