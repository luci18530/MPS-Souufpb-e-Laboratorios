public class ClimaTracker {
    String condicaoAtual;

    public ClimaTracker() {
    }

    public void setCondicaoAtual(String condicaoClimatica) {
        this.condicaoAtual = condicaoClimatica;
    }
}

public class AlertaClima {
    public void enviarAlerta(String condicaoClimatica) {
        Phone phone = new Phone();
        EmailCliente emailCliente = new EmailCliente();

        if (condicaoClimatica == "chovendo") {
            String alerta = phone.geraClimaAlerta(condicaoClimatica);
            System.out.print(alerta);
        }
        if (condicaoClimatica == "ensolarado") {
            String alerta = emailCliente.geraClimaAlerta(condicaoClimatica);
            System.out.print(alerta);
        }
    }
}