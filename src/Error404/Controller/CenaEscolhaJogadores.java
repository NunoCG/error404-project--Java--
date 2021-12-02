package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Jogador;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseJogador;
import Error404.Model.Database.DatabaseJogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CenaEscolhaJogadores implements Initializable {
    public Button btnCriarJogador;
    public Button btnVoltar;
    public TableView tabela11Casa;
    public TableColumn colID11Casa;
    public TableColumn colJogador11Casa;
    public TableView tabelaJogadoresCasa;
    public TableColumn colIDCasa;
    public TableColumn colJogadorCasa;
    public TableView tabela11Fora;
    public TableColumn colID11Fora;
    public TableColumn colJogador11Fora;
    public TableView tabelaJogadoresFora;
    public TableColumn colIDFora;
    public TableColumn colJogadorFora;
    public Button btnAdd11Casa;
    public Button btnAdd11Fora;
    public Button btnRemove11Fora;
    public Button btnRemove11Casa;
    private ArrayList<Jogador> listaJogadoresCasa;
    private ObservableList<Jogador> obJogadoresCasa;
    private ArrayList<Jogador> listaJogadoresFora;
    private ObservableList<Jogador> obJogadoresFora;
    private ArrayList<Jogador> listaJogadores11Casa = new ArrayList<>();
    private ObservableList<Jogador> obJogadores11Casa;
    private ArrayList<Jogador> listaJogadores11Fora = new ArrayList<>();
    private ObservableList<Jogador> obJogadores11Fora;

    public void confirmar11(ActionEvent actionEvent) throws IOException, SQLException {
        CenaJogoEventos.onzeTitularCasa = listaJogadores11Casa;
        CenaJogoEventos.onzeTitularFora = listaJogadores11Fora;
        CenaJogoEventos.substituicoesCasa = listaJogadoresCasa;
        CenaJogoEventos.substituicoesFora = listaJogadoresFora;

        CenaJogoEventos.jogoPublico.setId(DatabaseJogo.getIdJogo(CenaJogoEventos.jogoPublico.getClubeCasa().getId(), CenaJogoEventos.jogoPublico.getClubeFora().getId(), CenaJogoEventos.jogoPublico.getJornada().getId()));
        for (Jogador j:CenaJogoEventos.onzeTitularCasa) { ;
            DatabaseJogador.inserirJogadorJogoDB(DatabaseJogador.getIdJogadorClubePorJogador(j), CenaJogoEventos.jogoPublico.getId(), true);
        }
        for (Jogador j:CenaJogoEventos.onzeTitularFora) {
            DatabaseJogador.inserirJogadorJogoDB(DatabaseJogador.getIdJogadorClubePorJogador(j), CenaJogoEventos.jogoPublico.getId(), true);
        }
        for (Jogador j:CenaJogoEventos.substituicoesCasa) {
            DatabaseJogador.inserirJogadorJogoDB(DatabaseJogador.getIdJogadorClubePorJogador(j), CenaJogoEventos.jogoPublico.getId(), false);
        }
        for (Jogador j:CenaJogoEventos.substituicoesFora) {
            DatabaseJogador.inserirJogadorJogoDB(DatabaseJogador.getIdJogadorClubePorJogador(j), CenaJogoEventos.jogoPublico.getId(), false);
        }
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaJogoEventos.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaLiga.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void loadData() throws SQLException {
        colID11Casa.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogador11Casa.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIDCasa.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogadorCasa.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colID11Fora.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogador11Fora.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIDFora.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogadorFora.setCellValueFactory(new PropertyValueFactory<>("nome"));
        listaJogadoresCasa = DatabaseJogador.getTodosJogadoresClubeDB(CenaJogoEventos.jogoPublico.getClubeCasa().getId());
        listaJogadoresFora = DatabaseJogador.getTodosJogadoresClubeDB(CenaJogoEventos.jogoPublico.getClubeFora().getId());
        obJogadoresCasa = FXCollections.observableArrayList(listaJogadoresCasa);
        obJogadoresFora = FXCollections.observableArrayList(listaJogadoresFora);
        tabelaJogadoresCasa.setItems(obJogadoresCasa);
        tabelaJogadoresFora.setItems(obJogadoresFora);
        obJogadores11Casa = FXCollections.observableArrayList(listaJogadores11Casa);
        obJogadores11Fora = FXCollections.observableArrayList(listaJogadores11Fora);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addJogador11Casa(ActionEvent actionEvent) {
        if (tabelaJogadoresCasa.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Jogador não selecionado",
                    "Por favor selecione um jogador da lista de jogadores para adicionar ao 11 titular");
        } else {
            Jogador j = (Jogador) tabelaJogadoresCasa.getSelectionModel().getSelectedItem();
            obJogadores11Casa.add(j);
            listaJogadores11Casa.add(j);
            obJogadoresCasa.remove(j);
            listaJogadoresCasa.remove(j);
            tabelaJogadoresCasa.setItems(obJogadoresCasa);
            tabela11Casa.setItems(obJogadores11Casa);
        }
    }

    public void addJogador11Fora(ActionEvent actionEvent) {
        if (tabelaJogadoresFora.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Jogador não selecionado",
                    "Por favor selecione um jogador da lista de jogadores para adicionar ao 11 titular");
        } else {
            Jogador j = (Jogador) tabelaJogadoresFora.getSelectionModel().getSelectedItem();
            obJogadores11Fora.add(j);
            listaJogadores11Fora.add(j);
            obJogadoresFora.remove(j);
            listaJogadoresFora.remove(j);
            tabelaJogadoresFora.setItems(obJogadoresFora);
            tabela11Fora.setItems(obJogadores11Fora);
        }
    }

    public void removeJogador11Casa(ActionEvent actionEvent) {
        if (tabela11Casa.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Jogador não selecionado",
                    "Por favor selecione um jogador da lista de jogadores para adicionar ao 11 titular");
        } else {
            Jogador j = (Jogador) tabela11Casa.getSelectionModel().getSelectedItem();
            obJogadores11Casa.remove(j);
            listaJogadores11Casa.remove(j);
            obJogadoresCasa.add(j);
            listaJogadoresCasa.add(j);
            tabelaJogadoresCasa.setItems(obJogadoresCasa);
            tabela11Casa.setItems(obJogadores11Casa);
        }
    }

    public void removeJogador11Fora(ActionEvent actionEvent) {
        if (tabela11Fora.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Jogador não selecionado",
                    "Por favor selecione um jogador da lista de jogadores para adicionar ao 11 titular");
        } else {
            Jogador j = (Jogador) tabela11Fora.getSelectionModel().getSelectedItem();
            obJogadores11Fora.remove(j);
            listaJogadores11Fora.remove(j);
            obJogadoresFora.add(j);
            listaJogadoresFora.add(j);
            tabelaJogadoresFora.setItems(obJogadoresFora);
            tabela11Fora.setItems(obJogadores11Fora);
        }
    }
}
