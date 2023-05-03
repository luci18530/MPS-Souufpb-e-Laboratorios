public class RentCalculator {
    public double calculaValorCorrente(Rent rent) {
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