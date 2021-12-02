package Error404.Model.Code;

import java.util.ArrayList;

/**
 *
 */
public class Jogo {

    private int id;
    private Clube clubeCasa;
    private Clube clubeFora;
    private Jornada jornada;
    private ArrayList<Evento> eventos;
    private int descontoPrimeira;
    private int descontoSegunda;
    private String data;
    private int resultCasa;
    private int resultFora;


    /**
     * @param id
     * @param clubeCasa
     * @param clubeFora
     * @param jornada
     * @param eventos
     * @param descontoPrimeira
     * @param descontoSegunda
     * @param data
     */
    public Jogo(int id, Clube clubeCasa, Clube clubeFora, Jornada jornada, ArrayList<Evento> eventos, int descontoPrimeira, int descontoSegunda, String data) {
        this.id = id;
        this.clubeCasa = clubeCasa;
        this.clubeFora = clubeFora;
        this.jornada = jornada;
        this.eventos = eventos;
        this.descontoPrimeira = descontoPrimeira;
        this.descontoSegunda = descontoSegunda;
        this.data = data;
    }

    /**
     *
     */
    public Jogo() {
    }

    public int getResultCasa() {
        return resultCasa;
    }

    public void setResultCasa(int resultCasa) {
        this.resultCasa = resultCasa;
    }

    public int getResultFora() {
        return resultFora;
    }

    public void setResultFora(int resultFora) {
        this.resultFora = resultFora;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
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
    public Clube getClubeCasa() {
        return clubeCasa;
    }

    /**
     * @param clubeCasa
     */
    public void setClubeCasa(Clube clubeCasa) {
        this.clubeCasa = clubeCasa;
    }

    /**
     * @return
     */
    public Clube getClubeFora() {
        return clubeFora;
    }

    /**
     * @param clubeFora
     */
    public void setClubeFora(Clube clubeFora) {
        this.clubeFora = clubeFora;
    }

    /**
     * @return
     */
    public Jornada getJornada() {
        return jornada;
    }

    /**
     * @param jornada
     */
    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    /**
     * @return
     */
    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    /**
     * @param eventos
     */
    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return
     */
    public int getDescontoPrimeira() {
        return descontoPrimeira;
    }

    /**
     * @param descontoPrimeira
     */
    public void setDescontoPrimeira(int descontoPrimeira) {
        this.descontoPrimeira = descontoPrimeira;
    }

    /**
     * @return
     */
    public int getDescontoSegunda() {
        return descontoSegunda;
    }

    /**
     * @param descontoSegunda
     */
    public void setDescontoSegunda(int descontoSegunda) {
        this.descontoSegunda = descontoSegunda;
    }

    /**
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return
     */

    public Clube getVencedor() {
        Clube c = new Clube();
        int casa = 0;
        int fora = 0;
        for (Evento e : this.getEventos()) {
            if (e.getClube().getId() == this.clubeCasa.getId()) {
                if (e instanceof Golo) {
                    casa += 1;
                } else if (e instanceof GoloAnulado) {
                    casa -= 1;
                } else if (e instanceof AutoGolo) {
                    fora += 1;
                }
            } else if (e.getClube().getId() == this.clubeFora.getId()) {
                if (e instanceof Golo) {
                    fora += 1;
                } else if (e instanceof GoloAnulado) {
                    fora -= 1;
                } else if (e instanceof AutoGolo) {
                    casa += 1;
                }
            }
        }
        if (casa > fora)
            return this.clubeCasa;
        else if (casa < fora)
            return this.clubeFora;
        else
            return c;
    }

    public int getGolosCasa() {
        int casa = 0;
        for (Evento e : this.getEventos()) {
            if (e.getClube().getId() == this.clubeCasa.getId()) {
                if (e instanceof Golo) {
                    casa += 1;
                } else if (e instanceof GoloAnulado) {
                    casa -= 1;
                }
            } else if (e.getClube().getId() == this.clubeFora.getId()) {
                if (e instanceof AutoGolo) {
                    casa += 1;
                }
            }
        }
        return casa;
    }

    public int getGolosFora() {
        int fora = 0;
        for (Evento e : this.getEventos()) {
            if (e.getClube().getId() == this.clubeFora.getId()) {
                if (e instanceof Golo) {
                    fora += 1;
                } else if (e instanceof GoloAnulado) {
                    fora -= 1;
                }
            } else if (e.getClube().getId() == this.clubeCasa.getId()) {
                if (e instanceof AutoGolo) {
                    fora += 1;
                }
            }
        }
        return fora;
    }


    @Override
    public String toString() {
        return "----------Jogo----------" +
                "\nClube Casa: " + clubeCasa +
                "\nClube Fora: " + clubeFora +
                "\nJornada: " + jornada +
                "\nEventos: " + eventos +
                "\nDesconto da 1ª parte: " + descontoPrimeira +
                "\nDesconto da 2ª parte: " + descontoSegunda +
                "\nData: " + data;

    }
}
