public class TabuleiroView {
    public static void display(Tabuleiro tabuleiro) {
        String tabuleiroFormatado = tabuleiro.getSpots()[0] + " | " + tabuleiro.getSpots()[1] + " | " + tabuleiro.getSpots()[2] + "\n" +
                                    tabuleiro.getSpots()[3] + " | " + tabuleiro.getSpots()[4] + " | " + tabuleiro.getSpots()[5] + "\n" +
                                    tabuleiro.getSpots()[6] + " | " + tabuleiro.getSpots()[7] + " | " + tabuleiro.getSpots()[8];
        System.out.print(tabuleiroFormatado);
    }
}