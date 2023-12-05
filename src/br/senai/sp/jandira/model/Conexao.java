package br.senai.sp.jandira.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String servidor, banco, usuario, senha;
    public Connection conexao;

    public Conexao (){
        this.servidor = "127.0.0.1";
        this.banco = "db_contatos";
        this.usuario = "root";
        this.senha = "bcd127";
    }

    public void connectDrive (){
        try {
            this.conexao = DriverManager.getConnection("jdbc:mysql://" +
                    this.servidor + "/" + this.banco, this.usuario, this.senha);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }


    public Connection getConnection (){
        connectDrive();
        return conexao;
    }


}
