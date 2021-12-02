package Error404.Model.Code;

/**
 *
 */
public class Substituicao extends Evento {

    private Jogador jogadorEntra;
    private Jogador jogadorSai;

    /**
     *
     * @param idEvento
     * @param minuto
     * @param parte
     * @param jogadorEntra
     * @param jogadorSai
     */
    public Substituicao(int idEvento, int minuto, int parte, Jogador jogadorEntra, Jogador jogadorSai, Clube clube) {
        super(idEvento, minuto, parte, clube);
        this.jogadorEntra = jogadorEntra;
        this.jogadorSai = jogadorSai;
    }

    /**
     *
     */
    public Substituicao() {
    }

    /**
     *
     * @return
     */
    public Jogador getJogadorEntra() {
        return jogadorEntra;
    }

    /**
     *
     * @param jogadorEntra
     */
    public void setJogadorEntra(Jogador jogadorEntra) {
        this.jogadorEntra = jogadorEntra;
    }

    /**
     *
     * @return
     */
    public Jogador getJogadorSai() {
        return jogadorSai;
    }

    /**
     *
     * @param jogadorSai
     */
    public void setJogadorSai(Jogador jogadorSai) {
        this.jogadorSai = jogadorSai;
    }

    @Override
    public String toString() {
        return "\n-----------Substituicao-----------" +
                "\nJogador que entrou: " + jogadorEntra +
                "\nJogador que saiu: " + jogadorSai;
    }
}
