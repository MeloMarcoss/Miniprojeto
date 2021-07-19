package dados;

import java.util.List;

import model.Presente;

public interface IRepositorioPresente {

    boolean salvar(Presente presente);
    boolean deletar(String descricao);
    List<Presente> ler();
    boolean alterar(Presente presente);

}
