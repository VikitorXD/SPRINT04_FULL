package org.example.models;

public class Veiculo {

    public int ID;
    public String modelo;
    public String cor;
    public int numeroEixos;
    public double carga;

    public Veiculo(int ID, String modelo, String cor, int numeroEixos, double carga) {
        this.ID = ID;
        this.modelo = modelo;
        this.cor = cor;
        this.numeroEixos = numeroEixos;
        this.carga = carga;
    }

    public Veiculo() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getNumeroEixos() {
        return numeroEixos;
    }

    public void setNumeroEixos(int numeroEixos) {
        this.numeroEixos = numeroEixos;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }
}
