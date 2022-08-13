/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeldto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lucia
 */
public class GraficoSalarioUsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> listPerfil = new ArrayList<>();

    private List<Double> listSalario = new ArrayList<>();

    public GraficoSalarioUsuarioDTO() {
    }

    public List<String> getListPerfil() {
        return listPerfil;
    }

    public void setListPerfil(List<String> listPerfil) {
        this.listPerfil = listPerfil;
    }

    public List<Double> getListSalario() {
        return listSalario;
    }

    public void setListSalario(List<Double> listSalario) {
        this.listSalario = listSalario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.listPerfil);
        hash = 19 * hash + Objects.hashCode(this.listSalario);
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
        final GraficoSalarioUsuarioDTO other = (GraficoSalarioUsuarioDTO) obj;
        if (!Objects.equals(this.listPerfil, other.listPerfil)) {
            return false;
        }
        return Objects.equals(this.listSalario, other.listSalario);
    }

    @Override
    public String toString() {
        return "GraficoSalarioUsuarioDTO{" + "listPerfil=" + listPerfil + ", listSalario=" + listSalario + '}';
    }

}
