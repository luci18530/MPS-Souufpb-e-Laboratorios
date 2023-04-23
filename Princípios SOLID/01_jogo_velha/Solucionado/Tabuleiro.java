public class Tabuleiro {
    private String[] spots;

    public Tabuleiro() {
        this.spots = new String[9];
        for (int i = 0; i < 9; i++) {
            this.spots[i] = " ";
        }
    }

    public String[] getSpots() {
        return spots;
    }

    public void setSpot(int index, String value) {
        this.spots[index] = value;
    }

    public boolean verificaGanhador() {
        if ((this.spots[0].equals(this.spots[1]) && this.spots[0].equals(this.spots[2])) ||
            (this.spots[3].equals(this.spots[4]) && this.spots[3].equals(this.spots[5])) ||
            (this.spots[6].equals(this.spots[7]) && this.spots[6].equals(this.spots[8])) ||
            (this.spots[0].equals(this.spots[3]) && this.spots[0].equals(this.spots[6])) ||
            (this.spots[1].equals(this.spots[4]) && this.spots[1].equals(this.spots[7])) ||
            (this.spots[2].equals(this.spots[5]) && this.spots[2].equals(this.spots[8])) ||
            (this.spots[0].equals(this.spots[4]) && this.spots[0].equals(this.spots[8])) ||
            (this.spots[2].equals(this.spots[4]) && this.spots[2].equals(this.spots[6]))) {
            return true;
        } else {
            return false;
        }
    }
}

