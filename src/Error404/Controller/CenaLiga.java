package Error404.Controller;

import Error404.Model.Code.*;
import Error404.Model.Database.DatabaseClube;
import Error404.Model.Database.DatabaseJogo;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 */
public class CenaLiga implements Initializable{
    
    public AnchorPane menuPane;
    public StackPane logoPane;
    public Button btnLiga;
    public Button btnClube;
    public Button btnJogador;
    public Button btnTreinador;
    public Button btnVoltar;
    public AnchorPane infoPane;
    public StackPane topPane;
    public TableView tabelaClassificacao;
    public TableColumn colClass;
    public TableColumn colClube;
    public TableColumn colPontos;
    public TableColumn colJogos;
    public TableColumn colVitorias;
    public TableColumn colEmpates;
    public TableColumn colDerrotas;
    public TableColumn colGM;
    public TableColumn colGS;
    public TableColumn colDG;
    ArrayList<Clube> listaClubes;
    ObservableList<Clube> obClubes;

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void voltar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/menu.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     *
     */
    public void loadData(){
        colClass.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClube.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPontos.setCellValueFactory(new PropertyValueFactory<>("p"));
        colJogos.setCellValueFactory(new PropertyValueFactory<>("j"));
        colVitorias.setCellValueFactory(new PropertyValueFactory<>("v"));
        colEmpates.setCellValueFactory(new PropertyValueFactory<>("e"));
        colDerrotas.setCellValueFactory(new PropertyValueFactory<>("d"));
        colGM.setCellValueFactory(new PropertyValueFactory<>("gm"));
        colGS.setCellValueFactory(new PropertyValueFactory<>("gs"));
        colDG.setCellValueFactory(new PropertyValueFactory<>("dg"));
        try {
            listaClubes = DatabaseClube.getTodosClubesEpocaAtualDB(1);
            for (Clube c: listaClubes) {
                ArrayList<Jogo> j = DatabaseJogo.getTodosJogosClubeEpoca(c.getId());
                c.setP(Clube.getVitorias(j,c.getId()) * 3);
                c.setJ(j.size());
                c.setV(Clube.getVitorias(j,c.getId()));
                c.setE(Clube.getEmpates(j));
                c.setD(Clube.getDerrotas(j,c.getId()));
                int gm =0;
                int gs =0;
                for (Jogo jog:j) {
                    for (Evento e:jog.getEventos()) {
                        if (e instanceof Golo) {
                            if (e.getClube().getId() == c.getId()) {
                                gm += 1;
                            } else {
                                gs += 1;
                            }
                        } else if (e instanceof GoloAnulado) {
                            if (e.getClube().getId() == c.getId()) {
                                gm -= 1;
                            } else {
                                gs -= 1;
                            }
                        } else if (e instanceof AutoGolo) {
                        if (e.getClube().getId() == c.getId()) {
                            gs += 1;
                        } else {
                            gm += 1;
                        }
                    }
                    }
                }
                c.setGm(gm);
                c.setGs(gs);
                c.setDg(gm - gs);
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        obClubes = FXCollections.observableArrayList(listaClubes);
        tabelaClassificacao.setItems(obClubes);
    }

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        colPontos.setSortType(TableColumn.SortType.DESCENDING);
        tabelaClassificacao.getSortOrder().add(colPontos);
        tabelaClassificacao.sort();
    }

    public void cenaJornadas(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/cenaJornadas.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void cenaEstatisticas(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/cenaEstatisticas.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void inserirJogo(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaCriarJogo.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
