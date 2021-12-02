package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Jogador;
import Error404.Model.Code.Jogo;
import Error404.Model.Code.Jornada;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseJogador;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CenaEstatisticas implements Initializable {
    public ComboBox combo;
    public TableView tableEstatistica;
    public TableColumn colunaJogador;
    public TableColumn coluna;
    public Button btnVoltar;
    ObservableList<String> tipoObs;
    ArrayList<String> tipoArr = new ArrayList<String>();
    ArrayList<Jogador> listJog = new ArrayList<>();
    ObservableList<Jogador> obsJog;
    String tipo=null;

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaLiga.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void combo(ActionEvent actionEvent) throws SQLException {
        loadData();
        coluna.setSortType(TableColumn.SortType.DESCENDING);
        tableEstatistica.getSortOrder().add(coluna);
        tableEstatistica.sort();
    }

    public void loadCombo() {

        tipoArr.add("Melhor Marcador");
        tipoArr.add("Mais Amarelos");
        tipoArr.add("Mais Vermelhos");
        tipoArr.add("Mais Auto-Golos");

        tipoObs = FXCollections.observableArrayList(tipoArr);
        combo.setItems(tipoObs);
        combo.setValue(tipoArr.get(0));


    }

    public void loadData() throws SQLException {

        tipo = (String) combo.getSelectionModel().getSelectedItem();
        if (tipo.equals("Melhor Marcador")) {
            colunaJogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
            coluna.setCellValueFactory(new PropertyValueFactory<>("golos"));
            coluna.setText("GOLOS");
        } else if (tipo.equals("Mais Amarelos")) {
            colunaJogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
            coluna.setCellValueFactory(new PropertyValueFactory<>("a"));
            coluna.setText("AMARELOS");
        } else if (tipo.equals("Mais Vermelhos")) {
            colunaJogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
            coluna.setCellValueFactory(new PropertyValueFactory<>("v"));
            coluna.setText("VERMELHOS");
        } else if (tipo.equals("Mais Auto-Golos")) {
            colunaJogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
            coluna.setCellValueFactory(new PropertyValueFactory<>("ag"));
            coluna.setText("AUTO-GOLOS");
        }

        listJog = DatabaseJogador.getTodosJogadoresDB();

        for (Jogador jog: listJog) {
            jog.setGolos(DatabaseJogador.getGolos(jog));
            jog.setA(DatabaseJogador.getAmarelos(jog));
            jog.setV(DatabaseJogador.getVermelhos(jog));
            jog.setAg(DatabaseJogador.getAutoGolos(jog));
        }


        obsJog = FXCollections.observableArrayList(listJog);
        tableEstatistica.setItems(obsJog);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadCombo();
        try {
            loadData();
            coluna.setSortType(TableColumn.SortType.DESCENDING);
            tableEstatistica.getSortOrder().add(coluna);
            tableEstatistica.sort();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
