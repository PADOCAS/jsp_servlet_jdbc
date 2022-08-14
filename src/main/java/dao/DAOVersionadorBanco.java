/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.SingleConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucia
 */
public class DAOVersionadorBanco implements Serializable {

    private static final long serialVersionUID = 1L;

    private Connection connection;

    public DAOVersionadorBanco() {
        connection = SingleConnection.getConnection();
    }

    public Boolean getArquivoSqlRodado(String nome_arquivo) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(*) as qtde FROM public.versao WHERE arquivo_sql = ?;");

        if (nome_arquivo != null
                && !nome_arquivo.isEmpty()) {
            try (PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, nome_arquivo);

                try (ResultSet rsSel = pstaSel.executeQuery();) {
                    if (rsSel.next()) {
                        if (rsSel.getLong("qtde") > 0) {
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
            throw new Exception("Nome do arquivo nao foi recebido!! Verifique!");
        }

        return false;
    }

    public void updateVersaoArquivoRodado(String nome_arquivo) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO public.versao (arquivo_sql, data_exec) VALUES (?, now());");

        if (nome_arquivo != null
                && !nome_arquivo.isEmpty()) {
            try (PreparedStatement pstaInsert = connection.prepareStatement(sql.toString());) {
                pstaInsert.setString(1, nome_arquivo);

                pstaInsert.executeUpdate();
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
            throw new Exception("Nome do arquivo nao foi recebido!! Verifique!");
        }
    }
}
