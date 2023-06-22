package infra;

import java.util.EmptyStackException;
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

    public void undo() throws InfraException {
        if (!mementos.empty()) {
            try {
                mementos.pop();
                Memento ultimoMemento = mementos.peek();
                ultimoMemento.getOriginator().restore(ultimoMemento);
            } catch (EmptyStackException e) {
                e.printStackTrace();
            } 
        }
    }
}