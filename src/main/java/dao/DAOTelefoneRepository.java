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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Login;
import model.Telefone;

/**
 *
 * @author lucia
 */
public class DAOTelefoneRepository {

    private Connection connection;

    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    public DAOTelefoneRepository() {
        connection = SingleConnection.getConnection();
    }

    public void salvarTelefone(Telefone telefoneASalvar) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO public.telefone (login, numero, usuario_login) VALUES (?, ?, ?);");

        if (telefoneASalvar != null
                && telefoneASalvar.getLogin() != null
                && telefoneASalvar.getLogin().getLogin() != null
                && telefoneASalvar.getNumero() != null
                && telefoneASalvar.getUsuarioLogin() != null
                && telefoneASalvar.getUsuarioLogin().getLogin() != null) {
            try (PreparedStatement pstaSave = connection.prepareStatement(sql.toString());) {
                pstaSave.setString(1, telefoneASalvar.getLogin().getLogin());
                pstaSave.setString(2, telefoneASalvar.getNumero());
                pstaSave.setString(3, telefoneASalvar.getUsuarioLogin().getLogin());

                pstaSave.executeUpdate();

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
            throw new Exception("Campos faltando serem informados para salvar. Verifique!");
        }
    }

    public Telefone getConsultaTelefone(String loginTel, Long idTel) throws Exception {
        Telefone telCharged = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM public.telefone WHERE login = ? AND id = ?;");

        if (loginTel != null
                && !loginTel.isEmpty()
                && idTel != null) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, loginTel);
                pstaSel.setLong(2, idTel);

                try (ResultSet rsSel = pstaSel.executeQuery();) {
                    if (rsSel.next()) {
                        telCharged = new Telefone();
                        telCharged.setId(rsSel.getLong("id"));
                        telCharged.setNumero(rsSel.getString("numero"));
                        telCharged.setLogin(daoLoginRepository.consultarUsuario(rsSel.getString("login")));
                        telCharged.setUsuarioLogin(daoLoginRepository.consultarUsuario(rsSel.getString("usuario_login")));
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
            throw new Exception("Campos faltando serem informados para deletar. Verifique!");
        }

        return telCharged;
    }

    public void deleteTelefone(Telefone telefone) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM public.telefone WHERE login = ? AND id = ?;");

        if (telefone != null
                && telefone.getLogin() != null
                && telefone.getLogin().getLogin() != null
                && telefone.getId() != null) {
            try (PreparedStatement pstaDel = connection.prepareStatement(sql.toString());) {
                pstaDel.setString(1, telefone.getLogin().getLogin());
                pstaDel.setLong(2, telefone.getId());

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
            throw new Exception("Campos faltando serem informados para deletar. Verifique!");
        }
    }

    public List<Telefone> getListConsultaTelefone(Login login) throws Exception {
        List<Telefone> listTelefone = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM public.telefone WHERE login = ?;");

        if (login != null
                && login.getLogin() != null) {
            try (PreparedStatement pstaConsulta = connection.prepareStatement(sql.toString());) {
                pstaConsulta.setString(1, login.getLogin());

                try (ResultSet rsConsulta = pstaConsulta.executeQuery();) {
                    while (rsConsulta.next()) {
                        Telefone telefone = new Telefone();
                        telefone.setId(rsConsulta.getLong("id"));
                        telefone.setNumero(rsConsulta.getString("numero"));
                        telefone.setLogin(daoLoginRepository.consultarUsuario(rsConsulta.getString("login")));
                        telefone.setUsuarioLogin(daoLoginRepository.consultarUsuario(rsConsulta.getString("usuario_login")));

                        listTelefone.add(telefone);
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
            throw new Exception("Campos faltando serem informados para deletar. Verifique!");
        }

        return listTelefone;
    }

    public Boolean existsTelefone(String numero, String login) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(*) as qtde FROM public.telefone WHERE login = ? AND numero = ?;");

        if (numero != null
                && !numero.isEmpty()
                && login != null
                && !login.isEmpty()) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, login);
                pstaSel.setString(2, numero);

                try (ResultSet rsSel = pstaSel.executeQuery();) {
                    if (rsSel.next()) {
                        if (rsSel.getObject("qtde") != null
                                && rsSel.getLong("qtde") > 0) {
                            return true;
                        }
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
            throw new Exception("Campos faltando serem informados para validação. Verifique!");
        }

        return false;
    }
}
