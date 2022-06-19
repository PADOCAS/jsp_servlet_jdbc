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
        StringBuilder sqlUpdate = new StringBuilder();
        Boolean gravouFoto = false;

        if (modelLogin != null
                && modelLogin.getFotoUser() != null
                && !modelLogin.getFotoUser().isEmpty()
                && modelLogin.getExtensaoFotoUser() != null
                && !modelLogin.getExtensaoFotoUser().isEmpty()) {
            gravouFoto = true;

            sqlInsert.append("INSERT INTO public.login (login, senha, email, nome, usuario_login, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero, foto_user, extensao_foto_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            sqlUpdate.append("UPDATE public.login SET senha = ?, email = ?, nome = ?, usuario_login = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ?, numero = ?, foto_user = ?, extensao_foto_user = ? WHERE login = ?;");
        } else {
            sqlInsert.append("INSERT INTO public.login (login, senha, email, nome, usuario_login, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            sqlUpdate.append("UPDATE public.login SET senha = ?, email = ?, nome = ?, usuario_login = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ?, numero = ? WHERE login = ?;");
        }

        if (modelLogin != null
                && modelLogin.getLogin() != null
                && modelLogin.getSenha() != null
                && modelLogin.getEmail() != null
                && modelLogin.getNome() != null
                && usuarioLogado != null
                && modelLogin.getPerfil() != null
                && modelLogin.getSexo() != null
                && modelLogin.getCep() != null
                && modelLogin.getLogradouro() != null
                && modelLogin.getBairro() != null
                && modelLogin.getLocalidade() != null
                && modelLogin.getUf() != null
                && modelLogin.getNumero() != null) {
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
                    pstaInsert.setString(7, modelLogin.getSexo());
                    pstaInsert.setString(8, modelLogin.getCep());
                    pstaInsert.setString(9, modelLogin.getLogradouro());
                    pstaInsert.setString(10, modelLogin.getBairro());
                    pstaInsert.setString(11, modelLogin.getLocalidade());
                    pstaInsert.setString(12, modelLogin.getUf());
                    pstaInsert.setString(13, modelLogin.getNumero());

                    if (gravouFoto) {
                        pstaInsert.setString(14, modelLogin.getFotoUser());
                        pstaInsert.setString(15, modelLogin.getExtensaoFotoUser());
                    }

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
                try (PreparedStatement pstaUpdate = connection.prepareStatement(sqlUpdate.toString());) {
                    pstaUpdate.setString(1, modelLogin.getSenha());
                    pstaUpdate.setString(2, modelLogin.getEmail());
                    pstaUpdate.setString(3, modelLogin.getNome());
                    pstaUpdate.setString(4, usuarioLogado);
                    pstaUpdate.setString(5, modelLogin.getPerfil());
                    pstaUpdate.setString(6, modelLogin.getSexo());
                    pstaUpdate.setString(7, modelLogin.getCep());
                    pstaUpdate.setString(8, modelLogin.getLogradouro());
                    pstaUpdate.setString(9, modelLogin.getBairro());
                    pstaUpdate.setString(10, modelLogin.getLocalidade());
                    pstaUpdate.setString(11, modelLogin.getUf());
                    pstaUpdate.setString(12, modelLogin.getNumero());

                    if (gravouFoto) {
                        pstaUpdate.setString(13, modelLogin.getFotoUser());
                        pstaUpdate.setString(14, modelLogin.getExtensaoFotoUser());
                        pstaUpdate.setString(15, modelLogin.getLogin());
                    } else {
                        pstaUpdate.setString(13, modelLogin.getLogin());
                    }

                    pstaUpdate.executeUpdate();
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

    public Login consultarUsuarioGeralSaberSeInsert(String login, String usuarioLogado) throws Exception {
        Login modelLogin = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM public.login WHERE login = ?;");

        if (login != null
                && usuarioLogado != null) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, login);

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
                        modelLogin.setSexo(rsSel.getString("sexo"));
                        modelLogin.setFotoUser(rsSel.getString("foto_user"));
                        modelLogin.setExtensaoFotoUser(rsSel.getString("extensao_foto_user"));
                        modelLogin.setCep(rsSel.getString("cep"));
                        modelLogin.setLogradouro(rsSel.getString("logradouro"));
                        modelLogin.setBairro(rsSel.getString("bairro"));
                        modelLogin.setLocalidade(rsSel.getString("localidade"));
                        modelLogin.setUf(rsSel.getString("uf"));
                        modelLogin.setNumero(rsSel.getString("numero"));
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
                        modelLogin.setSexo(rsSel.getString("sexo"));
                        modelLogin.setFotoUser(rsSel.getString("foto_user"));
                        modelLogin.setExtensaoFotoUser(rsSel.getString("extensao_foto_user"));
                        modelLogin.setCep(rsSel.getString("cep"));
                        modelLogin.setLogradouro(rsSel.getString("logradouro"));
                        modelLogin.setBairro(rsSel.getString("bairro"));
                        modelLogin.setLocalidade(rsSel.getString("localidade"));
                        modelLogin.setUf(rsSel.getString("uf"));
                        modelLogin.setNumero(rsSel.getString("numero"));
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
                        modelLogin.setSexo(rsSel.getString("sexo"));
                        modelLogin.setFotoUser(rsSel.getString("foto_user"));
                        modelLogin.setExtensaoFotoUser(rsSel.getString("extensao_foto_user"));
                        modelLogin.setCep(rsSel.getString("cep"));
                        modelLogin.setLogradouro(rsSel.getString("logradouro"));
                        modelLogin.setBairro(rsSel.getString("bairro"));
                        modelLogin.setLocalidade(rsSel.getString("localidade"));
                        modelLogin.setUf(rsSel.getString("uf"));
                        modelLogin.setNumero(rsSel.getString("numero"));

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
                        modelLogin.setSexo(rsSel.getString("sexo"));
                        modelLogin.setFotoUser(rsSel.getString("foto_user"));
                        modelLogin.setExtensaoFotoUser(rsSel.getString("extensao_foto_user"));
                        modelLogin.setCep(rsSel.getString("cep"));
                        modelLogin.setLogradouro(rsSel.getString("logradouro"));
                        modelLogin.setBairro(rsSel.getString("bairro"));
                        modelLogin.setLocalidade(rsSel.getString("localidade"));
                        modelLogin.setUf(rsSel.getString("uf"));
                        modelLogin.setNumero(rsSel.getString("numero"));

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
