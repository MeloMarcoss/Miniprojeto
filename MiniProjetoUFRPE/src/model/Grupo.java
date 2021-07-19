package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Grupo {

    private String nome;
    private LocalDate dataSorteio;
    private ArrayList<Pessoa> pessoas;
    private boolean sorteado;

    public Grupo(String nome, LocalDate dataSorteio) {
        this.nome = nome;
        this.dataSorteio = dataSorteio;
        this.pessoas = new ArrayList<Pessoa>();
        this.sorteado = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(LocalDate dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public boolean isSorteado() {
        return sorteado;
    }

    public void setSorteado(boolean sorteado) {
        this.sorteado = sorteado;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void adicionarPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    public void removerPessoa(Pessoa pessoa){
        if(this.pessoas.contains(pessoa)){
            this.pessoas.remove(pessoa);
        }
    }

    public void listarPessoas(){
        for (int i = 0 ; i < getPessoas().size() ; i++){
            System.out.println(getPessoas().get(i).getApelido());
        }
    }

    public void listarAmigoSecreto(){
        for (int i = 0 ; i < getPessoas().size() ; i++){
            if (i + 1 == getPessoas().size()){
                System.out.println("O amigo secreto de " + getPessoas().get(i).getApelido() + " é " + getPessoas().get(0).getApelido());
            }
            else {
                System.out.println("O amigo secreto de " + getPessoas().get(i).getApelido() + " é " + getPessoas().get(i + 1).getApelido());
            }
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}
