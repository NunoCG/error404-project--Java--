package Error404.Model.Code;

/**
 *
 */
public abstract class Evento {

    private int idEvento;
    private int minuto;
    private int parte;
    private Clube clube;

    /**
     *
     * @param idEvento
     * @param minuto
     * @param parte
     */
    public Evento(int idEvento, int minuto, int parte, Clube clube) {
        this.idEvento = idEvento;
        this.minuto = minuto;
        this.parte = parte;
        this.clube = clube;
    }

    /**
     *
     */
    public Evento() {
    }

    /**
     *
     * @return
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     *
     * @param idEvento
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * @param minuto
     */
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    /**
     * @return
     */
    public int getParte() {
        return parte;
    }

    /**
     * @param parte
     */
    public void setParte(int parte) {
        this.parte = parte;
    }


    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "\n---------Evento---------" +
                "\nMinuto: " + minuto +
                "\nParte: " + parte;
    }
}
