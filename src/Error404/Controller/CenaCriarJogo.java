package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Epoca;
import Error404.Model.Code.Jornada;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseJogo;
import Error404.Model.Database.DatabaseJornada;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CenaCriarJogo implements Initializable {
    public Button btnCriarJogador;
    public Button btnVoltar;
    public ComboBox equipaForaCombo;
    public ComboBox equipaCasaCombo;
    public DatePicker dataPicker;
    public ComboBox comboJornada;
    public TextField fieldDescontoPrimeira;
    public TextField fieldDescontoSegunda;
    private ObservableList<Clube> obClubes;
    private ArrayList<Clube> arrayClubes = new ArrayList<>();
    private ArrayList<String> arrayJornadas = new ArrayList<>();
    private ObservableList<String> obJornadas;

    public void criarJogo(ActionEvent actionEvent) throws IOException, SQLException {
        try {
            int primeira = Integer.parseInt(fieldDescontoPrimeira.getText());
            int segunda = Integer.parseInt(fieldDescontoSegunda.getText());
        } catch (Exception e) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Campos inválidos", "Verifique os campos dos descontos");
        }
        if (fieldDescontoPrimeira.getText().isEmpty() || fieldDescontoSegunda.getText().isEmpty()
                || equipaCasaCombo.getSelectionModel().getSelectedItem() == null
                || equipaForaCombo.getSelectionModel().getSelectedItem() == null
                || comboJornada.getSelectionModel().getSelectedItem() == null || dataPicker.getValue() == null
                || equipaForaCombo.getSelectionModel().getSelectedItem() == equipaCasaCombo.getSelectionModel().getSelectedItem()) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Campos Vazios ou inválidos", "Preencha todos os campos");
        } else {
            CenaJogoEventos.jogoPublico.setDescontoPrimeira(Integer.parseInt(fieldDescontoPrimeira.getText()));
            CenaJogoEventos.jogoPublico.setDescontoSegunda(Integer.parseInt(fieldDescontoSegunda.getText()));
            CenaJogoEventos.jogoPublico.setClubeCasa((Clube) equipaCasaCombo.getSelectionModel().getSelectedItem());
            CenaJogoEventos.jogoPublico.setClubeFora((Clube) equipaForaCombo.getSelectionModel().getSelectedItem());
            CenaJogoEventos.jogoPublico.setData(dataPicker.getValue().toString());
            Jornada jornada = new Jornada();
            jornada.setNumJornada(Integer.parseInt((String)comboJornada.getSelectionModel().getSelectedItem()));
            Epoca e = new Epoca();
            e.setId(1);
            jornada.setEpoca(e);
            jornada.setData(dataPicker.getValue().toString());
            boolean flag = false;
            ArrayList<Jornada> lj = DatabaseJornada.getJornadasEpocaDB(1);
            for (Jornada j: lj) {
                if (j.getNumJornada() == jornada.getNumJornada())
                    flag = true;
            }
            if (!flag) {
                DatabaseJornada.inserirJornadaDB(jornada);
            }
            ArrayList<Jornada> ljj = DatabaseJornada.getJornadasEpocaDB(1);
            int idj = 0;
            for (Jornada j: ljj) {
                if (j.getNumJornada() == jornada.getNumJornada())
                    idj = j.getId();
            }
            jornada.setId(idj);
            CenaJogoEventos.jogoPublico.setJornada(jornada);
            DatabaseJogo.inserirJogoDB(CenaJogoEventos.jogoPublico);
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaEscolhaJogadores.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaLiga.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void loadData() {
        try {
            arrayJornadas.add("1");
            arrayJornadas.add("2");
            arrayJornadas.add("3");
            arrayJornadas.add("4");
            arrayJornadas.add("5");
            arrayJornadas.add("6");
            arrayJornadas.add("7");
            arrayJornadas.add("8");
            arrayJornadas.add("9");
            arrayJornadas.add("10");
            obJornadas = FXCollections.observableArrayList(arrayJornadas);
            comboJornada.setItems(obJornadas);
            arrayClubes = DatabaseClube.getTodosClubesDB();
            obClubes = FXCollections.observableArrayList(arrayClubes);
            equipaForaCombo.setItems(obClubes);
            equipaCasaCombo.setItems(obClubes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
