package Error404.Model.Database;

import Error404.Model.Code.Clube;
import Error404.Model.Code.Contrato;
import Error404.Model.Code.Jogador;
import Error404.Model.Code.Treinador;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class DatabaseTreinador {

    /**
     * @param t
     * @return
     * @throws SQLException
     */
    public static boolean inserirTreinadorDB(Treinador t) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Treinador" +
                    " (nome, nacionalidade, data_nascimento) values (?,?,?)");
            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getNacionalidade());
            stmt.setString(3, t.getDataNascimento());
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
     * @param treinador
     * @return
     * @throws SQLException
     */
    public static boolean eliminarTreinadorDB(Treinador treinador) throws SQLException {
        if (treinador != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("DELETE FROM Treinador WHERE id_treinador = ?");
                pst.setInt(1, treinador.getId());
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
     * @return
     * @throws SQLException
     */
    public static ArrayList<Treinador> getTodasTreinadoresDB() throws SQLException {
        ArrayList<Treinador> listaTreinadores = new ArrayList<>();
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Treinador");
            while (rst.next()) {
                Treinador t = new Treinador();
                t.setId(rst.getInt("id_treinador"));
                t.setNome(rst.getString("nome"));
                t.setNacionalidade(rst.getString("nacionalidade"));
                t.setDataNascimento(rst.getString("data_nascimento"));
                listaTreinadores.add(t);
            }
            rst.close();
            st.close();
            con.close();
            return listaTreinadores;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param id_clube
     * @return
     * @throws SQLException
     */
    public static Treinador getTreinadorClubeDB(int id_clube) throws SQLException {
        Treinador t = new Treinador();
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM TreinadorClube INNER JOIN Treinador " +
                    "ON Treinador.id_treinador=TreinadorClube.id_treinador WHERE id_clube=" + id_clube + " AND data_fim is NULL;");
            while (rst.next()) {
                t.setId(rst.getInt("id_treinador"));
                t.setNome(rst.getString("nome"));
                t.setNacionalidade(rst.getString("nacionalidade"));
                t.setDataNascimento(rst.getString("data_nascimento"));
            }
            rst.close();
            st.close();
            con.close();
            return t;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public static boolean atualizarTreinadorDB(Treinador t) throws SQLException {
        if (t != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("UPDATE Treinador SET nome = ?, nacionalidade = ?, data_nascimento = ? WHERE id_treinador = ?");
                pst.setString(1, t.getNome());
                pst.setString(2, t.getNacionalidade());
                pst.setString(3, t.getDataNascimento());
                pst.setInt(4, t.getId());
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
     * @param treinador
     * @return
     * @throws SQLException
     */
    public static boolean checkRelationTreinador(Treinador treinador) throws SQLException {
        boolean flag = true;
        if (treinador != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement ps =
                        con.prepareStatement
                                ("SELECT DISTINCT id_treinador FROM TreinadorClube WHERE id_treinador = ?");
                ps.setInt(1, treinador.getId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flag = true;
                } else {
                    flag = false;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return flag;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static Treinador procurarTreinadorPorId(int id) throws SQLException {
        ArrayList<Treinador> listaTreinadores = getTodasTreinadoresDB();
        Treinador t = new Treinador();
        for (Treinador treinador: listaTreinadores) {
            if (treinador.getId() == id) {
                t = treinador;
            }
        }
        return t;
    }

    /**
     *
     * @param treinador
     * @return
     * @throws SQLException
     */
    public static Contrato getContratoTreinador(Treinador treinador) throws SQLException {
        try (Connection con = DatabaseConnect.getConnectionBD()){
            Contrato c = new Contrato();
            try (PreparedStatement st = con.prepareStatement("select TOP 1 * from TreinadorClube where id_treinador = ? order by data_fim, data_inicio DESC")) {
                st.setInt(1, treinador.getId());
                try (ResultSet rst = st.executeQuery()) {
                    while (rst.next()) {
                        c.setTreinador(procurarTreinadorPorId(rst.getInt("id_treinador")));
                        c.setClube(DatabaseClube.procurarClubePorId(rst.getInt("id_clube")));
                        c.setDataInicio(rst.getString("data_inicio"));
                        c.setDataFim(rst.getString("data_fim"));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param idTreinador
     * @param idClube
     * @param data
     * @return
     * @throws SQLException
     */
    public static boolean transferirTreinador(int idTreinador, int idClube, String data) throws SQLException {
        try (Connection con = DatabaseConnect.getConnectionBD()) {
            try (PreparedStatement st = con.prepareStatement("EXECUTE transferirTreinador @id_treinador = ?, @id_clube = ?, @data = ?")) {
                st.setInt(1, idTreinador);
                st.setInt(2, idClube);
                st.setString(3, data);
                st.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static Clube getClubeAtual(Treinador t) throws SQLException {
        Clube c = null;
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM TreinadorClube INNER JOIN Clube ON Clube.id_clube=TreinadorClube.id_clube WHERE id_treinador=" + t.getId() + " AND data_fim is NULL;");
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

    public static boolean inserirTreinadorNumClubeDB(int idTreinador, int idClube, String dataInicio) throws SQLException {
        try {
            try (Connection con = DatabaseConnect.getConnectionBD()) {
                try (PreparedStatement stmt = con.prepareStatement("insert into TreinadorClube (id_treinador, id_clube, data_inicio) values (?,?,?)")) {
                    stmt.setInt(1, idTreinador);
                    stmt.setInt(2, idClube);
                    stmt.setString(3, dataInicio);
                    stmt.executeUpdate();
                } catch (SQLException throwables) {}
            } catch (SQLException throwables) {}
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
