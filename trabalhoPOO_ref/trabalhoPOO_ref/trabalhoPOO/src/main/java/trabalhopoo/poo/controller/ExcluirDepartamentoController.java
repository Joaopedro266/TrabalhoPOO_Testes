package trabalhopoo.poo.controller;

import trabalhopoo.poo.utils.AlertaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import trabalhopoo.poo.dao.DepartamentoDAO;

import java.sql.SQLException;

public class ExcluirDepartamentoController {

    @FXML
    private TextField txtIdDepartamento;

    @FXML
    private void btnExcluirOnAction(ActionEvent event) {
        // Verifica se o campo ID está vazio
        if (txtIdDepartamento.getText().isEmpty()) {
            AlertaUtils.mostrarErro("Erro", "O campo ID do departamento não pode estar vazio.", null);
            return; // Interrompe a execução se o campo estiver vazio
        }

        try {
            int idDepartamento = Integer.parseInt(txtIdDepartamento.getText());
            DepartamentoDAO departamentoDAO = new DepartamentoDAO();
            departamentoDAO.excluir(idDepartamento);
            AlertaUtils.mostrarInformacao("Sucesso", "Departamento excluído com sucesso!");
        } catch (NumberFormatException e) {
            // Exibe um alerta se o ID não for um número válido
            AlertaUtils.mostrarErro("Erro", "O ID do departamento deve ser um número válido.", e);
        } catch (SQLException e) {
            AlertaUtils.mostrarErro("Erro", "Erro ao excluir departamento!", e);
        }
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) {
        Stage stageAtual = (Stage) txtIdDepartamento.getScene().getWindow();
        stageAtual.close();
    }
}
