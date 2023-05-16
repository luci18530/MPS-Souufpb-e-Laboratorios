package view;

import business.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.EmailInvalidException;
import util.PasswordInvalidException;
import util.UserValidador;

import java.util.NoSuchElementException;
import java.util.logging.Logger;


public class LoginScreen extends Application {

    private UserMainScreen userMainScreen;
    private AdminMainScreen adminMainScreen;
    private static LoginScreen instance = null;

    private LoginScreen() {
        
    }
    
    public static synchronized LoginScreen getInstance(){
        if(instance == null){
            instance = new LoginScreen();
        }
        return instance;
    }

    public void showScreen(Stage primaryStage) {
        primaryStage.setTitle("Bem Vindo ao souUFPB");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //label e campo de email
        Label emailLabel = new Label("email:");
        GridPane.setConstraints(emailLabel, 0, 0);

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Digite seu email");
        GridPane.setConstraints(emailTextField,1, 0);

        //label e campo de senha
        Label passwordLabel = new Label("Senha:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Digite sua senha");
        GridPane.setConstraints(passwordField,1,1);

        //botão de login
        Button buttonLogin = new Button("Entrar");
        GridPane.setConstraints(buttonLogin, 1, 2);

        //Botão de cadastro
        Button buttonCadastro = new Button("Cadastre-se");
        GridPane.setConstraints(buttonCadastro,1,3);

        grid.getChildren().addAll(emailLabel,emailTextField,passwordLabel,passwordField,buttonLogin,buttonCadastro);

        buttonLogin.setOnAction(e -> {
            

            //transição para a tela principal
            try {
                if (UserValidador.validateEmail(emailTextField.getText()) && UserValidador.validatePassword(passwordField.getText())) {

                    Stage mainScreenStage = new Stage();
                    userMainScreen = new UserMainScreen();
                    userMainScreen.start(mainScreenStage);

                } else if(emailTextField.getText() == "Admim@admin.com" && passwordField.getText() == "Admin123") {
                    Stage adminScreenStage = new Stage();
                    adminMainScreen = new AdminMainScreen();
                    adminMainScreen.start(adminScreenStage);
                } else {
                    throw new NoSuchElementException("Email ou senha incorretos. Tente Novamente");
                }
            } catch (EmailInvalidException ex) {
                ex.printStackTrace();
            } catch(PasswordInvalidException ex) { 
                ex.printStackTrace();
            }
        });

        Scene loginScene = new Scene(grid, 800, 600);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    @Override
    public void start(Stage stage) {
        throw new UnsupportedOperationException("Método não suportado. Use o método showScreen");
    }

    public static void main(String[] args) {
        LoginScreen.getInstance().showScreen(new Stage());
    }
}
