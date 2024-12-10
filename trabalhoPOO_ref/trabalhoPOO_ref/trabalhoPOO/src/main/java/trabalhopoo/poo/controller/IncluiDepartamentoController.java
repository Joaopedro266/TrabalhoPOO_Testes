package trabalhopoo.poo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import trabalhopoo.poo.dao.DepartamentoDAO;
import trabalhopoo.poo.model.Departamento;

import java.sql.SQLException;

public class IncluiDepartamentoController {

    @FXML
    private TextField txtNomeDep;

    private final DepartamentoDAO departamentoDAO;

    public IncluiDepartamentoController() {
        this.departamentoDAO = new DepartamentoDAO(); // Ou inicialize conforme necessário
    }


    public IncluiDepartamentoController(DepartamentoDAO departamentoDAO) {
        this.departamentoDAO = departamentoDAO;
    }

    @FXML
    private void btnConfirmaDepOnAction(ActionEvent event) {
        if (txtNomeDep.getText().isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "O nome do departamento não pode estar vazio.");
            return;
        }

        Departamento departamento = new Departamento();
        departamento.setNome(txtNomeDep.getText());

        try {
            departamentoDAO.inserir(departamento);
            mostrarAlerta(AlertType.INFORMATION, "Departamento cadastrado com sucesso!");
        } catch (SQLException e) {
            mostrarAlerta(AlertType.ERROR, "Erro ao cadastrar departamento: " + e.getMessage());
        }
    }

    @FXML
    private void btnVoltarDepOnAction(ActionEvent event) {
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
