import java.util.List;
import java.util.ArrayList;

public class Production {
    
    private Symbol head;
    private List<Symbol> body = new ArrayList<>();

    public Production(Symbol head, List<Symbol> body) { // una produzione si definisce da una testa, un NonTerminal, e un corpo, una sequenza di Terminal e NonTerminal

        if (head.getIsTerminal() == true) throw new IllegalArgumentException(); // la testa non può essere un terminale
        this.setHead(head);
        this.setBody(body);

    }

    public void setHead(Symbol head) {
        this.head = head;
    }

    public Symbol getHead() {
        return this.head;
    }

    public void setBody(List<Symbol> body) {
        for (Symbol symbol : body)
            this.body.add(symbol);
    }

    public List<Symbol> getBody() {
        return this.body;
    }

    public Symbol getSymbolOfBody(int index) {
        return this.body.get(index);
    }

    public void removeSymbolOfBody(int index) {
        this.body.remove(index);
    }

    @Override
	public String toString() {
        String bodyToString = "";

        for (Symbol symbol : this.getBody()) {
            bodyToString += symbol.toString() + " ";
        }

		return this.getHead().toString() + " -> " + bodyToString;
	}

}
