package trabalhopoo.poo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import trabalhopoo.poo.dao.DepartamentoDAO;
import trabalhopoo.poo.dao.FuncionarioDAO;
import trabalhopoo.poo.model.Departamento;
import trabalhopoo.poo.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class IncluiFuncionarioController {

    @FXML
    private TextField txtNomeFunc;

    @FXML
    private ComboBox<Departamento> comboDepartamento;

    @FXML
    private Button btnConfirmaFunc;

    @FXML
    private Button btnVoltarFunc;

    @FXML
    public void initialize() {
        carregarDepartamentos();
    }

    private void carregarDepartamentos() {
        DepartamentoDAO depDAO = new DepartamentoDAO();
        try {
            List<Departamento> departamentos = depDAO.listarTodos();
            comboDepartamento.getItems().addAll(departamentos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnConfirmaFuncOnAction(ActionEvent event) {
        // Obtém o nome do campo de texto
        String nome = txtNomeFunc.getText().trim();  // Removendo espaços em branco ao redor

        // Verifica se o nome está vazio
        if (nome.isEmpty()) {
            // Exibe uma mensagem de erro se o nome não foi preenchido
            Alert alert = new Alert(Alert.AlertType.ERROR, "O nome do funcionário não pode estar vazio.");
            alert.show();
            return;  // Interrompe a execução do método para não continuar com o cadastro
        }

        // Cria o objeto funcionário e define o nome
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setDepartamento(comboDepartamento.getValue());

        // Tenta inserir o funcionário no banco de dados
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        try {
            funcDAO.inserir(funcionario);
            // Exibe uma mensagem de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Funcionário cadastrado com sucesso!");
            alert.show();
        } catch (SQLException e) {
            // Exibe uma mensagem de erro caso ocorra uma falha no banco de dados
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao cadastrar funcionário.");
            alert.show();
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVoltarFuncOnAction(ActionEvent event) {
        // Fecha a tela atual
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }
}
