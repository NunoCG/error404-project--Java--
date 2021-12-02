package Error404.Model.Code;

/**
 *
 */
public abstract class Pessoa {

    private int id;
    private String nome;
    private String nacionalidade;
    private String dataNascimento;
    private String sexo;
    private int titulosSLE;

    public int getTitulosSLE() {
        return titulosSLE;
    }

    public void setTitulosSLE(int titulosSLE) {
        this.titulosSLE = titulosSLE;
    }

    /**
     *
     * @param id
     * @param nome
     * @param nacionalidade
     * @param dataNascimento
     * @param sexo
     */
    public Pessoa(int id, String nome, String nacionalidade, String dataNascimento, String sexo, int titulosSLE) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.titulosSLE = titulosSLE;
    }

    /**
     *
     */
    public Pessoa() {
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
     *
     * @param nacionalidade
     */
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    /**
     *
     * @return
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     *
     * @param dataNascimento
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     *
     * @return
     */
    public String getSexo() {
        return sexo;
    }

    /**
     *
     * @param sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "\n----------Pessoa----------" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", titulosSLE=" + titulosSLE +
                '}';
    }
}
