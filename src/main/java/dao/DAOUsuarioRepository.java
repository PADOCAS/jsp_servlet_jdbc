/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Login;

/**
 *
 * @author lucia
 */
public class DAOUsuarioRepository {

    private Connection connection;

    public DAOUsuarioRepository() {
        connection = SingleConnection.getConnection();
    }

    public void salvarUsuario(Login modelLogin, Boolean update) throws Exception {
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append("INSERT INTO public.login (login, senha, email, nome) VALUES (?, ?, ?, ?);");

        StringBuilder sqlUpdate = new StringBuilder();
        sqlUpdate.append("UPDATE public.login SET senha = ?, email = ?, nome = ? WHERE login = ?;");

        if (modelLogin != null
                && modelLogin.getLogin() != null
                && modelLogin.getSenha() != null
                && modelLogin.getEmail() != null
                && modelLogin.getNome() != null) {
            if (update == null
                    || !update) {
                //INSERT:
                try (PreparedStatement pstaInsert = connection.prepareStatement(sqlInsert.toString());) {
                    pstaInsert.setString(1, modelLogin.getLogin());
                    pstaInsert.setString(2, modelLogin.getSenha());
                    pstaInsert.setString(3, modelLogin.getEmail());
                    pstaInsert.setString(4, modelLogin.getNome());

                    pstaInsert.executeUpdate();
                    connection.commit();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                    if (connection != null) {
                        try {
                            connection.rollback();
                        } catch (SQLException ex1) {
                            ex1.printStackTrace();
                        }
                    }

                    throw new Exception(ex.getMessage());
                }
            } else {
                //UPDATE:
                try (PreparedStatement pstaInsert = connection.prepareStatement(sqlUpdate.toString());) {
                    pstaInsert.setString(1, modelLogin.getSenha());
                    pstaInsert.setString(2, modelLogin.getEmail());
                    pstaInsert.setString(3, modelLogin.getNome());
                    pstaInsert.setString(4, modelLogin.getLogin());

                    pstaInsert.executeUpdate();
                    connection.commit();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                    if (connection != null) {
                        try {
                            connection.rollback();
                        } catch (SQLException ex1) {
                            ex1.printStackTrace();
                        }
                    }

                    throw new Exception(ex.getMessage());
                }
            }
        } else {
            throw new Exception("Campos faltando serem informados. Verifique!");
        }
    }

    public Login consultarUsuario(String login) throws Exception {
        Login modelLogin = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM public.login WHERE login = ?;");

        if (login != null) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, login);

                try (ResultSet rsSel = pstaSel.executeQuery();) {
                    if (rsSel.next()) {
                        modelLogin = new Login();
                        modelLogin.setLogin(rsSel.getString("login"));
                        modelLogin.setSenha(rsSel.getString("senha"));
                        modelLogin.setConfirmSenha(rsSel.getString("senha"));
                        modelLogin.setEmail(rsSel.getString("email"));
                        modelLogin.setNome(rsSel.getString("nome"));
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                }

                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("Informe um Login para ser consultado!");
        }

        return modelLogin;
    }
}
