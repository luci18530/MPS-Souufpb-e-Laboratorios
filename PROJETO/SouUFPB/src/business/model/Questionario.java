package business.model;
import java.io.Serializable;

// Classe Questionario
public class Questionario implements Serializable {
    private String pergunta;
    private String area;

    public Questionario(String pergunta, String area){
        this.pergunta = pergunta;
        this.area = area;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Pergunta: " + pergunta + "\nArea: " + area;
    }
}