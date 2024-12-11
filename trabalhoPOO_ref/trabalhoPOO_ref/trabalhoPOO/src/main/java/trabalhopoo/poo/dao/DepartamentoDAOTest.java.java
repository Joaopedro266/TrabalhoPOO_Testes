package trabalhopoo.poo.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.List;

public class DepartamentoDAOTest {

    @Test
    public void testListarTodos() throws SQLException {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        List<Departamento> departamentos = departamentoDAO.listarTodos();

        assertNotNull(departamentos, "A lista de departamentos não pode ser nula");
        assertTrue(departamentos.size() > 0, "Deve haver pelo menos um departamento na lista");
    }

    @Test
    public void testInserirEBuscar() throws SQLException {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        Departamento departamento = new Departamento();
        departamento.setNome("TI");

        departamentoDAO.inserir(departamento);

        Departamento departamentoBuscado = departamentoDAO.buscarPorID(departamento.getDepartamentoID());
        assertNotNull(departamentoBuscado, "O departamento deve ser inserido e encontrado pelo ID");
        assertEquals("TI", departamentoBuscado.getNome(), "O nome do departamento deve ser 'TI'");
    }
}
