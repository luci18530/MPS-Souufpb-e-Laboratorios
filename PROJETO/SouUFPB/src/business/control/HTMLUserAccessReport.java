package business.control;
import java.util.List;

public class HTMLUserAccessReport extends UserAccessReport {

    @Override
    protected List<String> getUsers() {
        // Lógica para obter a lista de usuários
    }

    @Override
    protected int getTotalAccessCount(String user) {
        // Lógica para obter o número total de acessos do usuário
    }

    @Override
    protected int getUniqueAccessCount(String user) {
        // Lógica para obter o número de acessos únicos do usuário
    }

    @Override
    protected void generateHeader() {
        // Lógica para gerar o cabeçalho do relatório em HTML
    }

    @Override
    protected void generateBody(String user, int totalAccessCount, int uniqueAccessCount) {
        // Lógica para gerar o corpo do relatório em HTML para cada usuário
    }

    @Override
    protected void generateFooter() {
        // Lógica para gerar o rodapé do relatório em HTML
    }
}
