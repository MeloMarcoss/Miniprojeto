package controllers;

import javafx.collections.ObservableList;
import model.Grupo;
import model.Pessoa;
import model.Presente;

import java.time.LocalDate;
import java.util.List;

public class Facade {

    private static Facade instance;
    Negocios negocios;

    private Facade () {
        negocios = new Negocios();
    }

    public static Facade getInstance(){
        if (instance == null){
            instance = new Facade();
        }
        return instance;
    }


    public List<Pessoa> listarPessoas(){
        return negocios.listarPessoas();
    }

    public List<Presente> listarPresentes(){
        return negocios.listarPresentes();
    }

    public List<Grupo> listarGrupos(){
        return negocios.listarGrupos();
    }

    public int salvarPessoa(String nome, String apelido, String senha, String confSenha){
        int codigoMensagem = negocios.salvarPessoa(nome, apelido, senha, confSenha);
        return codigoMensagem;
    }

    public int adicionarPessoaGrupo(Pessoa pessoa, Grupo grupo){
        int codigoMensagem = negocios.adicionarPessoaGrupo(pessoa, grupo);
        return codigoMensagem;
    }

    public boolean removerPessoaGrupo(Pessoa pessoa, Grupo grupo){
        boolean codigoMensagem = negocios.removerPessoaGrupo(pessoa, grupo);
        return codigoMensagem;
    }

    public ObservableList<Pessoa> listarPessoasGrupo(Grupo grupo){
        return negocios.listarPessoasGrupo(grupo);
    }

    public int salvarGrupo(String nome, LocalDate data){
        return negocios.salvarGrupo(nome, data);
    }

    public void adicionarPresentePessoa(Presente presente, Pessoa pessoa){
        negocios.adicionarPresentePessoa(presente, pessoa);
    }

    public void removerPresentePessoa(Presente presente, Pessoa pessoa){
        negocios.removerPresentePessoa(presente, pessoa);
    }

    public ObservableList<Presente> listarPresentesPessoa(Pessoa pessoa){
        return negocios.lerPresentesPessoa(pessoa);
    }

    public int salvarPresente(String descricao, String categoria, String preco){
        return negocios.salvarPresente(descricao, categoria, preco);
    }

    public Pessoa consultarAmigoSecreto(Grupo grupo, Pessoa pessoa, String senha) {
        return consultarAmigoSecreto (grupo, pessoa, senha);
    }

    public int sortear(Grupo grupo){
        return negocios.sortear(grupo);
    }
}
