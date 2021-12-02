package Error404.Model.Database;

import Error404.Model.Code.*;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

import Error404.Model.Code.Evento;


/**
 *
 */
public class DatabaseEvento {


    public static boolean inserirGoloDB(Golo g, Jogo j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("execute InserirEvento @id_tipo_evento=?, @minuto=?, @parte=?, @id_jogador_jogo=?");
            stmt.setInt(1, 1);
            stmt.setInt(2, g.getMinuto());
            stmt.setInt(3, g.getParte());
            stmt.setInt(4, DatabaseJogador.getIdJogadorJogoPorIdJogadorClubeEIdJogo(DatabaseJogador.getIdJogadorClubePorJogador(g.getJogador()), j.getId()));
            stmt.executeUpdate();
            con.close();
            stmt.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static boolean inserirGoloAnuladoDB(GoloAnulado ga, Jogo j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("execute InserirEvento @id_tipo_evento=?, @minuto=?, @parte=?, @id_jogador_jogo=?");
            stmt.setInt(1, 2);
            stmt.setInt(2, ga.getMinuto());
            stmt.setInt(3, ga.getParte());
            stmt.setInt(4, DatabaseJogador.getIdJogadorJogoPorIdJogadorClubeEIdJogo(DatabaseJogador.getIdJogadorClubePorJogador(ga.getJogador()), j.getId()));
            stmt.executeUpdate();
            con.close();
            stmt.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static boolean inserirGADB(GoloAnulado ga, Jogo j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("execute InserirEvento @id_tipo_evento=?, @minuto=?, @parte=?, @id_jogador_jogo=?");
            stmt.setInt(1, 2);
            stmt.setInt(2, ga.getMinuto());
            stmt.setInt(3, ga.getParte());
            stmt.setInt(4, DatabaseJogador.getIdJogadorJogoPorIdJogadorClubeEIdJogo(DatabaseJogador.getIdJogadorClubePorJogador(ga.getJogador()), j.getId()));
            stmt.executeUpdate();
            con.close();
            stmt.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static boolean inserirSubEDB(Substituicao s, Jogo j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Evento VALUES (?,?,?,?)");
            stmt.setInt(1, 3);
            stmt.setInt(2, s.getMinuto());
            stmt.setInt(3, s.getParte());
            stmt.setInt(4, DatabaseJogador.getIdJogadorJogoPorIdJogadorClubeEIdJogo(DatabaseJogador.getIdJogadorClubePorJogador(s.getJogadorEntra()), j.getId()));
            stmt.executeUpdate();
            con.close();
            stmt.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static boolean inserirSubSDB(Substituicao s, Jogo j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Evento VALUES (?,?,?,?)");
            stmt.setInt(1, 4);
            stmt.setInt(2, s.getMinuto());
            stmt.setInt(3, s.getParte());
            stmt.setInt(4, DatabaseJogador.getIdJogadorJogoPorIdJogadorClubeEIdJogo(DatabaseJogador.getIdJogadorClubePorJogador(s.getJogadorSai()), j.getId()));
            stmt.executeUpdate();
            con.close();
            stmt.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static boolean inserirCADB(Cartao c, Jogo j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("execute InserirEvento @id_tipo_evento=?, @minuto=?, @parte=?, @id_jogador_jogo=?");
            int tipo = 0;
            if (c.getCor().equals("vermelho"))
                tipo = 6;
            else
                tipo = 5;
            stmt.setInt(1, tipo);
            stmt.setInt(2, c.getMinuto());
            stmt.setInt(3, c.getParte());
            stmt.setInt(4, DatabaseJogador.getIdJogadorJogoPorIdJogadorClubeEIdJogo(DatabaseJogador.getIdJogadorClubePorJogador(c.getJogador()), j.getId()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }


    public static boolean inserirAGDB(AutoGolo ag, Jogo j) throws SQLException {
        try {
            Connection con = DatabaseConnect.getConnectionBD();
            PreparedStatement stmt = con.prepareStatement("execute InserirEvento @id_tipo_evento=?, @minuto=?, @parte=?, @id_jogador_jogo=?");
            stmt.setInt(1, 7);
            stmt.setInt(2, ag.getMinuto());
            stmt.setInt(3, ag.getParte());
            stmt.setInt(4, DatabaseJogador.getIdJogadorJogoPorIdJogadorClubeEIdJogo(DatabaseJogador.getIdJogadorClubePorJogador(ag.getJogador()), j.getId()));
            stmt.executeUpdate();
            con.close();
            stmt.close();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }


    public static ArrayList<Evento> getEventosJogoDB(int id_jogo) throws SQLException {
        ArrayList<Evento> listaEventos = new ArrayList<>();

        try (Connection con = DatabaseConnect.getConnectionBD()) {
            try (Statement st = con.createStatement()) {
                try (ResultSet rst = st.executeQuery("SELECT * FROM vEventosJogo WHERE vEventosJogo.id_jogo = " + id_jogo + ";")) {
                    while (rst.next()) {
                        if (rst.getInt("id_tipo_evento") == 1) {
                            Golo g = new Golo();
                            g.setIdEvento(rst.getInt("id_evento"));
                            g.setMinuto(rst.getInt("minuto"));
                            g.setJogador(DatabaseJogador.getJogadorPorJogadorClube(rst.getInt("id_jogador_clube")));
                            g.setParte(rst.getInt("parte"));
                            Clube c = new Clube();
                            c.setId(rst.getInt("id_clube"));
                            g.setClube(c);
                            listaEventos.add(g);
                        } else if (rst.getInt("id_tipo_evento") == 2) {
                            GoloAnulado ga = new GoloAnulado();
                            ga.setIdEvento(rst.getInt("id_evento"));
                            ga.setMinuto(rst.getInt("minuto"));
                            ga.setParte(rst.getInt("parte"));
                            Golo g = new Golo();
                            for (Evento e : listaEventos) {
                                if (e instanceof Golo)
                                    g = (Golo) e;
                            }
                            ga.setGolo(g);
                            Clube c = new Clube();
                            c.setId(rst.getInt("id_clube"));
                            ga.setClube(c);
                            listaEventos.add(ga);
                        } else if (rst.getInt("id_tipo_evento") == 3) {
                            Substituicao s = new Substituicao();
                            s.setIdEvento(rst.getInt("id_evento"));
                            s.setMinuto(rst.getInt("minuto"));
                            s.setParte(rst.getInt("parte"));
                            s.setJogadorEntra(DatabaseJogador.getJogadorPorJogadorClube(rst.getInt("id_jogador_clube")));
                            Clube c = new Clube();
                            c.setId(rst.getInt("id_clube"));
                            s.setClube(c);
                            listaEventos.add(s);
                        } else if (rst.getInt("id_tipo_evento") == 4) {
                            for (Evento e : listaEventos) {
                                if (e instanceof Substituicao && ((Substituicao) e).getJogadorSai() == null)
                                    ((Substituicao) e).setJogadorSai(DatabaseJogador.getJogadorPorJogadorClube(rst.getInt("id_jogador_clube")));
                            }
                        } else if (rst.getInt("id_tipo_evento") == 5) {
                            Cartao ca = new Cartao();
                            ca.setCor("amarelo");
                            ca.setIdEvento(rst.getInt("id_evento"));
                            ca.setMinuto(rst.getInt("minuto"));
                            ca.setJogador(DatabaseJogador.getJogadorPorJogadorClube(rst.getInt("id_jogador_clube")));
                            ca.setParte(rst.getInt("parte"));
                            Clube c = new Clube();
                            c.setId(rst.getInt("id_clube"));
                            ca.setClube(c);
                            listaEventos.add(ca);
                        } else if (rst.getInt("id_tipo_evento") == 6) {
                            Cartao cv = new Cartao();
                            cv.setCor("vermelho");
                            cv.setIdEvento(rst.getInt("id_evento"));
                            cv.setMinuto(rst.getInt("minuto"));
                            cv.setJogador(DatabaseJogador.getJogadorPorJogadorClube(rst.getInt("id_jogador_clube")));
                            cv.setParte(rst.getInt("parte"));
                            Clube c = new Clube();
                            c.setId(rst.getInt("id_clube"));
                            cv.setClube(c);
                            listaEventos.add(cv);
                        } else if (rst.getInt("id_tipo_evento") == 7) {
                            AutoGolo ag = new AutoGolo();
                            ag.setIdEvento(rst.getInt("id_evento"));
                            ag.setMinuto(rst.getInt("minuto"));
                            ag.setJogador(DatabaseJogador.getJogadorPorJogadorClube(rst.getInt("id_jogador_clube")));
                            ag.setParte(rst.getInt("parte"));
                            Clube c = new Clube();
                            c.setId(rst.getInt("id_clube"));
                            ag.setClube(c);
                            listaEventos.add(ag);
                        }
                    }
                } catch (SQLException e) {
                    e.getMessage();
                }
            } catch (SQLException e) {
                e.getMessage();
            }
            return listaEventos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
