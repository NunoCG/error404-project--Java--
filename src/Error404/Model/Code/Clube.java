package Error404.Model.Code;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 *
 */
public class Clube {

    private int id;
    private String nome;
    private String pais;
    private String cidade;
    private String estadio;
    private int titulosSle;
    private ArrayList<Jogador> jogadores;
    private Treinador treinador;
    private int p;
    private int j;
    private int v;
    private int e;
    private int d;
    private int gm;
    private int gs;
    private int dg;


    /**
     *
     * @param id
     * @param nome
     * @param pais
     * @param cidade
     * @param estadio
     * @param titulosSle
     * @param jogadores
     * @param treinador
     */
    public Clube(int id, String nome, String pais, String cidade, String estadio, int titulosSle, ArrayList<Jogador> jogadores, Treinador treinador) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.cidade = cidade;
        this.estadio = estadio;
        this.titulosSle = titulosSle;
        this.jogadores = jogadores;
        this.treinador = treinador;

    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getGm() {
        return gm;
    }

    public void setGm(int gm) {
        this.gm = gm;
    }

    public int getGs() {
        return gs;
    }

    public void setGs(int gs) {
        this.gs = gs;
    }

    public int getDg() {
        return dg;
    }

    public void setDg(int dg) {
        this.dg = dg;
    }

    /**
     *
     */
    public Clube() {
    }

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
    public String getPais() {
        return pais;
    }

    /**
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     *
     * @return
     */
    public String getCidade() {
        return cidade;
    }

    /**
     *
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     *
     * @return
     */
    public String getEstadio() {
        return estadio;
    }

    /**
     *
     * @param estadio
     */
    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    /**
     *
     * @return
     */
    public int getTitulosSle() {
        return titulosSle;
    }

    /**
     *
     * @param titulosSle
     */
    public void setTitulosSle(int titulosSle) {
        this.titulosSle = titulosSle;
    }

    /**
     *
     * @return
     */
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    /**
     *
     * @param jogadores
     */
    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    /**
     *
     * @return
     */
    public Treinador getTreinador() {
        return treinador;
    }

    /**
     *
     * @param treinador
     */
    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public static int getVitorias(ArrayList<Jogo> list, int id_clube) {
        int v = 0;
        for (Jogo j:list) {
            if (j.getVencedor().getId()==id_clube)
                v+=1;
        }
        return v;
    }

    public static int getEmpates(ArrayList<Jogo> list) {
        int e = 0;
        for (Jogo j:list) {
            if (j.getVencedor()==null)
                e+=1;
        }
        return e;
    }

    public static int getDerrotas(ArrayList<Jogo> list, int id_clube) {
        int d = 0;
        for (Jogo j:list) {
            if (j.getVencedor().getId()!=id_clube)
                d+=1;
        }
        return d;
    }

    @Override
    public String toString() {
        return nome;
    }
}
