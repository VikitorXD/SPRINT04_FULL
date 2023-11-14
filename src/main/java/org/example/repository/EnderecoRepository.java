package org.example.repository;

import org.example.Infraestructure.DatabaseFactory;
import org.example.models.Endereco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnderecoRepository {

    public List<Endereco> findEndereco() throws SQLException {
        var end = new ArrayList<Endereco>();
        var sql = "SELECT * FROM ENDERECO";
        try (
                var conn = DatabaseFactory.getConnection();
                var statment = conn.prepareStatement(sql);
                var result = statment.executeQuery()
        ) {
            while (result.next()) {
                end.add(new Endereco(
                        result.getInt("IDENDERECO"),
                        result.getString("NMRUA"),
                        result.getString("NRCASA"),
                        result.getString("CEP")
                ));
            }
        }
        return end;
    }

    public Optional<Endereco> findby(int id) throws SQLException {
        var sql = "SELECT * FROM ENDERECO WHERE IDENDERECO = ?";


        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            try {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    var endereco = new Endereco(
                            rs.getInt("IDENDERECO"),
                            rs.getString("NMRUA"),
                            rs.getString("NRCASA"),
                            rs.getString("CEP")
                    );
                    return Optional.ofNullable(endereco);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return Optional.empty();
    }

    public void add(Endereco endereco) throws SQLException {
        var sql = "INSERT INTO ENDERECO (IDENDERECO,NMRUA, NRCASA, CEP) VALUES (SEQ_ID_GERADOR.nextval,?,?,?)";

        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, endereco.getNmrua());
            statement.setString(2, endereco.getNrcasa());
            statement.setString(3, endereco.getCep());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ENDERECO WHERE IDENDERECO = ?";

        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Endereco update(Endereco endereco) throws SQLException {

        var sql = "UPDATE ENDERECO SET NMRUA = ?, NRCASA = ?, CEP = ? WHERE IDENDERECO = ?";
        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, endereco.getNmrua());
            statement.setString(2, endereco.getNrcasa());
            statement.setString(3, endereco.getCep());
            statement.setInt(4, endereco.getIdendereco());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return endereco;
    }
}
