package Error404.Model.Code;

/**
 *
 */
public class Contrato {

    private Jogador jogador;
    private Clube clube;
    private String dataInicio;
    private String dataFim;
    private Treinador treinador;

    /**
     *
     * @param jogador
     * @param clube
     * @param dataInicio
     * @param dataFim
     */
    public Contrato(Jogador jogador, Clube clube, String dataInicio, String dataFim, Treinador treinador) {
        this.jogador = jogador;
        this.clube = clube;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.treinador = treinador;
    }

    /**
     *
     */
    public Contrato() {}

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
    public Clube getClube() {
        return clube;
    }

    /**
     *
     * @param clube
     */
    public void setClube(Clube clube) {
        this.clube = clube;
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\n----------Contrato----------" +
                "\nJogador: " + jogador +
                "\nClube: " + clube +
                "\nData inicio: " + dataInicio +
                "\nData fim: " + dataFim;
    }
}
