package org.example.models;

public class Veiculo {

    private int ID;
    private String modelo;
    private String placa;
    private int numeroEixos;
    private double carga;

    public Veiculo(int ID, String modelo, int numeroEixos, double carga, String placa) {
        this.ID = ID;
        this.modelo = modelo;
        this.numeroEixos = numeroEixos;
        this.carga = carga;
        this.placa = placa;
    }

    public Veiculo() {

    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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
