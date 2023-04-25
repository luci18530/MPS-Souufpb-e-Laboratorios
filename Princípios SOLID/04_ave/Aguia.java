public class Aguia extends Ave {
    public Aguia(int contagemNumeroDePenas) {
        super(contagemNumeroDePenas);
    }

    //águias podem voar implementando o método voa()
    public void voa() {
        setLocalizacaoAtual("no ar");
    }
}