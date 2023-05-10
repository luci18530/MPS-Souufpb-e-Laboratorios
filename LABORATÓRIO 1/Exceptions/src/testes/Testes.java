package testes;

import javax.swing.JOptionPane;

public class Testes {
    // in this class we will pass the college graduation vocational test
    // with 30 questions and 5 options each
    // each possible response will affect the field of knowledge pontuation (linguagens, humanas, exatas, biologicas, tecnologicas)
    // the final result will generate a list of thr best courses that the student can apply for in UFPB
    double linguagens = 0;
    double humanas = 0;
    double exatas = 0;
    double biologicas = 0;
    double tecnologicas = 0;

    public static void questionario() {
        Testes testes = new Testes();
        testes.question("1 - De 1 a 5 qual seu interesse em física?", "exatas");
        testes.question("2 - De 1 a 5 qual seu interesse em literatura?", "linguagens");
        testes.question("3 - De 1 a 5 qual seu interesse em tecnologia?", "tecnologicas");
        testes.question("4 - De 1 a 5 qual seu interesse em história?", "humanas");
        testes.question("5 - De 1 a 5 qual seu interesse em biologia?", "biologicas");
        testes.question("6 - De 1 a 5 qual seu interesse em programação?", "tecnologicas");
        testes.question("7 - De 1 a 5 qual seu interesse em sociologia?", "humanas");
        testes.question("8 - De 1 a 5 qual seu interesse em matemática?", "exatas");
        testes.question("9 - De 1 a 5 qual seu interesse em artes?", "linguagens");
        testes.question("10 - De 1 a 5 qual seu interesse em química?", "biologicas");
    }

    private void question(String message, String area) {
        String[] options = {"1", "2", "3", "4", "5"};
        int response = JOptionPane.showOptionDialog(null, message, "Questionario", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        addScore(area, response + 1);
    }

    private void addScore(String area, int score) {
        switch (area) {
            case "linguagens":
                this.linguagens += score;
                break;
            case "humanas":
                this.humanas += score;
                break;
            case "exatas":
                this.exatas += score;
                break;
            case "biologicas":
                this.biologicas += score;
                break;
            case "tecnologicas":
                this.tecnologicas += score;
                break;
        }
    }

    public void setLinguagens(double linguagens) {
        this.linguagens = linguagens;
    }

    public void setHumanas(double humanas) {
        this.humanas = humanas;
    }

    public void setExatas(double exatas) {
        this.exatas = exatas;
    }

    public void setBiologicas(double biologicas) {
        this.biologicas = biologicas;
    }   

    public void setTecnologicas(double tecnologicas) {
        this.tecnologicas = tecnologicas;
    }

    public double getLinguagens() {
        return linguagens;
    }

    public double getHumanas() {
        return humanas;
    }

    public double getExatas() {
        return exatas;
    }

    public double getBiologicas() {
        return biologicas;
    }

    public double getTecnologicas() {
        return tecnologicas;
    }

    public static String[] fiveoptions() {
        String[] options = {"1", "2", "3", "4", "5"};
        return options;
        
    }

}
