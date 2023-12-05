package br.senai.sp.jandira;

import br.senai.sp.jandira.controller.ConsultarContatos;
import br.senai.sp.jandira.view.Server;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(5600), 0);
        server.createContext("/contatos", (HttpHandler) new Server());
        server.createContext("/contatosList", (HttpHandler) new ConsultarContatos());

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor Iniciado na Porta 5600...");

    }
}
