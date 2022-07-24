/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lucia
 */
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;

    private String senha;

    //Transient
    private String confirmSenha;

    private String nome;

    private String email;

    private Boolean admin;

    private String usuarioLogin;

    private String perfil;

    private String sexo;

    private String fotoUser;

    private String extensaoFotoUser;

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private String numero;

    private java.util.Date dataNascimento;

    private Double rendaMensal;

    private List<Telefone> listTelefone;

    public Login() {
        this.rendaMensal = Double.valueOf(0);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmSenha() {
        return confirmSenha;
    }

    public void setConfirmSenha(String confirmSenha) {
        this.confirmSenha = confirmSenha;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSexo() {
        return sexo;
    }

    public List<Telefone> getListTelefone() {
        return listTelefone;
    }

    public void setListTelefone(List<Telefone> listTelefone) {
        this.listTelefone = listTelefone;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFotoUser() {
        return fotoUser;
    }

    public void setFotoUser(String fotoUser) {
        this.fotoUser = fotoUser;
    }

    public String getExtensaoFotoUser() {
        return extensaoFotoUser;
    }

    public void setExtensaoFotoUser(String extensaoFotoUser) {
        this.extensaoFotoUser = extensaoFotoUser;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public java.util.Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(java.util.Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Login other = (Login) obj;
        return Objects.equals(this.login, other.login);
    }

    @Override
    public String toString() {
        return "Login{" + "login=" + login + ", senha=" + senha + ", confirmSenha=" + confirmSenha + ", nome=" + nome + ", email=" + email + ", admin=" + admin + ", usuarioLogin=" + usuarioLogin + ", perfil=" + perfil + ", sexo=" + sexo + ", fotoUser=" + fotoUser + ", extensaoFotoUser=" + extensaoFotoUser + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", numero=" + numero + ", dataNascimento=" + dataNascimento + ", rendaMensal=" + rendaMensal + '}';
    }

}
