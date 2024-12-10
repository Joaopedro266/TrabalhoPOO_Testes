import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDAOTest {

    @Test
    public void testListarTodos() throws SQLException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        List<Funcionario> funcionarios = funcionarioDAO.listarTodos();

        assertNotNull(funcionarios, "A lista de funcionários não pode ser nula");
        assertTrue(funcionarios.size() > 0, "Deve haver pelo menos um funcionário na lista");
    }

    @Test
    public void testInserirEExcluir() throws SQLException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Departamento departamento = new Departamento();
        departamento.setDepartamentoID(1); // Supondo que o departamento 1 exista no banco
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setDepartamento(departamento);

        funcionarioDAO.inserir(funcionario);
        int id = funcionario.getFuncionarioID();

        funcionarioDAO.excluir(id);

        Funcionario funcionarioBuscado = funcionarioDAO.buscarPorID(id);
        assertNull(funcionarioBuscado, "O funcionário deve ser excluído e não encontrado pelo ID");
    }
}