package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Grupo;
import model.Pessoa;
import model.Presente;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import dados.*;

public class Negocios {

    private IRepositorioGrupo iRepositorioGrupo;
    private IRepositorioPessoa iRepositorioPessoa;
    private IRepositorioPresente iRepositorioPresente;

    public Negocios() {
        iRepositorioGrupo = RepositorioGrupo.getInstance();
        iRepositorioPessoa = RepositorioPessoa.getInstance();
        iRepositorioPresente = RepositorioPresente.getInstance();
    }

    List<Pessoa> listarPessoas(){
        return this.iRepositorioPessoa.lerPessoa();
    }

    List<Presente> listarPresentes(){
        return this.iRepositorioPresente.ler();
    }

    List<Grupo> listarGrupos(){
        return this.iRepositorioGrupo.lerGrupo();
    }

    int salvarPessoa(String nome, String apelido, String senha, String confSenha){
        if (nome.equals("") || apelido.equals("") || senha.equals("") || confSenha.equals("")){
            return 4;
        } else {
            if (senha.equals(confSenha)){
                boolean existeIgual = false;

                for (int i = 0 ; i < listarPessoas().size() ; i ++){
                    if (listarPessoas().get(i).getApelido().equals(apelido)){
                        existeIgual = true;
                    }
                }

                if (!existeIgual){
                   
                    Pessoa pessoa = new Pessoa(nome, apelido, senha);

                    iRepositorioPessoa.salvarPessoa(pessoa);

                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 3;
            }
        }
    }

    int adicionarPessoaGrupo (Pessoa p, Grupo g) {
        if(p != null && g != null && !g.getPessoas().contains(p)){

            g.adicionarPessoa(p);
            g.setSorteado(false);

            return 1;
        }
        else if (g.getPessoas().contains(p)){
            return 2;
        }
        else if (p == null){
            return 3;
        }
        else if (g == null){
            return 4;
        } else{
            return 5;
        }
    }

    public void removerPresentePessoa(Presente presente, Pessoa pessoa){
        iRepositorioPessoa.removerPresente(pessoa, presente);
    }

    public void adicionarPresentePessoa(Presente presente, Pessoa pessoa){
        iRepositorioPessoa.adicionarPresente(pessoa, presente);
    }

    ObservableList<Presente> lerPresentesPessoa(Pessoa pessoa){
        for (int i = 0 ; i < listarPessoas().size() ; i++){
            if (listarPessoas().get(i).equals(pessoa)){
                return FXCollections.observableArrayList(listarPessoas().get(i).getPresentes());
            }
        }
        return null;
    }

    public boolean removerPessoaGrupo(Pessoa pessoa, Grupo grupo){
        return iRepositorioGrupo.removerPessoaGrupo(pessoa, grupo);
    }

    public int salvarGrupo (String nome, LocalDate data) {
        if (nome.equals("") && data == null){
            return 2;
        }
        else if (data == null) {
            return 3;
        }
        else if (nome.equals("")){
            return 4;
        } else {
            boolean existe = false;
            for (int i = 0 ; i < listarGrupos().size() ; i ++){
                if (listarGrupos().get(i).getNome().equals(nome)){
                    existe = true;
                }
            }
            if (existe){
                return 5;
            }
            else {
                Grupo grupo = new Grupo(nome, data);
                iRepositorioGrupo.salvarGrupo(grupo);
                return 1;
            }
        }
    }

    public ObservableList<Pessoa> listarPessoasGrupo(Grupo grupo) {
        for (int i = 0 ; i < listarGrupos().size() ; i++){
            if (listarGrupos().get(i).equals(grupo)){
                return FXCollections.observableArrayList(listarGrupos().get(i).getPessoas());
            }
        }
        return null;
    }

    public int salvarPresente (String descricao, String categoria, String preco) {
        if (descricao.equals("")){
            return 2;
        }
        else if (preco.equals("")){
            return 3;
        }
        else if (categoria.equals("")) {
            return 4;
        }
        else {

            float precoNumero = Float.parseFloat(preco);

            Presente presente = new Presente(descricao, categoria, precoNumero);

            iRepositorioPresente.salvar(presente);

            return 1;

        }
    }



    public Pessoa consultarAmigoSecreto (Grupo grupo, Pessoa pessoa, String senha) {
        if (grupo.getPessoas().indexOf(pessoa) == grupo.getPessoas().size() - 1){

            return grupo.getPessoas().get(0);

        } else{

            return grupo.getPessoas().get(grupo.getPessoas().indexOf(pessoa) + 1);

        }
    }

    public int sortear (Grupo grupo) {

        if (grupo == null){
            return 2;
        }
        else if (grupo.isSorteado()){
            return 3;
        }
        else {
            if (grupo.getDataSorteio().isAfter(LocalDate.now())){
                return 4;
            }
            else{

                Collections.shuffle(grupo.getPessoas());

                grupo.setSorteado(true);

                iRepositorioGrupo.alterarGrupo(grupo);

                grupo.listarPessoas();
                grupo.listarAmigoSecreto();

                return 1;

            }
        }
    }

}
