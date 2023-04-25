
//Anteriormente a classe Saudacao abrigava duas responsabilidades: Armazenar o tipo de formalidade e imprimir a saudação, 
//ferindo o princípio de única responsabilidade(SRP), além de que a classe teria que ser mudada a cada extensão de código ferindo
// o princípio aberto-fechado(OCP): aberto para extensão, fechado para implementação. 

public class Saudacao {

    private final TipoSaudacao tipoSaudacao; //Agora temos um atributo TipoSaudacao, que representam classes que implemetam a interface TipoSaudacao
    //resolvendo o problema do princípio aberto-fechado

    public Saudacao(TipoSaudacao tipoSaudacao){

        this.tipoSaudacao = tipoSaudacao;

    }

    public String saudar(){

        return tipoSaudacao.saudar();
        //aqui, o tipo de saudacao escolhida fará seu respectivo gesto
    }

}
