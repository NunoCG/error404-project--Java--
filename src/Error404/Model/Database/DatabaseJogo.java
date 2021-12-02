package Error404.Model.Database;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Jogo;
import Error404.Model.Code.Jornada;
import javafx.scene.control.Alert;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class DatabaseJogo {

    /**
     *
     * @param j
     * @return
     * @throws SQLException
     */
    public static boolean inserirJogoDB(Jogo j) {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Jogo VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, j.getClubeCasa().getId());
            stmt.setInt(2, j.getClubeFora().getId());
            stmt.setInt(3, j.getJornada().getId());
            stmt.setInt(4, j.getDescontoPrimeira());
            stmt.setInt(5,j.getDescontoSegunda());
            stmt.setString(6, j.getData());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogo> getTodosJogosJornada(int id_jornada) throws SQLException {
        ArrayList<Jogo> listaJogos = new ArrayList<>();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Jogo WHERE id_jornada="+id_jornada+";");
            while (rst.next()) {
                Jogo j = new Jogo();
                j.setId(rst.getInt("id_jogo"));
                j.setClubeCasa(DatabaseClube.procurarClubePorId(rst.getInt("id_clubec")));
                j.setClubeFora(DatabaseClube.procurarClubePorId(rst.getInt("id_clubef")));
                j.setDescontoPrimeira(rst.getInt("desconto_primeira"));
                j.setDescontoSegunda(rst.getInt("desconto_segunda"));
                j.setEventos(DatabaseEvento.getEventosJogoDB(j.getId()));
                j.setData(rst.getString("data_jogo"));
                listaJogos.add(j);
            }
            con.close();
            st.close();
            rst.close();
            return listaJogos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }
    }

    public static ArrayList<Jogo> getTodosJogos() throws SQLException {
        ArrayList<Jogo> listaJogos = new ArrayList<>();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Jogo");
            while (rst.next()) {
                Jogo j = new Jogo();
                j.setId(rst.getInt("id_jogo"));
                Clube casa = new Clube();
                Clube fora = new Clube();
                casa.setId(rst.getInt("id_clubec"));
                fora.setId(rst.getInt("id_clubef"));
                j.setClubeCasa(casa);
                j.setClubeFora(fora);
                Jornada jornada = new Jornada();
                jornada.setId(rst.getInt("id_jornada"));
                j.setJornada(jornada);
                j.setDescontoPrimeira(rst.getInt("desconto_primeira"));
                j.setDescontoSegunda(rst.getInt("desconto_segunda"));
                j.setEventos(DatabaseEvento.getEventosJogoDB(j.getId()));
                j.setData(rst.getString("data_jogo"));
                listaJogos.add(j);
            }
            rst.close();
            st.close();
            con.close();
            return listaJogos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }
    }

    public static ArrayList<Jogo> getTodosJogosClubeEpoca(int id_clube) throws SQLException {
        ArrayList<Jogo> listaJogos = new ArrayList<>();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Jogo WHERE id_clubec="+id_clube+" OR id_clubef="+id_clube);
            while (rst.next()) {
                Jogo j = new Jogo();
                j.setId(rst.getInt("id_jogo"));
                Clube casa = new Clube();
                Clube fora = new Clube();
                casa.setId(rst.getInt("id_clubec"));
                fora.setId(rst.getInt("id_clubef"));
                j.setClubeCasa(casa);
                j.setClubeFora(fora);
                Jornada jornada = new Jornada();
                jornada.setId(rst.getInt("id_jornada"));
                j.setJornada(jornada);
                j.setDescontoPrimeira(rst.getInt("desconto_primeira"));
                j.setDescontoSegunda(rst.getInt("desconto_segunda"));
                j.setEventos(DatabaseEvento.getEventosJogoDB(j.getId()));
                j.setData(rst.getString("data_jogo"));
                listaJogos.add(j);
            }
            rst.close();
            st.close();
            con.close();
            return listaJogos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static int getIdJogo(int id_clubec, int id_clubef, int id_jornada) throws SQLException {

        int a =0;
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Jogo WHERE id_clubec="+id_clubec+" AND id_clubef="+id_clubef+" AND id_jornada="+id_jornada);
            while (rst.next()) {
                a=rst.getInt("id_jogo");
            }
            rst.close();
            st.close();
            con.close();
            return a;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
