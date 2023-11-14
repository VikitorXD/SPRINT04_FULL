package org.example.models;

public class Endereco {
    private int idendereco;
    private String nmrua;
    private String nrcasa;
    private String cep;

    public Endereco(int idendereco, String nmrua, String nrcasa, String cep) {
        this.idendereco = idendereco;
        this.nmrua = nmrua;
        this.nrcasa = nrcasa;
        this.cep = cep;
    }

    public Endereco() {
    }

    public int getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(int idendereco) {
        this.idendereco = idendereco;
    }

    public String getNmrua() {
        return nmrua;
    }

    public void setNmrua(String nmrua) {
        this.nmrua = nmrua;
    }

    public String getNrcasa() {
        return nrcasa;
    }

    public void setNrcasa(String nrcasa) {
        this.nrcasa = nrcasa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
