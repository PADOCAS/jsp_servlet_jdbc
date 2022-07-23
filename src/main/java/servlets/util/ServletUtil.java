/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets.util;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Login;

/**
 *
 * @author lucia
 */
public class ServletUtil implements Serializable {

    private final static long serialVersionUID = 1L;

    public String getUsuarioLogado(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") != null
                && ((Login) session.getAttribute("usuario")).getLogin() != null) {
            return ((Login) session.getAttribute("usuario")).getLogin();
        }

        return null;
    }
    
}
