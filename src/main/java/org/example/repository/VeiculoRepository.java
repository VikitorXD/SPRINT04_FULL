package org.example.repository;

import org.example.Infraestructure.DatabaseFactory;
import org.example.models.Veiculo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepository {
    public List<Veiculo> findVeiculo() throws SQLException {
        var veiculo = new ArrayList<Veiculo>();
        var sql = "SELECT * FROM veiculo";
        try (
                var conn = DatabaseFactory.getConnection();
                var statment = conn.prepareStatement(sql);
                var result = statment.executeQuery()
        ) {
            while (result.next()) {
                veiculo.add(new Veiculo(
                        result.getInt("IDVEICULO"),
                        result.getString("MODELO"),
                        result.getInt("NUMEIXOS"),
                        result.getDouble("CARGA"),
                        result.getString("PLACAVEICULO")
                ));
            }
        }
        return veiculo;
    }

    public Optional<Veiculo> findby(String placa) throws SQLException {
        var sql = "SELECT * FROM VEICULO WHERE PLACAVEICULO = ?";


        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, placa);
            try {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    var veiculo = new Veiculo(
                            rs.getInt("IDVEICULO"),
                            rs.getString("MODELO"),
                            rs.getInt("NUMEIXOS"),
                            rs.getDouble("CARGA"),
                            rs.getString("PLACAVEICULO")
                    );
                    return Optional.ofNullable(veiculo);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return Optional.empty();
    }

    public void addveiculo(Veiculo veiculo) throws SQLException {
        var sql = "INSERT INTO VEICULO(IDVEICULO, MODELO, NUMEIXOS, CARGA, PLACAVEICULO)" +
                "VALUES(seq_id_gerador.nextval,?,?,?,?)";
        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, veiculo.getModelo());
            statement.setInt(2, veiculo.getNumeroEixos());
            statement.setDouble(3, veiculo.getCarga());
            statement.setString(4, veiculo.getPlaca());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Veiculo update(Veiculo veiculo) throws SQLException {
        var sql = "UPDATE VEICULO SET MODELO = ?, NUMEIXOS = ?, CARGA = ?, PLACAVEICULO = ? WHERE PLACAVEICULO = ? ";


        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, veiculo.getModelo());
            statement.setInt(2, veiculo.getNumeroEixos());
            statement.setDouble(3, veiculo.getCarga());
            statement.setString(4, veiculo.getPlaca());
            statement.setString(5, veiculo.getPlaca());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veiculo;
    }

    public Optional<Veiculo> findbyid(int id) throws SQLException {
        var sql = "SELECT * FROM VEICULO WHERE IDVEICULO = ?";


        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            try {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    var veiculo = new Veiculo(
                            rs.getInt("IDVEICULO"),
                            rs.getString("MODELO"),
                            rs.getInt("NUMEIXOS"),
                            rs.getDouble("CARGA"),
                            rs.getString("PLACAVEICULO")
                    );
                    return Optional.ofNullable(veiculo);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return Optional.empty();
    }

    public void delete(String placa) {
        String sql = "DELETE FROM VEICULO WHERE PLACAVEICULO = ?";

        try {
            var conn = DatabaseFactory.getConnection();
            var statement = conn.prepareStatement(sql);
            statement.setString(1, placa);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
