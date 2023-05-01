import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
    private String nome;
    private List<Rent> rents = new ArrayList<>();

    public Client(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionaRent(Rent rent) {
        rents.add(rent);
    }

    public String extrato() {
        final String fimDeLinha = System.getProperty("line.separator");
        double valorTotal = 0.0;
        int pontosDeAlugadorFrequente = 0;
        StringBuilder resultado = new StringBuilder("Registro de Alugueis de " + getNome() + fimDeLinha);
        Iterator<Rent> iterator = rents.iterator();
        while (iterator.hasNext()) {
            Rent rent = iterator.next();
            double valorCorrente = calculaValorCorrente(rent);

            // trata de pontos de alugador frequente
            pontosDeAlugadorFrequente++;
            // adiciona bonus para Rent de um lançamento por pelo menos 2 dias
            if (rent.getTape().getCodigoDePreco() == Tape.LANCAMENTO &&
                    rent.getDiasAlugada() > 1) {
                pontosDeAlugadorFrequente++;
            }

            // mostra valores para este Rent
            resultado.append("\t").append(rent.getTape().getTitulo()).append("\t").append(valorCorrente).append(fimDeLinha);
            valorTotal += valorCorrente;
        }
        // adiciona rodape
        resultado.append("Valor total devido: ").append(valorTotal).append(fimDeLinha);
        resultado.append("Você acumulou ").append(pontosDeAlugadorFrequente).append(" pontos de alugador frequente");
        return resultado.toString();
    }

    private double calculaValorCorrente(Rent rent) {
        double valorCorrente = 0.0;
        switch (rent.getTape().getCodigoDePreco()) {
            case Tape.NORMAL:
                valorCorrente += 2;
                if (rent.getDiasAlugada() > 2) {
                    valorCorrente += (rent.getDiasAlugada() - 2) * 1.5;
                }
                break;
            case Tape.LANCAMENTO:
                valorCorrente += rent.getDiasAlugada() * 3;
                break;
            case Tape.INFANTIL:
                valorCorrente += 1.5;
                if (rent.getDiasAlugada() > 3) {
                    valorCorrente += (rent.getDiasAlugada() - 3) * 1.5;
                }
                break;
        }
        return valorCorrente;
    }
}
