package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Jogador;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseJogador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 *
 */
public class CenaCriarJogador implements Initializable {

    public TextField txtNome;
    public TextField txtNacionalidade;
    public ComboBox comboPosicao;
    public DatePicker dataNascimento;
    private ObservableList<String> posicoes = FXCollections.observableArrayList(Menu.listaPosicoes());

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboPosicao.setItems(posicoes);
    }

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * @throws SQLException
     */
    public void criarJogador(ActionEvent actionEvent) throws SQLException, IOException {
        if (txtNome.getText().isEmpty() || comboPosicao.getSelectionModel().getSelectedItem() == null || txtNacionalidade.getText().isEmpty() || dataNascimento.getValue() == null) {
            Menu.showAlert(Alert.AlertType.WARNING, "Aviso", "Campos por preencher", "Não pode criar um clube sem preencher todos os campos");
        } else {
            Jogador j = new Jogador();
            j.setNome(txtNome.getText());
            if (comboPosicao.getSelectionModel().getSelectedItem() != null) {
                j.setPosicao(comboPosicao.getSelectionModel().getSelectedItem().toString());
            }
            j.setNacionalidade(txtNacionalidade.getText());
            j.setDataNascimento(dataNascimento.getValue().toString());
            if (DatabaseJogador.inserirJogadorDB(j)) {
                Menu.showAlert(Alert.AlertType.INFORMATION, "Aviso", "Inserção de Jogador na base de dados", "Jogador criado com sucesso!");
            } else {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Inserção de Jogador na base de dados", "Erro ao criar e/ou inserir Jogador!");
            }
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogador.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }
}
