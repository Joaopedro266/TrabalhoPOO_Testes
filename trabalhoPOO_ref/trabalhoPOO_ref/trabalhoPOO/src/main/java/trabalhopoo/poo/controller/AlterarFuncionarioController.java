package trabalhopoo.poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import trabalhopoo.poo.dao.DepartamentoDAO;
import trabalhopoo.poo.dao.FuncionarioDAO;
import trabalhopoo.poo.model.Departamento;
import trabalhopoo.poo.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class AlterarFuncionarioController {

    @FXML
    private TextField txtIdFuncionario;

    @FXML
    private TextField txtNomeFuncionario;

    @FXML
    private ComboBox<Departamento> cmbDepartamentos;

    private final FuncionarioDAO funcionarioDAO;
    private final DepartamentoDAO departamentoDAO;

    public AlterarFuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
        this.departamentoDAO = new DepartamentoDAO();
    }

    public AlterarFuncionarioController(FuncionarioDAO funcionarioDAO, DepartamentoDAO departamentoDAO) {
        this.funcionarioDAO = funcionarioDAO;
        this.departamentoDAO = departamentoDAO;
    }

    @FXML
    public void initialize() {
        carregarDepartamentos();
    }

    private void carregarDepartamentos() {
        try {
            List<Departamento> departamentos = departamentoDAO.listarTodos();
            cmbDepartamentos.getItems().addAll(departamentos);
        } catch (SQLException e) {
            mostrarAlerta(AlertType.ERROR, "Erro ao carregar departamentos: " + e.getMessage());
        }
    }

    @FXML
    public void salvarAlteracoes() {
        if (!validarEntradas()) {
            return;
        }

        try {
            int idFuncionario = Integer.parseInt(txtIdFuncionario.getText());
            String nomeFuncionario = txtNomeFuncionario.getText();
            Departamento departamentoSelecionado = cmbDepartamentos.getValue();

            Funcionario funcionario = funcionarioDAO.buscarPorID(idFuncionario);
            if (funcionario != null) {
                funcionario.setNome(nomeFuncionario);
                funcionario.setDepartamento(departamentoSelecionado);
                funcionarioDAO.altera(funcionario);
                mostrarAlerta(AlertType.INFORMATION, "Funcionário atualizado com sucesso.");
            } else {
                mostrarAlerta(AlertType.WARNING, "Funcionário com ID " + idFuncionario + " não encontrado.");
            }
        } catch (SQLException e) {
            mostrarAlerta(AlertType.ERROR, "Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    private boolean validarEntradas() {
        if (txtIdFuncionario.getText().isEmpty() || txtNomeFuncionario.getText().isEmpty() || cmbDepartamentos.getValue() == null) {
            mostrarAlerta(AlertType.WARNING, "Todos os campos devem ser preenchidos.");
            return false;
        }
        try {
            Integer.parseInt(txtIdFuncionario.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta(AlertType.WARNING, "ID do Funcionário deve ser um número válido.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(AlertType tipo, String mensagem) {
        Alert alert = new Alert(tipo, mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
