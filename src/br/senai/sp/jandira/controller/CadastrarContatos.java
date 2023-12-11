package br.senai.sp.jandira.controller;

import br.senai.sp.jandira.model.Contato;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CadastrarContatos implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Configurar cabeçalhos CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");


        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if ("POST".equals(exchange.getRequestMethod())) {
            // Ler o corpo da requisição como JSON
            InputStream requestBody = exchange.getRequestBody();
            InputStreamReader isr = new InputStreamReader(requestBody, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            StringBuilder jsonBuilder = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String jsonData = jsonBuilder.toString();

//            System.out.println(jsonData);

            // Converter o JSON para um objeto Contato usando Gson
            Gson gson = new Gson();
            Contato newContato = gson.fromJson(jsonData, Contato.class);

            // Validar se os dados necessários não são nulos
            if (newContato.getNome() != null) {
                ContatosController contatosController = new ContatosController();
                try {
                    contatosController.cadastrarContato(newContato);
                    System.out.println("Contato Cadastrado com Sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro ao cadastrar contato: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Dados insuficientes para cadastrar o contato.");
            }
        }
    }
}