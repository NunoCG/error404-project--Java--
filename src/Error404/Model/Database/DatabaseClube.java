package Error404.Model.Database;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Jogador;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class DatabaseClube {

    /**
     *
     * @param c
     * @return
     * @throws SQLException
     */
    public static boolean inserirClubeDB(Clube c) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("insert into Clube" +
                    " (nome, pais, cidade, estadio) values (?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getPais());
            stmt.setString(3, c.getCidade());
            stmt.setString(4,c.getEstadio());
            stmt.executeUpdate();
            con.close();
            stmt.close();
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
    public static ArrayList<Clube> getTodosClubesDB() throws SQLException {
        ArrayList<Clube> listaClubes = new ArrayList<>();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Clube");
            while (rst.next()) {
                Clube c = new Clube();
                c.setId(rst.getInt("id_clube"));
                c.setNome(rst.getString("nome"));
                c.setPais(rst.getString("pais"));
                c.setCidade(rst.getString("cidade"));
                c.setEstadio(rst.getString("estadio"));
                //c.setJogadores(DatabaseJogador.getTodosJogadoresClubeDB(c.getId()));
                //c.setTreinador(DatabaseTreinador.getTreinadorClubeDB(c.getId()));
                listaClubes.add(c);
            }
            con.close();
            st.close();
            rst.close();
            return listaClubes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }
    }

    public static ArrayList<Clube> getTodosClubesEpocaAtualDB(int id_epoca) throws SQLException {
        ArrayList<Clube> listaClubes = new ArrayList<>();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT distinct id_clube,nome FROM vEventosJogo WHERE id_epoca= '"+id_epoca+"'");
            while (rst.next()) {
                Clube c = new Clube();
                c.setId(rst.getInt("id_clube"));
                c.setNome(rst.getString("nome"));
                listaClubes.add(c);
            }
            rst.close();
            st.close();
            con.close();
            return listaClubes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }
    }

    /**
     *
     * @param c
     * @return
     * @throws SQLException
     */
    public static boolean atualizarCubeDB(Clube c) throws SQLException {
        if (c != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("UPDATE Clube SET nome = ?, pais = ?, cidade = ?, estadio = ? WHERE id_clube LIKE ?");
                pst.setString(1, c.getNome());
                pst.setString(2, c.getPais());
                pst.setString(3, c.getCidade());
                pst.setString(4, c.getEstadio());
                pst.setInt(5, c.getId());
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
     * @param c
     * @return
     * @throws SQLException
     */
    public static boolean eliminarClubeDB(Clube c) throws SQLException {
        if (c != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("DELETE FROM Clube WHERE id_clube = ?");
                pst.setInt(1, c.getId());
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
     * @param id
     * @return
     * @throws SQLException
     */
    public static Clube procurarClubePorId(int id) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT nome FROM Clube WHERE id_clube="+id);
            Clube c = new Clube();
            while (rst.next()) {
                c.setId(id);
                c.setNome(rst.getString("nome"));
            }
            rst.close();
            con.close();
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static Clube getClubeAtual(Jogador j) throws SQLException {
        Clube c = null;
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM JogadorClube INNER JOIN Clube ON Clube.id_clube=JogadorClube.id_clube WHERE id_jogador=" + j.getId() + " AND data_fim is NULL;");
            while (rst.next()) {
                c = new Clube();
                c.setId(rst.getInt("id_clube"));
                c.setNome(rst.getString("nome"));
            }
            rst.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return c;
    }
}
