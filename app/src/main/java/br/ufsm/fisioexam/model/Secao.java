package br.ufsm.fisioexam.model;

public class Secao {
    private int id;
    private String nome;
    private boolean preenchida;

    public Secao(int id, String nome, boolean preenchida) {
        this.id = id;
        this.nome = nome;
        this.preenchida = preenchida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPreenchida() {
        return preenchida;
    }

    public void setPreenchida(boolean preenchida) {
        this.preenchida = preenchida;
    }
}
