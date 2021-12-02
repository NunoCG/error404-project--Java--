package Error404.Model.Code;

import java.util.ArrayList;

/**
 *
 */
public class Jogador extends Pessoa {

    private double peso;
    private double altura;
    private int golos;
    private String posicao;
    private int a;
    private int v;
    private int ag;
    private Clube clube;

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }


    /**
     *
     * @param id
     * @param nome
     * @param nacionalidade
     * @param dataNascimento
     * @param sexo
     * @param titulosSLE
     * @param peso
     * @param altura
     * @param golos
     * @param posicao
     */
    public Jogador(int id, String nome, String nacionalidade, String dataNascimento, String sexo, int titulosSLE, double peso, double altura, int golos, String posicao) {
        super(id, nome, nacionalidade, dataNascimento, sexo, titulosSLE);
        this.peso = peso;
        this.altura = altura;
        this.golos = golos;
        this.posicao = posicao;
    }

    /**
     *
     */
    public Jogador() {
    }

    /**
     * @return
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return
     */
    public double getAltura() {
        return altura;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getAg() {
        return ag;
    }

    public void setAg(int ag) {
        this.ag = ag;
    }

    /**
     * @param altura
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * @return
     */
    public int getGolos() {
        return golos;
    }

    /**
     * @param golos
     */
    public void setGolos(int golos) {
        this.golos = golos;
    }

    /**
     *
     * @return
     */
    public String getPosicao() {
        return posicao;
    }

    /**
     *
     * @param posicao
     */
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }



    /**
     * @return
     */
    @Override
    public String toString() {
        return this.getNome();
    }
}
