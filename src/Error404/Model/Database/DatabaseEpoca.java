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
public class DatabaseEpoca {

    /**
     *
     * @param ep
     * @return
     * @throws SQLException
     */
    public static boolean inserirEpocaDB(Epoca ep) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Epoca " +
                    "(id_epoca, nome, data_inicio, data_fim) VALUES (?,?,?,?)");
            stmt.setInt(1, ep.getId());
            stmt.setString(2, ep.getNome());
            stmt.setString(3, ep.getDataInicio());
            stmt.setString(4, ep.getDataFim());
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
    public static ArrayList<Epoca> getTodasEpocasDB() throws SQLException {
        ArrayList<Epoca> listaEpocas = new ArrayList<>();
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Epoca");
            while (rst.next()) {
                Epoca e = new Epoca();
                e.setId(rst.getInt("id_epoca"));
                e.setNome(rst.getString("nome"));
                e.setDataInicio(rst.getString("data_inicio"));
                e.setDataFim(rst.getString("data_fim"));
                listaEpocas.add(e);
                rst.close();
                st.close();
                con.close();
            }
            return listaEpocas;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }
    }

    /**
     *
     * @param e
     * @return
     * @throws SQLException
     */
    public static boolean atualizarEpocaDB(Epoca e) throws SQLException {
        if (e != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("UPDATE TABLE Epoca " +
                        "SET nome = ?, data_inicio = ?, data_fim = ?" +
                        "WHERE id_epoca LIKE ?");
                pst.setString(1, e.getNome());
                pst.setString(2, e.getDataInicio());
                pst.setString(3, e.getDataFim());
                pst.setInt(4, e.getId());
                pst.executeUpdate();
                pst.close();
                con.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param e
     * @return
     * @throws SQLException
     */
    public static boolean eliminarEpocaDB(Epoca e) throws SQLException {
        if (e != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("DELETE FROM Epoca " +
                        "WHERE id_epoca = ?");
                pst.setInt(1, e.getId());
                pst.executeUpdate();
                pst.close();
                con.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static Epoca procurarEpocaPorId(int id) throws SQLException {
        ArrayList<Epoca> listaEpocas = getTodasEpocasDB();
        Epoca e = new Epoca();
        for (Epoca epoca: listaEpocas) {
            if (epoca.getId() == id) {
                e=epoca;
            }
        }
        return e;
    }
}
