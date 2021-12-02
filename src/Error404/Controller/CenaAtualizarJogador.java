package Error404.Controller;

import Error404.Model.Code.Jogador;
import Error404.Model.Database.DatabaseJogador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CenaAtualizarJogador implements Initializable {
    public static Jogador jogadorPublico;
    public Button btnAtualizarClube;
    public Button btnVoltar;
    public TextField nomeField;
    public TextField idField;
    public ComboBox posicaoCombo;
    public DatePicker dataNascimentoField;
    public TextField nacionalidadeField;
    private ObservableList<String> posicoes = FXCollections.observableArrayList(Menu.listaPosicoes());

    public void setJogador() {
        if (jogadorPublico != null) {
            idField.setText(String.valueOf(jogadorPublico.getId()));
            nomeField.setText(jogadorPublico.getNome());
            if (jogadorPublico.getDataNascimento() != null) {
                LocalDate localDate = LocalDate.parse(jogadorPublico.getDataNascimento());
                dataNascimentoField.setValue(localDate);
            }
            nacionalidadeField.setText(jogadorPublico.getNacionalidade());
            posicaoCombo.setValue(jogadorPublico.getPosicao());
        }
    }


    public void atualizarJogador(ActionEvent actionEvent) {
        if (nomeField.getText().isEmpty() || posicaoCombo.getSelectionModel().getSelectedItem() == null || nacionalidadeField.getText().isEmpty() || dataNascimentoField.getValue() == null) {
            Menu.showAlert(Alert.AlertType.WARNING, "Aviso", "Campos por preencher", "Não pode criar um jogador sem preencher todos os campos");
        } else {
            jogadorPublico.setNome(nomeField.getText());
            jogadorPublico.setNacionalidade(nacionalidadeField.getText());
            jogadorPublico.setDataNascimento(dataNascimentoField.getValue().toString());
            jogadorPublico.setPosicao(posicaoCombo.getSelectionModel().getSelectedItem().toString());
            try {
                DatabaseJogador.atualizarJogadorDB(jogadorPublico);
                Menu.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Jogador alterado com sucesso", "");
                Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogador.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (Exception e) {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Jogador não inserido", "Não foi possível alterar o jogador");
            }
        }
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        posicaoCombo.setItems(posicoes);
        setJogador();
    }

}
