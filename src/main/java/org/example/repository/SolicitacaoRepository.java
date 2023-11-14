package org.example.repository;

import org.example.Infraestructure.DatabaseFactory;
import org.example.models.Solicitacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SolicitacaoRepository {
    public List<Solicitacao> findSolit() throws SQLException {
        var solit = new ArrayList<Solicitacao>();
        var sql = "SELECT * FROM SOLICITACAO";
        try (
                var conn = DatabaseFactory.getConnection();
                var statment = conn.prepareStatement(sql);
                var result = statment.executeQuery()
        ) {
            while (result.next()) {
                solit.add(new Solicitacao(
                        result.getInt("IDSOLICITACAO"),
                        result.getString("SOLITUSER")
                ));
            }
        }
        return solit;
    }

    public Optional<Solicitacao> findby(int id) throws SQLException {
        var sql = "SELECT * FROM SOLICITACAO WHERE IDSOLICITACAO = ?";


        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            try {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    var solicitacao = new Solicitacao(
                            rs.getInt("IDSOLICITACAO"),
                            rs.getString("SOLITUSER")
                    );
                    return Optional.ofNullable(solicitacao);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return Optional.empty();
    }

    public void add(Solicitacao solicitacao) throws SQLException {
        var sql = "insert into solicitacao (idsolicitacao, SOLITUSER) " +
                "values(seq_id_gerador.nextval,?)";

        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, solicitacao.getSolitUsuario());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Solicitacao update(Solicitacao solicitacao) throws SQLException {

        var sql = "UPDATE SOLICITACAO SET SOLITUSER = ? WHERE IDSOLICITACAO = ?";
        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, solicitacao.getSolitUsuario());
            statement.setInt(2, solicitacao.getIdsolit());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solicitacao;
    }
}
