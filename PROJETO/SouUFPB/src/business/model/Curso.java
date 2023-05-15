package business.model;


public class Curso {

    private String nome;
    private String cidade;
    private String centro;
    

    public Curso(String nome, String cidade, String centro){
        this.nome = nome;
        this.cidade = cidade;
        this.centro = centro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCentro(String centro){
        this.centro = centro;
    }

    public String getCentro() {
        return centro;
    }

    @Override
    public String toString() {
        return "Curso: " + nome + "\nCidade: " + cidade + "/nCentro: " + centro;
    }
}