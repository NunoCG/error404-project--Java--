package Error404.Controller;

import Error404.Model.Code.Jogador;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseJogador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 */
public class CenaJogador implements Initializable {

    public AnchorPane menuPane;
    public StackPane logoPane;
    public Button btnVoltar;
    public AnchorPane infoPane;
    public StackPane topPane;
    public TableView<Jogador> tableJogador;
    public TableColumn<Jogador, Integer> colId;
    public TableColumn<Jogador, String> colNome;
    public TableColumn<Jogador, String> colNacionalidade;
    public TableColumn<Jogador, String> colDataNasc;
    public TableColumn<Jogador, String> colPosicao;
    public TextField txtPesquisar;
    public Button btnInserir;
    ArrayList<Jogador> listaJogadores;
    ObservableList<Jogador> obJogadores;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            mostrarJogadores();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/menu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * @param actionEvent
     */
    public void criarJogador(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaCriarJogador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * @param actionEvent
     */
    public void atualizarJogador(ActionEvent actionEvent) throws IOException, SQLException {
        if (tableJogador.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao selecionar jogador", "Por favor selecione um jogador");
        } else {
            CenaAtualizarJogador.jogadorPublico = (Jogador) tableJogador.getSelectionModel().getSelectedItem();
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaAtualizarJogador.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            mostrarJogadores();
        }
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void inserirJogador(ActionEvent actionEvent) throws IOException, SQLException {
        if (tableJogador.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao alterar!", "Tem de selecionar um jogador da tabela primeiro");
        } else {
            CenaInserirJogador.jogadorPublicoIns = tableJogador.getSelectionModel().getSelectedItem();
            CenaInserirJogador.jogadorPublicoIns.setClube(DatabaseClube.getClubeAtual(CenaInserirJogador.jogadorPublicoIns));
            if  (CenaInserirJogador.jogadorPublicoIns.getClube() == null) {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Error404/View/abrirCenaInserirJogador.fxml")));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else {
                Menu.showAlert(Alert.AlertType.WARNING, "AVISO", "O jogador já tem clube associado", "Se quiser mudar o clube, tem de o tranferir");
            }
        }
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void transferirJogador(ActionEvent actionEvent) throws IOException, SQLException {
        if (tableJogador.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao alterar!", "Tem de selecionar um jogador da tabela primeiro");
        } else {
            CenaTransferirJogador.jogadorPublicoTr = tableJogador.getSelectionModel().getSelectedItem();
            CenaTransferirJogador.jogadorPublicoTr.setClube(DatabaseJogador.getContratoJogador(CenaTransferirJogador.jogadorPublicoTr).getClube());
            if  (CenaTransferirJogador.jogadorPublicoTr.getClube() != null) {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Error404/View/abrirCenaTransferirJogador.fxml")));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else {
                Menu.showAlert(Alert.AlertType.WARNING, "AVISO", "O jogador não tem historico de clubes associados","Tem de o inserir num clube primeiro");
            }
        }
    }

    /**
     *
     */
    public void eliminarJogador() {
        if (tableJogador.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao selecionar jogador", "Por favor selecione um jogador");
        } else {
            Jogador jogador = (Jogador) tableJogador.getSelectionModel().getSelectedItem();
            try {
                if (DatabaseJogador.checkRelationJogador(jogador) == false) {
                    DatabaseJogador.eliminarJogadorDB(jogador);
                    mostrarJogadores();
                } else {
                    Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível eliminar o jogador",
                            "O jogador que está a tentar eliminar está relacionado a eventos.");
                }
            } catch (Exception e) {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro", "Tente novamente");
            }
        }
    }

    /**
     *
     * @throws SQLException
     */
    public void mostrarJogadores() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        colDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colPosicao.setCellValueFactory(new PropertyValueFactory<>("posicao"));
        try {
            listaJogadores = DatabaseJogador.getTodosJogadoresDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        obJogadores = FXCollections.observableArrayList(listaJogadores);
        tableJogador.setItems(obJogadores);
        filteredSearchList();
    }

    /**
     *
     * @throws SQLException
     */
    public void filteredSearchList() throws SQLException {
        FilteredList<Jogador> filteredList = new FilteredList<>(obJogadores, b -> true);
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(jogador -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase(Locale.ROOT);
                if (jogador.getNome().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else if (jogador.getPosicao().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else if (jogador.getNacionalidade().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else if (jogador.getDataNascimento().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Jogador> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableJogador.comparatorProperty());
        tableJogador.setItems(sortedList);
    }
}
