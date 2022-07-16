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

    private void salvarTelefone(Telefone telefoneASalvar) throws Exception {
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
}
