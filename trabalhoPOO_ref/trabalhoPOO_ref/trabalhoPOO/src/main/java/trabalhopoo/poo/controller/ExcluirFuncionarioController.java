package trabalhopoo.poo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import trabalhopoo.poo.dao.FuncionarioDAO;

import java.sql.SQLException;

public class ExcluirFuncionarioController {

    @FXML
    private TextField txtIdFuncionario;

    private final FuncionarioDAO funcionarioDAO;

    public ExcluirFuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public ExcluirFuncionarioController(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) {
        if (txtIdFuncionario.getText().isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "O campo ID do Funcionário não pode estar vazio.");
            return;
        }

        try {
            int funcionarioId = Integer.parseInt(txtIdFuncionario.getText());
            funcionarioDAO.excluir(funcionarioId);
            mostrarAlerta(AlertType.INFORMATION, "Funcionário excluído com sucesso!");
        } catch (SQLException e) {
            mostrarAlerta(AlertType.ERROR, "Erro ao excluir funcionário: " + e.getMessage());
        } catch (NumberFormatException e) {
            mostrarAlerta(AlertType.WARNING, "ID do Funcionário deve ser um número válido.");
        }
    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) {
        fecharJanela(event);
    }

    private void mostrarAlerta(AlertType tipo, String mensagem) {
        Alert alert = new Alert(tipo, mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void fecharJanela(ActionEvent event) {
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }
}
