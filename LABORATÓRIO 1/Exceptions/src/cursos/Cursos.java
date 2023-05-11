package cursos;

import javax.swing.JOptionPane;

public class Cursos {
    private String[] cursos = {
        "CIÊNCIA DA COMPUTAÇÃO", // 1
        "CIÊNCIA DE DADOS E INTELIGÊNCIA ARTIFICIAL", // 2
        "ENGENHARIA DA COMPUTAÇÃO", // 3
        "ARQUITETURA E URBANISMO", // 4
        "ENGENHARIA AMBIENTAL", // 5
        "ENGENHARIA CIVIL", // 6
        "ENGENHARIA ELÉTRICA", // 7
        "GASTRONOMIA", // 8
        "TECNOLOGIA DE ALIMENTOS", // 9
        "TECNOLOGIA EM PRODUÇÃO SUCROALCOOLEIRA",   // 10
        "MEDICINA", // 11
        "DIREITO", // 12
        "PEDAGOGIA", // 13
        "FÍSICA", // 14
        "GEOGRAFIA", // 15
        "MATÉMATICA", // 16
        "NUTRIÇÃO", // 17
        "QUIMICA" // 18
    };

    public static void entraremcursos(){
        // print some stuff
        System.out.println("Entrando em cursos...");
    
        Cursos cursos = new Cursos();
        cursos.mostrarCursos();
    }
    

    public void mostrarCursos() {
        StringBuilder sb = new StringBuilder("Escolha um curso:\n");
        for (int i = 0; i < cursos.length; i++) {
            sb.append((i + 1) + "-" + cursos[i] + "\n");
        }
        String opcao = JOptionPane.showInputDialog(sb.toString());

        // Validação da entrada do usuário
        int opcaoEscolhida;
        try {
            opcaoEscolhida = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Opção inválida!");
            return;
        }

        if (opcaoEscolhida < 1 || opcaoEscolhida > cursos.length) {
            JOptionPane.showMessageDialog(null, "Opção fora do intervalo!");
            return;
        }

        // Mostrar detalhes do curso
        mostrarDetalhesCurso(opcaoEscolhida - 1);
    }

    private void mostrarDetalhesCurso(int index) {
        // Aqui vamos apenas mostrar alguns detalhes genéricos para cada curso.
        // Em uma implementação real, você provavelmente iria buscar essas informações de um banco de dados ou outra fonte de dados.
        String nomeCurso = cursos[index];
        String local = "João Pessoa, PB";
        String Centro = "";
        if (index == 0 || index == 1 || index == 2){
            Centro = "Informática";
            System.out.println(index);
        }

        else if (index == 3 || index == 4 || index == 5 || index == 6){
            Centro = "Tecnologia";
        }

        else if (index == 7 || index == 8 || index == 9){
            Centro = "TECNOLOGIA E DESENVOLVIMENTO REGIONAL";
        }

        else if (index == 10){
            Centro = "Ciências Médicas";
        }

        else if (index == 11){
            Centro = "Ciências Jurídicas";
        }

        else if (index == 12){
            Centro = "Ciências Humanas, Letras e Artes";
        }

        else if (index == 13 || index == 14 || index == 15 || index == 17){
            Centro = "Ciências Exatas e da Natureza";
        }

        else if (index == 16){
            Centro = "Saúde";
        }


        String centroEnsino = "Centro de " + Centro;
        String descricao = "O curso de " + nomeCurso + " é um programa de estudos muito interessante na UFPB...";  // Adicione a descrição real aqui

        String detalhesCurso = "Nome do Curso: " + nomeCurso + "\n"
                + "Local: " + local + "\n"
                + "Centro de Ensino: " + centroEnsino + "\n"
                + "Descrição: " + descricao;

        JOptionPane.showMessageDialog(null, detalhesCurso);
    }

    public static void main(String[] args) {
        Cursos cursos = new Cursos();
        cursos.mostrarCursos();
    }
}
