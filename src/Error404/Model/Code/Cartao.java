package Error404.Model.Code;

/**
 *
 */
public class Cartao extends Evento{

    private String cor;
    private Jogador jogador;

    /**
     *
     * @param idEvento
     * @param minuto
     * @param parte
     * @param cor
     * @param jogador
     */
    public Cartao(int idEvento, int minuto, int parte, String cor, Jogador jogador, Clube clube) {
        super(idEvento, minuto, parte, clube);
        this.cor = cor;
        this.jogador = jogador;
    }

    /**
     *
     */
    public Cartao() {}

    /**
     *
     * @return
     */
    public String getCor() {
        return cor;
    }

    /**
     *
     * @param cor
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     *
     * @return
     */
    public Jogador getJogador() {
        return jogador;
    }

    /**
     *
     * @param jogador
     */
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\n-----------Cartao-----------" +
                "\nCor: " + cor +
                "\nJogador: " + jogador +
                "\n----------------------------";
    }
}
