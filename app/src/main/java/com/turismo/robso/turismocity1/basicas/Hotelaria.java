package com.turismo.robso.turismocity1.basicas;

/**
 * Created by GabrielDev on 09/06/2018.
 */

public class Hotelaria {

    private int idHot;
    private String nomeHot;
    private String cnpj;
    private String email;
    private  String fone;
    private  Cidade cidade;

    public Hotelaria(){

    }

    public Hotelaria(Cidade cidade) {
        this.cidade = new Cidade();
    }

    public int getIdHot() {
        return idHot;
    }

    public void setIdHot(int idHot) {
        this.idHot = idHot;
    }

    public String getNomeHot() {
        return nomeHot;
    }

    public void setNomeHot(String nomeHot) {
        this.nomeHot = nomeHot;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
