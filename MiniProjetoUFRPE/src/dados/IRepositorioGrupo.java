package dados;

import java.util.ArrayList;
import java.util.List;

import model.Grupo;
import model.Pessoa;

public interface IRepositorioGrupo {

    boolean salvarGrupo(Grupo grupo);
    boolean deletarGrupoPorNome(String nome);
    List<Grupo> lerGrupo();
    boolean alterarGrupo(Grupo grupo);
    public boolean removerPessoaGrupo(Pessoa pessoa, Grupo grupo);

}
