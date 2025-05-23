/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacegrafica.dao;

import interfacegrafica.bd.ConexaoBD;
import interfacegrafica.modelo.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dinho
 */
public class ContatoDao {
    private final Connection conexao;
    
    public ContatoDao() {
        this.conexao = new ConexaoBD().getConnection();
    }
    
    public void adicionaContato(Contato contato) {
        String sql = "INSERT INTO Contato " +
                    "(nome, telefone, email, datanascimento, login, " +
                    "senha) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setDate(4, contato.getDataNascimento());
            ps.setString(5, contato.getLogin());
            ps.setString(6, contato.getSenha());
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean login(String login, String senha) {
        String sql = "SELECT * FROM Contato WHERE login = ?"
                + " AND senha = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
