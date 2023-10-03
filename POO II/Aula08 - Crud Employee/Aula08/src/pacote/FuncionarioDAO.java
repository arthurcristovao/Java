package pacote;

import java.util.List;

public interface FuncionarioDAO {
    void adicionarFuncionario(Funcionario funcionario);
    void atualizarFuncionario(Funcionario funcionario);
    void removerFuncionario(int id);
    Funcionario buscarFuncionarioPorId(int id);
    List<Funcionario> listarFuncionarios();
}
