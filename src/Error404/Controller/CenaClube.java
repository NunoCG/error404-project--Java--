package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Treinador;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseConnect;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 */
public class CenaClube implements Initializable {

    public AnchorPane menuPane;
    public StackPane logoPane;
    public Button btnVer;
    public Button btnCriar;
    public Button btnAlterar;
    public Button btnEliminar;
    public Button btnVoltar;
    public AnchorPane infoPane;
    public StackPane topPane;
    public TableView<Clube> tableClube;
    public TableColumn<Clube, Integer> colId;
    public TableColumn<Clube, String> colNome;
    public TableColumn<Clube, String> colPais;
    public TableColumn<Clube, String> colCidade;
    public TableColumn<Clube, String> colEstadio;
    public TableColumn<Clube, String> colTitulos;
    int index = -1;
    ArrayList<Clube> listaClubes;
    ObservableList<Clube> obClubes;

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void verClube(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaLiga.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void criarClube(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaCriarClube.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void alterarClube(ActionEvent actionEvent) throws IOException {
        if (tableClube.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO",
                    "Erro ao alterar!", "Tem de selecionar um clube da tabela primeiro");
        } else {
            CenaAtualizarClube.clube = tableClube.getSelectionModel().getSelectedItem();
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaAtualizarClube.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void eliminarClube(ActionEvent actionEvent) throws IOException {
        if (tableClube.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao selecionar clube", "Por favor selecione um clube");
        } else {
            Clube c = tableClube.getSelectionModel().getSelectedItem();
            try {
                DatabaseClube.eliminarClubeDB(c);
                mostrarClubes();
            } catch (Exception e) {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro", "Tente novamente");
            }
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
     *
     */
    public void mostrarClubes() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colEstadio.setCellValueFactory(new PropertyValueFactory<>("estadio"));
        colTitulos.setCellValueFactory(new PropertyValueFactory<>("titulosSle"));
        try {
            listaClubes = DatabaseClube.getTodosClubesDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        obClubes = FXCollections.observableArrayList(listaClubes);
        tableClube.setItems(obClubes);
    }

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarClubes();
    }

}
