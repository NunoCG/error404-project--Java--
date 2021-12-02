package Error404.Controller;

import Error404.Model.Code.Clube;
import Error404.Model.Database.DatabaseClube;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 */
public class CenaAtualizarClube implements Initializable {

    public AnchorPane menuPane;
    public StackPane logoPane;
    public Button btnVoltar;
    public AnchorPane infoPane;
    public StackPane topPane;
    public TextField txtNome;
    public TextField txtEstadio;
    public TextField txtCidade;
    public ComboBox comBoxPais;
    public Button btnClear;
    public Button btnAtualizarClube;
    public ImageView imageView;
    public Button btnBrowse;
    public TextField txtArea;
    public TextField txtId;
    private FileInputStream fis;
    private FileChooser fileChooser = new FileChooser();
    private File file;
    private Image image;
    private ObservableList<String> paises = FXCollections.observableArrayList(Menu.listaPaises());
    public static Clube clube;

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaClube.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     */
    public void clear() {
        txtNome.clear();
        comBoxPais.getSelectionModel().clearSelection();
        txtCidade.clear();
        txtEstadio.clear();
        imageView.setImage(null);
    }

    /**
     *
     */
    public void atualizarClube() throws SQLException {
        if (txtNome.getText().isEmpty() && comBoxPais.getItems().isEmpty() &&
                txtCidade.getText().isEmpty() && txtEstadio.getText().isEmpty()) {
            Menu.showAlert(Alert.AlertType.ERROR, "ERRO!", "Erro ao alterar clube!",
                    "Não pode alterar um clube sem preencher todos os campos.");
        } else {
            try {
                Clube c = new Clube();
                c.setId(Integer.parseInt(txtId.getText()));
                c.setNome(txtNome.getText());
                String pais = comBoxPais.getSelectionModel().getSelectedItem().toString();
                c.setPais(pais);
                c.setCidade(txtCidade.getText());
                c.setEstadio(txtEstadio.getText());
                if (DatabaseClube.atualizarCubeDB(c)) {
                    Menu.showAlert(Alert.AlertType.CONFIRMATION, "CONFIRMAÇÃO!", "SUCESSO!",
                            "Clube atualizado com sucesso!");
                } else {
                    Menu.showAlert(Alert.AlertType.ERROR, "ERRO!", "Erro ao alterar clube!",
                            "");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Menu.showAlert(Alert.AlertType.ERROR, "ERRO!", "Erro ao alterar!", "Algo não correu bem");
            }
        }
    }

    /*public void browse() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txtArea.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(), 274, 239, true, true);
            imageView.setFitWidth(274);
            imageView.setFitHeight(239);
            imageView.setPreserveRatio(true);
            imageView.setImage(image);
        }
    }*/

    public void preencherCampos() {
        txtId.setText(String.valueOf(clube.getId()));
        txtId.setDisable(true);
        txtNome.setText(clube.getNome());
        comBoxPais.setValue(clube.getPais());
        txtCidade.setText(clube.getCidade());
        txtEstadio.setText(clube.getEstadio());
    }

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comBoxPais.setItems(paises);
        preencherCampos();
    }
}
