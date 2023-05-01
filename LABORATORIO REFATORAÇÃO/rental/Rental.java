public class Rental {
  public static void main(String[] args) {
    Client c1 = new Client("Juliana");

    c1.adicionaRent(new Rent(new Tape("O Exorcista", Tape.NORMAL), 3));
    c1.adicionaRent(new Rent(new Tape("Men in Black                  ", Tape.NORMAL), 2));
    c1.adicionaRent(new Rent(new Tape("Jurassic Park III             ", Tape.LANCAMENTO), 3));
    c1.adicionaRent(new Rent(new Tape("Planeta dos Macacos           ", Tape.LANCAMENTO), 4));
    c1.adicionaRent(new Rent(new Tape("Pateta no Planeta dos Macacos ", Tape.INFANTIL), 10));
    c1.adicionaRent(new Rent(new Tape("O Rei Leao                    ", Tape.INFANTIL), 30));

    System.out.println(c1.extrato());
  }
}
