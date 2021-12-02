package Error404.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 */
public class Menu {

    public AnchorPane menuPane;
    public AnchorPane infoPane;
    public StackPane logoPane;
    public Button btnLiga;
    public Button btnClube;
    public Button btnJogador;
    public Button btnTreinador;
    public Button btnSair;
    public StackPane topPane;

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void abrirCenaLiga(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaLiga.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void abrirCenaClube(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaClube.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void abrirCenaJogador(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void abrirCenaTreinador(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaTreinador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param actionEvent
     */
    public void fecharPrograma(ActionEvent actionEvent) {
        btnSair.getScene().getWindow().hide();
    }

    /**
     *
     * @param alertType
     * @param title
     * @param header
     * @param message
     */
    public static void showAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     *
     * @return
     */
    public static String [] listaPaises() {
        String[] listaPaises = {"Portugal", "Alemanha", "Grécia", "Austria", "Hungria", "Bélgica", "Irlanda",
                "Bulgária", "Itália", "Chéquia", "Letónia", "Chipre", "Lituânia", "Croácia", "Luxemburgo",
                "Dinamarca", "Malta", "Eslováquia", "Países Baixos", "Eslovénia", "Polónia", "Espanha",
                "Estónia", "Roménia", "Finlândia", "Suécia", "França"};
        Arrays.sort(listaPaises);
        return listaPaises;
    }

    /**
     *
     * @return
     */
    public static  String [] listaPosicoes() {
        String [] listaPosicoes = {"GR", "DC", "DLE", "DLD", "MDF", "MDC", "MDE", "MDD", "MO", "EE", "ED", "PL"};
        return  listaPosicoes;
    }
}

