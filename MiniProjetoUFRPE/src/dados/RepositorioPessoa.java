package dados;

import com.sun.jdi.event.ExceptionEvent;

import model.Pessoa;
import model.Presente;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPessoa implements IRepositorioPessoa {

    private List<Pessoa> listaPessoas;
    private static RepositorioPessoa instance;

    private RepositorioPessoa(){
        listaPessoas = new ArrayList<Pessoa>();
    }

    public static RepositorioPessoa getInstance() {
        if (instance == null){
            instance = new RepositorioPessoa();
        }
        return instance;
    }

    @Override
    public boolean salvarPessoa(Pessoa pessoa) {
        try {
            listaPessoas.add(pessoa);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deletarPessoaPorApelido(String apelido) {
        for (Pessoa pessoa : listaPessoas){
            if (pessoa.getApelido().equals(apelido)){
                listaPessoas.remove(pessoa);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Pessoa> lerPessoa() {
        return listaPessoas;
    }

    @Override
    public boolean alterarPessoa(Pessoa pessoa) {
        for (Pessoa pessoa1 : listaPessoas){
            if (pessoa.getApelido().equals(pessoa1.getApelido())){
                listaPessoas.remove(pessoa1);
                listaPessoas.add(pessoa);
                return true;
            }
        }
        return false;
    }

    @Override
    public String lerApelidoPessoa(Pessoa pessoa){
        return pessoa.getApelido();
    }

    @Override
    public String lerNomeCompletoPessoa(Pessoa pessoa){
        return pessoa.getNomeCompleto();
    }

    @Override
    public boolean removerPresente(Pessoa pessoa, Presente presente) {
        for(int i = 0 ; i < listaPessoas.size() ; i ++){
            if (listaPessoas.get(i).getApelido().equals(pessoa.getApelido())){
                pessoa.removerPresente(presente);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean adicionarPresente(Pessoa pessoa, Presente presente) {
        for(int i = 0 ; i < listaPessoas.size() ; i ++){
            if (listaPessoas.get(i).getApelido().equals(pessoa.getApelido())){
                pessoa.adicionarPresente(presente);
                return true;
            }
        }
        return false;
    }

}
