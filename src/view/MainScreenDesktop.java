package view;

import infra.InfraException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import business.control.UserManager;
import business.model.User;

public class MainScreenDesktop {
	
	private UserManager userManager;
	private static MainScreenDesktop instance = null;

	private MainScreenDesktop(){

	}
	
	public static void main (String[] args) throws IOException {
		showMenu();
	}
	
	public static void showMenu() throws IOException {

		MainScreenDesktop main = getInstance();

		String option = JOptionPane.showInputDialog("Bem vindo ao sistema de SASF!\nEscolha a opcao desejada:\n1-Listar Usuarios\n2-Sair","Sua opcao");		
		
		main.readUserInput(option);
	}
	
	public void readUserInput(String option) throws IOException {
		try {

			userManager = new UserManager();
		} catch (InfraException e) {
			String option2 = JOptionPane.showInputDialog(e.getMessage());
		}
		int choice = Integer.parseInt(option);

		switch (choice) {
	
			case 1:
				String usuarios = "";
				Iterator<User> users;
				try {
					users = this.userManager.list().values().iterator();
					while (users.hasNext()) {
						User user = users.next();
						usuarios = usuarios + "[ Login: " + user.getLogin() + " | Email: " + user.getEmail() + " | Senha: " + user.getSenha() + " ]" + "\n";
					}
					JOptionPane.showMessageDialog(null, usuarios);
				} catch (InfraException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
	
				showMenu();
				break;

			case 2:
			// exit
				System.exit(0);
				break;
	
		}
	}

	public static void printCsvData(String caminhoArquivo) {
        
        String line = "";
        String csvDelimiter = ",";
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            
            while ((line = br.readLine()) != null) {
                
                // separa os campos do CSV utilizando o delimitador
                String[] campos = line.split(csvDelimiter);
                
                // faça o processamento necessário com os campos lidos
				//String nome = campos[0];
                String email = campos[0];
				String linguagens = campos[1];
				String humanas = campos[2];
				String exatas = campos[3];
				String biologicas = campos[4];
				String tecnologicas = campos[5];
                
                // imprime os dados lidos do arquivo CSV na tela
                //System.out.println("Nome: " + nome);
                System.out.println("E-mail: " + email);
				System.out.println("Resultados do teste");
				System.out.println("Linguagens: " + linguagens);
				System.out.println("Humanas: " + humanas);
				System.out.println("Exatas: " + exatas);
				System.out.println("Biologicas: " + biologicas);
				System.out.println("Tecnologicas: " + tecnologicas);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static synchronized MainScreenDesktop getInstance(){
		if(instance == null){
			instance = new MainScreenDesktop();
		}
		return instance;
	}
}