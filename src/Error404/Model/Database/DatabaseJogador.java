package Error404.Model.Database;

import Error404.Model.Code.Contrato;
import Error404.Model.Code.Jogador;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class DatabaseJogador {

    /**
     * @param j
     * @return
     * @throws SQLException
     */
    public static boolean inserirJogadorDB(Jogador j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Jogador (nome, nacionalidade, data_nascimento, posicao) VALUES (?,?,?,?)");
            stmt.setString(1, j.getNome());
            stmt.setString(2, j.getNacionalidade());
            stmt.setString(3, j.getDataNascimento());
            stmt.setString(4, j.getPosicao());
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
     * @param idJogador
     * @param idClube
     * @param dataInicio
     * @return
     * @throws SQLException
     */
    public static boolean inserirJogadorNumClubeDB(int idJogador, int idClube, String dataInicio) throws SQLException {
        try {
            try (Connection con = DatabaseConnect.getConnectionBD()) {
                try (PreparedStatement stmt = con.prepareStatement("insert into JogadorClube (id_jogador, id_clube, data_inicio) values (?,?,?)")) {
                    stmt.setInt(1, idJogador);
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

    public static boolean inserirJogadorJogoDB(int id_jogador_clube, int id_jogo, boolean titular) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO JogadorJogo VALUES (?,?,?)");
            stmt.setInt(1, id_jogo);
            stmt.setInt(2, id_jogador_clube);
            stmt.setBoolean(3, titular);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogador> getTodosJogadoresDB() throws SQLException {
        ArrayList<Jogador> listaJogadores = new ArrayList<>();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM Jogador");
            while (rst.next()) {
                Jogador j = new Jogador();
                j.setId(rst.getInt("id_jogador"));
                j.setNome(rst.getString("nome"));
                j.setNacionalidade(rst.getString("nacionalidade"));
                j.setDataNascimento(rst.getString("data_nascimento"));
                j.setPosicao(rst.getString("posicao"));
                listaJogadores.add(j);
            }
            rst.close();
            st.close();
            con.close();
            return listaJogadores;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogador> getTodosJogadoresClubeDB(int id_clube) throws SQLException {
        ArrayList<Jogador> listaJogadores = new ArrayList<>();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM JogadorClube INNER JOIN Jogador " +
                    "ON Jogador.id_jogador=JogadorClube.id_jogador WHERE id_clube=" + id_clube + " AND data_fim is NULL;");
            while (rst.next()) {
                Jogador j = new Jogador();
                j.setId(rst.getInt("id_jogador"));
                j.setNome(rst.getString("nome"));
                j.setNacionalidade(rst.getString("nacionalidade"));
                j.setDataNascimento(rst.getString("data_nascimento"));
                j.setPosicao(rst.getString("posicao"));
                listaJogadores.add(j);
            }
            rst.close();
            st.close();
            con.close();
            return listaJogadores;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * @param jogador
     * @return
     * @throws SQLException
     */
    public static Contrato getContratoJogador(Jogador jogador) throws SQLException {
        try (Connection con = DatabaseConnect.getConnectionBD()) {
            Contrato c = new Contrato();
            try (PreparedStatement st = con.prepareStatement("select TOP 1 * from JogadorClube where id_jogador = ? order by id_jogador_clube DESC")) {
                st.setInt(1, jogador.getId());
                try (ResultSet rst = st.executeQuery()) {
                    while (rst.next()) {
                        c.setJogador(procurarJogadorPorId(rst.getInt("id_jogador")));
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
     * @param id
     * @return
     * @throws SQLException
     */
    public static Jogador procurarJogadorPorId(int id) throws SQLException {
        ArrayList<Jogador> listaJogadores = getTodosJogadoresDB();
        Jogador j = new Jogador();
        for (Jogador jogador : listaJogadores) {
            if (jogador.getId() == id) {
                j = jogador;
            }
        }
        return j;
    }

    /**
     * @param id_jogador_clube
     * @return
     * @throws SQLException
     */
    public static Jogador getJogadorPorJogadorClube(int id_jogador_clube) throws SQLException {
        ArrayList<Jogador> listaJogadores = getTodosJogadoresDB();

        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM JogadorClube;");
            Jogador j = new Jogador();
            while (rst.next()) {
                if (rst.getInt("id_jogador_clube") == id_jogador_clube) {
                    j.setId(rst.getInt("id_jogador"));
                    for (Jogador jog : listaJogadores) {
                        if (j.getId() == jog.getId())
                            j = jog;

                    }
                }
            }
            rst.close();
            st.close();
            con.close();
            return j;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;

        }
    }

    /**
     * @param jogador
     * @return
     * @throws SQLException
     */
    public static int getIdJogadorClubePorJogador(Jogador jogador) throws SQLException {
        try {
            int id_jogador_clube = 0;
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement st = con.prepareStatement("SELECT * FROM JogadorClube where data_fim is null and id_jogador= ?");
            st.setInt(1,jogador.getId());
            ResultSet rst = st.executeQuery();
            while (rst.next()) {
                id_jogador_clube = rst.getInt("id_jogador_clube");
            }
            return id_jogador_clube;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * @param id_jogador_clube
     * @param id_jogo
     * @return
     * @throws SQLException
     */
    public static int getIdJogadorJogoPorIdJogadorClubeEIdJogo(int id_jogador_clube, int id_jogo) throws SQLException {
        int id_jogador_jogo = 0;
        try (Connection con = DatabaseConnect.getConnectionBD()) {
            try (PreparedStatement st = con.prepareStatement("SELECT * FROM JogadorJogo WHERE id_jogador_clube = ? AND id_jogo = ?")) {
                st.setInt(1, id_jogador_clube);
                st.setInt(2, id_jogo);
                try (ResultSet rst = st.executeQuery()) {
                    while (rst.next()) {
                        id_jogador_jogo = rst.getInt("id_jogador_jogo");
                    }
                } catch (SQLException throwables) {}
            } catch (SQLException throwables) {}
            return id_jogador_jogo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public static int getIdJogadorJogoPorIdJogadorEIdJogo(int id_jogador, int id_jogo) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM JogadorJogo INNER JOIN Jogador.id_jogador=JogadorJogo.id_jogador WHERE id_jogador=" + id_jogador + " AND id_jogo=" + id_jogo);
            int id_jogador_jogo = 0;
            id_jogador_jogo = rst.getInt("id_jogador_jogo");
            return id_jogador_jogo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * @param j
     * @return
     * @throws SQLException
     */
    public static boolean atualizarJogadorDB(Jogador j) throws SQLException {
        if (j != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("UPDATE Jogador SET nome = ?, nacionalidade = ?, data_nascimento = ?, posicao = ? WHERE id_jogador = ?");
                pst.setString(1, j.getNome());
                pst.setString(2, j.getNacionalidade());
                pst.setString(3, j.getDataNascimento());
                pst.setString(4, j.getPosicao());
                pst.setInt(5, j.getId());
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
     * @param j
     * @return
     * @throws SQLException
     */
    public static boolean eliminarJogadorDB(Jogador j) throws SQLException {
        if (j != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement pst = con.prepareStatement("DELETE FROM Jogador " +
                        "WHERE id_jogador = ?");
                pst.setInt(1, j.getId());
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
     * @param j
     * @return
     * @throws SQLException
     */
    public static boolean checkRelationJogador(Jogador j) throws SQLException {
        boolean flag = true;
        if (j != null) {
            try {
                Connection con = DatabaseConnect.getConnectionBD();
                PreparedStatement ps =
                        con.prepareStatement
                                ("SELECT DISTINCT Jogador.id_jogador\n" +
                                        "  FROM Jogador\n" +
                                        "  INNER JOIN JogadorClube\n" +
                                        "  ON Jogador.id_jogador = JogadorClube.id_jogador\n" +
                                        "  WHERE Jogador.id_jogador = ?");
                ps.setInt(1, j.getId());
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
     * @param idJogador
     * @param idClube
     * @param data
     * @return
     * @throws SQLException
     */
    public static boolean transferirJogador(int idJogador, int idClube, String data) throws SQLException {
        try (Connection con = DatabaseConnect.getConnectionBD()) {
            try (PreparedStatement st = con.prepareStatement("EXECUTE transferirJogador @id_jogador = ?, @id_clube = ?, @data = ?")) {
                st.setInt(1, idJogador);
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

    /**
     * @param j
     * @return
     * @throws SQLException
     */
    public static int getGolos(Jogador j) throws SQLException {
        int g = 0;
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM vEventosJogo WHERE id_jogador=" + j.getId() + " AND id_tipo_evento=" + 1);
            while (rst.next()) {
                g += 1;
            }
            rst.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return g;
    }

    /**
     * @param j
     * @return
     * @throws SQLException
     */
    public static int getAmarelos(Jogador j) throws SQLException {
        int g = 0;
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM vEventosJogo WHERE id_jogador=" + j.getId() + " AND id_tipo_evento=" + 5);
            while (rst.next()) {
                g += 1;
            }
            rst.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return g;
    }

    /**
     * @param j
     * @return
     * @throws SQLException
     */
    public static int getVermelhos(Jogador j) throws SQLException {
        int g = 0;
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM vEventosJogo WHERE id_jogador=" + j.getId() + " AND id_tipo_evento=" + 6);
            while (rst.next()) {
                g += 1;
            }
            rst.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return g;
    }

    /**
     * @param j
     * @return
     * @throws SQLException
     */
    public static int getAutoGolos(Jogador j) throws SQLException {
        int g = 0;
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM vEventosJogo WHERE id_jogador=" + j.getId() + " AND id_tipo_evento=" + 7);
            while (rst.next()) {
                g += 1;
            }
            rst.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return g;
    }
}
