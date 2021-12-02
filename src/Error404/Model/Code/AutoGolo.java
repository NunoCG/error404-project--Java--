package Error404.Model.Code;

public class AutoGolo extends Evento{

    private Jogador jogador;

    public AutoGolo(int idEvento, int minuto, int parte, Jogador jogador, Clube clube) {
        super(idEvento, minuto, parte, clube);
        this.jogador = jogador;
    }

    public AutoGolo() {
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public String toString() {
        return "\n---------AutoGolo----------" +
                "\nJogador: " + jogador;
    }
}
