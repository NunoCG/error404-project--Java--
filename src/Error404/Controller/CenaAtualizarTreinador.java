package Error404.Controller;

import Error404.Model.Code.Jogador;
import Error404.Model.Code.Treinador;
import Error404.Model.Database.DatabaseJogador;
import Error404.Model.Database.DatabaseTreinador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CenaAtualizarTreinador implements Initializable {
    public static Treinador treinadorPublico;
    public Button btnVoltar;
    public Button btnAtualizarClube;
    public TextField nomeField;
    public TextField idField;
    public TextField nacionalidadeField;
    public DatePicker dataNascimentoField;

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaTreinador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void atualizarTreinador(ActionEvent actionEvent) {
        if (nomeField.getText().isEmpty() || nacionalidadeField.getText().isEmpty() || dataNascimentoField.getValue() == null) {
            Menu.showAlert(Alert.AlertType.WARNING, "Aviso", "Campos por preencher", "Não pode criar um treinador sem preencher todos os campos");
        } else {
            treinadorPublico.setNome(nomeField.getText());
            treinadorPublico.setNacionalidade(nacionalidadeField.getText());
            treinadorPublico.setDataNascimento(dataNascimentoField.getValue().toString());
            try {
                DatabaseTreinador.atualizarTreinadorDB(treinadorPublico);
                Menu.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Treinador alterado com sucesso", "");
                Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaTreinador.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (Exception e) {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Treinador não inserido", "Não foi possível alterar o treinador");
            }
        }
    }

    public void setTreinadorPublico() {
        if (treinadorPublico != null) {
            idField.setText(String.valueOf(treinadorPublico.getId()));
            nomeField.setText(treinadorPublico.getNome());
            if (treinadorPublico.getDataNascimento() != null) {
                LocalDate localDate = LocalDate.parse(treinadorPublico.getDataNascimento());
                dataNascimentoField.setValue(localDate);
            }
            nacionalidadeField.setText(treinadorPublico.getNacionalidade());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTreinadorPublico();
    }
}
