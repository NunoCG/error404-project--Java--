package Error404.Model.Code;

import java.util.ArrayList;

/**
 *
 */
public class Jornada {

    private int id;
    private int numJornada;
    private Epoca epoca;
    private Jogador goleadorJornada;
    private ArrayList<Jogo> jogos;
    private String data;


    /**
     *
     * @param id
     * @param numJornada
     * @param epoca
     * @param goleadorJornada
     * @param jogos
     * @param data
     */
    public Jornada(int id, int numJornada, Epoca epoca, Jogador goleadorJornada, ArrayList<Jogo> jogos, String data) {
        this.id = id;
        this.numJornada = numJornada;
        this.epoca = epoca;
        this.goleadorJornada = goleadorJornada;
        this.jogos = jogos;
        this.data = data;
    }

    /**
     *
     */
    public Jornada() {
    }



    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public int getNumJornada() {
        return numJornada;
    }

    /**
     *
     * @param numJornada
     */
    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    /**
     *
     * @return
     */
    public Epoca getEpoca() {
        return epoca;
    }

    /**
     *
     * @param epoca
     */
    public void setEpoca(Epoca epoca) {
        this.epoca = epoca;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public Jogador getGoleadorJornada() {
        return goleadorJornada;
    }

    /**
     * @param goleadorJornada
     */
    public void setGoleadorJornada(Jogador goleadorJornada) {
        this.goleadorJornada = goleadorJornada;
    }

    /**
     * @return
     */
    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    /**
     * @param jogos
     */
    public void setJogos(ArrayList<Jogo> jogos) {
        this.jogos = jogos;
    }

    /**
     *
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(numJornada);
    }
}
