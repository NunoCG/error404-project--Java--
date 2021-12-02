package Error404.Controller;

import Error404.Model.Code.Treinador;
import Error404.Model.Database.DatabaseJogador;
import Error404.Model.Database.DatabaseTreinador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CenaCriarTreinador {
    public Button btnCriarJogador;
    public Button btnVoltar;
    public TextField txtNacionalidade;
    public DatePicker dataNascimento;
    public TextField txtNome;

    public void criarTreinador(ActionEvent actionEvent) throws IOException, SQLException {
        if (txtNome.getText().isEmpty() || txtNacionalidade.getText().isEmpty() || dataNascimento.getValue() == null) {
            Menu.showAlert(Alert.AlertType.WARNING, "Aviso", "Campos por preencher", "Não pode criar um clube sem preencher todos os campos");
        } else {
            Treinador t = new Treinador();
            t.setNome(txtNome.getText());
            t.setDataNascimento(dataNascimento.getValue().toString());
            t.setNacionalidade(txtNacionalidade.getText());
            if (DatabaseTreinador.inserirTreinadorDB(t)) {
                Menu.showAlert(Alert.AlertType.INFORMATION, "Informação", "Inserção de Treinador na base de dados", "Treinador criado com sucesso!");
            } else {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Inserção de Treinador na base de dados", "Erro ao criar e/ou inserir Treinador!");
            }
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaTreinador.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaTreinador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
