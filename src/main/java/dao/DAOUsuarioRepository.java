/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.SingleConnection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Login;
import model.Telefone;
import modeldto.GraficoSalarioUsuarioDTO;

/**
 *
 * @author lucia
 */
public class DAOUsuarioRepository {

    private Connection connection;

    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

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

            sqlInsert.append("INSERT INTO public.login (login, senha, email, nome, usuario_login, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero, foto_user, extensao_foto_user, data_nascimento, renda_mensal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            sqlUpdate.append("UPDATE public.login SET senha = ?, email = ?, nome = ?, usuario_login = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ?, numero = ?, foto_user = ?, extensao_foto_user = ?, data_nascimento = ?, renda_mensal = ? WHERE login = ?;");
        } else {
            sqlInsert.append("INSERT INTO public.login (login, senha, email, nome, usuario_login, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero, data_nascimento, renda_mensal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            sqlUpdate.append("UPDATE public.login SET senha = ?, email = ?, nome = ?, usuario_login = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ?, numero = ?, data_nascimento = ?, renda_mensal = ? WHERE login = ?;");
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
                && modelLogin.getNumero() != null
                && modelLogin.getRendaMensal() != null) {
            if (update == null
                    || !update) {
                //INSERT:
                try ( PreparedStatement pstaInsert = connection.prepareStatement(sqlInsert.toString());) {
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

                        if (modelLogin.getDataNascimento() != null) {
                            java.sql.Date date = new java.sql.Date(modelLogin.getDataNascimento().getTime());
                            pstaInsert.setDate(16, date);
                        } else {
                            pstaInsert.setNull(16, Types.DATE);
                        }

                        pstaInsert.setDouble(17, modelLogin.getRendaMensal());
                    } else {
                        if (modelLogin.getDataNascimento() != null) {
                            java.sql.Date date = new java.sql.Date(modelLogin.getDataNascimento().getTime());
                            pstaInsert.setDate(14, date);
                        } else {
                            pstaInsert.setNull(14, Types.DATE);
                        }

                        pstaInsert.setDouble(15, modelLogin.getRendaMensal());
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
                try ( PreparedStatement pstaUpdate = connection.prepareStatement(sqlUpdate.toString());) {
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

                        if (modelLogin.getDataNascimento() != null) {
                            java.sql.Date date = new java.sql.Date(modelLogin.getDataNascimento().getTime());
                            pstaUpdate.setDate(15, date);
                        } else {
                            pstaUpdate.setNull(15, Types.DATE);
                        }

                        pstaUpdate.setDouble(16, modelLogin.getRendaMensal());
                        pstaUpdate.setString(17, modelLogin.getLogin());
                    } else {
                        if (modelLogin.getDataNascimento() != null) {
                            java.sql.Date date = new java.sql.Date(modelLogin.getDataNascimento().getTime());
                            pstaUpdate.setDate(13, date);
                        } else {
                            pstaUpdate.setNull(13, Types.DATE);
                        }

                        pstaUpdate.setDouble(14, modelLogin.getRendaMensal());
                        pstaUpdate.setString(15, modelLogin.getLogin());
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
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, login);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
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
                        modelLogin.setDataNascimento(rsSel.getObject("data_nascimento") != null ? rsSel.getDate("data_nascimento") : null);
                        modelLogin.setRendaMensal(rsSel.getDouble("renda_mensal"));
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
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, login);
                pstaSel.setString(2, usuarioLogado);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
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
                        modelLogin.setDataNascimento(rsSel.getObject("data_nascimento") != null ? rsSel.getDate("data_nascimento") : null);
                        modelLogin.setRendaMensal(rsSel.getDouble("renda_mensal"));
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
        sql.append("SELECT * FROM public.login WHERE lower(nome) LIKE ? AND admin is false AND usuario_login = ? ORDER BY login LIMIT 5;");

        if (nome != null
                && !nome.isEmpty()
                && usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, "%" + nome.toLowerCase() + "%");
                pstaSel.setString(2, usuarioLogado);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
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
                        modelLogin.setDataNascimento(rsSel.getObject("data_nascimento") != null ? rsSel.getDate("data_nascimento") : null);
                        modelLogin.setRendaMensal(rsSel.getDouble("renda_mensal"));

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

    public Long consultarUsuarioPorNomeTotalRegistros(String nome, String usuarioLogado) throws Exception {
        Long totalReg = 0L;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(*) as total FROM public.login WHERE lower(nome) LIKE ? AND admin is false AND usuario_login = ?;");

        if (nome != null
                && !nome.isEmpty()
                && usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, "%" + nome.toLowerCase() + "%");
                pstaSel.setString(2, usuarioLogado);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
                    if (rsSel.next()) {
                        Long totalSel = rsSel.getLong("total");
                        totalReg = totalSel;
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

        return totalReg;
    }

    public Long consultarUsuarioPorNomeTotalPorPagina(String nome, String usuarioLogado) throws Exception {
        Long numPagina = 1L;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(*) as total FROM public.login WHERE lower(nome) LIKE ? AND admin is false AND usuario_login = ?;");

        if (nome != null
                && !nome.isEmpty()
                && usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, "%" + nome.toLowerCase() + "%");
                pstaSel.setString(2, usuarioLogado);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
                    if (rsSel.next()) {
                        Long totalSel = rsSel.getLong("total");
                        //Paginacao de 5 em 5:
                        Double limit = 5D;
                        Double pagina = totalSel.doubleValue() / limit;

                        BigDecimal paginacaoAprox = BigDecimal.valueOf(pagina).setScale(0, RoundingMode.HALF_UP);
                        if (paginacaoAprox.compareTo(BigDecimal.valueOf(pagina)) > 0) {
                            pagina = paginacaoAprox.doubleValue();
                        } else if (paginacaoAprox.compareTo(BigDecimal.valueOf(pagina)) < 0) {
                            pagina = (paginacaoAprox.add(BigDecimal.ONE)).doubleValue();
                        }

                        numPagina = pagina.longValue();
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

        return numPagina;
    }

    public List<Login> consultarUsuarioPorNomePaginada(String nome, String usuarioLogado, Integer offset) throws Exception {
        List<Login> listModelLogin = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM public.login WHERE lower(nome) LIKE ? AND admin is false AND usuario_login = ? ORDER BY login offset ? LIMIT 5;");

        if (nome != null
                && !nome.isEmpty()
                && usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, "%" + nome.toLowerCase() + "%");
                pstaSel.setString(2, usuarioLogado);
                pstaSel.setInt(3, offset == null ? 0 : offset);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
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
                        modelLogin.setDataNascimento(rsSel.getObject("data_nascimento") != null ? rsSel.getDate("data_nascimento") : null);
                        modelLogin.setRendaMensal(rsSel.getDouble("renda_mensal"));

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

    public List<Login> consultarTodosUsuariosPaginada(String usuarioLogado, Integer offset) throws Exception {
        List<Login> listModelLogin = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        //Paginar com limit 5 registros: (para não carregar tudo de uma vez)
        sql.append("SELECT * FROM public.login WHERE admin is false AND usuario_login = ? ORDER BY login offset ? LIMIT 5;");

        if (usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, usuarioLogado);
                pstaSel.setInt(2, offset == null ? 0 : offset);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
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
                        modelLogin.setDataNascimento(rsSel.getObject("data_nascimento") != null ? rsSel.getDate("data_nascimento") : null);
                        modelLogin.setRendaMensal(rsSel.getDouble("renda_mensal"));

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

    public List<Login> consultarTodosUsuariosRel(String usuarioLogado, Map<String, Object> param) throws Exception {
        List<Login> listModelLogin = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        Boolean filterDataNasc = false;

        if (param != null
                && param.get("dataInicial") != null
                && !((String) param.get("dataInicial")).isEmpty()
                && param.get("dataFinal") != null
                && !((String) param.get("dataFinal")).isEmpty()) {
            filterDataNasc = true;
        }

        if (!filterDataNasc) {
            sql.append("SELECT * FROM public.login WHERE admin is false AND usuario_login = ? ORDER BY login;");
        } else {
            sql.append("SELECT * FROM public.login WHERE admin is false AND usuario_login = ? AND data_nascimento BETWEEN TO_DATE(?, 'yyyy-mm-dd') AND TO_DATE(?, 'yyyy-mm-dd') ORDER BY login;");
        }

        if (usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, usuarioLogado);
                if (filterDataNasc
                        && param != null) {
                    pstaSel.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse((String) param.get("dataInicial")))));
                    pstaSel.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse((String) param.get("dataFinal")))));
                }

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
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
                        modelLogin.setDataNascimento(rsSel.getObject("data_nascimento") != null ? rsSel.getDate("data_nascimento") : null);
                        modelLogin.setRendaMensal(rsSel.getDouble("renda_mensal"));

                        //telefones:
                        modelLogin.setListTelefone(getListConsultaTelefone(modelLogin));

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

    public GraficoSalarioUsuarioDTO getListGraficoMediaSalarioPorTipo(String usuarioLogado, Map<String, Object> param) throws Exception {
        GraficoSalarioUsuarioDTO graficoSalarioUsuarioDto = new GraficoSalarioUsuarioDTO();

        Boolean filterDataNasc = false;

        if (param != null
                && param.get("dataInicial") != null
                && !((String) param.get("dataInicial")).isEmpty()
                && param.get("dataFinal") != null
                && !((String) param.get("dataFinal")).isEmpty()) {
            filterDataNasc = true;
        }

        StringBuilder sql = new StringBuilder();
        if (!filterDataNasc) {
            sql.append("select perfil, ROUND(avg(renda_mensal),2) as media_salarial from public.login where usuario_login = ? group by perfil order by perfil;");
        } else {
            sql.append("select perfil, ROUND(avg(renda_mensal),2) as media_salarial from public.login where usuario_login = ? and data_nascimento BETWEEN TO_DATE(?, 'yyyy-mm-dd') AND TO_DATE(?, 'yyyy-mm-dd') group by perfil order by perfil;");
        }

        if (usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, usuarioLogado);

                if (filterDataNasc
                        && param != null) {
                    pstaSel.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse((String) param.get("dataInicial")))));
                    pstaSel.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse((String) param.get("dataFinal")))));
                }

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
                    List<String> listPerfil = new ArrayList<>();
                    List<Double> listSalario = new ArrayList<>();

                    while (rsSel.next()) {
                        listPerfil.add(rsSel.getString("perfil"));
                        listSalario.add(rsSel.getDouble("media_salarial"));
                    }

                    graficoSalarioUsuarioDto.setListPerfil(listPerfil);
                    graficoSalarioUsuarioDto.setListSalario(listSalario);
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

        return graficoSalarioUsuarioDto;
    }

    public List<Telefone> getListConsultaTelefone(Login login) throws Exception {
        List<Telefone> listTelefone = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM public.telefone WHERE login = ?;");

        if (login != null
                && login.getLogin() != null) {
            try ( PreparedStatement pstaConsulta = connection.prepareStatement(sql.toString());) {
                pstaConsulta.setString(1, login.getLogin());

                try ( ResultSet rsConsulta = pstaConsulta.executeQuery();) {
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

    public List<Login> consultarTodosUsuarios(String usuarioLogado) throws Exception {
        List<Login> listModelLogin = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        //Paginar com limit 5 registros: (para não carregar tudo de uma vez)
        sql.append("SELECT * FROM public.login WHERE admin is false AND usuario_login = ? ORDER BY login;");

        if (usuarioLogado != null) {
            try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
                pstaSel.setString(1, usuarioLogado);

                try ( ResultSet rsSel = pstaSel.executeQuery();) {
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
                        modelLogin.setDataNascimento(rsSel.getObject("data_nascimento") != null ? rsSel.getDate("data_nascimento") : null);
                        modelLogin.setRendaMensal(rsSel.getDouble("renda_mensal"));

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

        StringBuilder sqlTel = new StringBuilder();
        sqlTel.append(" DELETE FROM public.telefone WHERE login = ?;");

        if (login != null) {
            try ( PreparedStatement pstaDelTel = connection.prepareStatement(sqlTel.toString());  PreparedStatement pstaDel = connection.prepareStatement(sql.toString());) {
                pstaDelTel.setString(1, login);
                pstaDelTel.executeUpdate();

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

    public Long getTotalPaginasConsultaTodosUsuarios(String usuarioLogado) throws Exception {
        Long numPagina = 1L;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(*) as total FROM public.login WHERE admin is false AND usuario_login = ?;");

        try ( PreparedStatement pstaSel = connection.prepareStatement(sql.toString());) {
            pstaSel.setString(1, usuarioLogado);

            try ( ResultSet rsSel = pstaSel.executeQuery();) {
                if (rsSel.next()) {
                    Long totalSel = rsSel.getLong("total");
                    //Paginacao de 5 em 5:
                    Double limit = 5D;
                    Double pagina = totalSel.doubleValue() / limit;

                    BigDecimal paginacaoAprox = BigDecimal.valueOf(pagina).setScale(0, RoundingMode.HALF_UP);
                    if (paginacaoAprox.compareTo(BigDecimal.valueOf(pagina)) > 0) {
                        pagina = paginacaoAprox.doubleValue();
                    } else if (paginacaoAprox.compareTo(BigDecimal.valueOf(pagina)) < 0) {
                        pagina = (paginacaoAprox.add(BigDecimal.ONE)).doubleValue();
                    }

                    numPagina = pagina.longValue();
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

        return numPagina;
    }
}
