package Error404.Model.Code;

/**
 *
 */
public class Treinador extends Pessoa {

    public Treinador(int id, String nome, String nacionalidade, String dataNascimento, String sexo, int titulosSLE) {
        super(id, nome, nacionalidade, dataNascimento, sexo, titulosSLE);
    }

    /**
     *
     */
    public Treinador() {
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "\n-----------Treinador-----------";
    }
}
