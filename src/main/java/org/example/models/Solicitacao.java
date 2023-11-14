package org.example.models;

public class Solicitacao {

    private int idsolit;
    private String solitUsuario;

    public Solicitacao(int idsolit, String solitUsuario) {
        this.idsolit = idsolit;
        this.solitUsuario = solitUsuario;
    }

    public Solicitacao() {
    }

    public int getIdsolit() {
        return idsolit;
    }

    public void setIdsolit(int idsolit) {
        this.idsolit = idsolit;
    }

    public String getSolitUsuario() {
        return solitUsuario;
    }

    public void setSolitUsuario(String solitUsuario) {
        this.solitUsuario = solitUsuario;
    }
}
