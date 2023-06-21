package infra;

import java.util.Stack;


public class Caretaker{
    private Stack<Memento> mementos;

    public Caretaker(){
        mementos = new Stack<>();
        
    }

    public void addMemento(Memento m){
        mementos.push(m);
    }

    public Memento getMemento() {
        return mementos.pop();    
    }

    public void undo() throws InfraException{
        Memento poppedMemento = mementos.pop();
        poppedMemento.getOriginator().restore(poppedMemento);
    }
}