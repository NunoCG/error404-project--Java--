package Error404.Model.Code;

import java.util.ArrayList;

/**
 *
 */
public class Epoca {

    private int id;
    private String nome;
    private String dataInicio;
    private String dataFim;
    private ArrayList<Jornada> jornadas;
    private ArrayList<Clube> clubes;

    /**
     *
     * @param id
     * @param nome
     * @param dataInicio
     * @param dataFim
     * @param jornadas
     * @param clubes
     */
    public Epoca(int id, String nome, String dataInicio, String dataFim, ArrayList<Jornada> jornadas, ArrayList<Clube> clubes) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.jornadas = jornadas;
        this.clubes = clubes;
    }

    /**
     *
     */
    public Epoca() {}

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getDataInicio() {
        return dataInicio;
    }

    /**
     *
     * @param dataInicio
     */
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     *
     * @return
     */
    public String getDataFim() {
        return dataFim;
    }

    /**
     *
     * @param dataFim
     */
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    /**
     *
     * @return
     */
    public ArrayList<Jornada> getJornadas() {
        return jornadas;
    }

    /**
     *
     * @param jornadas
     */
    public void setJornadas(ArrayList<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    /**
     *
     * @return
     */
    public ArrayList<Clube> getClubes() {
        return clubes;
    }

    /**
     *
     * @param clubes
     */
    public void setClubes(ArrayList<Clube> clubes) {
        this.clubes = clubes;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "\n---------Época---------" +
                "\nId: " + id +
                "\nNome: " + nome +
                "\nData de início: " + dataInicio +
                "\nData de fim: " + dataFim +
                "\nJornadas: " + jornadas;
    }
}
