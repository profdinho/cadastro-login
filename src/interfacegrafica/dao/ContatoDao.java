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
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Contato> listaContatos() {
        List<Contato> listaContatos = new ArrayList();
        String sql = "SELECT * FROM Contato";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getDate("datanascimento"));
                contato.setLogin(rs.getString("login"));
                contato.setSenha(rs.getString("senha"));
                listaContatos.add(contato);
            }
            return listaContatos;
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Contato getContatoById(int id) {
        Contato contato = new Contato();
        String sql = "SELECT * FROM Contato WHERE id = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getDate("dataNascimento"));
                contato.setLogin(rs.getString("login"));
                contato.setSenha(rs.getString("senha"));
            }
            rs.close();
            ps.close();
            return contato;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualizaContato(Contato contato) {
        String sql = "UPDATE Contato SET nome=?, telefone=?,"
                + "email=?, dataNascimento=?, login=?,"
                + "senha=? WHERE id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setDate(4, contato.getDataNascimento());
            ps.setString(5, contato.getLogin());
            ps.setString(6, contato.getSenha());
            ps.setInt(7, contato.getId());
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeContato(Integer id) {
        String sql = "DELETE FROM Contato WHERE id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
