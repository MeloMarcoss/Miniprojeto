package dados;

import java.util.ArrayList;
import java.util.List;

import model.Presente;

public class RepositorioPresente implements IRepositorioPresente {

    private List<Presente> listaPresentes;
    private static RepositorioPresente instance;

    private RepositorioPresente () {
        listaPresentes = new ArrayList<Presente>();
    }

    public static RepositorioPresente getInstance(){
        if (instance == null){
            instance = new RepositorioPresente();
        }
        return instance;
    }

    @Override
    public boolean salvar(Presente presente) {
        try {
            listaPresentes.add(presente);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(String descricao) {
        for(int i = 0; i < listaPresentes.size() ; i ++){
            if (listaPresentes.get(i).getDescricao().equals(descricao)){
                listaPresentes.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Presente> ler() {
        return listaPresentes;
    }

    @Override
    public boolean alterar(Presente presente) {
        return false;
    }
}
