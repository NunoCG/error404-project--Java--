package Error404.Model.Database;

import java.sql.*;

/**
 *
 */
public class DatabaseConnect {

    /**
     * Connecta o nosso projeto Java com a base de dados de SQL Server
     * @return conexão
     * @throws SQLException
     */
    public static Connection getConnectionBD() throws SQLException {

        String url = "jdbc:sqlserver://ctespbd.dei.isep.ipp.pt:1433;databaseName=DIAS_grupo7_2021"; //1433 - é a porta default do SQL Server
        String loginName = "DIAS_grupo7_2021";
        String password = "Pa$$Word404";
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, loginName, password);
        } catch (SQLException throwables) {
            System.out.println("Erro!");
            throwables.printStackTrace();
        }
        return con;
    }

    /**
     *
     * @throws SQLException
     */
    public static void name() throws SQLException {

        Connection con = getConnectionBD();
        Statement st = con.createStatement();
        String query = "SELECT * FROM a";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString("Nome"));
        }
    }
}
