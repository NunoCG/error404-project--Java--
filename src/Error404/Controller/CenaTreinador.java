package Error404.Controller;

import Error404.Model.Code.Treinador;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseTreinador;
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
public class CenaTreinador implements Initializable {

    public AnchorPane menuPane;
    public StackPane logoPane;
    public Button btnLiga;
    public Button btnClube;
    public Button btnJogador;
    public Button btnTreinador;
    public Button btnVoltar;
    public AnchorPane infoPane;
    public StackPane topPane;
    public TableColumn colunaID;
    public TableColumn colunaNome;
    public TableColumn colunaNacionalidade;
    public TableColumn colunaDataNascimento;
    public TableView<Treinador> tabelaTreinadores;
    public TextField pesquisar;
    private ArrayList<Treinador> listaTreinadores;
    private ObservableList<Treinador> obTreinadores;

    public void criarTreinador(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaCriarTreinador.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void alterarTreinador(ActionEvent actionEvent) throws IOException, SQLException {
        if (tabelaTreinadores.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao selecionar treinador", "Por favor selecione um treinador");
        } else {
            CenaAtualizarTreinador.treinadorPublico = (Treinador) tabelaTreinadores.getSelectionModel().getSelectedItem();
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaAtualizarTreinador.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            carregarTreinadores();
        }
    }

    public void transferirTreinador(ActionEvent actionEvent) throws IOException, SQLException {
        if (tabelaTreinadores.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao alterar!", "Tem de selecionar um treinador da tabela primeiro");
        } else {
            CenaTransferirTreinador.treinadorPublico = tabelaTreinadores.getSelectionModel().getSelectedItem();
            if (DatabaseTreinador.getClubeAtual(CenaTransferirTreinador.treinadorPublico) != null) {
                CenaTransferirTreinador.treinadorPublico = tabelaTreinadores.getSelectionModel().getSelectedItem();
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Error404/View/abrirCenaTransferirTreinador.fxml")));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else {
                Menu.showAlert(Alert.AlertType.WARNING, "AVISO", "O treinador não tem historico de clubes associados", "Tem de o inserir num clube primeiro");
            }
        }
    }

    public void eliminarTreinador(ActionEvent actionEvent) {
        if (tabelaTreinadores.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao selecionar treinador", "Por favor selecione um treinador");
        } else {
            Treinador t = (Treinador) tabelaTreinadores.getSelectionModel().getSelectedItem();
            try {
                if (DatabaseTreinador.checkRelationTreinador(t) == false) {
                    DatabaseTreinador.eliminarTreinadorDB(t);
                    carregarTreinadores();
                } else {
                    Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível eliminar o treinador",
                            "O treinador que está a tentar eliminar está relacionado a uma equipa.");
                }
            } catch (Exception e) {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro", "Tente novamente");
            }
        }
    }

    public void carregarTreinadores() throws SQLException {
        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        colunaDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        try {
            listaTreinadores = DatabaseTreinador.getTodasTreinadoresDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        obTreinadores = FXCollections.observableArrayList(listaTreinadores);
        tabelaTreinadores.setItems(obTreinadores);
        filteredSearchList();
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/menu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void filteredSearchList() throws SQLException {
        FilteredList<Treinador> filteredList = new FilteredList<>(obTreinadores, b -> true);
        pesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(treinador -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase(Locale.ROOT);
                if (treinador.getNome().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else if (treinador.getNacionalidade().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else if (treinador.getDataNascimento().toLowerCase(Locale.ROOT).contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Treinador> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tabelaTreinadores.comparatorProperty());
        tabelaTreinadores.setItems(sortedList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carregarTreinadores();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void inserirTreinador(ActionEvent actionEvent) throws SQLException, IOException {
        if (tabelaTreinadores.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao alterar!", "Tem de selecionar um treinador da tabela primeiro");
        } else {
            CenaInserirTreinador.treinadorPublicoIns = tabelaTreinadores.getSelectionModel().getSelectedItem();
            if  (DatabaseTreinador.getClubeAtual(CenaInserirTreinador.treinadorPublicoIns) == null) {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Error404/View/abrirCenaInserirTreinador.fxml")));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else {
                Menu.showAlert(Alert.AlertType.WARNING, "AVISO", "O treinador já tem clube associado", "Se quiser mudar o clube, tem de o tranferir");
            }
        }
    }
}
