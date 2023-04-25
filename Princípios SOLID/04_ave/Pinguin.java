public class Pinguin extends Ave {
    public Pinguin(int contagemNumeroDePenas) {
        super(contagemNumeroDePenas);
    }

    // Pinguins podem implementar ou não o método voa()
    // Nesse caso, o método não foi implementado para seguir o princípio da substituição
    /*public void voa() {
        throw new UnsupportedOperationException();
    }*/

    public void nada() {
        setLocalizacaoAtual("na agua");
    }
}