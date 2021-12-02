package Error404.Model.Code;

/**
 *
 */
public class Golo extends Evento{

    private Jogador jogador;

    /**
     *
     * @param idEvento
     * @param minuto
     * @param parte
     * @param jogador
     */
    public Golo(int idEvento, int minuto, int parte, Jogador jogador, Clube clube) {
        super(idEvento, minuto, parte, clube);
        this.jogador = jogador;
    }

    /**
     *
     */
    public Golo() {}

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
        return "\n-----------Golo-----------" +
                "\nJogador: " + jogador;
    }
}
