package business.control;

import business.model.User;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TXTUserAccessReport extends UserAccessReport {

    @Override
    protected FileWriter openFile(String fileName) {
        String nomeArquivo = fileName + ".txt";
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

        bufferedWriter.write("User Access Report");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.newLine();
    }

    @Override
    protected void generateBody(User user, BufferedWriter bufferedWriter) throws IOException{
        String username = user.getLogin();
        String timeStamp = user.getTimestamp();
        int totalAccessCount = user.getTotalAccessCount();
       
        bufferedWriter.write("Usuário: "+ username);
        bufferedWriter.newLine();
        bufferedWriter.write(timeStamp);
        bufferedWriter.newLine();
        bufferedWriter.write("Número total de acessos: " + totalAccessCount);
        bufferedWriter.newLine();
        bufferedWriter.newLine();
    }

    @Override
    protected void generateFooter(BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("---------- End of report ----------");
        bufferedWriter.newLine();
    }
}
