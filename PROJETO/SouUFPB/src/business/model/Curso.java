package business.model;
import infra.Timestamp;
import infra.TimestampAdapter;

// Definindo a classe Curso
public class Curso {
    // Definindo os atributos privados da classe
    private transient Timestamp time;
    private String nome;
    private String cidade;
    private String centro;
    private String timestamp;

    // Construtor da classe Curso
    public Curso(String nome, String cidade, String centro){
        // Atribuindo valores para os atributos
        this.nome = nome;
        this.cidade = cidade;
        this.centro = centro;
        this.time = new TimestampAdapter();
        this.timestamp = time.getTime();
        
    }

    // Métodos getter e setter para cada um dos atributos
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Sobrescrevendo o método toString para retornar uma representação do objeto em String
    @Override
    public String toString() {
        return "Curso: " + nome + "\nCidade: " + cidade + "/nCentro: " + centro;
    }
}
