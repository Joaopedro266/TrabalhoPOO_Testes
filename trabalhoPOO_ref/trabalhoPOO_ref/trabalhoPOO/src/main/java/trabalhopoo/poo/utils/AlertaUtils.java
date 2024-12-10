package trabalhopoo.poo.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertaUtils {
    public static void mostrarInformacao(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void mostrarErro(String titulo, String mensagem, Exception e) {
        Alert alert = new Alert(AlertType.ERROR, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
        if (e != null) e.printStackTrace();
    }
}
