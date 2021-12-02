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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 */
public class CenaCriarClube implements Initializable {
    
    public AnchorPane menuPane;
    public StackPane logoPane;
    public Button btnCriarClube;
    public Button btnVoltar;
    public AnchorPane infoPane;
    public StackPane topPane;
    public TextField txtNome;
    public TextField txtEstadio;
    public TextField txtCidade;
    public Button btnClear;
    public ComboBox comBoxPais;
    public ImageView imageView;
    public Button btnBrowse;
    public TextField txtArea;
    public FileChooser fileChooser = new FileChooser();
    public static File file;
    public Image image;
    private ObservableList<String> paises = FXCollections.observableArrayList(Menu.listaPaises());

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
    }

    /**
     *
     */
    public void criarClube() throws SQLException, IOException {
        if (txtNome.getText().isEmpty() || comBoxPais.getItems().isEmpty() || txtCidade.getText().isEmpty() || txtEstadio.getText().isEmpty()) {
            Menu.showAlert(Alert.AlertType.WARNING, "Aviso", "Campos por preencher", "Não pode criar um clube sem preencher todos os campos");
        } else {
            Clube c = new Clube();
            c.setNome(txtNome.getText());
            String pais = comBoxPais.getSelectionModel().getSelectedItem().toString();
            c.setPais(pais);
            c.setCidade(txtCidade.getText());
            c.setEstadio(txtEstadio.getText());
            if (DatabaseClube.inserirClubeDB(c)) {
                Menu.showAlert(Alert.AlertType.CONFIRMATION, "Aviso", "Inserção de Clube na base de dados", "Clube criado e inserido com sucesso!");
            } else {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Inserção de clube na base de dados", "Erro ao criar e/ou inserir clube!");
            }
        }
    }

    /*public void browse() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txtArea.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);
            imageView.setFitWidth(274);
            imageView.setFitHeight(239);
            imageView.setPreserveRatio(true);
            imageView.setImage(image);
        }
    }*/

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comBoxPais.setItems(paises);
    }
}
