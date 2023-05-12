package view;

public class Home {
    private static Home instance = null;

    private Home(){
        TelaUsuario.showMenuLogin();
    }

    public static Home getInstance(){
        if(instance == null){
            instance = new Home();
        }
        return instance;
    }

    public static void main(String[] args) {
        Home home = getInstance();
    }
}
