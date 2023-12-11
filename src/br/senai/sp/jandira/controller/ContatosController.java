package br.senai.sp.jandira.controller;

import br.senai.sp.jandira.model.Conexao;
import br.senai.sp.jandira.model.Contato;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatosController {
    Conexao conexao = new Conexao();
    Connection connection = conexao.getConnection();

    public String consultarContatos() throws SQLException {
        Statement statement = connection.createStatement();
        String queryConsulta = "SELECT * FROM contatos";

        ResultSet resultSet = statement.executeQuery(queryConsulta);

        List<Contato> listContatos = new ArrayList<>();

        while (resultSet.next()){
            Contato contato = new Contato();
            contato.setId(resultSet.getInt("id"));
            contato.setNome(resultSet.getString("nome"));
            contato.setEmail(resultSet.getString("email"));
            contato.setFoto(resultSet.getString("foto"));
            contato.setFavorito(resultSet.getString("favorito"));
            contato.setTelefone(resultSet.getString("telefone"));
            listContatos.add(contato);
        }

        Gson gson = new Gson();
        String json = gson.toJson(listContatos);
        return json;

    }
    public void pesquisarContato(){}
    public void deletarContato(String nome) throws SQLException {
        Statement statement = connection.createStatement();
        String queryDelete = "DELETE FROM contatos " +
                "WHERE nome='" + nome +  "'";

        statement.executeUpdate(queryDelete);
        System.out.println("Contato " + nome + " Deletado com sucesso !");

    }
    public void cadastrarContato(Contato newContato) throws SQLException {

        String queryCadastro = "INSERT INTO contatos (id, nome, telefone, email, favorito, foto) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryCadastro)) {
            preparedStatement.setInt(1, newContato.getId());
            preparedStatement.setString(2, newContato.getNome());
            preparedStatement.setString(3, newContato.getTelefone());
            preparedStatement.setString(4, newContato.getEmail());
            preparedStatement.setString(5, newContato.isFavorito());
            preparedStatement.setString(6, newContato.getFoto());

            preparedStatement.executeUpdate();
            System.out.println("Contato Cadastrado com Sucesso!");
        } catch (SQLException error) {
            System.out.println(error);
            throw error; // rethrow the exception if needed
        }
    }
}
