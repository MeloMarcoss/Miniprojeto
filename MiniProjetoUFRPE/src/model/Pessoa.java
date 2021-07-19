package model;

import java.util.ArrayList;

public class Pessoa {

    private String nomeCompleto;
    private String apelido;
    private String senha;
    private ArrayList<Presente> presentes;

    public Pessoa(String nomeCompleto, String apelido, String senha) {
        setNomeCompleto(nomeCompleto);
        setApelido(apelido);
        setSenha(senha);
        presentes = new ArrayList<Presente>();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Presente> getPresentes() {
        return presentes;
    }

    public void setPresentes(ArrayList<Presente> presentes) {
        this.presentes = presentes;
    }

    public void adicionarPresente(Presente presente){
        this.presentes.add(presente);
    }

    public void removerPresente(Presente presente){
        if (this.presentes.contains(presente)){
            this.presentes.remove(presente);
        }
    }

    @Override
    public String toString() {
        return  apelido;
    }
}
