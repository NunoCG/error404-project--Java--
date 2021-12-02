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
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 */
public class CenaInserirJogador implements Initializable {

    public TextField txtNomeJog;
    public TextField txtIdadeJog;
    public ComboBox comboClubes;
    public DatePicker datePickerInicio;
    public static Jogador jogadorPublicoIns;
    ArrayList<Clube> listaClubes = new ArrayList<>();
    ObservableList<Clube> obClubes;

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carregarDados();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogador.fxml")));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @throws SQLException
     */
    public void carregarDados() throws SQLException {
        listaClubes = DatabaseClube.getTodosClubesDB();
        obClubes = FXCollections.observableArrayList(listaClubes);
        comboClubes.setItems(obClubes);
        txtNomeJog.setText(jogadorPublicoIns.getNome());
        LocalDate hoje = LocalDate.now();
        LocalDate dataNasc = LocalDate.parse(jogadorPublicoIns.getDataNascimento());
        int diff = Period.between(dataNasc, hoje).getYears();
        txtIdadeJog.setText(String.valueOf(diff));
        txtNomeJog.setDisable(true);
        txtIdadeJog.setDisable(true);
    }

    /**
     *
     * @throws SQLException
     */
    public void inserir() throws SQLException {
        int idJogador = jogadorPublicoIns.getId();
        int idClube = ((Clube) comboClubes.getSelectionModel().getSelectedItem()).getId();
        String dataInicio = datePickerInicio.getValue().toString();

        if (DatabaseJogador.inserirJogadorNumClubeDB(idJogador, idClube, dataInicio)) {
            Menu.showAlert(Alert.AlertType.INFORMATION, "Sucesso!", "Jogador inserido com sucesso", "O jogador já tem clube");
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro!", "Jogador não foi inserido", "Ocorreu um erro!");
        }
    }
}
