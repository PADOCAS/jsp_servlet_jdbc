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
import java.util.ArrayList;
import java.util.List;
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

    public void salvarUsuario(Login modelLogin, Boolean update, String usuarioLogado) throws Exception {
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append("INSERT INTO public.login (login, senha, email, nome, usuario_login, perfil) VALUES (?, ?, ?, ?, ?, ?);");

        StringBuilder sqlUpdate = new StringBuilder();
        sqlUpdate.append("UPDATE public.login SET senha = ?, email = ?, nome = ?, usuario_login = ?, perfil = ? WHERE login = ?;");

        if (modelLogin != null
                && modelLogin.getLogin() != null
                && modelLogin.getSenha() != null
                && modelLogin.getEmail() != null
                && modelLogin.getNome() != null
                && usuarioLogado != null
                && modelLogin.getPerfil() != null) {
            if (update == null
                    || !update) {
                //INSERT:
                try (PreparedStatement pstaInsert = connection.prepareStatement(sqlInsert.toString());) {
                    pstaInsert.setString(1, modelLogin.getLogin());
                    pstaInsert.setString(2, modelLogin.getSenha());
                    pstaInsert.setString(3, modelLogin.getEmail());
                    pstaInsert.setString(4, modelLogin.getNome());
                    pstaInsert.setString(5, usuarioLogado);
                    pstaInsert.setString(6, modelLogin.getPerfil());

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
                    pstaInsert.setString(4, usuarioLogado);
                    pstaInsert.setString(5, modelLogin.getPerfil());
                    pstaInsert.setString(6, modelLogin.getLogin());

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

    public Login consultarUsuario(String login, String usuarioLogado) throws Exception {
        Login modelLogin = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM public.login WHERE login = ? AND admin is false AND usuario_login = ?;");

        if (login != null
                && usuarioLogado != null) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, login);
                pstaSel.setString(2, usuarioLogado);

                try (ResultSet rsSel = pstaSel.executeQuery();) {
                    if (rsSel.next()) {
                        modelLogin = new Login();
                        modelLogin.setLogin(rsSel.getString("login"));
                        modelLogin.setSenha(rsSel.getString("senha"));
                        modelLogin.setConfirmSenha(rsSel.getString("senha"));
                        modelLogin.setEmail(rsSel.getString("email"));
                        modelLogin.setAdmin(rsSel.getBoolean("admin"));
                        modelLogin.setNome(rsSel.getString("nome"));
                        modelLogin.setUsuarioLogin(rsSel.getString("usuario_login"));
                        modelLogin.setPerfil(rsSel.getString("perfil"));
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

    public List<Login> consultarUsuarioPorNome(String nome, String usuarioLogado) throws Exception {
        List<Login> listModelLogin = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM public.login WHERE nome LIKE ? AND admin is false AND usuario_login = ?;");

        if (nome != null
                && !nome.isEmpty()
                && usuarioLogado != null) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, nome + "%");
                pstaSel.setString(2, usuarioLogado);

                try (ResultSet rsSel = pstaSel.executeQuery();) {
                    while (rsSel.next()) {
                        Login modelLogin = new Login();
                        modelLogin.setLogin(rsSel.getString("login"));
                        modelLogin.setSenha(rsSel.getString("senha"));
                        modelLogin.setConfirmSenha(rsSel.getString("senha"));
                        modelLogin.setEmail(rsSel.getString("email"));
                        modelLogin.setAdmin(rsSel.getBoolean("admin"));
                        modelLogin.setNome(rsSel.getString("nome"));
                        modelLogin.setUsuarioLogin(rsSel.getString("usuario_login"));
                        modelLogin.setPerfil(rsSel.getString("perfil"));

                        listModelLogin.add(modelLogin);
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
            throw new Exception("Informe um Nome para ser consultado!");
        }

        return listModelLogin;
    }

    public List<Login> consultarTodosUsuarios(String usuarioLogado) throws Exception {
        List<Login> listModelLogin = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM public.login WHERE admin is false AND usuario_login = ?;");

        if (usuarioLogado != null) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, usuarioLogado);

                try (ResultSet rsSel = pstaSel.executeQuery();) {
                    while (rsSel.next()) {
                        Login modelLogin = new Login();
                        modelLogin.setLogin(rsSel.getString("login"));
                        modelLogin.setSenha(rsSel.getString("senha"));
                        modelLogin.setConfirmSenha(rsSel.getString("senha"));
                        modelLogin.setEmail(rsSel.getString("email"));
                        modelLogin.setAdmin(rsSel.getBoolean("admin"));
                        modelLogin.setNome(rsSel.getString("nome"));
                        modelLogin.setUsuarioLogin(rsSel.getString("usuario_login"));
                        modelLogin.setPerfil(rsSel.getString("perfil"));

                        listModelLogin.add(modelLogin);
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
            throw new Exception("Usuário Logado não foi informado!");
        }

        return listModelLogin;
    }

    public void deletarUsuario(String login) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM public.login WHERE login = ? AND admin is false;");

        if (login != null) {
            try (PreparedStatement pstaDel = connection.prepareStatement(sql.toString());) {
                pstaDel.setString(1, login);

                pstaDel.executeUpdate();
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
            throw new Exception("Informe um Login para ser deletado!");
        }
    }
}
