/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author lucia
 */
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    private Login login;

    private Long id;

    private String numero;

    private Login usuarioLogin;

    public Telefone() {
    }

    public Telefone(Login login, Long id) {
        this.login = login;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Login usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.login);
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Telefone other = (Telefone) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Telefone{" + "login=" + login + ", id=" + id + '}';
    }

}
