package org.example.repository;

import org.example.Infraestructure.DatabaseFactory;
import org.example.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    public List<User> findUser() throws SQLException {
        var user = new ArrayList<User>();
        var sql = "SELECT * FROM USUARIO";
        try (
                var conn = DatabaseFactory.getConnection();
                var statment = conn.prepareStatement(sql);
                var result = statment.executeQuery()
        ) {
            while (result.next()) {
                user.add(new User(
                        result.getInt("IDCLIENTE"),
                        result.getString("NOME"),
                        result.getString("TELEFONE"),
                        result.getString("email"),
                        result.getString("senha"),
                        result.getString("CPF"),
                        result.getString("CNH")
                ));
            }
        }
        return user;
    }

    public Optional<User> findby(int id) throws SQLException {
        var sql = "SELECT * FROM USUARIO WHERE IDCLIENTE = ?";


        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            try {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    var usuario = new User(
                            rs.getInt("IDCLIENTE"),
                            rs.getString("NOME"),
                            rs.getString("TELEFONE"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("CPF"),
                            rs.getString("CNH")
                    );
                    return Optional.ofNullable(usuario);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return Optional.empty();
    }

    public void add(User usuario) throws SQLException {
        var sql = "INSERT INTO USUARIO (IDCLIENTE,NOME, TELEFONE" +
                ",email, senha, CPF, CNH) VALUES (SEQ_ID_GERADOR.nextval,?,?,?,?,?,?)";

        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getTelefone());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getCpf());
            statement.setString(6, usuario.getCnh());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    public User updater(User usuario) throws SQLException {

        var sql = "UPDATE USUARIO SET NOME = ?, TELEFONE = ?, EMAIL = ?, SENHA = ?, CPF = ?, CNH = ? WHERE IDCLIENTE = ?";
        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getTelefone());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getCpf());
            statement.setString(6, usuario.getCnh());
            statement.setString(7, usuario.getCnh());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public User update(User usuario) throws SQLException {
        PreparedStatement ps = null;

        try {
            var sql = "UPDATE USUARIO SET NOME = ?, TELEFONE = ?, EMAIL = ?, SENHA = ?, CPF = ?, CNH = ? WHERE IDCLIENTE = ?";
            var conn = DatabaseFactory.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getTelefone());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getCpf());
            ps.setString(6, usuario.getCnh());
            ps.setInt(7, usuario.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Optional<User> find(int id) throws SQLException {
        var sql = "SELECT * FROM USUARIO WHERE IDCLIENTE = ?";
        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            try {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    var user = new User(
                            rs.getInt("IDCLIENTE"),
                            rs.getString("NOME"),
                            rs.getString("TELEFONE"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("CPF"),
                            rs.getString("CNH")
                    );
                    return Optional.ofNullable(user);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return Optional.empty();
    }

    public void delete(String cpf) {
        String sql = "DELETE FROM USUARIO WHERE CPF = ?";

        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
