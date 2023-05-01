public class Tape {
  public static final int NORMAL = 0;
  public static final int LANCAMENTO = 1;
  public static final int INFANTIL = 2;

  private String titulo;
  private int codigoDePreco;

  public Tape(String titulo, int codigoDePreco) {
    this.titulo = titulo;
    this.codigoDePreco = codigoDePreco;
  }

  public String getTitulo() {
    return titulo;
  }

  public int getCodigoDePreco() {
    return codigoDePreco;
  }

  public void setCodigoDePreco(int codigoDePreco) {
    this.codigoDePreco = codigoDePreco;
  }
}

