package Error404.Model.Code;

/**
 *
 */
public class GoloAnulado extends Evento {

    private String descricao;
    private Golo golo;
    private Jogador jogador;

    public GoloAnulado(int idEvento, int minuto, int parte, String descricao, Golo golo, Jogador jogador, Clube clube) {
        super(idEvento, minuto, parte, clube);
        this.descricao = descricao;
        this.golo = golo;
        this.jogador= jogador;
    }

    /**
     *
     */
    public GoloAnulado() {
    }

    public Golo getGolo() {
        return golo;
    }

    public void setGolo(Golo golo) {
        this.golo = golo;
    }

    /**
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "\n-----------GoloAnulado-----------" +
                "\nDescricao: " + descricao;
    }
}
