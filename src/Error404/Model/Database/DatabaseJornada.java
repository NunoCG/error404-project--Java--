package Error404.Model.Database;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Epoca;
import Error404.Model.Code.Jornada;
import javafx.scene.control.Alert;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class DatabaseJornada {

    /**
     *
     * @param j
     * @return
     * @throws SQLException
     */
    public static boolean inserirJornadaDB(Jornada j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Jornada (nr_jornada, id_epoca, data_jornada) VALUES (?,?,?)");
            stmt.setInt(1, j.getNumJornada());
            stmt.setInt(2, j.getEpoca().getId());
            stmt.setString(3, j.getData());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jornada> getTodasJornadasDB() throws SQLException {
        ArrayList<Jornada> listaJornadas = new ArrayList<>();
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Jornada");
            while (rst.next()) {
                Jornada j = new Jornada();
                j.setId(rst.getInt("id_jornada"));
                j.setNumJornada(rst.getInt("nr_jornada"));
                Epoca epoca = new Epoca ();
                epoca.setId(rst.getInt("id_epoca"));
                j.setEpoca(epoca);
                j.setData(rst.getString("data_jornada"));
                listaJornadas.add(j);
            }
            rst.close();
            st.close();
            con.close();
            return listaJornadas;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }
    }

    public static ArrayList<Jornada> getJornadasEpocaDB(int id_epoca) throws SQLException {
        ArrayList<Jornada> listaJornadas = new ArrayList<>();
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Jornada WHERE id_epoca=" + id_epoca+";");
            while (rst.next()) {
                Jornada j = new Jornada();
                j.setId(rst.getInt("id_jornada"));
                j.setNumJornada(rst.getInt("nr_jornada"));
                Epoca e = new Epoca();
                e.setId(rst.getInt("id_epoca"));
                j.setEpoca(e);
                listaJornadas.add(j);
            }
            rst.close();
            st.close();
            con.close();
            return listaJornadas;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static Jornada procurarJornadaPorId(int id) throws SQLException {
        ArrayList<Jornada> listaJornadas = getTodasJornadasDB();
        Jornada j = new Jornada();
        for (Jornada jornada: listaJornadas) {
            if (jornada.getId() == id) {
                j=jornada;
            }
        }
        return j;
    }
}
