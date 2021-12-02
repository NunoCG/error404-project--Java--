package Error404.Controller;

import Error404.Model.Code.*;
import Error404.Model.Database.DatabaseEvento;
import Error404.Model.Code.Evento;
import Error404.Model.Code.Golo;
import Error404.Model.Code.Jogador;
import Error404.Model.Code.Jogo;
import Error404.Model.Database.DatabaseJogador;
import Error404.Model.Database.DatabaseJogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CenaJogoEventos implements Initializable {
    public static ArrayList<Jogador> onzeTitularCasa;
    public static ArrayList<Jogador> substituicoesCasa;
    public static ArrayList<Jogador> onzeTitularFora;
    public static ArrayList<Jogador> substituicoesFora;
    public static Jogo jogoPublico = new Jogo();
    public TableView tabelaFora;
    public TableColumn colunaIdFora;
    public TableColumn colunaJogadorFora;
    public Button btnConfirmar;
    public Button btnCancelar;
    public Label resultadoCasa;
    public Label resultadoFora;
    public Button btnAddGolosCasa;
    public Button btnRemoveGolosCasa;
    public Button btnAddGolosFora;
    public Button btnRemoveGolosFora;
    public TableView tabelaCasa;
    public TableColumn colunaIdCasa;
    public TableColumn colunaJogadorCasa;
    public TableColumn colunaAmarelosFora;
    public TableColumn colunaAmarelosCasa;
    public Button btnAddSubstituicaoCasa;
    public Button btnAddSubstituicaoFora;
    public Button btnAddAmareloCasa;
    public Button btnAddVermelhoCasa;
    public Button btnAddAmareloFora;
    public Button btnAddVermelhoFora;
    public TextField minutoGoloCasa;
    public TextField minuteGoloFora;
    public TableView tabelaGolosCasa;
    public TableView tabelaGolosFora;
    public Label labelCasa;
    public Label labelFora;
    public ComboBox parteCombo;
    public TableColumn colunaGoloCasaJog;
    public TableColumn colMinutoCasa;
    public TableColumn colunaGoloForaJog;
    public TableColumn colMinutoFora;
    private ArrayList<String> arrayPartes = new ArrayList<>();
    private ObservableList<String> obPartes;
    public static Jogador jogadorSai;
    public static Jogador jogadorEntra;
    private ArrayList<Evento> arrayGolosCasa = new ArrayList<>();
    private ObservableList<Evento> obGolosCasa;
    private ArrayList<Evento> arrayGolosFora = new ArrayList<>();
    private ObservableList<Evento> obGolosFora;
    private ObservableList<Jogador> obJogadorC;
    private ObservableList<Jogador> obJogadorF;


    public void confirmar(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Tem a certeza que quer concluir a inserção?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaCriarJogo.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }

    public void cancelar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirCenaCriarJogo.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void addGolosCasa(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();
        if (tabelaCasa.getSelectionModel().getSelectedItem() != null) {
            jogador = (Jogador) tabelaCasa.getSelectionModel().getSelectedItem();
            int a = Integer.parseInt(resultadoCasa.getText());
            a = a + 1;
            Golo g = new Golo();
            g.setClube(jogoPublico.getClubeCasa());
            g.setParte(Integer.parseInt((String) parteCombo.getSelectionModel().getSelectedItem()));
            g.setJogador(jogador);
            g.setMinuto(Integer.parseInt(minutoGoloCasa.getText()));
            resultadoCasa.setText(String.valueOf(a));
            DatabaseEvento.inserirGoloDB(g, jogoPublico);
            arrayGolosCasa.add(g);
            colMinutoCasa.setCellValueFactory(new PropertyValueFactory<>("minuto"));
            colunaGoloCasaJog.setCellValueFactory(new PropertyValueFactory<>("jogador"));
            obGolosCasa = FXCollections.observableArrayList(arrayGolosCasa);
            tabelaGolosCasa.setItems(obGolosCasa);
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar o golo", "Selecione um jogador para adicionar um golo");
        }
    }

    public void removeGolosCasa(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();
        if (tabelaGolosCasa.getSelectionModel().getSelectedItem() != null) {
            jogador = (Jogador) tabelaCasa.getSelectionModel().getSelectedItem();
            int a = Integer.parseInt(resultadoCasa.getText());
            a -= 1;
            GoloAnulado golo = new GoloAnulado();
            golo.setClube(jogoPublico.getClubeCasa());
            golo.setParte(Integer.parseInt((String) parteCombo.getSelectionModel().getSelectedItem()));
            golo.setJogador(jogador);
            golo.setMinuto(Integer.parseInt(minutoGoloCasa.getText()));
            resultadoCasa.setText(String.valueOf(a));
            DatabaseEvento.inserirGoloAnuladoDB(golo, jogoPublico);
            int index = tabelaGolosCasa.getSelectionModel().getSelectedIndex();
            arrayGolosCasa.remove(index);
            obGolosCasa = FXCollections.observableArrayList(arrayGolosCasa);
            tabelaGolosCasa.setItems(obGolosCasa);
            tabelaGolosCasa.refresh();
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível remover o golo", "Selecione um jogador para poder remover um golo");
        }
    }

    public void addGolosFora(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();
        if (tabelaFora.getSelectionModel().getSelectedItem() != null) {
            jogador = (Jogador) tabelaFora.getSelectionModel().getSelectedItem();
            int a = Integer.parseInt(resultadoFora.getText());
            a = a + 1;
            Golo g = new Golo();
            g.setClube(jogoPublico.getClubeFora());
            g.setParte(Integer.parseInt((String) parteCombo.getSelectionModel().getSelectedItem()));
            g.setJogador(jogador);
            g.setMinuto(Integer.parseInt(minuteGoloFora.getText()));
            resultadoFora.setText(String.valueOf(a));
            DatabaseEvento.inserirGoloDB(g, jogoPublico);
            arrayGolosFora.add(g);
            colMinutoFora.setCellValueFactory(new PropertyValueFactory<>("minuto"));
            colunaGoloForaJog.setCellValueFactory(new PropertyValueFactory<>("jogador"));
            obGolosFora = FXCollections.observableArrayList(arrayGolosFora);
            tabelaGolosFora.setItems(obGolosFora);
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar o golo", "Selecione um jogador para adicionar um golo");
        }
    }

    public void removeGolosFora(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();
        if (tabelaGolosFora.getSelectionModel().getSelectedItem() != null) {
            jogador = (Jogador) tabelaFora.getSelectionModel().getSelectedItem();
            int a = Integer.parseInt(resultadoFora.getText());
            a -= 1;
            GoloAnulado golo = new GoloAnulado();
            golo.setClube(jogoPublico.getClubeCasa());
            golo.setParte(Integer.parseInt((String) parteCombo.getSelectionModel().getSelectedItem()));
            golo.setJogador(jogador);
            golo.setMinuto(Integer.parseInt(minuteGoloFora.getText()));
            resultadoFora.setText(String.valueOf(a));
            DatabaseEvento.inserirGoloAnuladoDB(golo, jogoPublico);
            int index = tabelaGolosFora.getSelectionModel().getSelectedIndex();
            arrayGolosFora.remove(index);
            obGolosFora = FXCollections.observableArrayList(arrayGolosFora);
            tabelaGolosFora.setItems(obGolosFora);
            tabelaGolosFora.refresh();
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível remover o golo", "Selecione um jogador para poder remover um golo");
        }
    }

    public void adicionarSubstituicaoCasa(ActionEvent actionEvent) throws IOException {
        try {
            Integer.parseInt(minutoGoloCasa.getText());
            if (tabelaCasa.getSelectionModel().getSelectedItem() == null || parteCombo.getSelectionModel().getSelectedItem() == null || minutoGoloCasa.getText().isEmpty()) {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Selecione um jogador", "Por favor selecione um jogador para substituir");
            } else {
                CenaSelecionarJogadorSubstituicao.partePublica = Integer.parseInt((String) parteCombo.getSelectionModel().getSelectedItem());
                CenaSelecionarJogadorSubstituicao.minutoPublico = Integer.parseInt(minutoGoloCasa.getText());
                jogadorSai = (Jogador) tabelaCasa.getSelectionModel().getSelectedItem();
                CenaSelecionarJogadorSubstituicao.flag = true;
                CenaSelecionarJogadorSubstituicao.arraySubstituicoes = substituicoesCasa;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirSelecionarJogadorSubstituicao.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        event.consume();
                    }
                });
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(btnAddAmareloCasa.getScene().getWindow());
                stage.setResizable(false);
                stage.showAndWait();
                obJogadorC = FXCollections.observableArrayList(onzeTitularCasa);
                tabelaCasa.setItems(obJogadorC);
            }
        } catch (Exception e) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Erro no minuto da substituição", "Insira um minuto válido");
        }

    }

    public void adicionarSubstituicaoFora(ActionEvent actionEvent) {
        try {
            Integer.parseInt(minuteGoloFora.getText());
            if (tabelaFora.getSelectionModel().getSelectedItem() == null || parteCombo.getSelectionModel().getSelectedItem() == null || minuteGoloFora.getText().isEmpty()) {
                Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Selecione um jogador", "Por favor selecione um jogador para substituir");
            } else {
                CenaSelecionarJogadorSubstituicao.partePublica = Integer.parseInt((String) parteCombo.getSelectionModel().getSelectedItem());
                CenaSelecionarJogadorSubstituicao.minutoPublico = Integer.parseInt(minuteGoloFora.getText());
                jogadorSai = (Jogador) tabelaFora.getSelectionModel().getSelectedItem();
                CenaSelecionarJogadorSubstituicao.flag = false;
                CenaSelecionarJogadorSubstituicao.arraySubstituicoes = substituicoesFora;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Error404/View/abrirSelecionarJogadorSubstituicao.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        event.consume();
                    }
                });
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(btnAddAmareloCasa.getScene().getWindow());
                stage.setResizable(false);
                stage.showAndWait();
                obJogadorF = FXCollections.observableArrayList(onzeTitularFora);
                tabelaFora.setItems(obJogadorF);
            }
        } catch (Exception e) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Erro no minuto da substituição", "Insira um minuto válido");
        }

    }

    public void adicionarAmareloCasa(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();

        if (tabelaCasa.getSelectionModel().getSelectedItem() != null) {
                jogador = (Jogador) tabelaCasa.getSelectionModel().getSelectedItem();
                Cartao c = new Cartao();
                c.setClube(jogoPublico.getClubeCasa());
                c.setParte(Integer.parseInt((String) parteCombo.getSelectionModel().getSelectedItem()));
                c.setJogador(jogador);
                c.setMinuto(Integer.parseInt(minutoGoloCasa.getText()));
                c.setCor("amarelo");
                DatabaseEvento.inserirCADB(c,jogoPublico);
                int a = tabelaCasa.getSelectionModel().getSelectedIndex();
                onzeTitularCasa.get(a).setA(onzeTitularCasa.get(a).getA() + 1);
                obJogadorC = FXCollections.observableArrayList(onzeTitularCasa);
                tabelaCasa.setItems(obJogadorC);
                tabelaCasa.refresh();
                if (onzeTitularCasa.get(a).getA() == 2) {
                    onzeTitularCasa.remove(tabelaCasa.getSelectionModel().getSelectedItem());
                    obJogadorC.remove(tabelaCasa.getSelectionModel().getSelectedItem());
                    tabelaCasa.setItems(obJogadorC);
                    tabelaCasa.refresh();
                }
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar o cartao", "");
        }
    }

    public void adicionarVermelhoCasa(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();
        if (tabelaCasa.getSelectionModel().getSelectedItem() != null) {
            jogador = (Jogador) tabelaCasa.getSelectionModel().getSelectedItem();
            Cartao c = new Cartao();
            c.setClube(jogoPublico.getClubeCasa());
            c.setParte(Integer.parseInt ((String) parteCombo.getSelectionModel().getSelectedItem()));
            c.setJogador(jogador);
            c.setMinuto(Integer.parseInt(minutoGoloCasa.getText()));
            c.setCor("vermelho");
            DatabaseEvento.inserirCADB(c,jogoPublico);
            int a=tabelaCasa.getSelectionModel().getSelectedIndex();
            onzeTitularCasa.remove(a);
            obJogadorC = FXCollections.observableArrayList(onzeTitularCasa);
            tabelaCasa.setItems(obJogadorC);
            tabelaCasa.refresh();
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar o cartao", "");
        }

    }

    public void adicionarAmareloFora(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();
        if (tabelaFora.getSelectionModel().getSelectedItem() != null) {
            jogador = (Jogador) tabelaFora.getSelectionModel().getSelectedItem();
            Cartao c = new Cartao();
            c.setClube(jogoPublico.getClubeFora());
            c.setParte(Integer.parseInt ((String) parteCombo.getSelectionModel().getSelectedItem()));
            c.setJogador(jogador);
            c.setMinuto(Integer.parseInt(minuteGoloFora.getText()));
            c.setCor("amarelo");
            DatabaseEvento.inserirCADB(c,jogoPublico);
            int a=tabelaFora.getSelectionModel().getSelectedIndex();
            onzeTitularFora.get(a).setA(onzeTitularFora.get(a).getA()+1);
            obJogadorF = FXCollections.observableArrayList(onzeTitularFora);
            tabelaFora.setItems(obJogadorF);
            tabelaFora.refresh();
            if (onzeTitularFora.get(a).getA() == 2) {
                onzeTitularFora.remove(tabelaFora.getSelectionModel().getSelectedItem());
                obJogadorF.remove(tabelaFora.getSelectionModel().getSelectedItem());
                tabelaFora.setItems(obJogadorF);
                tabelaFora.refresh();
            }
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar o cartao", "");
        }
    }

    public void adicionarVermelhoFora(ActionEvent actionEvent) throws SQLException {
        Jogador jogador = new Jogador();
        if (tabelaFora.getSelectionModel().getSelectedItem() != null) {
            jogador = (Jogador) tabelaFora.getSelectionModel().getSelectedItem();
            Cartao c = new Cartao();
            c.setClube(jogoPublico.getClubeFora());
            c.setParte(Integer.parseInt ((String) parteCombo.getSelectionModel().getSelectedItem()));
            c.setJogador(jogador);
            c.setMinuto(Integer.parseInt(minuteGoloFora.getText()));
            c.setCor("vermelho");
            DatabaseEvento.inserirCADB(c,jogoPublico);
            int a=tabelaFora.getSelectionModel().getSelectedIndex();
            onzeTitularFora.remove(a);
            obJogadorF = FXCollections.observableArrayList(onzeTitularFora);
            tabelaFora.setItems(obJogadorF);
            tabelaFora.refresh();
        } else {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar o cartao", "");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.jogoPublico.setId(DatabaseJogo.getIdJogo(CenaJogoEventos.jogoPublico.getClubeCasa().getId(), CenaJogoEventos.jogoPublico.getClubeFora().getId(), CenaJogoEventos.jogoPublico.getJornada().getId()));
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadData() throws SQLException {
        colunaIdCasa.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaIdFora.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaJogadorCasa.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaJogadorFora.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaAmarelosCasa.setCellValueFactory(new PropertyValueFactory<>("a"));
        colunaAmarelosFora.setCellValueFactory(new PropertyValueFactory<>("a"));
        arrayPartes.add("1");
        arrayPartes.add("2");
        obPartes = FXCollections.observableArrayList(arrayPartes);
        parteCombo.setItems(obPartes);
        //arrayJogadorC = DatabaseJogador.getTodosJogadoresClubeDB(jogoPublico.getClubeCasa().getId());//jogador casa
        obJogadorC = FXCollections.observableArrayList(onzeTitularCasa);
        tabelaCasa.setItems(obJogadorC);
        //arrayJogadorF = DatabaseJogador.getTodosJogadoresClubeDB(jogoPublico.getClubeFora().getId()); //jogador fora
        obJogadorF = FXCollections.observableArrayList(onzeTitularFora);
        tabelaFora.setItems(obJogadorF);
    }
}

