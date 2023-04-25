public abstract class Ave {
    private String localizacaoAtual;
    private int numeroDePenas;

    public Ave(int contagemNumeroDePenas) {
        this.numeroDePenas = contagemNumeroDePenas;
    }

    public void voa() {
        //método voa() genérico
        setLocalizacaoAtual("no ar");
    }

    public void trocaPlumagem() {
        this.numeroDePenas -= 1;
    }

    // getters e setters
    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(String localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public int getNumeroDePenas() {
        return numeroDePenas;
    }

    public void setNumeroDePenas(int numeroDePenas) {
        this.numeroDePenas = numeroDePenas;
    }
}