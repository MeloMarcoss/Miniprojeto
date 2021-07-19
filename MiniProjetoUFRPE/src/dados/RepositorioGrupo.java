package dados;

import model.Grupo;
import model.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controllers.Facade;

public class RepositorioGrupo implements IRepositorioGrupo {

    private List<Grupo> listaGrupos;
    private static RepositorioGrupo instance;

    private RepositorioGrupo() {
        listaGrupos = new ArrayList<Grupo>();
    }

    public static RepositorioGrupo getInstance(){
        if (instance == null){
            instance = new RepositorioGrupo();
        }
        return instance;
    }


    @Override
    public boolean salvarGrupo(Grupo grupo) {
        try {
            listaGrupos.add(grupo);
        } catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public boolean deletarGrupoPorNome(String nome) {
        for (Grupo grupo : listaGrupos){
            if (grupo.getNome().equals(nome)){
                listaGrupos.remove(grupo);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Grupo> lerGrupo() {
        return listaGrupos;
    }

    @Override
    public boolean alterarGrupo(Grupo grupo) {
        for (Grupo grupo1 : listaGrupos){
            if (grupo1.getNome().equals(grupo.getNome())){
                listaGrupos.remove(grupo1);
                listaGrupos.add(grupo);
                return true;
            }
        }
        return false;
    }

    public boolean removerPessoaGrupo(Pessoa pessoa, Grupo grupo){
        for (int i = 0 ; i < listaGrupos.size() ; i++){
            if (listaGrupos.get(i).equals(grupo)){
                listaGrupos.get(i).removerPessoa(pessoa);
                return true;
            }
        }
        return false;
    }

}
