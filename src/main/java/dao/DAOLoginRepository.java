/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Login;

/**
 *
 * @author lucia
 */
public class DAOLoginRepository {

    private Connection connection;

    public DAOLoginRepository() {
        connection = SingleConnection.getConnection();
    }

    public Boolean getValidAutenticacao(Login login) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM public.login WHERE login = ? and senha = ?;");

        try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
            pstaSel.setString(1, login.getLogin());
            pstaSel.setString(2, login.getSenha());

            try (ResultSet rsSel = pstaSel.executeQuery();) {
                if (rsSel.next()) {
                    return true;
                }
            }
        }

        return false;
    }

}
