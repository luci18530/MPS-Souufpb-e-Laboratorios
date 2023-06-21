package business.control;

import business.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLUserAccessReport extends UserAccessReport {

    @Override
    protected FileWriter openFile(String fileName) {
        String nomeArquivo = fileName + ".html";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return fileWriter;
    }

    @Override
    protected void generateHeader(BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("<html>");
        bufferedWriter.newLine();
        bufferedWriter.write("<head>");
        bufferedWriter.newLine();
        bufferedWriter.write("<title>User Access Report</title>");
        bufferedWriter.newLine();
        bufferedWriter.write("</head>");
        bufferedWriter.newLine();
        bufferedWriter.write("<body>");
        bufferedWriter.newLine();
    }

    @Override
    protected void generateBody(User user, BufferedWriter bufferedWriter) throws IOException{
        String username = user.getLogin();
        String timeStamp = user.getTimestamp();
        int totalAccessCount = user.getTotalAccessCount();

        bufferedWriter.write("<p>Usuário: "+username+".</p>");
        bufferedWriter.newLine();
        bufferedWriter.write("<p>"+timeStamp+".</p>");
        bufferedWriter.newLine();
        bufferedWriter.write("<p>Número total de acessos: "+totalAccessCount+".</p>");
        bufferedWriter.newLine();
        bufferedWriter.write("<br>");
        bufferedWriter.newLine();
    }

    @Override
    protected void generateFooter(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("<p>---------- End of report ----------</p>");
        bufferedWriter.newLine();
        bufferedWriter.write("</body>");
        bufferedWriter.newLine();
        bufferedWriter.write("</html>");
    }

}
