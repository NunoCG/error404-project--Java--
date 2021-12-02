package Error404.Controller;

import Error404.Model.Code.Jogador;
import Error404.Model.Code.Substituicao;
import Error404.Model.Database.DatabaseEvento;
import Error404.Model.Database.DatabaseJogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CenaSelecionarJogadorSubstituicao implements Initializable {
    public static ArrayList<Jogador> arraySubstituicoes;
    public static boolean flag;
    public TableView tabelaSubstituicoes;
    public Button btnConfirmar;
    public Button btnCancelar;
    public TableColumn colID;
    public TableColumn colJogador;
    private ObservableList<Jogador> obSubstituicoes;
    public static int partePublica;
    public static int minutoPublico;

    public void confirmar(ActionEvent actionEvent) throws SQLException {
        if (tabelaSubstituicoes.getSelectionModel().getSelectedItem() == null) {
            Menu.showAlert(Alert.AlertType.ERROR, "Erro", "Jogador não selecionado", "Por favor selecione um jogador");
        } else {
            if (flag == true) {
                //Lista jogadores casa
                CenaJogoEventos.jogadorEntra = (Jogador) tabelaSubstituicoes.getSelectionModel().getSelectedItem();
                CenaJogoEventos.onzeTitularCasa.add(CenaJogoEventos.jogadorEntra);
                CenaJogoEventos.onzeTitularCasa.remove(CenaJogoEventos.jogadorSai);
                CenaJogoEventos.substituicoesCasa.remove(CenaJogoEventos.jogadorEntra);
                Substituicao sub = new Substituicao();
                sub.setClube(CenaJogoEventos.jogoPublico.getClubeCasa());
                sub.setJogadorEntra(CenaJogoEventos.jogadorEntra);
                sub.setJogadorSai(CenaJogoEventos.jogadorSai);
                sub.setParte(partePublica);
                sub.setMinuto(minutoPublico);
                int id = DatabaseJogo.getIdJogo(CenaJogoEventos.jogoPublico.getClubeCasa().getId(), CenaJogoEventos.jogoPublico.getClubeFora().getId(), CenaJogoEventos.jogoPublico.getJornada().getId());
                CenaJogoEventos.jogoPublico.setId(id);
                DatabaseEvento.inserirSubEDB(sub, CenaJogoEventos.jogoPublico);
                DatabaseEvento.inserirSubSDB(sub, CenaJogoEventos.jogoPublico);
                btnConfirmar.getScene().getWindow().hide();
            } else {
                //Lista jogadores fora
                CenaJogoEventos.jogadorEntra = (Jogador) tabelaSubstituicoes.getSelectionModel().getSelectedItem();
                CenaJogoEventos.onzeTitularFora.add(CenaJogoEventos.jogadorEntra);
                CenaJogoEventos.onzeTitularFora.remove(CenaJogoEventos.jogadorSai);
                CenaJogoEventos.substituicoesFora.remove(CenaJogoEventos.jogadorEntra);
                Substituicao sub = new Substituicao();
                sub.setClube(CenaJogoEventos.jogoPublico.getClubeFora());
                sub.setJogadorEntra(CenaJogoEventos.jogadorEntra);
                sub.setJogadorSai(CenaJogoEventos.jogadorSai);
                sub.setParte(partePublica);
                sub.setMinuto(minutoPublico);
                int id = DatabaseJogo.getIdJogo(CenaJogoEventos.jogoPublico.getClubeCasa().getId(), CenaJogoEventos.jogoPublico.getClubeFora().getId(), CenaJogoEventos.jogoPublico.getJornada().getId());
                CenaJogoEventos.jogoPublico.setId(id);
                DatabaseEvento.inserirSubEDB(sub, CenaJogoEventos.jogoPublico);
                DatabaseEvento.inserirSubSDB(sub, CenaJogoEventos.jogoPublico);
                btnConfirmar.getScene().getWindow().hide();
            }
        }
    }

    public void cancelar(ActionEvent actionEvent) {
        btnCancelar.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void loadData() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogador.setCellValueFactory(new PropertyValueFactory<>("nome"));
        obSubstituicoes = FXCollections.observableArrayList(arraySubstituicoes);
        tabelaSubstituicoes.setItems(obSubstituicoes);
    }
}
