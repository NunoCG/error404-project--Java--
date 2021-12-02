package Error404.Controller;

import Error404.Model.Code.*;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CenaJornadas implements Initializable {

    public TableView tableJornadas;
    public TableColumn colunaUm;
    public TableColumn resultadoUm;
    public TableColumn resultadoDois;
    public TableColumn colunaDois;
    public Button btnVoltar;
    public ComboBox combo;
    ArrayList<Jogo> listaJogos = new ArrayList<>();
    ObservableList<Jogo> obJogos;
    ArrayList<Jornada> listaJornadas = new ArrayList<>();
    ObservableList<Jornada> obJornadas;
    Jornada jornada = new Jornada();

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaLiga.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void detalhes(ActionEvent actionEvent) throws IOException {
        if (tableJornadas.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao alterar!", "Tem de selecionar um jogador da tabela primeiro");
        } else {
            CenaDetalhesJornada.jogo = (Jogo) tableJornadas.getSelectionModel().getSelectedItem();
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaDetalhesJornada.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    public void loadJornada() {

        try {
            listaJornadas=DatabaseJornada.getJornadasEpocaDB(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        obJornadas = FXCollections.observableArrayList(listaJornadas);
        combo.setItems(obJornadas);

        if (listaJornadas.size() != 0) {
            combo.setValue(listaJornadas.get(0));

        }
    }

    public void loadData(){

        colunaUm.setCellValueFactory(new PropertyValueFactory<>("clubeCasa"));
        colunaDois.setCellValueFactory(new PropertyValueFactory<>("clubeFora"));
        resultadoUm.setCellValueFactory(new PropertyValueFactory<>("resultCasa"));
        resultadoDois.setCellValueFactory(new PropertyValueFactory<>("resultFora"));
        try {
            jornada = (Jornada) combo.getSelectionModel().getSelectedItem();

            if ( jornada != null) {
                listaJogos = DatabaseJogo.getTodosJogosJornada(jornada.getId());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (listaJogos.size() != 0) {
            for (Jogo jog: listaJogos) {
                jog.setResultCasa(jog.getGolosCasa());
                jog.setResultFora(jog.getGolosFora());
            }
        }
        if (listaJogos.size() != 0) {
            obJogos = FXCollections.observableArrayList(listaJogos);
            tableJornadas.setItems(obJogos);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadJornada();
        loadData();
    }

    public void comboJornadas(ActionEvent actionEvent) {
        loadData();
    }

}
