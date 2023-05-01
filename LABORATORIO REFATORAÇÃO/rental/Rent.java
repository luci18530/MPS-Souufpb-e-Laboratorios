public class Rent {
  private Tape Tape;
  private int diasAlugada;

  public Rent(Tape Tape, int diasAlugada) {
    this.Tape = Tape;
    this.diasAlugada = diasAlugada;
  }

  public Tape getTape() {
    return Tape;
  }

  public int getDiasAlugada() {
    return diasAlugada;
  }
}

