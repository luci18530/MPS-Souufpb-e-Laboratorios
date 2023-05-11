package testes;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

public class Testes {
    // in this class we will pass the college graduation vocational test
    // with 30 questions and 5 options each
    // each possible response will affect the field of knowledge pontuation (linguagens, humanas, exatas, biologicas, tecnologicas)
    // the final result will generate a list of thr best courses that the student can apply for in UFPB
    String email = "";
    double linguagens = 0;
    double humanas = 0;
    double exatas = 0;
    double biologicas = 0;
    double tecnologicas = 0;
    // array of results of interger
    double[] results = new double[5];
    

    public static void questionario(String emailUser) {
        Testes testes = new Testes();
        testes.email = emailUser;
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
        // set results
        testes.setResults();
        testes.showResults();
        // save handler to database
        testes.saveHandler();
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

    // set results
    public void setResults() {
        // set results
        this.results[0] = this.linguagens;
        this.results[1] = this.humanas;
        this.results[2] = this.exatas;
        this.results[3] = this.biologicas;
        this.results[4] = this.tecnologicas;
    }

    // show results (in joptionpane)
    public void showResults() {
        JOptionPane.showMessageDialog(null, "Resultados: \n" + "Linguagens:" + this.linguagens + "\nHumanas:" + this.humanas + "\nExatas:" + this.exatas + "\nBiologicas:" + this.biologicas + "\nTecnologicas:" + this.tecnologicas);
    }

    // save handler
    public void saveHandler() {
        String fileName = "user_results.csv";

        File file = new File(fileName);
        try {
            boolean newFile = file.createNewFile();  // will create file if not exists
            if (!newFile) {
                // if file already exists, check for existing results for the user and delete them
                List<String[]> lines = new ArrayList<>();
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String[] line = scanner.nextLine().split(",");
                        if (!line[0].equals(this.email)) {
                            lines.add(line);
                        }
                    }
                }
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    for (String[] line : lines) {
                        writer.println(String.join(",", line));
                    }
                }
            }

            // write results for the user
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                writer.println(this.email + "," + String.join(",", this.results));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving results.");
            e.printStackTrace();
        }
    }

    

    public static String[] fiveoptions() {
        String[] options = {"1", "2", "3", "4", "5"};
        return options;
        
    }

}
