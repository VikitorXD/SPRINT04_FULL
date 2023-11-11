package org.example.repository;

import org.example.Infraestructure.DatabaseFactory;
import org.example.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
