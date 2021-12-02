package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Contrato;
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
import javafx.scene.control.*;
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
public class CenaTransferirJogador implements Initializable {

    public Button btnTransferir;
    public Button btnVoltar;
    public TextField txtNomeJog;
    public TextField txtClube;
    public TextField txtDataFim;
    public TextField txtDataInicio;
    public TextField txtIdadeJog;
    public ComboBox comboClubes;
    public DatePicker datePickerInicio;
    public static Jogador jogadorPublicoTr;
    Contrato c = new Contrato();
    ArrayList<Clube> listaClubes = new ArrayList<>();
    ObservableList<Clube> obClubes;

    /**
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
     * @throws SQLException
     */
    public void carregarDados() throws SQLException {
        listaClubes = DatabaseClube.getTodosClubesDB();
        obClubes = FXCollections.observableArrayList(listaClubes);
        comboClubes.setItems(obClubes);
        txtNomeJog.setText(jogadorPublicoTr.getNome());
        LocalDate hoje = LocalDate.now();
        LocalDate dataNasc = LocalDate.parse(jogadorPublicoTr.getDataNascimento());
        int diff = Period.between(dataNasc, hoje).getYears();
        txtIdadeJog.setText(String.valueOf(diff));
        c = DatabaseJogador.getContratoJogador(jogadorPublicoTr);
        assert c != null;
        txtClube.setText(c.getClube().getNome());
        txtDataInicio.setText(c.getDataInicio());
        if (c.getDataFim() == null) {
            txtDataFim.setText("Contrato Ativo");
        } else {
            txtDataFim.setText(c.getDataFim());
        }
        txtNomeJog.setDisable(true);
        txtIdadeJog.setDisable(true);
        txtClube.setDisable(true);
        txtDataInicio.setDisable(true);
        txtDataFim.setDisable(true);
    }

    /**
     * @param actionEvent
     */
    public void transferir(ActionEvent actionEvent) throws SQLException, IOException {
        int idJogador = c.getJogador().getId();
        int idClube = ((Clube) comboClubes.getSelectionModel().getSelectedItem()).getId();
        LocalDate data = datePickerInicio.getValue();
        String dataS = data.toString();

        if (DatabaseJogador.transferirJogador(idJogador, idClube, dataS)) {
            Menu.showAlert(Alert.AlertType.CONFIRMATION, "CONFIRMAÇÃO", "Transferência feita com sucesso", "");
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogador.fxml")));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO", "Erro ao tranferir jogador", "");
        }
    }
}
