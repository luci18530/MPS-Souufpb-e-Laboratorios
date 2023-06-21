package business.control;

import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.JOptionPane;

import infra.InfraException;

public class UserAccessReportGenerator {

    public void handleReport() throws IOException, InfraException{
        String fileName = JOptionPane.showInputDialog("Digite o nome do arquivo a ser salvo:");
        if (fileName == null || fileName.equals("")) {
            JOptionPane.showMessageDialog(null, "Operação cancelada!");
            return;
        }
        String[] options = { "HTML", "TXT", "Cancelar" };
        int choice = JOptionPane.showOptionDialog(
                null,
                "Escolha o formato do relatório:",
                "Opções",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                generateHTMLReport(fileName);
                break;
            case 1:
                generateTXTReport(fileName);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operação cancelada!");
                break;
        }
    }

    public void generateHTMLReport(String fileName) throws IOException, InfraException {
        UserAccessReport report = new HTMLUserAccessReport();
        report.generateReport(fileName);
    }

    public void generateTXTReport(String fileName) throws IOException, InfraException {
        UserAccessReport report = new TXTUserAccessReport();
        report.generateReport(fileName);
    }
}
