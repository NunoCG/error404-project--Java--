package Error404.Model.Code;

import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseEvento;
import Error404.Model.Database.DatabaseJogo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 */
public class Main extends Application {

    private static Scene scene;

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Error404/View/menu.fxml")));
        primaryStage.setTitle("Super Liga Europeia by error404");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param fxml
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * @param fxml
     * @return
     * @throws IOException
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/" + fxml + ".fxml"));
        return fxmlLoader.load();

    }

    /**
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
    }

}
