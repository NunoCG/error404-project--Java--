package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Jogador;
import Error404.Model.Code.Treinador;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseJogador;
import Error404.Model.Database.DatabaseTreinador;
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
public class CenaInserirTreinador implements Initializable {

    public TextField txtNomeJog;
    public TextField txtIdadeJog;
    public ComboBox comboClubes;
    public DatePicker datePickerInicio;
    public static Treinador treinadorPublicoIns;
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
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Error404/View/abrirCenaTreinador.fxml")));
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
        txtNomeJog.setText(treinadorPublicoIns.getNome());
        LocalDate hoje = LocalDate.now();
        LocalDate dataNasc = LocalDate.parse(treinadorPublicoIns.getDataNascimento());
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
        int idTreinador = treinadorPublicoIns.getId();
        int idClube = ((Clube) comboClubes.getSelectionModel().getSelectedItem()).getId();
        String dataInicio = datePickerInicio.getValue().toString();

        if (DatabaseTreinador.inserirTreinadorNumClubeDB(idTreinador,idClube,dataInicio)) {
            Menu.showAlert(Alert.AlertType.INFORMATION, "Sucesso!", "Treinador inserido com sucesso", "O treinador já tem clube");
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro!", "Treinador não foi inserido", "Ocorreu um erro!");
        }
    }
}
